/**
 * 
 */
package com.xtivia.boostcamp.portlet.configuration;

import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.portlet.DefaultConfigurationAction;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.PortalUtil;
import com.xtivia.boostcamp.common.ModuleConstants;

import aQute.bnd.annotation.metatype.Configurable;

/**
 * @author pnguyen
 *
 */
@Component(
	     configurationPid = "com.xtivia.boostcamp.portlet.configuration.ProductConfiguration",
	     configurationPolicy = ConfigurationPolicy.OPTIONAL,
	     immediate = true,
	     property = {
	         "javax.portlet.name=mavenportlet_WAR_mavenportlet001SNAPSHOT"
	     },
	     service = ConfigurationAction.class
	 )
public class ProductConfigurationAction extends DefaultConfigurationAction {
	
	private volatile ProductConfiguration _configuration;

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
