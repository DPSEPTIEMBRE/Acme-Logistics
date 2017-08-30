<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<jstl:if test="${store != null}">
	<acme:acme_view entity="${store}" skip_fields="comments,products">
		<tr>
			<td><spring:message code="store.products"/></td>
			<td>
				<table class="table">
					<jstl:forEach var="e" items="${store.products}">
						<tr>
							<td>${e.fish.name}</td>
							<td>${e.quantity} ${e.measure.value}</td>
							<td>${e.price} euro</td>
							<td><a href="product/actor/view.do?q=${e.id}"><spring:message code="product.view"/></a></td>
						</tr>
					</jstl:forEach>
				</table>
			</td>
		</tr>
		<tr>
			<td><spring:message code="store.comments"/></td>
			<td>
				<acme:list requestURI="store/businessman/view.do" list="${store.comments}"></acme:list>
			</td>
		</tr>
	</acme:acme_view>
	<div>
		<a href="comment/actor/create.do?q=${store.id}"><spring:message code="comment.create"/></a>
	</div>
</jstl:if>

<jstl:if test="${store == null}">
	<security:authorize access="hasRole('BUSINESSMAN')">
		<a href="store/businessman/create.do"><spring:message code="store.create"/></a>
	</security:authorize>
</jstl:if>