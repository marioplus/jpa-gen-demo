package com.marioplus.jpagendemo.rest;

import com.marioplus.jpagendemo.entity.User;
import com.marioplus.jpagendemo.model.dto.UserAddDto;
import com.marioplus.jpagendemo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author xianglei
 * @date 2020/1/30 0:30
 **/
@Api(tags = "用户相关接口")
@RestController
@RequestMapping("/user")
public class UserRest {

    @Resource
    private UserService userService;

    @GetMapping()
    @ApiOperation(value = "获取用户列表")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "根据id查找用户")
    public User findOne(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping()
    @ApiOperation(value = "新增用户")
    public Long save(@RequestBody @Valid UserAddDto addDto) {
        User user = new User();
        BeanUtils.copyProperties(addDto, user);
        userService.save(user);
        return user.getId();
    }
}
