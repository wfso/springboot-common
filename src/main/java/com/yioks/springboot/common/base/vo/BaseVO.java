package com.yioks.springboot.common.base.vo;

import com.yioks.validation.groups.Update;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * VO的父类
 */
@Getter
@Setter
@ToString
public class BaseVO implements Serializable {
  @ApiModelProperty(value = "创建时间戳", example = "0")
  private Long createdAt;

  @ApiModelProperty(value = "修改时间戳", example = "0")
  private Long updatedAt;

  @ApiModelProperty(value = "ID-唯一标识", example = "0")
  @NotNull(groups = {Update.class})
  private Long id;
}
