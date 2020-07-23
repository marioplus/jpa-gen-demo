package com.marioplus.jpagendemo.service;

import com.marioplus.jpagendemo.common.BaseService;
import com.marioplus.jpagendemo.entity.Org;
import com.marioplus.jpagendemo.repository.OrgRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Org service层
 *
 * @author auto generated
 * @since 2020-07-23 20:55:20
 */
@Service
public class OrgService extends BaseService<Org, Long> {

    @Resource
    private OrgRepository rep;
}
