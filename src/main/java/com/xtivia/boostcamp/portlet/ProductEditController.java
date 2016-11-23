/**
 * 
 */
package com.xtivia.boostcamp.portlet;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

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
@RequestMapping(value = "EDIT")
public class ProductEditController {
	/**
	 * That the method is render method
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ReadOnlyException 
	 */
	@RenderMapping
    public String renderPrefsPage(RenderRequest renderRequest, RenderResponse renderResponse) { 
		return ModuleConstants.URL_PRODUCTS_PREFS;
    }
	
	@ActionMapping(params = "action=savePrefs")
	public void savePreferences(ActionRequest actionRequest, ActionResponse actionResponse)
			throws PortletException, IOException {
		String msgWelcome = ParamUtil.getString(actionRequest, "prefsWelcome");
		
		PortletPreferences prefs = actionRequest.getPreferences();
		if (Validator.isBlank(msgWelcome)){
			msgWelcome = ModuleConstants.MSG_WELCOME;
		} 
		prefs.setValue("msgWelcome", msgWelcome);
		prefs.store();
	}
}
