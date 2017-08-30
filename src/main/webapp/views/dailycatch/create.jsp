<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:acme_form type="create" hiddenFields="fisherman" skip_fields="measure,fish"
	entity="${dailycatch}" url="dailycatch/fisherman/save.do"
	numberSteps="0.25" cancel="welcome/index.do">
	
	<div class="form-group" style="width: 55%;">
			<label for="label"><spring:message code='dailycatch.measure' /></label>
			<select name="measure">
				<option value="KG">KG</option>
				<option value="POUND">POUND</option>
			</select>
		</div>
		<div class="form-group" style="width: 55%;">
			<label for="label"><spring:message code="dailycatch.fish" /> </label>

			<select name="fish">
				<jstl:forEach var="s" items="${fishes}">
					<option value="${s.id}">${s.name}</option>
				</jstl:forEach>
			</select>
			
		</div>
	
</acme:acme_form>
