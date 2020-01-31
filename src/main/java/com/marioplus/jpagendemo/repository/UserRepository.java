package com.marioplus.jpagendemo.repository;

import com.marioplus.jpagendemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User Repository层
 *
 * @author auto generated
 * @date 2020-01-31 01:40:59
 */
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

}
