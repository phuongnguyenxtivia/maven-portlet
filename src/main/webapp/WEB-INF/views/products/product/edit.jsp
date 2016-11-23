<%@ include file="/WEB-INF/views/products/init.jsp"%>>

<%@ page import="com.liferay.portal.kernel.util.UnicodeFormatter" %>
<%@ page import="com.xtivia.boostcamp.domain.Product" %>

<% 
	Product prod = (Product) request.getAttribute("product");
%>

<div class="container">
	<h2>
		<liferay-ui:message key="portlet.products.product.edit.caption" />
	</h2>

	<div>
		<portlet:actionURL var="updateProductAU">
			<portlet:param name="action" value="updateProduct"/>
		</portlet:actionURL>
		
		<form:form id="<portlet:namespace />frm_UpdateProduct"
			action="${updateProductAU}" modelAttribute="product" method="post">
			
			<c:set var="lbl_prodId"><liferay-ui:message key="prod.id" /></c:set>
			<aui:input type="text" name="id" label="${lbl_prodId}" value="${product.id}"></aui:input>
			
			<c:set var="lbl_prodName"><liferay-ui:message key="prod.name" /></c:set>
			<aui:input type="text" name="name" label="${lbl_prodName}" value="${product.name}"></aui:input>
			
			<c:set var="lbl_prodQuantity"><liferay-ui:message key="prod.quantity" /></c:set>
			<aui:input type="text" name="quantity" label="${lbl_prodQuantity}" value="${product.quantity}"></aui:input>
			
			<c:set var="lbl_prodUnitPrice"><liferay-ui:message key="prod.unitPrice" /></c:set>
			<aui:input type="text" name="unitPrice" label="${lbl_prodUnitPrice}" value="${product.unitPrice}"></aui:input>
			
			<c:set var="lbl_prodDesc"><liferay-ui:message key="prod.desc"/></c:set>
			<aui:field-wrapper label="${lbl_prodDesc}">
					<liferay-ui:input-editor name="description" initMethod="initEditor" />
					<script type="text/javascript">
						function <portlet:namespace />initEditor() {
							return "<%= UnicodeFormatter.toString(prod.getDescription()) %>";
						}
					</script>
					<aui:input type="hidden" name="description"></aui:input>
			</aui:field-wrapper>
			
			<button type="submit" class="btn btn-primary"><liferay-ui:message key="frm.btnSubmit"/></button>
		</form:form>
	</div>
	
	<div>
		<portlet:renderURL var="goToViewProductPageRU">
			<portlet:param name="action" value="renderViewProductPage"/>
			<portlet:param name="prodId" value="${product.id}" />
		</portlet:renderURL>
		<a href="${goToViewProductPageRU}"><liferay-ui:message key="url.cancel" /></a>
	</div>
</div>
