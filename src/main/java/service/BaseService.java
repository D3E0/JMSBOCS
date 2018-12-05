package service;

import java.util.List;

public interface BaseService<T> {
    int update(T t);

    int delete(int id);

    int save(T t);

    T selectOne(int id);

    List<T> selectAll();
}
