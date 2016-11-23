package com.xtivia.boostcamp.portlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Event;
import javax.portlet.EventRequest;
import javax.portlet.EventResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletSession;
import javax.portlet.ProcessEvent;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.EventMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.xtivia.boostcamp.common.ModuleConstants;
import com.xtivia.boostcamp.domain.Product;
import com.xtivia.boostcamp.portlet.configuration.ProductConfiguration;
import com.xtivia.boostcamp.service.IProductService;

import aQute.bnd.annotation.metatype.Configurable;

@Component(configurationPid = "com.xtivia.boostcamp.portlet.configuration.ProductConfiguration")
@Controller
@RequestMapping(value = "VIEW")
public class ProductController {
	private static Log _log = LogFactoryUtil.getLog(ProductController.class);
	@Autowired
	@Qualifier("productService")
	protected IProductService productService;
	private volatile ProductConfiguration _configuration;

	/**
	 * That the method is render method
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RenderMapping
    public String viewHomePage(RenderRequest renderRequest, RenderResponse renderResponse){
		return renderViewProductListPage(renderRequest, renderResponse);
    }
	
	/**
	 * Add new Product action mapping, then call renderAddProductPage render
	 * mapping
	 * 
	 * @param actionRequest
	 * @param actionResponse
	 * @param product
	 */
	@ActionMapping(params = "action=addProduct")
	public void addProduct(ActionRequest actionRequest, ActionResponse actionResponse,
			@ModelAttribute("product") Product product) {
		if (productService.validate(product)) {
			// TODO: why liferay ui edit-note adds "," in the last
			// remove last "," in edit note
			String desc = product.getDescription();
			if (!Validator.isBlank(desc) && desc.length() > 1) {
				product.setDescription(desc.substring(0, desc.length() - 2));
			}
			productService.add(product);
		}
		
		actionResponse.setRenderParameter("action", "renderAddProductPage");
	}
	
	/**
	 * Update Product then return to View Product page, otherwise, still be in
	 * Update Product page
	 * 
	 * @param actionRequest
	 * @param actionResponse
	 * @param product
	 * @param model
	 */
	@ActionMapping(params = "action=updateProduct")
	public void updateProduct(ActionRequest actionRequest, ActionResponse actionResponse,
			@ModelAttribute("product") Product product, Model model) {
		if (productService.validate(product)) {
			// TODO: why liferay ui edit-note adds "," in the last
			// remove last "," in edit note
			String desc = product.getDescription();
			if (!Validator.isBlank(desc) && desc.length() > 1) {
				product.setDescription(desc.substring(0, desc.length() - 2));
			}
			productService.update(product);

//			model.addAttribute("product", product);
			actionResponse.setRenderParameter("prodId", product.getId());
			actionResponse.setRenderParameter("action", "renderViewProductPage");
			return;
		}
		
		actionResponse.setRenderParameter("action", "renderEditProductPage");
	}

	/**
	 * This deleteProductById must match with the id of ResourceURL that is
	 * created in JSP page.
	 * 
	 * @param resourceRequest
	 * @param resourceResponse
	 * @throws IOException
	 * @throws PortletException
	 */
	@ResourceMapping(value="deleteProductById")
	public void deleteProductById(ResourceRequest resourceRequest, ResourceResponse resourceResponse)
			throws IOException, PortletException {
		String key = ParamUtil.getString(resourceRequest, "prodId");
		if (!Validator.isBlank(key)) {
			productService.delete(key);
		}
	}
	
	/**
	 * Render View Product page
	 * 
	 * @param renderRequest
	 * @param renderResponse
	 * @param model
	 * @return
	 */
	@RenderMapping(params = "action=renderViewProductPage")
	public String renderViewProductPage(RenderRequest renderRequest, RenderResponse renderResponse, Model model) {
		String key = ParamUtil.getString(renderRequest, "prodId");
		if (!Validator.isBlank(key)) {
			model.addAttribute("product", productService.get(key));
		}
		return ModuleConstants.URL_PRODUCT_VIEW;
	}
	
	/**
	 * Render Add Product page
	 * 
	 * @param renderRequest
	 * @param renderResponse
	 * @param model
	 * @return
	 */
	@RenderMapping(params = "action=renderAddProductPage")
    public String goToAddProductPage(RenderRequest renderRequest, RenderResponse renderResponse, Model model){
		model.addAttribute("product", new Product());
		return ModuleConstants.URL_PRODUCT_ADD;
	}
	
	/**
	 * Render Edit Product page
	 * 
	 * @param renderRequest
	 * @param renderResponse
	 * @param model
	 * @param key
	 * @return
	 */
	@RenderMapping(params = "action=renderEditProductPage")
	public String renderEditProductPage(RenderRequest renderRequest, RenderResponse renderResponse, Model model) {
		String key = ParamUtil.getString(renderRequest, "prodId");
		if (!Validator.isBlank(key)) {
			model.addAttribute("product", productService.get(key));
		}
		return ModuleConstants.URL_PRODUCT_EDIT;
	}
	
	/**
	 * Render View Product List page
	 * 
	 * @param renderRequest
	 * @param renderResponse
	 * @return
	 */
	@RenderMapping(params = "action=renderViewProductListPage")
	public String renderViewProductListPage(RenderRequest renderRequest, RenderResponse renderResponse) {
		String searchByProdId = StringPool.BLANK;
		List<Product> list = new ArrayList<Product>();
		
		/**
		 * Get information from another portlet by using PortletSession
		 */
//		PortletSession ps = renderRequest.getPortletSession();
//		searchByProdId = (String) ps.getAttribute("searchByProdId", PortletSession.APPLICATION_SCOPE);
		
		/**
		 * Get information from another portlet by using event
		 */
		searchByProdId = ParamUtil.getString(renderRequest, "searchByProdId", StringPool.BLANK);
		
		
		if (Validator.isBlank(searchByProdId)) {
			list = productService.getList();
		} else {
			/**
			 * Get information from another portlet by using PortletSession
			 */
			// remove session attribute
			//ps.setAttribute("searchByProdId", null, PortletSession.APPLICATION_SCOPE);
		
			list = productService.getListByKey(searchByProdId);
		}
		
		// get message welcome from Prefs, if don't have use default message
		PortletPreferences prefs = renderRequest.getPreferences();
		String msgWelcome = prefs.getValue("msgWelcome", ModuleConstants.MSG_WELCOME);
		
		renderRequest.setAttribute("msgWelcome", msgWelcome);
		renderRequest.setAttribute(ProductConfiguration.class.getName(), _configuration);
		renderRequest.setAttribute("products", list);

		return ModuleConstants.URL_PRODUCTS_VIEW;
	}
	
	/**
	 * Handle portlet event
	 * 
	 * @param request
	 * @param response
	 */
	@EventMapping(value = "{http://proliferay.com/events}ipc-text")
    public void myEvent(EventRequest request, EventResponse response) {
		String text = StringPool.BLANK;
		Event event = request.getEvent();
        
        
        //Get data from the event
		if (Validator.isNotNull(event.getValue())) {
			text = (String) event.getValue();
		}
        
        //Set the text in response to display in UI
        response.setRenderParameter("searchByProdId", text);
        response.setRenderParameter("action", "renderViewProductListPage");
    }
	
	/**
	 * This method is invoked when the application starts (due to the @Activate
	 * annotation) and whenever the configuration is modified (due to
	 * the @Modified annotation)
	 */
	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_configuration = Configurable.createConfigurable(ProductConfiguration.class, properties);
	}
}
