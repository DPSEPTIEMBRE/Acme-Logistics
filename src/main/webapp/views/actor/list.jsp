<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<spring:message code="actor.follow" var="follow"/> 

<acme:list list="${actores}"
	hidden_fields="numberidentif,city,phone,postalAddress,email,userAccount,creditCard,folders,follower,followed,wall,orders,store,tickers,dailyCatchs,offerMarkets,offerTransport"
	requestURI="actor/list.do" pagesize="20" variable="e">
	<security:authentication property="principal.id" var="id" />
	<jstl:if test="${!me.followed.contains(e) and e.userAccount.id != id}">
		<a href="actor/follow.do?q=${e.id}">${follow}</a>
	</jstl:if>
</acme:list>