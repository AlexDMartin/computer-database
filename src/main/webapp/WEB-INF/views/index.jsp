<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="application.title"/></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
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
			<a class="navbar-brand" href=""> <spring:message code="application.title"/> </a>
		</div>
	</header>

	<span style="float: right;"><spring:message code="lang" /> : <br /><a href="?lang=en"><spring:message code="lang.en" /></a> | <a href="?lang=fr"><spring:message code="lang.fr" /></a></span>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle"><spring:message code="dashboard.computersFound" arguments="${page.getCount()}"/></h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="/cdb/computers" method="GET" class="form-inline">
						<input type="hidden" name="request" value="new" />
						<input type="hidden" name="lpp" value="${page.getLinePerPage()}" />
						<input type="hidden" name="page" value="${page.getPageIndex()}" />
						<input type="search" id="searchbox" name="filter"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="<spring:message code="dashboard.searchButton" />"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="/cdb/computer"><spring:message code="dashboard.addComputerButton" /> </a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();"><spring:message code="dashboard.editComputerButton" /></a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="/computer" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
							<th><a href="?filter=${filter}&page=${page.getPageIndex()}&lpp=${page.getLinePerPage()}&col=NAME&asc=${page.getInvertedAscendency(page.getAscendency())}"><spring:message code="dashboard.computerNameColumn" /></a></th>
							<th><a href="?filter=${filter}&page=${page.getPageIndex()}&lpp=${page.getLinePerPage()}&col=INTRODUCED&asc=${page.getInvertedAscendency(page.getAscendency())}"><spring:message code="dashboard.introducedColumn" /></a></th>
							<th><a href="?filter=${filter}&page=${page.getPageIndex()}&lpp=${page.getLinePerPage()}&col=DISCONTINUED&asc=${page.getInvertedAscendency(page.getAscendency())}"><spring:message code="dashboard.discontinuedColumn" /></a></th>
							<th><spring:message code="dashboard.companyColumn" /></th>					
					</tr>
				</thead>
				<tbody id="results">
					<c:forEach var="computer" items="${page.getComputers()}" varStatus="i">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.getId()}"></td>
							<td><a href="/cdb/computer/${computer.getId()}" onclick="">${computer.getName()}</a>
							</td>
							<td>${computer.getIntroduced()}</td>
							<td>${computer.getDiscontinued()}</td>
							<td>${computer.getCompanyDto().getName()}</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<li><a href="?page=${page.getPrevious()}&lpp=${page.getLinePerPage()}&col=${page.getSortColumn()}" aria-label="Previous"> <span
						aria-hidden="true">&laquo;</span>
				</a></li>
					<li><a href="?page=${page.getPages()[0]}&lpp=${page.getLinePerPage()}&col=${page.getSortColumn()}&asc=${page.getAscendency()}">${page.getPages()[0]}</a></li>
					<li><a href="?page=${page.getPages()[1]}&lpp=${page.getLinePerPage()}&col=${page.getSortColumn()}&asc=${page.getAscendency()}">${page.getPages()[1]}</a></li>
					<li><a href="?page=${page.getPages()[2]}&lpp=${page.getLinePerPage()}&col=${page.getSortColumn()}&asc=${page.getAscendency()}">${page.getPages()[2]}</a></li>
					<li><a href="?page=${page.getPages()[3]}&lpp=${page.getLinePerPage()}&col=${page.getSortColumn()}&asc=${page.getAscendency()}">${page.getPages()[3]}</a></li>
					<li><a href="?page=${page.getPages()[4]}&lpp=${page.getLinePerPage()}&col=${page.getSortColumn()}&asc=${page.getAscendency()}">${page.getPages()[4]}</a></li>
					<li><a href="?page=${page.getNext()}&lpp=${page.getLinePerPage()}&col=${page.getSortColumn()}&asc=${page.getAscendency()}" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a></li>
			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
					<a href="?filter=${filter}&page=${page.getPageIndex()}&lpp=10&col=${page.getSortColumn()}"><button type="button" class="btn btn-default">10</button></a>
					<a href="?filter=${filter}&page=${page.getPageIndex()}&lpp=50&col=${page.getSortColumn()}"><button type="button" class="btn btn-default">50</button></a>
					<a href="?filter=${filter}&page=${page.getPageIndex()}&lpp=100&col=${page.getSortColumn()}"><button type="button" class="btn btn-default">100</button></a>				
			</div>
		</div>
	</footer>
	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/dashboard.js"></script>

</body>
</html>