/**
 * 
 */
package com.xtivia.boostcamp.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @author pnguyen
 *
 */
public interface IBaseDAO <T, PK extends Serializable> {
	List<T> getList();
	
	List<T> getListByKey(PK key);
	
	T get(PK key);
	
	void add(T entity);
	
	void update(T entity);
	
	void delete(PK key);
}