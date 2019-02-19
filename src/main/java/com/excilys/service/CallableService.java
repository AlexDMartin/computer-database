package com.excilys.service;

import java.util.List;
import java.util.Optional;

public interface CallableService<T> {
  Optional<T> get(long id);

  List<T> getAll();

  void save(T t) throws Exception;

  void update(T t);

  void delete(T t);
}
