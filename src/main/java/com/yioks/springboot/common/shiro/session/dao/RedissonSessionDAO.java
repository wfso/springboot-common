package com.yioks.springboot.common.shiro.session.dao;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.redisson.api.RMapCache;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class RedissonSessionDAO extends EnterpriseCacheSessionDAO {


  private final RMapCache<Serializable, Session> rMap;

  public RedissonSessionDAO(RMapCache<Serializable, Session> rMap) {
    super();
    this.rMap = rMap;
  }

  @Override
  protected Session doReadSession(Serializable sessionId) {
    return rMap.get(sessionId);
  }

  @Override
  protected void doUpdate(Session session) {
    rMap.put(session.getId(), session,session.getTimeout(), TimeUnit.MILLISECONDS);
  }

  @Override
  protected void doDelete(Session session) {
    rMap.remove(session.getId());
  }

}
