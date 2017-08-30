<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<security:authorize access="hasRole('ADMINISTRATOR')">

	<!-- avgMaxMinMessagesSentByActor -->
	<b><spring:message code="administrator.avgMessagesSentByActor" />
	</b>
	<jstl:out value="${avgMaxMinMessagesSentByActor[0]}" />
	<br />
	<b><spring:message code="administrator.maxMessagesSentByActor" />
	</b>
	<jstl:out value="${avgMaxMinMessagesSentByActor[1]}" />
	<br />
	<b><spring:message code="administrator.minMessagesSentByActor" />
	</b>
	<jstl:out value="${avgMaxMinMessagesSentByActor[2]}" />
	<br />

	<!-- avgMaxMinMessagesReceivedByActor -->
	<b><spring:message code="administrator.avgMessagesReceivedByActor" />
	</b>
	<jstl:out value="${avgMaxMinMessagesReceivedByActor[0]}" />
	<br />
	<b><spring:message code="administrator.maxMessagesReceivedByActor" />
	</b>
	<jstl:out value="${avgMaxMinMessagesReceivedByActor[1]}" />
	<br />
	<b><spring:message code="administrator.minMessagesReceivedByActor" />
	</b>
	<jstl:out value="${avgMaxMinMessagesReceivedByActor[2]}" />
	<br />
	
	<!-- avgMaxMinMarketsPublishe -->
	<b><spring:message code="administrator.avgMarketsPublishe" />
	</b>
	<jstl:out value="${avgMaxMinMarkets[0]}" />
	<br />
	<b><spring:message code="administrator.maxMarketsPublishe" />
	</b>
	<jstl:out value="${avgMaxMinMarkets[1]}" />
	<br />
	<b><spring:message code="administrator.minMarketsPublishe" />
	</b>
	<jstl:out value="${avgMaxMinMarkets[2]}" />
	<br />

	<!-- avgMaxMinMarketsPublisheDaily -->
	<b><spring:message code="administrator.avgMarketsPublisheDaily" />
	</b>
	<jstl:out value="${avgMaxMinMarketsPublisheDaily[0]}" />
	<br />
	<b><spring:message code="administrator.maxMarketsPublisheDaily" />
	</b>
	<jstl:out value="${avgMaxMinMarketsPublisheDaily[1]}" />
	<br />
	<b><spring:message code="administrator.minMarketsPublisheDaily" />
	</b>
	<jstl:out value="${avgMaxMinMarketsPublisheDaily[2]}" />
	<br />

	<!-- ratioStaticMarkets -->
	<b><spring:message code="administrator.ratioStaticMarkets" />
	</b>
	<jstl:out value="${ratioStaticMarkets}" />
	<br />
	
	<!-- numberFisherman -->
	<b><spring:message code="administrator.numberFisherman" />
	</b>
	<jstl:out value="${numberFisherman}" />
	<br />
	
	<!-- numberBuyer -->
	<b><spring:message code="administrator.numberBuyer" />
	</b>
	<jstl:out value="${numberBuyer}" />
	<br />
	
	<!-- numberBussinesman -->
	<b><spring:message code="administrator.numberBussinesman" />
	</b>
	<jstl:out value="${numberBussinesman}" />
	<br />
	
	<!-- numberTransporter -->
	<b><spring:message code="administrator.numberTransporter" />
	</b>
	<jstl:out value="${numberTransporter}" />
	<br />
	
	<!-- avgMaxMinOrders -->
	<b><spring:message code="administrator.avgOrders" /> </b>
	<jstl:out value="${avgMaxMinOrders[0]}" />
	<br />
	<b><spring:message code="administrator.maxOrders" /> </b>
	<jstl:out value="${avgMaxMinOrders[1]}" />
	<br />
	<b><spring:message code="administrator.minOrders" /> </b>
	<jstl:out value="${avgMaxMinOrders[2]}" />
	<br />
	
	<!-- avgMaxMinOrdersDaily -->
	<b><spring:message code="administrator.avgOrdersDaily" /> </b>
	<jstl:out value="${avgMaxMinOrdersDaily[0]}" />
	<br />
	<b><spring:message code="administrator.maxOrdersDaily" /> </b>
	<jstl:out value="${avgMaxMinOrdersDaily[1]}" />
	<br />
	<b><spring:message code="administrator.minOrdersDaily" /> </b>
	<jstl:out value="${avgMaxMinOrdersDaily[2]}" />
	<br />

	<!-- avgMaxMinPropertiesFish -->
	<b><spring:message code="administrator.avgPropertiesFish" /> </b>
	<jstl:out value="${avgMaxMinPropertiesFish[0]}" />
	<br />
	<b><spring:message code="administrator.maxPropertiesFish" /> </b>
	<jstl:out value="${avgMaxMinPropertiesFish[1]}" />
	<br />
	<b><spring:message code="administrator.minPropertiesFish" /> </b>
	<jstl:out value="${avgMaxMinPropertiesFish[2]}" />
	<br />


</security:authorize>