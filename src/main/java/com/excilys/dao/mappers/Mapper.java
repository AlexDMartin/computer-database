package com.excilys.dao.mappers;

import com.excilys.dto.Dto;
import java.sql.ResultSet;
import java.util.List;

/**
 * The Interface Mapper.
 *
 * @param <T> the generic type
 */
public interface Mapper<T> {

  /**
   * Take a ResulSet and returns a list of the generic type, useful to map items directly after a
   * Database call.
   * 
   * @param resultSet Any ResultSet
   * @return List&lt;Company&gt;
   */
  List<T> map(ResultSet resultSet);

  /**
   * Transforms an Entity into a DTO.
   * 
   * @param entity Any entity
   * @return DTO
   */
  Dto entityToDto(T entity);

  /**
   * Transforms DTO into an Entity.
   * 
   * @param dto Any data object
   * @return T
   */
  T dtoToEntity(Dto dto);
}
