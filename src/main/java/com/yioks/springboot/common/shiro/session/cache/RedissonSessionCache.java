package com.yioks.springboot.common.shiro.session.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.session.Session;
import org.redisson.api.RMapCache;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class RedissonSessionCache implements Cache<Serializable, Session> {

  private final String name;

  private final RMapCache<Serializable, Session> map;


  public RedissonSessionCache(String name, RMapCache<Serializable, Session> map) {
    this.name = name;
    this.map = map;
  }

  public String getName() {
    return name;
  }

  public RMapCache<Serializable, Session> getMap() {
    return map;
  }

  @Override
  public Session get(Serializable key) throws CacheException {
    return map.get(key);
  }

  @Override
  public Session put(Serializable key, Session session) throws CacheException {
    return map.put(key, session, session.getTimeout(), TimeUnit.MILLISECONDS);
  }

  @Override
  public Session remove(Serializable key) throws CacheException {
    return map.remove(key);
  }

  @Override
  public void clear() throws CacheException {
    map.clear();
  }

  @Override
  public int size() {
    return map.size();
  }

  @Override
  public Set<Serializable> keys() {
    return map.keySet();
  }

  @Override
  public Collection<Session> values() {
    return map.values();
  }

}
