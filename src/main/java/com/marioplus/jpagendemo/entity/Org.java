package com.marioplus.jpagendemo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.marioplus.jpagendemo.common.BaseEntity;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Data;

import java.io.Serializable;


/**
 * 组织
 *
 * @author auto generated
 * @date 2020-07-23 20:55:20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "org")
@ApiModel(value = "组织")
public class Org extends BaseEntity implements Serializable {

	/**
	 * 名称
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "名称")
	@Column(name = "name", nullable = true, length = 20)
	private String name;
}
