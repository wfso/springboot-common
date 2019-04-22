package com.yioks.springboot.common.shiro.authentication;

import com.yioks.springboot.common.shiro.exception.UserAuthenticationException;
import com.yioks.springboot.common.shiro.model.IUser;
import com.yioks.springboot.common.shiro.token.UserAuthenticationToken;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;

public abstract class AbstractUserAuthenticationService extends AbstractAuthenticationService {

  public abstract IUser getByExtendedIdentification(String extendedIdentification);

  public boolean validateCredentials(String userCredentials, String tokenCredentials) {
    return StringUtils.isNotEmpty(userCredentials)
      && StringUtils.isNotEmpty(tokenCredentials)
      && userCredentials.equalsIgnoreCase(tokenCredentials);
  }

  @Override
  public boolean supports(AuthenticationToken token) {
    return token instanceof UserAuthenticationToken;
  }

  @Override
  protected IUser verifyToken(AuthenticationToken token) throws AuthenticationException {
    UserAuthenticationToken userToken = (UserAuthenticationToken) token;
    if (StringUtils.isEmpty(userToken.getUsername())) {
      throw new UserAuthenticationException("UAE-000001");
    }
    IUser user = getByExtendedIdentification(userToken.getUsername());
    if (user == null) {
      throw new UserAuthenticationException("UAE-000002");
    }

    if (StringUtils.isEmpty(userToken.getStringCredentials())) {
      throw new UserAuthenticationException("UAE-000003");
    }

    if (StringUtils.isEmpty(user.getCredentials())) {
      throw new UserAuthenticationException("UAE-000004");
    }

    if (!validateCredentials(user.getCredentials(), userToken.getStringCredentials())) {
      throw new UserAuthenticationException("UAE-000005");
    }

    if (!user.isAvailable()) {
      throw new UserAuthenticationException("UAE-000006");
    }
    userToken.setUserId(user.getIdentification());
    return user;
  }
}
