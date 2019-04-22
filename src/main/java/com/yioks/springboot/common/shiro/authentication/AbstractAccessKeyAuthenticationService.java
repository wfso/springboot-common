package com.yioks.springboot.common.shiro.authentication;

import com.yioks.springboot.common.shiro.exception.StatelessAuthenticationException;
import com.yioks.springboot.common.shiro.model.IAccessKey;
import com.yioks.springboot.common.shiro.model.ShiroPrincipal;
import com.yioks.springboot.common.shiro.model.UserIdentificationPrincipal;
import com.yioks.springboot.common.shiro.token.AccessKeyAuthenticationToken;
import com.yioks.springboot.common.utils.MacUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;

import java.util.Map;

public abstract class AbstractAccessKeyAuthenticationService extends AbstractAuthenticationService {

  protected abstract IAccessKey getByAccessKeyId(String accessKeyId);

  protected boolean supportSignType(String signType) {
    switch (signType.toLowerCase()) {
      case "hmacsha256":
      case "hmacsha512":
      case "hmacsha1":
      case "hmacmd5": {
        return true;
      }
      default: {
        return false;
      }
    }
  }

  protected String sign(String signType, String signStr, String secret) {
    switch (signType.toLowerCase()) {
      case "hmacsha256": {
        return MacUtil.hmacSha256Hex(signStr, secret);
      }
      case "hmacsha512": {
        return MacUtil.hmacSha512Hex(signStr, secret);
      }
      case "hmacsha1": {
        return MacUtil.hmacSha1Hex(signStr, secret);
      }
      case "hmacmd5": {
        return MacUtil.hmacMd5Hex(signStr, secret);
      }
      default: {
        return "";
      }
    }
  }

  protected String genSignString(Map<String, String> params) throws AuthenticationException {
    StringBuffer buffer = new StringBuffer();
    for (Map.Entry<String, String> entry : params.entrySet()) {
      if (entry.getValue() != null) {
        buffer.append(entry.getKey())
          .append("=")
          .append(entry.getValue())
          .append("&");
      }
    }
    buffer.deleteCharAt(buffer.length() - 1);
    return buffer.toString();
  }

  @Override
  public boolean supports(AuthenticationToken token) {
    return token instanceof AccessKeyAuthenticationToken;
  }


  @Override
  protected ShiroPrincipal verifyToken(AuthenticationToken token) throws AuthenticationException {

    AccessKeyAuthenticationToken AccessKeyToken = (AccessKeyAuthenticationToken) token;

    Map<String, String> params = AccessKeyToken.getParams();

    // 请求参数中不包含 accessKeyId
    if (!params.containsKey("accessKeyId")) {
      throw new StatelessAuthenticationException("AKE-000001");
    }

    // 请求参数中不包含 sign
    if (!params.containsKey("sign")) {
      throw new StatelessAuthenticationException("AKE-000002");
    }

    // 请求参数中不包含 timestamp
    if (!params.containsKey("timestamp")) {
      throw new StatelessAuthenticationException("AKE-000006");
    }

    String timestamp = params.get("timestamp");

    if (System.currentTimeMillis() - Long.parseLong(timestamp) > 5 * 60 * 1000) {
      throw new StatelessAuthenticationException("AKE-000007");
    }


    String accessKeyId = params.get("accessKeyId");
    String sign = params.remove("sign");
    String signType = "hmacSha256";
    if (params.containsKey("signType")) {
      signType = params.get("signType");
    }

    // 根据 accessKeyId 获取 AccessKey
    IAccessKey accessKey = getByAccessKeyId(accessKeyId);

    // 没有与 accessKeyId 对应的 AccessKey
    if (accessKey == null || accessKey.isAvailable()) {
      throw new StatelessAuthenticationException("AKE-000003");
    }

    // 构造待签名字符串
    String signStr = genSignString(params);

    String localSign = "";

    if (supportSignType(signType)) {
      localSign = sign(signType, signStr, accessKey.getAccessKeySecret());
    } else {
      throw new StatelessAuthenticationException("AKE-000004");
    }

    if (!sign.equalsIgnoreCase(localSign)) {
      throw new StatelessAuthenticationException("AKE-000005");
    }
    AccessKeyToken.setUserId(accessKey.getUserIdentification());
    AccessKeyToken.setPassword(localSign);
    return new UserIdentificationPrincipal(accessKey.getUserIdentification());
  }
}
