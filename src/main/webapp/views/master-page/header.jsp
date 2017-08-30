<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="Acme-Logistics Co., Inc." />
</div>

<div style="width: 60%">
	<nav class="navbar navbar-default" style="margin-bottom: 0px">
		<div class="container-fluid">
			<div class="navbar-header">
				<ul class="nav navbar-nav">
					<security:authorize access="isAnonymous()">
						<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
					</security:authorize>
					
					<security:authorize access="!isAuthenticated()">
						<li class="dropdown">
							<a class="fNiv" href="fisherman/actor/create.do"><spring:message code="fisherman.create" /></a>
						</li>
						<li class="dropdown">
							<a class="fNiv" href="businessman/actor/create.do"><spring:message code="businessman.create" /></a>
						</li>
						<li class="dropdown">
							<a class="fNiv" href="buyer/actor/create.do"><spring:message code="buyer.create" /></a>
						</li>
						<li class="dropdown">
							<a class="fNiv" href="transporter/actor/create.do"><spring:message code="transporter.create" /></a>
						</li>
					</security:authorize>
					
					<security:authorize access="isAuthenticated()">
						<security:authentication property="principal.id" var="id" />
						<li class="dropdown">
				          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><spring:message code="master.page.profile" /><span class="caret"></span></a>
				          <ul class="dropdown-menu">
							<li><a href="wall/actor/view.do"><spring:message code="actor.wall" /> </a></li>
							<li><a href="offermarket/actor/search.do"><spring:message code="offer.search" /> </a></li>
							<li><a href="fish/actor/list.do"><spring:message code="administrator.list.fish" /> </a></li>
							<li><a href="folder/actor/list.do"><spring:message code="master.page.actor.folders" /> </a></li>
							<li><a href="actor/list.do"><spring:message code="master.page.actor.list" /> </a></li>
							<li><a href="actor/myListFollowers.do"><spring:message code="master.page.actor.followers" /> </a></li>
							<li><a href="actor/myListFolloweds.do"><spring:message code="master.page.actor.followeds" /> </a></li>
							
							<security:authorize access="hasRole('FISHERMAN')">
								<li><a href="fisherman/fisherman/edit.do"><spring:message code="actor.edit" /> </a></li>
								<li><a href="dailycatch/fisherman/create.do"><spring:message code="dailycatch.create" /> </a></li>
								<li><a href="dailycatch/fisherman/list.do"><spring:message code="dailycatch.list" /> </a></li>
							</security:authorize>
							
							<security:authorize access="hasRole('BUSINESSMAN')">
								<li><a href="businessman/businessman/edit.do"><spring:message code="actor.edit" /> </a></li>
								<li><a href="store/businessman/view.do"><spring:message code="businessman.store" /> </a></li>
								<li><a href="businessorder/actor/list.do"><spring:message code="businessman.businessorder" /> </a></li>
							</security:authorize>
							
							<security:authorize access="hasRole('BUYER')">
								<li><a href="buyer/buyer/edit.do"><spring:message code="actor.edit" /> </a></li>
								<li><a href="ticker/buyer/list.do"><spring:message code="buyer.tickers" /> </a></li>
							</security:authorize>
							
							<security:authorize access="hasRole('TRANSPORTER')">
								<li><a href="transporter/transporter/edit.do"><spring:message code="actor.edit" /> </a></li>
								<li><a href="offertransport/transporter/create.do"><spring:message code="transporter.create" /> </a></li>
								<li><a href="offertransport/transporter/list.do"><spring:message code="transporter.list" /> </a></li>
							</security:authorize>
							
							<security:authorize access="hasRole('ADMINISTRATOR')">
								<li><a href="administrator/administrator/edit.do"><spring:message code="actor.edit" /> </a></li>
								<li><a href="fish/administrator/create.do"><spring:message code="administrator.create.fish" /> </a></li>
								<li><a href="spamword/administrator/create.do"><spring:message code="actor.property.create" /> </a></li>
								<li><a href="spamword/administrator/list.do"><spring:message code="actor.property.list" /> </a></li>
								<li><a href="administrator/administrator/dashboard.do"><spring:message code="master.page.administrator.dashboard" /> </a></li>
							</security:authorize>
							
							<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				          </ul>
				        </li>
					</security:authorize>
					
				</ul>
			</div>
		</div>
	</nav>
	<a href="?language=en"> <img src="images/flag_en.png" /> </a>
	<a href="?language=es"> <img src="images/flag_es.png" /> </a>
</div>


