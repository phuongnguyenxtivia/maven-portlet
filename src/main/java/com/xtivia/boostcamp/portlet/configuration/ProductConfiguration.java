/**
 * 
 */
package com.xtivia.boostcamp.portlet.configuration;

import aQute.bnd.annotation.metatype.*;

/**
 * @author pnguyen
 *
 */
@Meta.OCD(id = "com.xtivia.boostcamp.portlet.configuration.ProductConfiguration")
public interface ProductConfiguration {	
	@Meta.AD(deflt = "true", required = true) 
	public boolean productId(); 
	
	@Meta.AD(deflt = "true", required = true)
	public boolean productName();
	
	@Meta.AD(deflt = "false", required = false)
	public boolean productQuantity();
	
	@Meta.AD(deflt = "true", required = true)
	public boolean productUnitPrice();
	
	@Meta.AD(deflt = "false", required = false)
	public boolean productDescription(); 
}
