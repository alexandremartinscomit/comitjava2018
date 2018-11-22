package repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    void add(T t);

    void modify(T t);

    void remove(T t);

    Optional<T> findById(String id);

    Optional<T> findByCriteria(String field,String id);

    List<T> findAll();


}
