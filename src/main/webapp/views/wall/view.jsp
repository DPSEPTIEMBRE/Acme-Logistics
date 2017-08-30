<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<div>
	<a href="wall/actor/edit.do?q=${wall.id}"><spring:message code="wall.edit"/></a>
</div>

<br />
<br />

<acme:acme_view skip_fields="offer" entity="${wall}">
	<tr>
		<td><spring:message code="wall.offer"/></td>
		<td id="offer"></td>
	</tr>
</acme:acme_view>

<script>
	$(document).ready(function() {
		$.post('offermarket/actor/view/async.do?q=${wall.offer}', function(html) {
			var obj = JSON.parse(html);
			if(obj.status == 200) {
				$('#offer').html(obj.data);
			} else {
				$('#offer').html('');
			}
		});
	});
</script>