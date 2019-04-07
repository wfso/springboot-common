package com.yioks.springboot.common.sms;

import java.util.Map;

public interface ISmsSender {
  ISmsResult sendSms(String phone, String template, Map<String, String> vars);

  default ISmsResult sendSms(String phone, String template, Map<String, String> vars, String type) {
    return sendSms(phone, template, vars);
  }
}
