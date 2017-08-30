<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<div>
	<a href="property/administrator/create.do?q=${fish.id}"><spring:message code="property.add"/></a>
</div>

<br />
<br />

<acme:acme_form type="edit" entity="${fish}" url="fish/administrator/edit.do" numberSteps="0.25" cancel="welcome/index.do">

</acme:acme_form>

<h2><spring:message code="fish.listProperties"/></h2>

<table class="table">
	<jstl:forEach var="e" items="${fish.listProperties}">
		<tr>
			<td>${e.name}</td>
			<td>${e.description}</td>
			<td><a href="property/administrator/delete.do?q=${e.id}&f=${fish.id}"><spring:message code="acme.delete"/></a></td>
		</tr>
	</jstl:forEach>
</table>
