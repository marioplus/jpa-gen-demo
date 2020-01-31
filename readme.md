趁着有时间又对之前的生成文件做了些调整，修改了配置项，加入了对 swagger 的支持

### 生成文件效果

选择的`jpagendemo`作为生成目录生成的文件结构

![生成的文件结构](https://i.loli.net/2020/01/31/Y46U5cChBo7NLtO.png)

- entity
```java
package com.marioplus.jpagendemo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.marioplus.jpagendemo.common.BaseEntity;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Data;

import java.io.Serializable;


/**
 * 用户表
 *
 * @author auto generated
 * @date 2020-01-31 01:02:41
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "user")
@ApiModel(value = "用户表")
public class User extends BaseEntity implements Serializable {

	/**
	 * 姓名
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "姓名")
	@Column(name = "name", nullable = true, length = 20)
	private String name;

	/**
	 * 年龄
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "年龄")
	@Column(name = "age", nullable = true, length = 11)
	private Integer age;

	/**
	 * 联系方式
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "联系方式")
	@Column(name = "mobile", nullable = true, length = 11)
	private String mobile;

	/**
	 * 性别
	 * nullable : true
	 * default  : null
	 */
	@ApiModelProperty(value = "性别")
	@Column(name = "sex", nullable = true)
	private String sex;
}
```

- service
```java
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
 * @date 2020-01-31 01:02:41
 */
@Service
public class UserService extends BaseService<User, Long> {

	@Resource
	private UserRepository rep;
}
```

- repository
```java
package com.marioplus.jpagendemo.repository;

import com.marioplus.jpagendemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User Repository层
 *
 * @author auto generated
 * @date 2020-01-31 01:02:41
 */
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

}
```

- repositoryCustom
```java
package com.marioplus.jpagendemo.repository;

/**
 * User 自定义Repository层
 *
 * @author auto generated
 * @date 2020-01-31 01:02:41
 */
public interface UserRepositoryCustom {

}
```

- repositoryCustomImpl
```java
package com.marioplus.jpagendemo.repository.impl;

import com.marioplus.jpagendemo.repository.UserRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * User 自定义Repository实现层
 *
 * @author auto generated
 * @date 2020-01-31 01:02:41
 */
@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

	@PersistenceContext
	private EntityManager em;
}
```

### 如何使用

如果需要使用 lombok 和 swagger 生成配置需要加入相应的 maven 依赖

1. 使用 `IDEA` 配置数据源
![](https://i.loli.net/2020/01/31/FZR6WkVYnvOahyb.png)

需要将生成文件`jpa-auto-generate.groovy`复制到对应文件夹。

![](https://i.loli.net/2020/01/31/FZR6WkVYnvOahyb.png)

可以直接在左侧`project`中直接找到文件夹，