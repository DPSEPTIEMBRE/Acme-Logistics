<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<div class="form-group" style="width: 35%;">
	<input id="in_search" placeholder="<spring:message code="offermarket.search"/>" name="q" type="search" class="form-control">
</div>

<button onclick="search()" id="bt_search" class="btn btn-primary"><spring:message code="acme.search"/></button>

<hr />

<div id="results"></div>

<script>
	function search() {
		$.post('offermarket/actor/search/async.do?q=' + $('#in_search').val(), function(html) {
			var obj = JSON.parse(html);
			
			if(obj.status == 200) {
				$('#results').html(obj.data);
			} else {
				$('#results').html('');
			}
		});
	}
	
	$("#in_search").keyup(function(event){
	    if(event.keyCode == 13){
	        $("#bt_search").click();
	    }
	});
</script>