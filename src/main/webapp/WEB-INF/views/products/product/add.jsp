<%@ include file="/WEB-INF/views/products/init.jsp"%>

<div class="container">
	<p>
	<h2>
		<liferay-ui:message key="portlet.products.product.add.caption" />
	</h2>
	</p>
	<div>
		<portlet:actionURL var="addProductAU">
			<portlet:param name="action" value="addProduct"/>
		</portlet:actionURL>


		<form:form id="<portlet:namespace />frm_AddProd"
			action="${addProductAU}" modelAttribute="product" method="post">
			<c:set var="lbl_prodId"><liferay-ui:message key="prod.id" /></c:set>
			<aui:input type="text" name="id" label="${lbl_prodId}"></aui:input>
			
			<c:set var="lbl_prodName"><liferay-ui:message key="prod.name" /></c:set>
			<aui:input type="text" name="name" label="${lbl_prodName}"></aui:input>
			
			<c:set var="lbl_prodQuantity"><liferay-ui:message key="prod.quantity" /></c:set>
			<aui:input type="text" name="quantity" label="${lbl_prodQuantity}"></aui:input>
			
			<c:set var="lbl_prodUnitPrice"><liferay-ui:message key="prod.unitPrice" /></c:set>
			<aui:input type="text" name="unitPrice" label="${lbl_prodUnitPrice}"></aui:input>

			<div class="form-group">
				<label for="<portlet:namespace />description"><liferay-ui:message key="prod.desc"/></label> 
				<liferay-ui:input-editor name="description" />
				<aui:input type="hidden" name="description"></aui:input>
			</div>
			
			<input type="submit" class="btn btn-primary" value="<liferay-ui:message key="frm.btnAdd" />" />
		</form:form>
	</div>
	<div>
		<portlet:renderURL var="goToViewProductListPageAU">
			<portlet:param name="action" value="renderViewProductListPage"/>
		</portlet:renderURL>
		<a href="${goToViewProductListPageAU}"><liferay-ui:message
				key="url.cancel" /></a>
	</div>
</div>
