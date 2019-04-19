package com.yioks.springboot.common.config.guavaEventBusConfig;

import com.google.common.eventbus.EventBus;
import com.yioks.springboot.common.annotation.EventProcessor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class GuavaEventBusRegister implements BeanPostProcessor {
  @Autowired
  EventBus eventBus;

  @Autowired
  @Qualifier("asyncEventBus")
  EventBus asyncEventBus;

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    EventProcessor processor = bean.getClass().getDeclaredAnnotation(EventProcessor.class);
    if (processor != null) {
      switch (processor.type()) {
        case BOTH: {
          asyncEventBus.register(bean);
          eventBus.register(bean);
          break;
        }
        case SYNCHRONOUS: {
          eventBus.register(bean);
          break;
        }
        case ASYNCHRONOUS: {
          asyncEventBus.register(bean);
          break;
        }
      }
    }
    return bean;
  }
}
