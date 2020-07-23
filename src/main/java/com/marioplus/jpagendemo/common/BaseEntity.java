package com.marioplus.jpagendemo.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 基础实体类
 *
 * @author mario
 * @since 2020/1/29 22:49
 **/
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 20, unique = true)
    @ApiModelProperty("id")
    protected Long id;

    @CreatedDate
    @Column(name = "create_date", nullable = false)
    @ApiModelProperty("创建时间")
    protected Date createDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    @ApiModelProperty("最后修改时间")
    protected Date lastModifiedDate;

    @Version
    @Column(name = "version", nullable = false)
    @ApiModelProperty("版本号")
    protected Integer version;
}
