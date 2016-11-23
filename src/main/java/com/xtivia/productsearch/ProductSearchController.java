/**
 * 
 */
package com.xtivia.productsearch;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.xml.namespace.QName;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.xtivia.boostcamp.common.ModuleConstants;

/**
 * @author pnguyen
 *
 */
@Controller
@RequestMapping(value = "VIEW")
public class ProductSearchController {
	/**
	 * That the method is render method
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RenderMapping
    public String renderPage(RenderRequest renderRequest, RenderResponse renderResponse){
		return ModuleConstants.URL_PRODUCT_SEARCH_BY_ID;
    }
	
//	@RenderMapping(params = "action=renderSearchByProdId")
//    public String renderSearchByProdId(RenderRequest renderRequest, RenderResponse renderResponse){
//		return ModuleConstants.URL_PRODUCT_SEARCH_BY_ID;
//    }
	
	/**
	 * Action search product by key
	 * 
	 * @param actionRequest
	 * @param actionResponse
	 */
	@ActionMapping(params = "action=searchByProdId")
	public void searchByProdId(ActionRequest actionRequest, ActionResponse actionResponse) {
		String prodId = ParamUtil.getString(actionRequest, "prodId");
		
		// TODO: validate product Id
		if (!Validator.isBlank(prodId)) {
			/**
			 *  IPC by using PortletSession
			 */
			//PortletSession ps = actionRequest.getPortletSession();
			//ps.setAttribute("searchByProdId", prodId ,PortletSession.APPLICATION_SCOPE);
			
			/**
	         *  Using IPC by using event. Refer portlet.xml
	         */
	        QName qName = new QName("http://proliferay.com/events", "ipc-text");
	        actionResponse.setEvent(qName, prodId);
		}
		//actionResponse.setRenderParameter("action", "renderSearchByProdId");
	}
}
