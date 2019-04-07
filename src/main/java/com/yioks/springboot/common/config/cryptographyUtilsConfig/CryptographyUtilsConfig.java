package com.yioks.springboot.common.config.cryptographyUtilsConfig;


import com.yioks.springboot.common.utils.DigestUtil;
import com.yioks.springboot.common.utils.MacUtil;
import com.yioks.springboot.common.utils.RandomUtil;
import com.yioks.springboot.common.utils.RsaUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.SecureRandom;

@Configuration
public class CryptographyUtilsConfig {

  /**
   * 摘要算法工具
   *
   * @return DigestUtil
   */
  @Bean
  DigestUtil digestUtil() {
    return new DigestUtil();
  }

  @Bean
  MacUtil macUtil() {
    return new MacUtil();
  }

  @Bean
  SecureRandom secureRandom() {
    return new SecureRandom();
  }

  @Bean
  RandomUtil randomUtil() {
    return new RandomUtil();
  }

  @Bean
  RsaUtil rsaUtil() {
    return new RsaUtil();
  }

}
