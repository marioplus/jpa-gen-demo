
使用 idea 反向生成实体

### 生成文件效果

选择的`jpagendemo`作为生成目录生成的文件结构

![](https://i.loli.net/2020/03/20/z8T3vhkUNjg4DB6.png)

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

1. `view -> Tool Windows -> Database`

![](https://i.loli.net/2020/03/20/dMpmgiFcfblnhyk.png)

2. `+ -> Data source -> MySQL`

![image.png](https://i.loli.net/2020/03/20/MiYB457HKrChWqe.png)

3. 配置数据库信息

![image.png](https://i.loli.net/2020/03/20/jDUhnkPcNsRid8p.png)

4. 在database视图区域任意地方右键，然后 `Scripted Extensions -> Go to Scripts Directory`

![image.png](https://i.loli.net/2020/03/20/xr6mTWY4bkHVXqs.png)

![image.png](https://i.loli.net/2020/03/20/AncgostTmIHXyC2.png)

5. 复制`jpa-auto-generate.groovy`到4中跳转的文件夹`schema`中

6. 在`database`视图区域选择你想要生成的表，然后`Scripted Extensions -> jpa-auto-generate.groovy`
可以使用`Shift`和`Ctrl`多选

![image.png](https://i.loli.net/2020/03/20/5KvZjSlY4NJV1MC.png)

7. 弹出的文件选择框中，选择生成位置

![image.png](https://i.loli.net/2020/03/20/4YdNz7QkWHwv9nV.png)

### 详细配置

配置在生成文件的 `config`
```java
config = [
        // 生成开关
        generate: [
                entity          : true,
                service         : true,
                repository      : true,
                repositoryCustom: true
        ],
        // 实体生成设置
        entity  : [
                // 继承父类设置
                parent         : [
                        // 是否继承父类
                        enable    : true,
                        // 父类名称
                        name      : "BaseEntity",
                        // 父类包名
                        package   : "com.marioplus.jpagendemo.common",
                        // 父类的属性，父类已有属性不在出现在生成的实体内
                        properties: ["id", "createDate", "lastModifiedDate", "version"],
                ],
                // 是否序列化
                impSerializable: true,
                // 是否生成 jpa 相关内容，设置为 false 可以生成与 jpa 无关的实体
                jpa            : true,
                // 是否生成 swagger 文档相关注解，相关说明来数据库注释
                useSwagger     : true,
                // 是否使用 lombok 注解代替 get、set方法
                useLombok      : true
        ],
        // service 生成设置
        service : [
                // 参照 entity 部分的 parent
                parent: [
                        enable : true,
                        name   : "BaseService",
                        package: "com.marioplus.jpagendemo.common"
                ]
        ]
]
```
> 1. 使用 lombok 或者 swagger 需要添加相关 maven 依赖
> 2. 重复生成会覆盖之前生成的
