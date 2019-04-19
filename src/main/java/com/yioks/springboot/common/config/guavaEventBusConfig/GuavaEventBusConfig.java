package com.yioks.springboot.common.config.guavaEventBusConfig;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ScheduledThreadPoolExecutor;


@Configuration
public class GuavaEventBusConfig {
  /**
   * 同步的eventBus，方便进行一些跨模块的处理
   *
   * @return EventBus
   */
  @Bean
  public EventBus eventBus() {
    return new EventBus();
  }

  /**
   * 异步的eventBus
   *
   * @return AsyncEventBus
   */
  @Bean
  @Qualifier("asyncEventBus")
  public EventBus asyncEventBus() {
    ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(100);
    return new AsyncEventBus(executor);
  }
}
