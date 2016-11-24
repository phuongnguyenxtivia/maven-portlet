/**
 * 
 */
package com.xtivia.boostcamp.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.xtivia.boostcamp.dao.IProductDAO;
import com.xtivia.boostcamp.domain.Product;

/**
 * @author pnguyen
 *
 */
@Repository("mySQLProductDAO")
public class MySQLProductDAOImpl extends BaseDAOImpl<Product, String> implements IProductDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getListByKey(String key) {
		Query query = currentSession().createQuery("from Product p where p.id like :id");
		query.setString("id", key + "%"); // start with
		return new ArrayList<Product>(query.list());
	}

}
