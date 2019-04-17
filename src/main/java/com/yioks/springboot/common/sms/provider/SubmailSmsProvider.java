package com.yioks.springboot.common.sms.provider;

import com.alibaba.fastjson.JSON;
import com.yioks.springboot.common.service.IConfigurationService;
import com.yioks.springboot.common.sms.ISmsProvider;
import com.yioks.springboot.common.sms.ISmsResult;
import com.yioks.springboot.common.sms.ISmsSender;
import com.yioks.springboot.common.sms.properties.SubmailSmsProperties;
import com.yioks.springboot.common.utils.DigestUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class SubmailSmsProvider implements ISmsProvider {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired(required = false)
  private IConfigurationService configurationService;

  @Autowired
  private SubmailSmsProperties smsProperties;

  private ISmsSender smsSender;

  @Override
  public ISmsSender getSender() {
    if (smsSender == null) {
      String appId = null, appKey = null, smsUrl = null, timestampUrl = null;
      if (configurationService != null) {
        appId = configurationService.getConfig("sms.submail.appId");
        appKey = configurationService.getConfig("sms.submail.appKey");
        smsUrl = configurationService.getConfig("sms.submail.smsUrl");
        timestampUrl = configurationService.getConfig("sms.submail.appKey");
      }

      if (StringUtils.isEmpty(appId)) {
        appId = smsProperties.getAppId();
        Assert.isTrue(!StringUtils.isEmpty(appId), "请配置Submail短信");
      }

      if (StringUtils.isEmpty(appKey)) {
        appKey = smsProperties.getAppKey();
        Assert.isTrue(!StringUtils.isEmpty(appKey), "请配置Submail短信");
      }

      if (StringUtils.isEmpty(smsUrl)) {
        smsUrl = smsProperties.getSmsUrl();
        Assert.isTrue(!StringUtils.isEmpty(smsUrl), "请配置Submail短信");
      }
      if (StringUtils.isEmpty(timestampUrl)) {
        timestampUrl = smsProperties.getTimestampUrl();
        Assert.isTrue(!StringUtils.isEmpty(timestampUrl), "请配置Submail短信");
      }
      smsSender = new SubmailSmsSender(appId, appKey, smsUrl, timestampUrl, restTemplate);
    }
    return smsSender;
  }

  @Override
  public void clear() {
    smsSender = null;
  }

  @Override
  public String getType() {
    return "submail";
  }

  private static class Timestamp {
    private String timestamp;

    public String getTimestamp() {
      return timestamp;
    }

    public void setTimestamp(String timestamp) {
      this.timestamp = timestamp;
    }
  }

  private static class SubmailSmsSender implements ISmsSender {

    private final String appId;

    private final String appKey;

    private final RestTemplate restTemplate;

    private final String smsUrl;

    private final String timestampUrl;

    public SubmailSmsSender(String appId, String appKey, String smsUrl, String timestampUrl, RestTemplate restTemplate) {
      this.appId = appId;
      this.appKey = appKey;
      this.restTemplate = restTemplate;
      this.smsUrl = smsUrl;
      this.timestampUrl = timestampUrl;
    }

    @Override
    public ISmsResult sendSms(String phone, String template, Map<String, String> vars) {
      Map<String, String> param = new TreeMap<>();
      param.put("appid", appId);
      param.put("to", phone);
      param.put("project", template);
      param.put("vars", JSON.toJSONString(vars));
      param.put("sign_type", "sha1");
      param.put("timestamp", getTimestamp());
      param.put("signature", sign(param));
      return restTemplate.postForObject(smsUrl, param, SubmailSmsResult.class);
    }

    private String sign(Map<String, String> map) {
      Iterator<String> iter = map.keySet().iterator();
      StringBuilder sb = new StringBuilder();
      sb.append(appId).append(appKey);
      while (iter.hasNext()) {
        String key = iter.next();
        sb.append(key).append("=").append(map.get(key)).append("&");
      }
      sb.deleteCharAt(sb.length() - 1).append(appId).append(appKey);
      return DigestUtil.sha1Hex(sb.toString()).toLowerCase();
    }

    private String getTimestamp() {
      Timestamp timestamp = restTemplate.getForObject(timestampUrl, Timestamp.class);
      if (timestamp != null) {
        return timestamp.getTimestamp();
      }
      return "";
    }

  }

  private static class SubmailSmsResult implements ISmsResult {

    private String status;
    private String send_id;
    private String fee;
    private String sms_credits;
    private String code;
    private String msg;

    public String getStatus() {
      return status;
    }

    public void setStatus(String status) {
      this.status = status;
    }

    public String getSend_id() {
      return send_id;
    }

    public void setSend_id(String send_id) {
      this.send_id = send_id;
    }

    public String getFee() {
      return fee;
    }

    public void setFee(String fee) {
      this.fee = fee;
    }

    public String getSms_credits() {
      return sms_credits;
    }

    public void setSms_credits(String sms_credits) {
      this.sms_credits = sms_credits;
    }

    public String getCode() {
      return code;
    }

    public void setCode(String code) {
      this.code = code;
    }

    public String getMsg() {
      return msg;
    }

    public void setMsg(String msg) {
      this.msg = msg;
    }

    @Override
    public String getId() {
      return getSend_id();
    }

    @Override
    public boolean isSuccess() {
      return "success".equalsIgnoreCase(getStatus());
    }

    @Override
    public String toJSON() {
      return JSON.toJSONString(this);
    }

    @Override
    public String toString() {
      return toJSON();
    }
  }
}
