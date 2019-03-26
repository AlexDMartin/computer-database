package com.excilys.service;

import com.excilys.service.exception.ServiceException;
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
  public abstract Optional<T> find(int id) throws ServiceException;

  /**
   * Gets the all.
   *
   * @return the all
   */
  public abstract List<T> findAll() throws ServiceException;

  /**
   * Save.
   *
   * @param t the t
   * @throws Exception the exception
   */
  public abstract void save(T t) throws ServiceException;

  /**
   * Update.
   *
   * @param t the t
   */
  public abstract void update(T t) throws ServiceException;

  /**
   * Delete.
   *
   * @param t the t
   */
  public abstract void delete(T t) throws ServiceException;
}
