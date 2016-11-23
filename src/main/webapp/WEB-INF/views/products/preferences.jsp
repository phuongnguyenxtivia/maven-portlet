<%@ include file="/WEB-INF/views/products/init.jsp"%>

<div class="container">
	<p>
	<h2>
		<liferay-ui:message key="portlet.products.preferences.caption" />
	</h2>
	</p>
	<div>
		<portlet:actionURL var="editPrefsAU">
			<portlet:param name="action" value="savePrefs" />
		</portlet:actionURL>

		<form id="<portlet:namespace />frm_editPrefs" action="${editPrefsAU}"
			method="post">
			<c:set var="lbl_welcome">
				<liferay-ui:message key="products.prefs.welcome" />
			</c:set>
			<aui:input name="prefsWelcome"
				value="${portletPreferences.getValue('msgWelcome', '')}"
				label="${lbl_welcome}"></aui:input>
			<button type="submit" class="btn btn-primary">
				<liferay-ui:message key="frm.btnSave" />
			</button>
		</form>
	</div>
</div>
