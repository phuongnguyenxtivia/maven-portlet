SET ROOT_WORK_JSP=C:\dev\bundles\liferay-dxp-7-ga1\work\maven-portlet-0.0.1-SNAPSHOT-0.0.1.SNAPSHOT\org
SET ROOT_DEPLOY=C:\dev\bundles\liferay-dxp-7-ga1\deploy
SET ROOT_WAR_FILE=C:\dev\ws\maven-portlet\target
RD /S /Q %ROOT_WORK_JSP%
:mvn clean package liferay:deploy
CALL MVN clean package
COPY "%ROOT_WAR_FILE%\*.war" "%ROOT_DEPLOY%"
