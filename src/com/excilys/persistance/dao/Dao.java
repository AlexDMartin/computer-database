package com.excilys.persistance.dao;

import java.util.List;
import java.util.Optional;

/**
 * 
 * @author Alex Martin
 *
 * @param <T>
 */
public interface Dao<T> {
     
    Optional<T> get(long id);
     
    List<T> getAll();
     
    void save(T t) throws Exception;
     
    void update(T t);
     
    int delete(T t);
}

