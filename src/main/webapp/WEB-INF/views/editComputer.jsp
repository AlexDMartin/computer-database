<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="application.title"/></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="resources/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="resources/css/font-awesome.css" rel="stylesheet"
	media="screen">
<link href="resources/css/main.css" rel="stylesheet"
	media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="/cdb/computers"> <spring:message code="application.title" /> </a>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id:
						${page.getComputer().getId()}</div>
					<h1><spring:message code="editComputer.title" /></h1>

					<form id="editform" action="/computer">
						<input type="hidden" name="_method" value="PATCH"/>
						<input type="hidden" value="${page.getComputer().getId()}" id="id" name="id" />
						<fieldset>
							<div class="form-group">
								<label for="computerName"><spring:message code="editComputer.name" /></label> <input
									type="text" class="form-control" id="computerName"
									name="computerName" value="${page.getComputer().getName()}">
							</div>
							<div class="form-group">
								<label for="introduced"><spring:message code="editComputer.introduced" /></label> <input
									type="date" class="form-control" id="introduced"
									name="introduced" placeholder="Introduced date"
									value="${page.getComputer().getIntroduced()}">
							</div>
							<div class="form-group">
								<label for="discontinued"><spring:message code="editComputer.discontinued" /></label> <input
									type="date" class="form-control" id="discontinued"
									name="discontinued" placeholder="Discontinued date"
									value="${page.getComputer().getDiscontinued()}">
							</div>
							<div class="form-group">
								<label for="companyId"><spring:message code="editComputer.company" /></label> <select
									class="form-control" id="companyId" name="companyId">
									<c:forEach var="company" items="${page.getCompanies()}" varStatus="i">
										<option
											<c:if test = "${page.getComputer().getCompanyDto().getId() == company.getId()}">selected=selected</c:if>
											value="${company.getId()}">${company.getName()}</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="<spring:message code="editComputer.editButton" />" class="btn btn-primary">
							<spring:message code="application.or" /> <a href="/cdb/computers" class="btn btn-default"><spring:message code="application.cancel" /></a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/jquery.validate.min.js"></script>
	<script src="resources/js/validation/EditValidator.js" ></script>
</body>
</html>