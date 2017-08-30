<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<jstl:if test="${offertransport.available}">
	<a href="offertransport/transporter/delete.do?q=${offertransport.id}">
		<spring:message code="acme.delete"/>
	</a>
	
	<br />
	<br />
</jstl:if>

<acme:acme_view entity="${offertransport}" field_mapping="{measure:value}"></acme:acme_view>
