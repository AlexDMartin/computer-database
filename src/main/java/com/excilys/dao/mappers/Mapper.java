package com.excilys.dao.mappers;

import com.excilys.dto.Dto;
import com.excilys.exception.validation.ValidationException;
import java.sql.ResultSet;
import java.util.List;

/**
 * The Interface Mapper.
 *
 * @param <T> the generic type
 */
public abstract interface Mapper<T> {

  /**
   * Transforms an Entity into a DTO.
   * 
   * @param entity Any entity
   * @return DTO
   * @throws ValidationException The exception thrown when the Entity is not valid
   */
  public abstract Dto entityToDto(T entity) throws ValidationException;

  /**
   * Transforms DTO into an Entity.
   * 
   * @param dto Any data object
   * @return T
   * @throws ValidationException The exception thrown when the Entity is not valid
   */
  public abstract T dtoToEntity(Dto dto) throws ValidationException;
}
