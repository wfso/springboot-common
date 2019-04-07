package com.yioks.springboot.common.shiro.session.cache;

import com.yioks.springboot.common.utils.SerializationUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.session.Session;
import org.redisson.api.RMap;
import org.redisson.api.RMapCache;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class SerializedRedissonSessionCache implements Cache<Serializable, Session> {

  private final String name;

  private final RMapCache<String, String> map;


  public SerializedRedissonSessionCache(String name, RMapCache<String, String> map) {
    this.name = name;
    this.map = map;
  }

  public String getName() {
    return name;
  }

  public RMap<String, String> getMap() {
    return map;
  }


  @Override
  public Session get(Serializable key) throws CacheException {
    return (Session) SerializationUtil.deserialization(map.get(SerializationUtil.serialization(key)));
  }

  @Override
  public Session put(Serializable key, Session session) throws CacheException {
    map.put(SerializationUtil.serialization(key), SerializationUtil.serialization(session), session.getTimeout(), TimeUnit.MILLISECONDS);
    return session;
  }

  @Override
  public Session remove(Serializable key) throws CacheException {
    return (Session) SerializationUtil.deserialization(map.remove(SerializationUtil.serialization(key)));
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
    Set<String> stringSet = map.keySet();
    Set<Serializable> kSet = new HashSet<>();
    stringSet.stream().forEach(string -> {
      kSet.add((Serializable) SerializationUtil.deserialization(string));
    });
    return kSet;
  }

  @Override
  public Collection<Session> values() {
    Collection<String> strings = map.values();
    Collection<Session> vs = new ArrayList<>();
    strings.stream().forEach(string -> {
      vs.add((Session) SerializationUtil.deserialization(string));
    });
    return vs;
  }
}
