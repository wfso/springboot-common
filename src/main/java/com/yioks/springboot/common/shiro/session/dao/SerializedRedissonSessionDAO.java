package com.yioks.springboot.common.shiro.session.dao;

import com.yioks.springboot.common.utils.SerializationUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.redisson.api.RMapCache;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class SerializedRedissonSessionDAO extends EnterpriseCacheSessionDAO {


  private final RMapCache<String, String> rMap;

  public SerializedRedissonSessionDAO(RMapCache<String, String> rMap) {
    super();
    this.rMap = rMap;
  }

  @Override
  protected Session doReadSession(Serializable sessionId) {
    return (Session) SerializationUtil.deserialization(rMap.get(sessionId.toString()));
  }

  @Override
  protected void doUpdate(Session session) {
    String value = SerializationUtil.serialization(session);
    rMap.put(session.getId().toString(), value, session.getTimeout(), TimeUnit.MILLISECONDS);
  }

  @Override
  protected void doDelete(Session session) {
    rMap.remove(session.getId().toString());
  }

}
