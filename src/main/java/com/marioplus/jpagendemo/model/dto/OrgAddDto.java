package com.marioplus.jpagendemo.model.dto;

import com.marioplus.jpagendemo.common.Convertible;
import com.marioplus.jpagendemo.entity.Org;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 新增 org dto
 *
 * @author marioplus
 * @since 2020/07/23 20:57
 **/
@Data
@ApiModel("组织新增dto")
public class OrgAddDto implements Convertible<Org> {
    /**
     * 名称
     * nullable : true
     * default  : null
     */
    @NotBlank
    @ApiModelProperty(value = "名称")
    private String name;

    @Override
    public Org newEntity() {
        return new Org();
    }
}
