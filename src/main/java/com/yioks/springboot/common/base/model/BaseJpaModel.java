package com.yioks.springboot.common.base.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yioks.validation.groups.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
@MappedSuperclass
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public abstract class BaseJpaModel implements Model<Long>, Serializable {
  @Column(updatable = false, nullable = false)
  @ApiModelProperty(value = "创建时间戳", example = "0")
  private long createdAt = 0;

  @Column(nullable = false)
  @ApiModelProperty(value = "更新时间戳", example = "0")
  private long updatedAt = 0;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(value = "ID-唯一标识", example = "0")
  @NotNull(groups = {Update.class})
  private Long id;

  @Override
  @Transient
  public boolean isAvailable() {
    return id != null && id > 0;
  }
}
