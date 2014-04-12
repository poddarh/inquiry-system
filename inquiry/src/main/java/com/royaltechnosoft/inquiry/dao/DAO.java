package com.royaltechnosoft.inquiry.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public interface DAO<T> {
	static Direction ASCENDING = Sort.Direction.ASC;
	static Direction DESCENDING = Sort.Direction.DESC;
	final int LIMIT_PER_PAGE = 15;
	
	List<T> find(T model);
	List<T> find(T model, String sortColumn, Direction direction);
	List<T> find(T model, Integer page);
	List<T> find(T model, String sortColumn, Direction direction, Integer page);
	T findOne(T model);
	T findOne(String id);
	long count(T model);
	int countPages(T model);
	void insert(T model);
	void save(T model);
	boolean update(String id, T updateModel);
	boolean update(T queryModel, T updateModel);
	void destroy(T model);
	void destroyMany(T queryModel);
}
