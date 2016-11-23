<%@ include file="/WEB-INF/views/products/init.jsp"%>

<div class="container">
	<div>
		<portlet:actionURL var="searchProductAU">
			<portlet:param name="action" value="searchByProdId" />
		</portlet:actionURL>
		
		<aui:form cssClass="form-inline" id="frm_searchProd" action="${searchProductAU}" method="post">
			<c:set var="lbl_prodId"><liferay-ui:message key="prod.id"/></c:set>
			<aui:input type="text" name="prodId" />
			<button type="submit" class="btn btn-primary"><liferay-ui:message key="frm.btnSearch"/></button>	
		</aui:form>
	</div>
</div>