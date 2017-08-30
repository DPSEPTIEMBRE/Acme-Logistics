<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<security:authorize access="hasRole('ADMINISTRATOR')">
	<div><a href="fish/administrator/edit.do?q=${fish.id}"><spring:message code="acme.edit"/></a></div>
	<br />
	<br />
</security:authorize>

<acme:acme_view entity="${fish}" skip_fields="listProperties">
	<tr>
		<td><spring:message code="fish.listProperties"/></td>
		<td>
			<table class="table">
				<jstl:forEach var="e" items="${fish.listProperties}">
					<tr>
						<td>${e.name}</td>
						<td>${e.description}</td>
					</tr>
				</jstl:forEach>
			</table>
		</td>
	</tr>
</acme:acme_view>