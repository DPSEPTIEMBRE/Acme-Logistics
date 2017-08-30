<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:acme_form type="create" skip_fields="offerTransport" hiddenFields="storeIdentif,vat,quantity,measure,price,devilered,fish,day" entity="${businessorder}" url="businessorder/businessman/save.do" numberSteps="0.25" cancel="welcome/index.do">
	<label for="label"><spring:message code='businessorder.offerTransport'/></label>
	<div class="form-group" style="width: 55%;">
		<select class="form-control" name="offerTransport">
			<jstl:forEach items="${offerTransport}" var="e">
				<option value="${e.id}">${e.transporterIdentif}</option>
			</jstl:forEach>
		</select>
	</div>
</acme:acme_form>
