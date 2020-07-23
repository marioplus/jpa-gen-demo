package com.marioplus.jpagendemo.repository;

import com.marioplus.jpagendemo.entity.Org;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Org Repository层
 *
 * @author auto generated
 * @date 2020-07-23 23:11:52
 */
public interface OrgRepository extends JpaRepository<Org, Long>, OrgRepositoryCustom {

}
