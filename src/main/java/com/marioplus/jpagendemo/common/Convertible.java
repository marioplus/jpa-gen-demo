package com.marioplus.jpagendemo.common;

import org.springframework.beans.BeanUtils;

/**
 * 可以转换为对应实体
 */
public interface Convertible<E> {

    /**
     * 常见一个E的实例
     *
     * @return E
     */
    E newEntity();

    /**
     * 转换为实体
     *
     * @return 实体
     */
    default E convertToEntity(E e) {
        BeanUtils.copyProperties(this, e);
        return e;
    }

    /**
     * 转换为实体
     *
     * @return 实体
     */
    default E convertToEntity() {
        return this.convertToEntity(this.newEntity());
    }
}
