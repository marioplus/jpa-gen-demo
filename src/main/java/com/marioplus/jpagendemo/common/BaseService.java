package com.marioplus.jpagendemo.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * 基础 service 类
 *
 * @author mario
 * @since 2020/1/29 23:18
 **/
public class BaseService<T, ID> {

    @Autowired
    protected JpaRepository<T, ID> repository;

    public <S extends T> S save(S s) {
        return repository.save(s);
    }

    public T findById(ID id) {
        Optional<T> optionalT = repository.findById(id);
        return optionalT.orElse(null);
    }

    public <S extends T> Iterable<S> saveAll(Iterable<S> iterable) {
        return repository.saveAll(iterable);
    }

    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public List<T> findAllById(Iterable<ID> iterable) {
        return repository.findAllById(iterable);
    }

    public long count() {
        return repository.count();
    }

    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    public void delete(T t) {
        repository.delete(t);
    }

    public void deleteAll(Iterable<? extends T> iterable) {
        repository.deleteAll(iterable);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public Iterable<T> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

}
