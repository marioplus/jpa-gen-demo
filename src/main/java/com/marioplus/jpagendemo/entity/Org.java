package com.marioplus.jpagendemo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.marioplus.jpagendemo.common.BaseEntity;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Data;

import java.io.Serializable;


/**
组织
 *
 * @author auto generated
 * @since 2020-07-23 23:11:52
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "org")
@ApiModel(value = "组织")
public class Org extends BaseEntity implements Serializable {

	/**
	名称
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "名称")
	@Column(name = "name", nullable = true, length = 20)
	private String name;

	/**
	null
	 * nullable : true
	 * default  : '2020-07-23 11:11:11.111111'
	 */
	@ApiModelProperty(value = "testDateTime")
	@Column(name = "test_date_time", nullable = true)
	private java.time.LocalDateTime testDateTime;

	/**
	null
	 * nullable : true
	 * default  : '2020-07-23'
	 */
	@ApiModelProperty(value = "testDate")
	@Column(name = "test_date", nullable = true)
	private java.time.LocalDate testDate;

	/**
	null
	 * nullable : true
	 * default  : '11:11:11.111111'
	 */
	@ApiModelProperty(value = "testTime")
	@Column(name = "test_time", nullable = true)
	private java.time.LocalTime testTime;
}
