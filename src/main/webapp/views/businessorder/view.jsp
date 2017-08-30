<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<jstl:if test="${!businessorder.devilered}">
	<a href="businessorder/businessman/delivered.do?q=${businessorder.id}"><spring:message code="businessorder.set.devilered"/></a>
	
	<br />
	<br />
</jstl:if>

<acme:acme_view skip_fields="fish" entity="${businessorder}" field_mapping="{measure:value}">
	<tr>
	<td><spring:message code="businessorder.fish"/></td>
	<td>
		<table class="table">
			<jstl:forEach var="e" items="${businessorder.fish}">
				<tr>
					<td>${e.name}</td>
				</tr>
			</jstl:forEach>
		</table>
	</td>
</tr>
</acme:acme_view>
