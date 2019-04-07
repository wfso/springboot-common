package com.yioks.springboot.common.storage.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties("storage.local")
@Getter
@Setter
public class LocalStorageProperties {
  private String filePath = "/uploads/";

  private String prefixUrl = "/uploads/";

}
