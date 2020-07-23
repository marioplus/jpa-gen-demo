package com.marioplus.jpagendemo.repository.impl;

import com.marioplus.jpagendemo.repository.OrgRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Org 自定义Repository实现层
 *
 * @author auto generated
 * @date 2020-07-23 23:11:52
 */
@Repository
public class OrgRepositoryCustomImpl implements OrgRepositoryCustom {

	@PersistenceContext
	private EntityManager em;
}
