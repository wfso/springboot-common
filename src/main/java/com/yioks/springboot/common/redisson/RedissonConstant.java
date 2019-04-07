package com.yioks.springboot.common.redisson;

public class RedissonConstant {

  /**
   * 并发JOB的等待时间
   */
  public static final int CONCURRENT_JOB_WAIT_TIME = 0;

  /**
   * 锁等待时间
   */
  public static final int LOCK_WAIT_TIME = 30;

  /**
   * 锁最长持有时间
   */
  public static final int LOCK_LEASE_TIME = 300;

  /**
   * 锁的超长持有时间 （3小时， 用于复杂的环境的超时等待）
   */
  public static final int LOCK_LONG_LEASE_TIME = 3 * 3600;

  /**
   * 用户信息锁前缀
   */
  public static final String LOCK_USER_PRE = "USER_ID";


  /**
   * 用户最后一次修改密码的时间前缀
   */
  public static final String USER_CHANGE_PASSWORD_TIME_PRE = "user:change:password:";
}
