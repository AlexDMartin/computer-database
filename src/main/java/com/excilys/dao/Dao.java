package com.excilys.dao;

import com.excilys.dao.exception.DatabaseCallException;
import java.util.List;
import java.util.Optional;

/**
 * The Interface Dao.
 *
 * @param <T> the generic type
 */
public abstract interface Dao<T> {

  /**
   * Gets the.
   *
   * @param id the id
   * @return the optional
   */
  public abstract Optional<T> get(int id) throws DatabaseCallException;

  /**
   * Gets the all.
   *
   * @return the all
   */
  public abstract List<T> getAll() throws DatabaseCallException;

  /**
   * Save.
   *
   * @param t the t
   * @throws Exception the exception
   */
  public abstract void save(T t) throws DatabaseCallException;

  /**
   * Update.
   *
   * @param t the t
   */
  public abstract void update(T t) throws DatabaseCallException;

  /**
   * Delete.
   *
   * @param t the t
   */
  public abstract void delete(T t) throws DatabaseCallException;
}
