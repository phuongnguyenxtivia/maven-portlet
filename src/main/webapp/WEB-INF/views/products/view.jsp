<%@ include file="/WEB-INF/views/products/init.jsp"%>

<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>

<%
	//ProductConfiguration configuration = (ProductConfiguration) renderRequest
	//		.getAttribute(ProductConfiguration.class.getName());

	//String productId = StringPool.BLANK;
	//String productName = StringPool.BLANK;
	//String productQuantity = StringPool.BLANK;
	//String productUnitPrice = StringPool.BLANK;
	//String productDescription = StringPool.BLANK;

	//if (Validator.isNotNull(configuration)) {
		//productId = portletPreferences.getValue("productId", String.valueOf(configuration.productId()));
		//productName = portletPreferences.getValue("productName", String.valueOf(configuration.productName()));
		//productQuantity = portletPreferences.getValue("productQuantity",
		//		String.valueOf(configuration.productQuantity()));
		//productUnitPrice = portletPreferences.getValue("productUnitPrice",
		//		String.valueOf(configuration.productUnitPrice()));
		//productDescription = portletPreferences.getValue("productDescription",
		//		String.valueOf(configuration.productDescription()));
		//productId = portletPreferences.getValue("productId", String.valueOf(true));
		//productName = portletPreferences.getValue("productName", String.valueOf(true));
		//productQuantity = portletPreferences.getValue("productQuantity",
		//		String.valueOf(true));
		//productUnitPrice = portletPreferences.getValue("productUnitPrice",
		//		String.valueOf(true));
		//productDescription = portletPreferences.getValue("productDescription",
		//		String.valueOf(true));
	//}
	
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
	<p class="text-info"><b><liferay-ui:message key="${msgWelcome}"/></b></p>
	
	<portlet:resourceURL id="deleteProductById" var="ajaxURL"></portlet:resourceURL>
	<script>
		function <portlet:namespace/>ajaxCall(prodId) {
			$.ajax({
				url : '${ajaxURL}',
				data : {
					prodId : prodId
				},
				type : 'POST',
				dataType : "json",
				success : function(data) {
					// do stuff on success
					$(this).closest('tr').remove();
				     return false;
				},
				error : function() {
					//do stuff on error
					console.log('Error Occurred');
				}
			});
		}
	</script>
	<div class="table-responsive">
		<table class="table table-hover">
			<thead>
				<tr>
					<c:if test="<%= showProductId %>">
						<th><liferay-ui:message key="prod.id" /></th>
					</c:if>
					<c:if test="<%= showProductName %>">
						<th><liferay-ui:message key="prod.name" /></th>
					</c:if>
					<c:if test="<%= showProductQuantity %>">
						<th><liferay-ui:message key="prod.quantity" /></th>
					</c:if>
					<c:if test="<%= showProductUnitPrice %>">
						<th><liferay-ui:message key="prod.unitPrice" /></th>
					</c:if>
					<c:if test="<%= showProductDescription %>">
						<th><liferay-ui:message key="prod.desc" /></th>
					</c:if>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<fmt:setLocale value="en_US"/>

				<c:forEach items="${products}" var="product">
					<tr id="row_${products}">
						<c:if test="<%= showProductId %>">
							<td>
								<portlet:renderURL var="goToViewProductPageRU">
									<portlet:param name="action" value="renderViewProductPage"/>
									<portlet:param name="prodId" value="${product.id}" />
								</portlet:renderURL>
								<a href="${goToViewProductPageRU}">${product.id}</a>
							</td>
						</c:if>
						<c:if test="<%= showProductName %>">
							<td>${product.name}</td>
						</c:if>
						<c:if test="<%= showProductQuantity %>">
							<td>${product.quantity}</td>
						</c:if>
						<c:if test="<%= showProductUnitPrice %>">
							<td>
								<fmt:formatNumber value="${product.unitPrice}" type="currency" />
							</td>
						</c:if>
						<c:if test="<%= showProductDescription %>">
							<td>${product.description}</td>
						</c:if>
						<td>
							<a href="#" onclick="<portlet:namespace/>ajaxCall('${product.id}')"><liferay-ui:message key="url.delete"/></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div>
		<portlet:renderURL var="goToAddProductPageRU">
			<portlet:param name="action" value="renderAddProductPage"/>
		</portlet:renderURL>
		<a href="${goToAddProductPageRU}"><liferay-ui:message key="url.add"/></a>
	</div>
</div>
