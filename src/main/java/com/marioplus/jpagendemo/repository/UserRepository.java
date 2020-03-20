package com.marioplus.jpagendemo.repository;

import com.marioplus.jpagendemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User Repository层
 *
 * @author auto generated
 * @date 2020-03-20 17:51:30
 */
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

}
