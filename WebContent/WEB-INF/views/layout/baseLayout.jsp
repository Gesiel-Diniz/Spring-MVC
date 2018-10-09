<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<spring:url value="/resources/js/jquery.min.js" var="jqueryJS"></spring:url>
<spring:url value="/resources/css/cosmo-bootstrap.css" var="bootstrapCSS"></spring:url>
<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJS"></spring:url>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><tiles:insertAttribute name="title" ignore="true"></tiles:insertAttribute></title>

<script type="text/javascript" src="${jqueryJS}"></script>
<link rel="stylesheet" href="${bootstrapCSS}" />
<script type="text/javascript" src="${bootstrapJS}"></script>
</head>
<body>
	<div style="width: 100%; position:fixed; z-index:10; margin-top:-60px;">
		<tiles:insertAttribute name="header" ignore="false"></tiles:insertAttribute>
	</div>
	
	<div>
		<tiles:insertAttribute name="menu" ignore="false"></tiles:insertAttribute>
	</div>
	
	<div class="container" style="margin-top:60px;">
		<tiles:insertAttribute name="body" ignore="false"></tiles:insertAttribute>
	</div>
</body>
</html>