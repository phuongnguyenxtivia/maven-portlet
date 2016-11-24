/**
 * 
 */
package com.xtivia.boostcamp.dao;

import java.util.List;

import com.xtivia.boostcamp.domain.Product;

/**
 * @author pnguyen
 *
 */
public interface IProductDAO extends IBaseDAO<Product, String> {
	List<Product> getListByKey(String key);
}
