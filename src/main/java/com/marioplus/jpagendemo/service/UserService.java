package com.marioplus.jpagendemo.service;

import com.marioplus.jpagendemo.repository.UserRepository;
import com.marioplus.jpagendemo.common.BaseService;
import com.marioplus.jpagendemo.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * User service层
 *
 * @author auto generated
 * @date 2020-03-20 17:51:30
 */
@Service
public class UserService extends BaseService<User, Long> {

	@Resource
	private UserRepository rep;
}
