package com.royaltechnosoft.inquiry.dao;

import java.util.List;

public interface DAO<T> {
	static byte ASCENDING = 1;
	static byte DESCENDING = 2;
	static int LIMIT_PER_PAGE = 15;
	
	List<T> find(T model);
	List<T> find(T model, String sortColumn, Byte direction);
	List<T> find(T model, Integer page);
	List<T> find(T model, String sortColumn, Byte direction, Integer page);
	T findOne(T model);
	T findOne(int id);
	Number count(T model);
	int countPage(T model);
	void insert(T model);
	void save(T model);
	void update(int id, T updateModel);
	void update(T queryModel, T updateModel);
	void destroy(T model);
	void destroyMany(T queryModel);
}
