package com.yioks.springboot.common.shiro.model;

import java.io.Serializable;

public interface ShiroPrincipal<T extends Serializable> extends Serializable {
  T getPrincipal();
}
