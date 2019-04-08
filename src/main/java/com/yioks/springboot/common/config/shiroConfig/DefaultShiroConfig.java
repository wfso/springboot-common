package com.yioks.springboot.common.config.shiroConfig;

import com.yioks.springboot.common.filter.CrossDomainFilter;
import com.yioks.springboot.common.shiro.factoryBeans.CustomizedSecurityManagerFactoryBean;
import com.yioks.springboot.common.shiro.realm.DefaultAuthorizationRealm;
import com.yioks.springboot.common.shiro.service.DefaultAuthorizationService;
import com.yioks.springboot.common.shiro.session.utils.DefaultShiroSessionUtil;
import com.yioks.springboot.common.shiro.session.utils.ISessionUtil;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.Map;

@Configuration
public class DefaultShiroConfig {


  @Bean(name = "shiroFilter")
  public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
    ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
    Map<String, String> filterChainDefinitionMapping = shiroFilter.getFilterChainDefinitionMap();
    filterChainDefinitionMapping.put("/api/**", "crossDomainFilter");
    shiroFilter.setSecurityManager(securityManager);
    Map<String, Filter> filters = shiroFilter.getFilters();
    filters.put("crossDomainFilter", new CrossDomainFilter());
    return shiroFilter;
  }

  @Bean
  public DefaultAuthorizationService defaultAuthorizationService(){
    return new DefaultAuthorizationService();
  }

  @Bean
  public ISessionUtil sessionUtil() {
    return new DefaultShiroSessionUtil();
  }

  @Bean
  public DefaultAuthorizationRealm defaultAuthorizationRealm() {
    return new DefaultAuthorizationRealm();
  }

  @Bean()
  public CustomizedSecurityManagerFactoryBean customizedSecurityManagerFactoryBean() {
    return new CustomizedSecurityManagerFactoryBean();
  }


  // 支持 Shiro 注解权限控制
  @Bean
  @DependsOn("lifecycleBeanPostProcessor")
  public AuthorizationAttributeSourceAdvisor advisor(SecurityManager securityManager) {
    AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
    authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
    return authorizationAttributeSourceAdvisor;
  }


  @Bean
  public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
    return new LifecycleBeanPostProcessor();
  }

}
