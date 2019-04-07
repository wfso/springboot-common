package com.yioks.springboot.common.shiro.model;

import java.io.Serializable;

public interface ShiroPrincipal extends Serializable {
  Object getPrincipal();
}
