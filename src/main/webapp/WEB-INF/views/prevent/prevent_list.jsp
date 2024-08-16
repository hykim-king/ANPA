<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>화재 예방법 - ANPA</title>
    <!-- 파비콘 추가 -->
    <link rel="icon" type="image/x-icon" href="${CP}/resources/img/favicon.ico">
    <link rel="stylesheet" href="${CP}/resources/css/bootstrap.css">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <!-- Custom Styles -->
    <link rel="stylesheet" href="${CP}/resources/css/basic_style.css">
    <link rel="stylesheet" href="${CP}/resources/css/main_style.css">
    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .container {
            width: 80%;
            margin: 0 auto;
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
    <jsp:include page="/WEB-INF/views/header.jsp" />

    <div class="container">
        <!-- 제목 -->
        <section class="board_con content content2 content3 align-items-center">
            <h3>화재예방법</h3>

            <!-- 검색 폼 -->
            <form action="${CP}/prevent/search.do" method="get" class="row g-2 align-items-center mb-4" id="preventFrm">
                <div class="col-sm-3">
                    <select name="searchType" class="form-select">
                        <option value="">제목 및 내용 전체 검색</option>
                        <option value="title">제목</option>
                        <option value="content">내용</option>
                    </select>
                </div>
                <div class="col-sm-6">
                    <input type="search" name="searchWord" class="form-control" placeholder="검색어 입력" required>
                </div>
                <div class="col-sm-3">
                    <button type="submit" class="btn btn-custom">검색</button>
                </div>
            </form>

            <!-- 페이지 정보 -->
            <div class="page-info mb-4">
                <span>전체 ${totalCount}건</span>, <span>${currentPage}/${totalPages}페이지</span>
            </div>

            <!-- 카드 리스트 -->
            <div class="card-list">
                <c:choose>
                    <c:when test="${list.size() > 0}">
                        <c:forEach var="vo" items="${list}">
                            <div class="card">
                                <p>${vo.preventSeq}</p>
                                <img src="<c:url value='/resources/img/${vo.imgSrc}'/>" alt="이미지">
                                <h4>${vo.title}</h4>
                                <p>${vo.contents}</p>
                                <p>${vo.modDt} 조회수: ${vo.readCnt}</p>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <p>검색 결과가 없습니다.</p>
                    </c:otherwise>
                </c:choose>
            </div>

            <!-- 페이지 네비게이션 -->
            <div class="pagination">
                <c:if test="${currentPage > 1}">
                    <a href="${CP}/prevent/search.do?pageNo=${currentPage - 1}">&laquo;</a>
                </c:if>
                <c:forEach var="page" begin="1" end="${totalPages}">
                    <a href="${CP}/prevent/search.do?pageNo=${page}" class="<c:if test='${page == currentPage}'>active</c:if>">${page}</a>
                </c:forEach>
                <c:if test="${currentPage < totalPages}">
                    <a href="${CP}/prevent/search.do?pageNo=${currentPage + 1}">&raquo;</a>
                </c:if>
            </div>
        </section>
    </div>

    <jsp:include page="/WEB-INF/views/footer.jsp" />
    <script src="${CP}/resources/js/bootstrap.bundle.min.js"></script>
</body>
</html>
