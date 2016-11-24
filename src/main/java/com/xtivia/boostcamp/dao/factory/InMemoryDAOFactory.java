/**
 * 
 */
package com.xtivia.boostcamp.dao.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.xtivia.boostcamp.dao.DAOFactory;
import com.xtivia.boostcamp.dao.IProductDAO;
import com.xtivia.boostcamp.dao.impl.InMemoryProductDAOImpl;

/**
 * @author pnguyen
 *
 */
@Component
public class InMemoryDAOFactory extends DAOFactory {
	@Autowired
	@Qualifier("inMemoryProductDAO")
	private IProductDAO productDAO; //= new InMemoryProductDAOImpl();
	
	@Override
	public IProductDAO getProductDAO() {
		return productDAO;
	}

}
