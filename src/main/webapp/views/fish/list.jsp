<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:list hidden_fields="listProperties" variable="e" entityUrl="{listProperties:property/list.do}" list="${fish}" viewUrl="fish/actor/view.do" requestURI="fish/list.do" pagesize="6">
	<security:authorize access="hasRole('ADMINISTRATOR')">
		<a href="fish/administrator/edit.do?q=${e.id}"><spring:message code="acme.edit"/></a>
	</security:authorize>
</acme:list>
