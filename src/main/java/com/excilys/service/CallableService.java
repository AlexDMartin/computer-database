package com.excilys.service;

import java.util.List;
import java.util.Optional;

// TODO: Auto-generated Javadoc
/**
 * The Interface CallableService.
 *
 * @param <T> the generic type
 */
public interface CallableService<T> {

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
