package mapper;

import java.util.List;

public interface BaseMapper<T> {
    int save(T t);

    int update(T t);

    int delete(int id);

    T selectOne(int id);

    List<T> selectAll();

}
