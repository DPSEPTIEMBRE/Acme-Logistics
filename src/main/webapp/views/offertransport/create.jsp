<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:acme_form numberMin="0" type="create" hiddenFields="transporterIdentif,available" skip_fields="measure" entity="${offertransport}" url="offertransport/transporter/save.do" numberSteps="0.25" cancel="welcome/index.do">
	<label for="label"><spring:message code='offertransport.measure'/></label>
	<div class="form-group" style="width: 55%;">
		<select class="form-control" name="measure">
			<option value="KG">KG</option>
			<option value="POUND">POUND</option>
		</select>
	</div>
</acme:acme_form>
