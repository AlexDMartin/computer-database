package com.excilys.persistance.mappers;

import java.sql.ResultSet;
import java.util.List;

public interface Mapper<T> {
	List<T> map(ResultSet rs);
}
