package com.excilys.dao.mappers;

import java.sql.ResultSet;
import java.util.List;

import com.excilys.dto.DTO;

/**
 * The Interface Mapper.
 *
 * @param <T> the generic type
 */
public interface Mapper<T> {

  /**
   * Take a ResulSet and returns a list of the generic type,
   * useful to map items directly after a Database call.
   * @param ResultSet
   * @return List<Company>
   */
  List<T> map(ResultSet rs);
  
  /**
   * Transforms an Entity into a DTO.
   * @param T
   * @return DTO
   */
  DTO entityToDTO(T entity);
  
  /**
   * Transforms DTO into an Entity.
   * @param DTO
   * @return T
   */
  T DTOToEntity(DTO dto);
}
