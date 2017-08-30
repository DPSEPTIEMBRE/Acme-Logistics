<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<acme:list hidden_fields="fish,offerTransport" field_mapping="{measure:value}" entityUrl="{fish:fish/view.do, offerTransport:offertransport/view.do}" list="${businessorder}" viewUrl="businessorder/actor/view.do" requestURI="businessorder/list.do" pagesize="6"></acme:list>
