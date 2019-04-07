package com.yioks.springboot.common.sms;

import java.util.Map;

public interface ISmsSender {
  ISmsResult sendSms(String phone, String template, Map<String, String> vars);
}
