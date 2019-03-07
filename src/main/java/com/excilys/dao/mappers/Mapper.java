package com.excilys.dao.mappers;

import java.sql.ResultSet;
import java.util.List;
import com.excilys.dto.Dto;
import com.excilys.exception.validation.ValidationException;

/**
 * The Interface Mapper.
 *
 * @param <T> the generic type
 */
public abstract interface Mapper<T> {

  /**
   * Take a ResulSet and returns a list of the generic type, useful to map items directly after a
   * Database call.
   * 
   * @param resultSet Any ResultSet
   * @return List&lt;Company&gt;
   */
  public abstract List<T> map(ResultSet resultSet);

  /**
   * Transforms an Entity into a DTO.
   * 
   * @param entity Any entity
   * @return DTO
   * @throws ValidationException 
   */
  public abstract Dto entityToDto(T entity) throws ValidationException;

  /**
   * Transforms DTO into an Entity.
   * 
   * @param dto Any data object
   * @return T
   * @throws ValidationException 
   */
  public abstract T dtoToEntity(Dto dto) throws ValidationException;
}
