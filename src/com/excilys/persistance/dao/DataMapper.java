package com.excilys.persistance.dao;

import java.sql.ResultSet;
import java.util.List;

public interface DataMapper<T> {

	List<T> map(ResultSet rs);
}
