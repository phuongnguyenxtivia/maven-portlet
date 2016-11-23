<%@ include file="/WEB-INF/views/products/init.jsp"%>

<%@ page import="com.liferay.portal.kernel.util.Constants" %>
<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>


<portlet:defineObjects />

<%
	//ProductConfiguration configuration = (ProductConfiguration) renderRequest
	//		.getAttribute(ProductConfiguration.class.getName());

	boolean showProductId = false;
	boolean showProductName = false;
	boolean showProductQuantity = false;
	boolean showProductUnitPrice = false;
	boolean showProductDescription = false;
	
	//if (Validator.isNotNull(configuration)) {
		showProductId = GetterUtil.getBoolean(portletPreferences.getValue("productId", "true"));
		showProductName = GetterUtil.getBoolean(portletPreferences.getValue("productName", "true"));
		showProductQuantity = GetterUtil.getBoolean(portletPreferences.getValue("productQuantity", "false"));
		showProductUnitPrice = GetterUtil.getBoolean(portletPreferences.getValue("productUnitPrice", "true"));
		showProductDescription = GetterUtil.getBoolean(portletPreferences.getValue("productDescription", "false"));
	//}
%>

<div class="container">
	<div>
		<liferay-portlet:actionURL portletConfiguration="<%=true%>"
			var="configurationActionURL" />

		<liferay-portlet:renderURL portletConfiguration="<%=true%>"
			var="configurationRenderURL" />

		<aui:form action="${configurationActionURL}" method="post" name="fm">
			<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
			<aui:input name="redirect" type="hidden" value="${configurationRenderURL}" />

			<c:set var="lbl_prodId">
				<liferay-ui:message key="prod.id" />
			</c:set>
			<aui:input type="checkbox" name="preferences--productId--" value="<%= showProductId %>"
				label="${lbl_prodId}" inlineLabel="true" />

			<c:set var="lbl_prodName">
				<liferay-ui:message key="prod.name" />
			</c:set>
			<aui:input type="checkbox" name="preferences--productName--" value="<%= showProductName %>"
				label="${lbl_prodName}" />
				
			<c:set var="lbl_prodQuantity">
				<liferay-ui:message key="prod.quantity" />
			</c:set>
			<aui:input type="checkbox" name="preferences--productQuantity--" value="<%= showProductQuantity %>"
				label="${lbl_prodQuantity}" />

			<c:set var="lbl_prodUnitPrice">
				<liferay-ui:message key="prod.unitPrice" />
			</c:set>
			<aui:input type="checkbox" name="preferences--productUnitPrice--" value="<%= showProductUnitPrice %>"
				label="${lbl_prodUnitPrice}" />

			<c:set var="lbl_prodDescription">
				<liferay-ui:message key="prod.desc" />
			</c:set>
			<aui:input type="checkbox" name="preferences--productDescription--" value="<%= showProductDescription %>"
				label="${lbl_prodDescription}" />

			<button type="submit" class="btn btn-primary">
				<liferay-ui:message key="frm.btnSave" />
			</button>
		</aui:form>
	</div>
</div>
