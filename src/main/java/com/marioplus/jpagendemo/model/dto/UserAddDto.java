package com.marioplus.jpagendemo.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 新增 user dto
 *
 * @author marioplus
 * @date 2020/1/31 14:05
 **/
@Data
@ApiModel("用户新增dto")
public class UserAddDto {
    /**
     * 姓名
     * nullable : true
     * default  : null
     */
    @NotBlank
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 年龄
     * nullable : true
     * default  : null
     */
    @NotNull
    @ApiModelProperty(value = "年龄")
    private Integer age;

    /**
     * 联系方式
     * nullable : true
     * default  : null
     */
    @NotBlank
    @ApiModelProperty(value = "联系方式")
    private String mobile;

    /**
     * 性别
     * nullable : true
     * default  : null
     */
    @Pattern(regexp = "[男女]")
    @ApiModelProperty(value = "性别")
    private String sex;
}
