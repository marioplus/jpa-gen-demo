package com.marioplus.jpagendemo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.marioplus.jpagendemo.common.BaseEntity;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Data;

import java.io.Serializable;


/**
 * 用户表
 *
 * @author auto generated
 * @date 2020-01-31 01:40:59
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "user")
@ApiModel(value = "用户表")
public class User extends BaseEntity implements Serializable {

	/**
	 * 姓名
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "姓名")
	@Column(name = "name", nullable = true, length = 20)
	private String name;

	/**
	 * 年龄
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "年龄")
	@Column(name = "age", nullable = true, length = 11)
	private Integer age;

	/**
	 * 联系方式
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "联系方式")
	@Column(name = "mobile", nullable = true, length = 11)
	private String mobile;

	/**
	 * 性别
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "性别")
	@Column(name = "sex", nullable = true)
	private String sex;
}
