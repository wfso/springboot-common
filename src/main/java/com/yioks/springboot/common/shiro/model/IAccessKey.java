package com.yioks.springboot.common.shiro.model;

public interface IAccessKey {

  Object getUserIdentification();

  String getAccessKeyId();

  String getAccessKeySecret();
}
