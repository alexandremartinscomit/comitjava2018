package service;

import java.util.List;

public interface Service<T> {

    void add(T t);

    void modify(T t);

    void remove(String id);

    T findById(String id);

    List<T> findAll();
}
