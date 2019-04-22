package com.yioks.springboot.common.shiro.event;

import com.yioks.springboot.common.model.IUser;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginEvent {

  public LoginEvent(IUser user) {
    this.user = user;
  }

  private IUser user;
}
