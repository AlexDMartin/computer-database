<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="application.title"/></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
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
			<a class="navbar-brand" href="Dashboard"> <spring:message code="application.title"/> </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle"><spring:message code="dashboard.computersFound" arguments="${count}"/></h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="Search" method="GET" class="form-inline">
						<input type="hidden" name="request" value="new" />
						<input type="hidden" name="lpp" value="${paginationController.getLinePerPage()}" />
						<input type="hidden" name="page" value="${paginationController.getPage()}" />
						<input type="search" id="searchbox" name="filter"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="<spring:message code="dashboard.searchButton" />"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="Add"><spring:message code="dashboard.addComputerButton" /> </a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();"><spring:message code="dashboard.editComputerButton" /></a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="Delete" method="POST">
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
						<c:choose>
							<c:when test="${pageType == 'search'}">
								<th><a href="Search?filter=${filter}&page=${paginationController.getPage()}&lpp=${paginationController.getLinePerPage()}&col=NAME&asc=${paginationController.getInvertedAscendency(paginationController.getAscendency())}"><spring:message code="dashboard.computerNameColumn" /></a></th>
								<th><a href="Search?filter=${filter}&page=${paginationController.getPage()}&lpp=${paginationController.getLinePerPage()}&col=INTRODUCED&asc=${paginationController.getInvertedAscendency(paginationController.getAscendency())}"><spring:message code="dashboard.introducedColumn" /></a></th>
								<th><a href="Search?filter=${filter}&page=${paginationController.getPage()}&lpp=${paginationController.getLinePerPage()}&col=DISCONTINUED&asc=${paginationController.getInvertedAscendency(paginationController.getAscendency())}"><spring:message code="dashboard.discontinuedColumn" /></a></th>
								<th><spring:message code="dashboard.companyColumn" /></th>
							</c:when>
							<c:otherwise>
								<th><a href="Dashboard?page=${paginationController.getPage()}&lpp=${paginationController.getLinePerPage()}&col=NAME&asc=${paginationController.getInvertedAscendency(paginationController.getAscendency())}"><spring:message code="dashboard.computerNameColumn" /></a></th>
								<th><a href="Dashboard?page=${paginationController.getPage()}&lpp=${paginationController.getLinePerPage()}&col=INTRODUCED&asc=${paginationController.getInvertedAscendency(paginationController.getAscendency())}"><spring:message code="dashboard.introducedColumn" /></a></th>
								<th><a href="Dashboard?page=${paginationController.getPage()}&lpp=${paginationController.getLinePerPage()}&col=DISCONTINUED&asc=${paginationController.getInvertedAscendency(paginationController.getAscendency())}"></a></th>
								<th><spring:message code="dashboard.companyColumn" /></th>
							</c:otherwise>
						</c:choose>
					
					</tr>
				</thead>
				<tbody id="results">
					<c:forEach var="computer" items="${computerList}" varStatus="i">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.getId()}"></td>
							<td><a href="Edit?id=${computer.getId()}" onclick="">${computer.getName()}</a>
							</td>
							<td>${computer.getIntroduced()}</td>
							<td>${computer.getDiscontinued()}</td>
							<td>${computer.getCompany().getName()}</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<c:choose>
					<c:when test="${pageType == 'search'}">
						<li><a href="Search?filter=${filter}&page=${paginationController.getPrevious()}&lpp=${paginationController.getLinePerPage()}&col=${paginationController.getSortColumn()}&asc=${paginationController.getAscendency()}" aria-label="Previous"> <span
							aria-hidden="true">&laquo;</span>
						</a></li>
						<li><a href="Search?filter=${filter}&page=${paginationController.getPages()[0]}&lpp=${paginationController.getLinePerPage()}&col=${paginationController.getSortColumn()}&asc=${paginationController.getAscendency()}">${paginationController.getPages()[0]}</a></li>
						<li><a href="Search?filter=${filter}&page=${paginationController.getPages()[1]}&lpp=${paginationController.getLinePerPage()}&col=${paginationController.getSortColumn()}&asc=${paginationController.getAscendency()}">${paginationController.getPages()[1]}</a></li>
						<li><a href="Search?filter=${filter}&page=${paginationController.getPages()[2]}&lpp=${paginationController.getLinePerPage()}&col=${paginationController.getSortColumn()}&asc=${paginationController.getAscendency()}">${paginationController.getPages()[2]}</a></li>
						<li><a href="Search?filter=${filter}&page=${paginationController.getPages()[3]}&lpp=${paginationController.getLinePerPage()}&col=${paginationController.getSortColumn()}&asc=${paginationController.getAscendency()}">${paginationController.getPages()[3]}</a></li>
						<li><a href="Search?filter=${filter}&page=${paginationController.getPages()[4]}&lpp=${paginationController.getLinePerPage()}&col=${paginationController.getSortColumn()}&asc=${paginationController.getAscendency()}">${paginationController.getPages()[4]}</a></li>
						<li><a href="Search?filter=${filter}&page=${paginationController.getNext()}&lpp=${paginationController.getLinePerPage()}&col=${paginationController.getSortColumn()}&asc=${paginationController.getAscendency()}" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</c:when>
					<c:otherwise>
					<li><a href="Dashboard?page=${paginationController.getPrevious()}&lpp=${paginationController.getLinePerPage()}&col=${paginationController.getSortColumn()}" aria-label="Previous"> <span
							aria-hidden="true">&laquo;</span>
					</a></li>
						<li><a href="Dashboard?page=${paginationController.getPages()[0]}&lpp=${paginationController.getLinePerPage()}&col=${paginationController.getSortColumn()}&asc=${paginationController.getAscendency()}">${paginationController.getPages()[0]}</a></li>
						<li><a href="Dashboard?page=${paginationController.getPages()[1]}&lpp=${paginationController.getLinePerPage()}&col=${paginationController.getSortColumn()}&asc=${paginationController.getAscendency()}">${paginationController.getPages()[1]}</a></li>
						<li><a href="Dashboard?page=${paginationController.getPages()[2]}&lpp=${paginationController.getLinePerPage()}&col=${paginationController.getSortColumn()}&asc=${paginationController.getAscendency()}">${paginationController.getPages()[2]}</a></li>
						<li><a href="Dashboard?page=${paginationController.getPages()[3]}&lpp=${paginationController.getLinePerPage()}&col=${paginationController.getSortColumn()}&asc=${paginationController.getAscendency()}">${paginationController.getPages()[3]}</a></li>
						<li><a href="Dashboard?page=${paginationController.getPages()[4]}&lpp=${paginationController.getLinePerPage()}&col=${paginationController.getSortColumn()}&asc=${paginationController.getAscendency()}">${paginationController.getPages()[4]}</a></li>
						<li><a href="Dashboard?page=${paginationController.getNext()}&lpp=${paginationController.getLinePerPage()}&col=${paginationController.getSortColumn()}&asc=${paginationController.getAscendency()}" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</c:otherwise>
				</c:choose>	
			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
			<c:choose>
				<c:when test="${pageType == 'search'}">
					<a href="Search?filter=${filter}&page=${paginationController.getPage()}&lpp=10&col=${paginationController.getSortColumn()}"><button type="button" class="btn btn-default">10</button></a>
					<a href="Search?filter=${filter}&page=${paginationController.getPage()}&lpp=50&col=${paginationController.getSortColumn()}"><button type="button" class="btn btn-default">50</button></a>
					<a href="Search?filter=${filter}&page=${paginationController.getPage()}&lpp=100&col=${paginationController.getSortColumn()}"><button type="button" class="btn btn-default">100</button></a>
				</c:when>
				<c:otherwise>
					<a href="Dashboard?page=${paginationController.getPage()}&lpp=10&col=${paginationController.getSortColumn()}"><button type="button" class="btn btn-default">10</button></a>
					<a href="Dashboard?page=${paginationController.getPage()}&lpp=50&col=${paginationController.getSortColumn()}"><button type="button" class="btn btn-default">50</button></a>
					<a href="Dashboard?page=${paginationController.getPage()}&lpp=100&col=${paginationController.getSortColumn()}"><button type="button" class="btn btn-default">100</button></a>
				</c:otherwise>
			</c:choose>
				
			</div>
		</div>
	</footer>
	<script src="<c:url value="/js/jquery.min.js" />"></script>
	<script src="<c:url value="/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/js/dashboard.js" />"></script>

</body>
</html>