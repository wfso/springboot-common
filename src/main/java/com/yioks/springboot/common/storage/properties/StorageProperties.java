package com.yioks.springboot.common.storage.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties("storage")
@Getter
@Setter
public class StorageProperties {
  private String type = "local";
}
