package ua.com.alevel.dao;

import java.util.List;

public interface Dao<T> {
     void create(T model);

    void update(T model, int id_model);

    T findById(int id_model);

    List<T> findAll();

    boolean deleteById(int id_model);
}
