package com.yioks.springboot.common.sms.provider;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.yioks.springboot.common.service.IConfigurationService;
import com.yioks.springboot.common.sms.ISmsProvider;
import com.yioks.springboot.common.sms.ISmsResult;
import com.yioks.springboot.common.sms.ISmsSender;
import com.yioks.springboot.common.sms.properties.AliyunSmsProperties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.Map;

public class AliyunSmsProvider implements ISmsProvider {

  public AliyunSmsProvider(IConfigurationService configurationService, AliyunSmsProperties smsProperties) {
    this.configurationService = configurationService;
    this.smsProperties = smsProperties;
  }

  private IConfigurationService configurationService;

  private AliyunSmsProperties smsProperties;

  private ISmsSender smsSender;

  @Override
  public ISmsSender getSender() {
    if (smsSender == null) {
      String accessKeyId = configurationService.getConfig("sms.aliyun.accessKeyId");
      if (StringUtils.isEmpty(accessKeyId)) {
        accessKeyId = smsProperties.getAccessKeyId();
        Assert.isTrue(!StringUtils.isEmpty(accessKeyId), "请配置阿里云短信");
      }
      String accessKeySecret = configurationService.getConfig("sms.aliyun.accessKeySecret");
      if (StringUtils.isEmpty(accessKeySecret)) {
        accessKeySecret = smsProperties.getAccessKeySecret();
        Assert.isTrue(!StringUtils.isEmpty(accessKeySecret), "请配置阿里云短信");
      }
      String signName = configurationService.getConfig("sms.aliyun.signName");
      if (StringUtils.isEmpty(signName)) {
        signName = smsProperties.getSignName();
        Assert.isTrue(!StringUtils.isEmpty(signName), "请配置阿里云短信");
      }
      String domain = configurationService.getConfig("sms.aliyun.domain");
      if (StringUtils.isEmpty(domain)) {
        domain = smsProperties.getDomain();
        Assert.isTrue(!StringUtils.isEmpty(domain), "请配置阿里云短信");
      }
      String version = configurationService.getConfig("sms.aliyun.version");
      if (StringUtils.isEmpty(version)) {
        version = smsProperties.getVersion();
        Assert.isTrue(!StringUtils.isEmpty(version), "请配置阿里云短信");
      }
      String action = configurationService.getConfig("sms.aliyun.action");
      if (StringUtils.isEmpty(action)) {
        action = smsProperties.getAction();
        Assert.isTrue(!StringUtils.isEmpty(action), "请配置阿里云短信");
      }
      smsSender = new AliyunSmsSender(
        accessKeyId,
        accessKeySecret,
        signName,
        domain,
        version,
        action
      );
    }
    return smsSender;
  }

  @Override
  public void clear() {
    smsSender = null;
  }

  @Override
  public String getType() {
    return "aliyun";
  }

  private static class AliyunSmsSender implements ISmsSender {
    private final IAcsClient client;
    private final String signName;
    private final String domain;
    private final String version;
    private final String action;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public AliyunSmsSender(String accessKeyId, String accessKeySecret, String signName, String domain, String version, String action) {
      DefaultProfile profile = DefaultProfile.getProfile("default", accessKeyId, accessKeySecret);
      this.signName = signName;
      this.domain = domain;
      this.version = version;
      this.action = action;
      this.client = new DefaultAcsClient(profile);
    }

    @Override
    public ISmsResult sendSms(String phone, String template, Map<String, String> vars) {
      CommonRequest request = new CommonRequest();
      request.setMethod(MethodType.POST);
      request.setDomain(this.domain);
      request.setVersion(this.version);
      request.setAction(this.action);
      request.putQueryParameter("PhoneNumbers", phone);
      request.putQueryParameter("SignName", signName);
      request.putQueryParameter("TemplateCode", template);
      request.putQueryParameter("TemplateParam", JSON.toJSONString(vars));
      try {
        CommonResponse response = client.getCommonResponse(request);
        // System.out.println(response.getData());
        return JSON.parseObject(response.getData(), AliyunSmsResult.class);
      } catch (ServerException e) {
        e.printStackTrace();
        logger.warn(e.getMessage(), e);
      } catch (ClientException e) {
        e.printStackTrace();
        logger.warn(e.getMessage(), e);
      }
      return null;
    }
  }

  private static class AliyunSmsResult implements ISmsResult {

    private String BizId;
    private String Code;
    private String Message;
    private String RequestId;

    public String getBizId() {
      return BizId;
    }

    public void setBizId(String bizId) {
      BizId = bizId;
    }

    public String getCode() {
      return Code;
    }

    public void setCode(String code) {
      Code = code;
    }

    public String getMessage() {
      return Message;
    }

    public void setMessage(String message) {
      Message = message;
    }

    public String getRequestId() {
      return RequestId;
    }

    public void setRequestId(String requestId) {
      RequestId = requestId;
    }

    @Override
    public String getId() {
      return getBizId();
    }

    @Override
    public boolean isSuccess() {
      return "OK".equalsIgnoreCase(getCode());
    }

    @Override
    public String toString() {
      return toJSON();
    }

    @Override
    public String toJSON() {
      return JSON.toJSONString(this);
    }
  }
}
