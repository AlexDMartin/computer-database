<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
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
			<a class="navbar-brand" href="Dashboard"> Application - Computer
				Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${requestCount} Computers found</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="Search" method="GET" class="form-inline">
						<input type="hidden" value="${pageType}" />
						<input type="hidden" value="${lpp}" />
						<input type="hidden" value="${page}" />
						<input type="search" id="searchbox" name="filter"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="Add">Add
						Computer</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="#" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="Delete"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th>Computer name</th>
						<th>Introduced date</th>
						<th>Discontinued date</th>
						<th>Company</th>
					</tr>
				</thead>
				<tbody id="results">
					<c:forEach var="computer" items="${computerList}" varStatus="i">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="0"></td>
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
						<li><a href="Search?filter=${filter}&page=${page-1}&lpp=${lpp}" aria-label="Previous"> <span
							aria-hidden="true">&laquo;</span>
						</a></li>
						<li><a href="Search?filter=${filter}&page=${page}&lpp=${lpp}">${page}</a></li>
						<li><a href="Search?filter=${filter}&page=${page+1}&lpp=${lpp}">${page+1}</a></li>
						<li><a href="Search?filter=${filter}&page=${page+2}&lpp=${lpp}">${page+2}</a></li>
						<li><a href="Search?filter=${filter}&page=${page+3}&lpp=${lpp}">${page+3}</a></li>
						<li><a href="Search?filter=${filter}&page=${page+4}&lpp=${lpp}">${page+4}</a></li>
						<li><a href="Search?filter=${filter}&page=${page+1}&lpp=${lpp}" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</c:when>
					<c:otherwise>
					<li><a href="Dashboard?page=${page-1}&lpp=${lpp}" aria-label="Previous"> <span
							aria-hidden="true">&laquo;</span>
					</a></li>
						<li><a href="Dashboard?page=${page}&lpp=${lpp}">${page}</a></li>
						<li><a href="Dashboard?page=${page+1}&lpp=${lpp}">${page+1}</a></li>
						<li><a href="Dashboard?page=${page+2}&lpp=${lpp}">${page+2}</a></li>
						<li><a href="Dashboard?page=${page+3}&lpp=${lpp}">${page+3}</a></li>
						<li><a href="Dashboard?page=${page+4}&lpp=${lpp}">${page+4}</a></li>
						<li><a href="Dashboard?page=${page+1}&lpp=${lpp}" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
						</a></li>
					</c:otherwise>
				</c:choose>	
			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
			<c:choose>
				<c:when test="${pageType == 'search'}">
					<a href="Search?filter=${filter}&page=${page}&lpp=10"><button type="button" class="btn btn-default">10</button></a>
					<a href="Search?filter=${filter}&page=${page}&lpp=50"><button type="button" class="btn btn-default">50</button></a>
					<a href="Search?filter=${filter}&page=${page}&lpp=100"><button type="button" class="btn btn-default">100</button></a>
				</c:when>
				<c:otherwise>
					<a href="Dashboard?page=${page}&lpp=10"><button type="button" class="btn btn-default">10</button></a>
					<a href="Dashboard?page=${page}&lpp=50"><button type="button" class="btn btn-default">50</button></a>
					<a href="Dashboard?page=${page}&lpp=100"><button type="button" class="btn btn-default">100</button></a>
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