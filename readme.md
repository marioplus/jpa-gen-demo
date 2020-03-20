
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

5. 复制下面的文件到4中跳转的文件夹`schema`中
- jpa-auto-generate.groovy
```java
import com.intellij.database.model.DasTable
import com.intellij.database.model.ObjectKind
import com.intellij.database.util.Case
import com.intellij.database.util.DasUtil

import javax.swing.*
import java.awt.Dialog
import java.lang.reflect.Method
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.stream.Collectors

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

typeMapping = [
        (~/(?i)bool|boolean|tinyint/)     : "Boolean",
        (~/(?i)bigint/)                   : "Long",
        (~/(?i)int/)                      : "Integer",
        (~/(?i)float|double|decimal|real/): "Double",
        (~/(?i)datetime|timestamp/)       : "java.util.Date",
        (~/(?i)date/)                     : "java.sql.Date",
        (~/(?i)time/)                     : "java.sql.Time",
        (~/(?i)/)                         : "String"
]


FILES.chooseDirectoryAndSave("\u9009\u62e9\u6587\u4ef6\u5939", "\u9009\u62e9\u6587\u4ef6\u751f\u6210\u4f4d\u7f6e") { dir ->
    SELECTION.filter {
        it instanceof DasTable && it.getKind() == ObjectKind.TABLE
    }
            .each { table ->
                def fields = calcFields(table)
                Gen.main(config, table, fields, dir.toString())
            }
}

// 转换类型
def calcFields(table) {
    def pk = Utils.getPK(table)
    DasUtil.getColumns(table).reduce([]) { fields, col ->
//        console(col, i++)
        def spec = Case.LOWER.apply(col.getDataType().getSpecification())
        def typeStr = typeMapping.find { p, t -> p.matcher(spec).find() }.value
        fields += [[
                           name        : Utils.toCamelCase(col.getName().toString()),
                           column      : col.getName(),
                           type        : typeStr,
                           dataType    : Utils.firstMatched(col.getDataType(), /\b\w+\b/, ""),
                           len         : Utils.firstMatched(col.getDataType(), /(?<=\()\d+(?!=\))/, -1),
                           default     : col.getDefault(),
                           comment     : col.getComment(),
                           nullable    : !col.isNotNull(),
                           isPrimaryKey: null != pk && pk == col.getName(),
                   ]]
    }
}

class Gen {

    // 生成对应的文件
    def static main(config, table, fields, dir) {
        def entityName = Utils.toUpperCamelCase(table.getName())
        def basePackage = Utils.firstMatched(dir.toString(), /(?<=src\\main\\java\\).+/, "").replace("\\", ".")
        dir = dir.toString()
        def pkType = fields.find { it.isPrimaryKey }.type
        // entity
        if (config.generate.entity) {
            Utils.createFile("${dir}\\entity", "${entityName}.java").withWriter("utf8") {
                writer -> genEntity(writer, config, config.entity.parent, table, entityName, fields, basePackage)
            }
        }
        // service
        if (config.generate.service) {
            Utils.createFile("${dir}\\service", "${entityName}Service.java").withWriter("utf8") {
                writer -> genService(writer, config, config.service.parent, entityName, pkType, basePackage)
            }
        }
        // rep
        if (config.generate.repository) {
            Utils.createFile("${dir}\\repository", "${entityName}Repository.java").withWriter("utf8") {
                writer -> genRepository(writer, config, entityName, basePackage, pkType)
            }
        }
        // repCustom
        if (config.generate.repositoryCustom) {
            Utils.createFile("${dir}\\repository", "${entityName}RepositoryCustom.java").withWriter("utf8") {
                writer -> genRepositoryCustom(writer, entityName, basePackage)
            }
            Utils.createFile("${dir}\\repository\\impl", "${entityName}RepositoryCustomImpl.java").withWriter("utf8") {
                writer -> genRepositoryCustomImpl(writer, entityName, basePackage)
            }
        }

    }

    // 生成实体
    def static genEntity(writer, config, parentConfig, table, entityName, fieldList, basePackage) {

        writer.writeLine "package ${basePackage}.entity;"
        writer.writeLine ""
        if (config.entity.useSwagger) {
            writer.writeLine "import io.swagger.annotations.ApiModel;"
            writer.writeLine "import io.swagger.annotations.ApiModelProperty;"
        }
        if (parentConfig.enable) {
            writer.writeLine "import ${parentConfig.package}.${parentConfig.name};"
        }
        if (config.entity.jpa) {
            writer.writeLine "import javax.persistence.*;"
        }
        if (config.entity.useLombok) {
            if (parentConfig.enable) {
                writer.writeLine "import lombok.EqualsAndHashCode;"
            }
            writer.writeLine "import lombok.Data;"
            writer.writeLine ""
        }
        if (config.entity.impSerializable) {
            writer.writeLine "import java.io.Serializable;"
            writer.writeLine ""
        }

        def tableComment = Utils.getDefaultValIfCurrentValIsBlank(table.getComment(), entityName)
        writer.writeLine ""
        writer.writeLine "/**"
        writer.writeLine "${Utils.docCommentConvert(tableComment, '')}"
        writer.writeLine " *"
        writer.writeLine " * @author auto generated"
        writer.writeLine " * @date ${Utils.localDateTimeStr()}"
        writer.writeLine " */"
        if (config.entity.useLombok) {
            if (parentConfig.enable) {
                writer.writeLine "@EqualsAndHashCode(callSuper = true)"
            }
            writer.writeLine "@Data"
        }
        if (config.entity.jpa) {
            writer.writeLine "@Entity"
            writer.writeLine "@Table(name = \"${table.name}\")"
        }
        if (config.entity.useSwagger) {
            writer.writeLine "@ApiModel(value = \"${Utils.swaggerCommentConvert(tableComment)}\")"
        }

        def extendsStr = parentConfig.enable ? " extends $parentConfig.name" : "",
            impStr = config.entity.impSerializable ? " implements Serializable" : ""
        writer.writeLine "public class $entityName$extendsStr$impStr {"

        if (parentConfig.enable) {
            fieldList = fieldList.findAll { field -> !parentConfig.properties.contains(field.name) }
        }

        fieldList.each() { field -> genEntityProperties(writer, config, parentConfig, field) }

        if (!config.entity.useLombok) {
            fieldList.each() { field -> genEntityGetAndSetMethod(writer, field) }
        }
        writer.writeLine "}"
    }

    // 实体属性
    def static genEntityProperties(writer, config, parentConfig, field) {
        writer.writeLine ""
        def comment = Utils.getDefaultValIfCurrentValIsBlank(field.comment, field.name)
        writer.writeLine "\t/**"
        writer.writeLine "${Utils.docCommentConvert(field.comment, "\t")}"
        writer.writeLine "\t * nullable : ${field.nullable}"
        writer.writeLine "\t * default  : ${field.default}"
        writer.writeLine "\t */"

        if (field.isPrimaryKey && config.entity.jpa) {
            writer.writeLine "\t@Id"
        }
        if (config.entity.useSwagger) {
            writer.writeLine "\t@ApiModelProperty(value = \"${Utils.swaggerCommentConvert(comment)}\")"
        }

        if (config.entity.jpa) {
            def lenStr = ""
            if (field.len.toInteger() >= 0 && !field.type.contains("java")) {
                lenStr = ", length = $field.len"
            }
            writer.writeLine "\t@Column(name = \"${field.column}\", nullable = ${!field.isNotNull}$lenStr)"
        }
        writer.writeLine "\tprivate ${field.type} ${field.name};"
    }

    // 生成get、get方法
    def static genEntityGetAndSetMethod(writer, field) {

        def methodName = Utils.toUpperCamelCase(field.name)

        // get
        writer.writeLine "\t"
        writer.writeLine "\tpublic ${field.type} get${methodName}() {"
        writer.writeLine "\t\treturn this.${field.name};"
        writer.writeLine "\t}"

        // set
        writer.writeLine "\t"
        writer.writeLine "\tpublic void set${methodName}($field.type $field.name) {"
        writer.writeLine "\t\tthis.${field.name} = ${field.name};"
        writer.writeLine "\t}"
    }

    // 生成Service
    def static genService(writer, config, parentConfig, entityName, pkType, basePackage) {
        writer.writeLine "package ${basePackage}.service;"
        writer.writeLine ""
        writer.writeLine "import ${basePackage}.repository.${entityName}Repository;"
        if (parentConfig.enable) {
            writer.writeLine "import $parentConfig.package.$parentConfig.name;"
            writer.writeLine "import ${basePackage}.entity.$entityName;"
        }
        writer.writeLine "import org.springframework.stereotype.Service;"
        writer.writeLine ""
        writer.writeLine "import javax.annotation.Resource;"
        writer.writeLine ""
        writer.writeLine "/**"
        writer.writeLine " * $entityName service\u5c42"
        writer.writeLine " *"
        writer.writeLine " * @author auto generated"
        writer.writeLine " * @date ${Utils.localDateTimeStr()}"
        writer.writeLine " */"
        writer.writeLine "@Service"

        def extendsStr = parentConfig.enable ? " extends ${parentConfig.name}<$entityName, $pkType>" : ""
        writer.writeLine "public class ${entityName}Service${extendsStr} {"
        writer.writeLine ""
        writer.writeLine "\t@Resource"
        writer.writeLine "\tprivate ${entityName}Repository rep;"
        writer.writeLine "}"
    }

    // 生成rep
    def static genRepository(writer, config, entityName, basePackage, pkType) {
        def customStr = config.generate.repositoryCustom ? ", ${entityName}RepositoryCustom" : ""

        writer.writeLine "package ${basePackage}.repository;"
        writer.writeLine ""
        writer.writeLine "import ${basePackage}.entity.$entityName;"
        writer.writeLine "import org.springframework.data.jpa.repository.JpaRepository;"
        writer.writeLine ""
        writer.writeLine "/**"
        writer.writeLine " * $entityName Repository\u5c42"
        writer.writeLine " *"
        writer.writeLine " * @author auto generated"
        writer.writeLine " * @date ${Utils.localDateTimeStr()}"
        writer.writeLine " */"
        writer.writeLine "public interface ${entityName}Repository extends JpaRepository<$entityName, $pkType>$customStr {"
        writer.writeLine ""
        writer.writeLine "}"
    }

    // 生成repCustom
    def static genRepositoryCustom(writer, entityName, basePackage) {
        writer.writeLine "package ${basePackage}.repository;"
        writer.writeLine ""
        writer.writeLine "/**"
        writer.writeLine " * $entityName \u81ea\u5b9a\u4e49Repository\u5c42"
        writer.writeLine " *"
        writer.writeLine " * @author auto generated"
        writer.writeLine " * @date ${Utils.localDateTimeStr()}"
        writer.writeLine " */"
        writer.writeLine "public interface ${entityName}RepositoryCustom {"
        writer.writeLine ""
        writer.writeLine "}"
    }

    // 生成repCustomImp
    def static genRepositoryCustomImpl(writer, entityName, basePackage) {
        writer.writeLine "package ${basePackage}.repository.impl;"
        writer.writeLine ""
        writer.writeLine "import ${basePackage}.repository.${entityName}RepositoryCustom;"
        writer.writeLine "import org.springframework.stereotype.Repository;"
        writer.writeLine ""
        writer.writeLine "import javax.persistence.EntityManager;"
        writer.writeLine "import javax.persistence.PersistenceContext;"
        writer.writeLine ""
        writer.writeLine "/**"
        writer.writeLine " * $entityName \u81ea\u5b9a\u4e49Repository\u5b9e\u73b0\u5c42"
        writer.writeLine " *"
        writer.writeLine " * @author auto generated"
        writer.writeLine " * @date ${Utils.localDateTimeStr()}"
        writer.writeLine " */"
        writer.writeLine "@Repository"
        writer.writeLine "public class ${entityName}RepositoryCustomImpl implements ${entityName}RepositoryCustom {"
        writer.writeLine ""
        writer.writeLine "\t@PersistenceContext"
        writer.writeLine "\tprivate EntityManager em;"
        writer.writeLine "}"
    }

}

class Utils {

    /**
     * 提示框
     * @param message
     * @return
     */
    static def dialog(message) {
        JOptionPane.showMessageDialog(null, message, "\u6807\u9898", JOptionPane.PLAIN_MESSAGE)
    }

    /**
     * 反射获取主键列名，
     * @param table
     * @return 若没有返回null
     */
    static def getPK(table) {
        def method = table.getClass().getMethod("getText")
        method.setAccessible(true)
        def text = method.invoke(table).toString()
        def reg = /(?<=\s{4,})\b[^\s]+\b(?!=.+\n\s+PRIMARY KEY,)/
        firstMatched(text, reg, null)
    }

    /**
     *  转换为大写驼峰
     * @param content
     * @return
     */
    static def toUpperCamelCase(content) {
        content.toString()
                .split(/_/)
                .toList()
                .stream()
                .filter { s -> s.length() > 0 }
                .map { s -> s.replaceFirst("^.", s.substring(0, 1).toUpperCase()) }
                .collect(Collectors.joining())
    }

    /**
     *  转换为驼峰
     * @param content
     * @return
     */
    static def toCamelCase(content) {
        content = content.toString()
        toUpperCamelCase(content).replaceFirst(/^./, content.substring(0, 1).toLowerCase())
    }

    /**
     * 寻找第一个匹配的值
     * @param content 匹配内容
     * @param reg 正则
     * @param defaultValue 默认值
     * @return 根据正则匹配，能匹配就返回匹配的值，不能则匹配默认值
     */
    static def firstMatched(content, reg, defaultValue) {
        if (null == content) {
            return defaultValue
        }
        def m = content =~ reg
        if (m.find()) {
            return m.group()
        }
        return defaultValue
    }

    static def localDateTimeStr() {
        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
    }

    static def createFile(filePath, fileName) {
        def file = new File(filePath)

        if (!file.exists()) {
            file.mkdir()
        }

        file = new File(filePath + "/" + fileName)
        if (!file.exists()) {
            file.createNewFile()
        }
        return file
    }

    static def getDefaultValIfCurrentValIsBlank(currentVal, defaultVal) {
        if (null == currentVal || currentVal.isEmpty()) {
            return defaultVal
        }
        return currentVal
    }

    static def docCommentConvert(comment, prefix) {
        Arrays.stream(comment.split("\n"))
                .map { prefix + " * " + it }
                .reduce { s1, s2 -> s1 + "\n" + s2 }
                .get()
    }

    static def swaggerCommentConvert(comment) {
        comment.replaceAll("\n", "\\\\n")
    }
}

class Debug {

    // 反射获取所有方法
    static def console(col) {
        def clazz = col.getClass()
        def desktop = "C:/Users/mario/Desktop/"
        def fileName = "console${col.getName()}.txt"
        def path = desktop + fileName

        def file = new File(path)
        if (!file.exists()) {
            file.createNewFile()
        }

        file.withWriter("utf8") { writer ->
            writer.writeLine "base properties:"
            writer.writeLine "name\t${clazz.name}"
            writer.writeLine "simpleName\t${clazz.simpleName}"
            writer.writeLine ""

            writer.writeLine "public methods:"
            HashSet<Method> objMethodSet = new HashSet<Method>(new Object().getClass().methods as Collection<? extends Method>)
            HashSet<Method> colMethodSet = new HashSet<Method>(clazz.methods as Collection<? extends Method>)

            colMethodSet.stream()
                    .filter { m -> !objMethodSet.contains(m) && m.name.matches("^(get|is).+") && m.parameterCount == 0 }
                    .sorted { m1, m2 -> (m1.name <=> m2.name) }
                    .forEach { m ->
                        m.setAccessible(true)
                        writer.writeLine "method name:\t${m.name}"
                        writer.writeLine "invoke result:\t${m.invoke(col)}"
                        writer.writeLine ""
                    }

            writer.writeLine "all methods:"
            Arrays.stream(clazz.declaredMethods)
                    .sorted { m1, m2 -> (m1.name <=> m2.name) }
                    .forEach {
                        writer.writeLine "#"
                        writer.writeLine "name\t${it.name}"
                        writer.writeLine "accessible\t${it.accessible.toString()}"
                        writer.writeLine "return\t${it.returnType.name}"
                        writer.writeLine "paramCount\t${it.parameterCount}"
                        writer.write "param\t"
                        it.parameterTypes.each { writer.write "${it.name}\t" }
                        writer.write "param\t"
                        it.parameters.each { writer.write "${it.name}\t" }

                        writer.writeLine ""
                    }

            writer.writeLine ""
        }
    }

    // 测试获取的字段
    static def consoleFields(fields) {
        def desktop = "C:/Users/mario/Desktop/"
        def fileName = "console-fields.txt"
        def path = desktop + fileName

        def file = new File(path)
        if (!file.exists()) {
            file.createNewFile()
        }

        file.withWriter("utf8") { writer ->
            fields.each {
                it.each { k, v ->
                    writer.writeLine "${k}:\t${v}"
                }
                writer.writeLine "======================================"
            }
        }
    }
}
```
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
