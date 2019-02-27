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
   * Map.
   *
   * @param rs the rs
   * @return the list
   */
  List<T> map(ResultSet rs);
  
  DTO entityToDTO(T entity);
  
  T DTOToEntity(DTO dto);
}
