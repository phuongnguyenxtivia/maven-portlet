/**
 * 
 */
package com.xtivia.boostcamp.portlet.utils;

import java.io.IOException;

import javax.portlet.PortletContext;
import javax.portlet.PortletException;
import javax.portlet.PortletRequestDispatcher;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author pnguyen
 *
 */
public class PortletUtils {
	private static Log _log = LogFactoryUtil.getLog(PortletUtils.class);

	public static void include(PortletContext portletContext, String path, RenderRequest renderRequest,
			RenderResponse renderResponse) throws IOException, PortletException {
		PortletRequestDispatcher portletRequestDispatcher = portletContext.getRequestDispatcher(path);
		if (portletRequestDispatcher != null) {
			portletRequestDispatcher.include(renderRequest, renderResponse);
		} else {
			_log.error(path + " is not a valid include");
		}
	}
}
