package com.excilys.service;

import java.util.List;
import java.util.Optional;

/**
 * The Interface CallableService.
 *
 * @param <T> the generic type
 */
public abstract interface CallableService<T> {

  /**
   * Gets the.
   *
   * @param id the id
   * @return the optional
   */
  public abstract Optional<T> get(long id);

  /**
   * Gets the all.
   *
   * @return the all
   */
  public abstract List<T> getAll();

  /**
   * Save.
   *
   * @param t the t
   * @throws Exception the exception
   */
  public abstract void save(T t) throws Exception;

  /**
   * Update.
   *
   * @param t the t
   */
  public abstract void update(T t);

  /**
   * Delete.
   *
   * @param t the t
   */
  public abstract void delete(T t);
}
