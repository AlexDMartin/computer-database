<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet"
	media="screen">
<link href="<c:url value="/css/font-awesome.css" />" rel="stylesheet"
	media="screen">
<link href="<c:url value="/css/main.css" />" rel="stylesheet"
	media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="Dashboard"> <spring:message code="application.title" /> </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1><spring:message code="addComputer.title" /></h1>
					<form id="addform"  action="Add" method="POST">
						<fieldset>
							<div class="form-group">
								<label for="computerName"><spring:message code="addComputer.namePlaceHolder" /></label> <input
									type="text" class="form-control" id="computerName"
									placeholder="Computer name" name="computerName">
							</div>
							<div class="form-group">
								<label for="introduced"><spring:message code="addComputer.introduced" /></label> <input
									type="date" class="form-control" id="introduced"
									placeholder="Introduced date" name="introduced" min="1970-01-01">
							</div>
							<div class="form-group">
								<label for="discontinued"><spring:message code="addComputer.discontinued" /></label> <input
									type="date" class="form-control" id="discontinued"
									placeholder="Discontinued date" name="discontinued" min="1970-01-01">
							</div>
							<div class="form-group">
								<label for="companyId"><spring:message code="addComputer.company" /></label> <select
									class="form-control" id="companyId" name="companyId">
									<c:forEach var="company" items="${companyList}" varStatus="i">
										<option value="${company.getId()}">${company.getName()}</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="<spring:message code="addComputer.addButton" />" class="btn btn-primary">
							<spring:message code="application.or" /> <a href="Dashboard" class="btn btn-default"><spring:message code="application.cancel" /></a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<script src="<c:url value="/js/jquery.min.js" />" ></script>
	<script src="<c:url value="/js/jquery.validate.min.js" />"></script>
	<script src="<c:url value="/js/validation/AddValidator.js" />" ></script>
</body>
</html>