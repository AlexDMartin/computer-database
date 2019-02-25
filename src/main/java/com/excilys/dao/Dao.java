package com.excilys.dao;

import java.util.List;
import java.util.Optional;

/**
 * The Interface Dao.
 *
 * @param <T> the generic type
 */
public interface Dao<T> {

  /**
   * Gets the.
   *
   * @param id the id
   * @return the optional
   */
  Optional<T> get(long id);

  /**
   * Gets the all.
   *
   * @return the all
   */
  List<T> getAll();

  /**
   * Save.
   *
   * @param t the t
   * @throws Exception the exception
   */
  void save(T t) throws Exception;

  /**
   * Update.
   *
   * @param t the t
   */
  void update(T t);

  /**
   * Delete.
   *
   * @param t the t
   */
  void delete(T t);
}
