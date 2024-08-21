<%@page import="com.acorn.anpa.cmn.StringUtil"%>
<%@page import="com.acorn.anpa.cmn.Search"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="CP" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>화재 예방법 - ANPA</title>
<!-- 파비콘 추가 -->
<link rel="icon" type="image/x-icon"
	href="${CP}/resources/img/favicon.ico">
<link rel="stylesheet" href="${CP}/resources/css/bootstrap.css">
<!-- Bootstrap Icons -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<!-- Custom Styles -->
<link rel="stylesheet" href="${CP}/resources/css/basic_style.css">
<link rel="stylesheet" href="${CP}/resources/css/main_style.css">
<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
document.addEventListener('DOMContentLoaded', function() {

    const test = "${search}";
    console.log(test);
    // .nav 클래스의 3번째 .nav-item의 자식 .nav-link를 선택합니다
    const firstNavLink = document.querySelector('.nav .nav-item:nth-child(3) .nav-link');

    // 선택한 요소에 "active" 클래스를 추가합니다
    firstNavLink.classList.add('active');
    
});
</script>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
}

.container {
	width: 80%;
	margin: 50px auto;
	background-color: #fff;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h1 {
	text-align: center;
	margin-bottom: 20px;
}

.card-list {
	display: grid;
	grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
	gap: 20px;
}

.card {
	background-color: #f9f9f9;
	border-radius: 8px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	overflow: hidden;
	text-align: center;
	transition: transform 0.2s;
}

.card:hover {
	transform: translateY(-5px);
}

.card img {
	width: 100%;
	height: auto;
}

.card h4 {
	margin: 15px 0 10px;
}

.card p {
	padding: 0 15px 15px;
	color: #666;
}

.pagination {
	text-align: center;
	margin-top: 20px;
}

.pagination a {
	color: black;
	float: left;
	padding: 8px 16px;
	text-decoration: none;
	border: 1px solid #ddd;
	margin: 0 4px;
	border-radius: 5px;
}

.pagination a:hover {
	background-color: #ddd;
	color: black;
}

.pagination a.active {
	background-color: #4CAF50;
	color: white;
	border: 1px solid #4CAF50;
}
/* Custom Style for Search Button */
.btn-custom {
	background-color: #6c757d; /* Gray color */
	color: white;
	border: none;
}

.btn-custom:hover {
	background-color: #5a6268; /* Darker gray on hover */
}
</style>
</head>
<body>
	<script>
		function pageRetrieve(url, pageNo) {
			const frm = document.querySelector("#preventFrm");
			frm.pageNo.value = pageNo;
			console.log("pageNo: " + pageNo);

			frm.action = url;
			frm.submit();
		}
		document.addEventListener("DOMContentLoaded", function() {
			const doRetrieveBtn = document.querySelector("#doRetrieveBtn");
			doRetrieveBtn.addEventListener("click", function(event) {
				event.stopPropagation();
				doRetrieve();
			});

			function doRetrieve() {
				console.log("doRetrieve()");
				const frm = document.querySelector("#preventFrm");

				frm.action = "/ehr/prevent/doRetrieve.do";
				frm.submit();
			}
		});
	</script>

	<jsp:include page="/WEB-INF/views/header.jsp" />
	<div class="container">
		<!-- 제목 -->
		<section
			class="board_con content content2 content3 align-items-center">
			<h3>화재예방법</h3>
			<!-- 검색 폼 -->
			<form action="${CP}/prevent/search.do" method="get"
				class="row g-2 align-items-center mb-4" id="preventFrm">
				<input type="text" class="d-none" id="pageNo" name="pageNo">
				<div class="col-sm-3">
					<select class="form-select" name="searchDiv" id="searchDiv">
						<c:forEach var="item" items="${BOARD_SEARCH}">
							<option value="${item.subCode}"
								<c:if test="${item.subCode == search.searchDiv}">selected</c:if>>${item.midList}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-sm-6">
					<input type="search" name="searchWord" id="searchWord"
						class="form-control" placeholder="검색어 입력" required>
				</div>
				<div class="col-sm-3">
					<button id="doRetrieveBtn" type="button" class="btn btn-custom">
						<i class="bi bi-search"></i> 검색
					</button>
				</div>
			</form>
			<!-- 페이지 정보 -->
			<div class="page-info mb-4">
				<span>전체 ${totalCnt}건</span>, <span>${currentPage}/${totalPages}페이지</span>
			</div>

			<!-- 카드 리스트 -->
			<div class="card-list">
				<c:choose>
					<c:when test="${list.size() > 0}">
						<c:forEach var="vo" items="${list}">
							<div class="card">
								<p class="d-none">
									<a
										href="<c:url value='/prevent/doSelectOne.do?preventSeq=${vo.preventSeq}'/>">${vo.preventSeq}</a>
								</p>
								<!-- 이미지에 대한 링크 -->
								<a
									href="<c:url value='/prevent/doSelectOne.do?preventSeq=${vo.preventSeq}'/>">
									<img src="<c:url value='/resources/img/${vo.imgSrc}'/>"
									alt="이미지">
								</a>
								<h4>${vo.title}</h4>
								<p>${vo.contents}</p>
								<p>${vo.modDt}조회수: ${vo.readCnt}</p>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<p>검색 결과가 없습니다.</p>
					</c:otherwise>
				</c:choose>
			</div>


			<!-- pagenation -->
			<div class="text-center">
				<div id="page-selection" class="text-center page">
					<%
						//총글수 :
					int maxNum = (int) request.getAttribute("totalCnt");

					Search search = (Search) request.getAttribute("search");
					//페이지 번호:
					int currentPageNo = search.getPageNo();
					//페이지 사이즈:
					int rowPerPage = search.getPageSize();
					//바닥글 :
					int bottomCount = search.BOTTOM_COUNT;

					String url = "/ehr/prevent/doRetrieve.do";
					String scriptName = "pageRetrieve";

					out.print(StringUtil.renderingPaging(maxNum, currentPageNo, rowPerPage, bottomCount, url, scriptName));
					%>
				</div>
			</div>
			<!--// pagenation -->
		</section>
	</div>
	<jsp:include page="/WEB-INF/views/footer.jsp" />
	<script src="${CP}/resources/js/bootstrap.bundle.min.js"></script>
</body>
</html>
