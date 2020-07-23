package com.marioplus.jpagendemo.service;

import com.marioplus.jpagendemo.repository.OrgRepository;
import com.marioplus.jpagendemo.common.BaseService;
import com.marioplus.jpagendemo.entity.Org;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Org service层
 *
 * @author auto generated
 * @date 2020-07-23 23:11:52
 */
@Service
public class OrgService extends BaseService<Org, Long> {

	@Resource
	private OrgRepository rep;
}
