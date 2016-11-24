/**
 * 
 */
package com.xtivia.boostcamp.dao.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.xtivia.boostcamp.dao.DAOFactory;
import com.xtivia.boostcamp.dao.IProductDAO;
import com.xtivia.boostcamp.dao.impl.MySQLProductDAOImpl;

/**
 * @author pnguyen
 *
 */
@Component
public class MySQLDAOFactory extends DAOFactory {
	@Autowired
	@Qualifier("mySQLProductDAO")
	private IProductDAO productDAO;
	
	@Override
	public IProductDAO getProductDAO() {
		return productDAO;
	}
}
