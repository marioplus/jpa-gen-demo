package com.marioplus.jpagendemo.rest;

import com.marioplus.jpagendemo.entity.Org;
import com.marioplus.jpagendemo.model.dto.OrgAddDto;
import com.marioplus.jpagendemo.service.OrgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author marioplus
 * @since 2020/07/23 20:56
 **/
@Api(tags = "组织相关接口")
@RestController
@RequestMapping("/org")
public class OrgRest {

    @Resource
    private OrgService service;

    @GetMapping()
    @ApiOperation(value = "获取列表")
    public List<Org> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查找")
    public Org findOne(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping()
    @ApiOperation(value = "新增")
    public Long save(@RequestBody @Valid OrgAddDto addDto) {
        Org org = addDto.convertToEntity();
        service.save(org);
        return org.getId();
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据id删除")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
