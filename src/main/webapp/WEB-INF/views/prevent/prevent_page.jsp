<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>화재예방법 상세보기 - ANPA</title>
<!-- 파비콘 추가 -->
<link rel="icon" type="image/x-icon" href="${CP}/resources/img/favicon.ico">
<link rel="stylesheet" href="${CP}/resources/css/bootstrap.css">
<!-- Bootstrap Icons -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link rel="stylesheet" href="${CP}/resources/css/basic_style.css">
<link rel="stylesheet" href="${CP}/resources/css/main_style.css">
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 20px;
    }
    .container {
        width: 70%; /* 흰색 박스 크기 줄이기 */
        margin: 0 auto;
        background-color: #fff;
        padding: 15px; /* 내부 여백 조정 */
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
    h1 {
        text-align: center;
        margin-bottom: 15px; /* 제목 마진 조정 */
    }
    .content-section {
        margin-bottom: 20px;
    }
    .content-section h2 {
        margin-top: 0;
    }
    .content-section p {
        color: #666;
        line-height: 1.6;
    }
    .content-footer {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    .btn {
        background-color: #4CAF50;
        color: white;
        padding: 10px 20px;
        border-radius: 5px;
        text-decoration: none;
        transition: background-color 0.3s;
    }
    .btn:hover {
        background-color: #45a049;
    }
</style>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const firstNavLink = document.querySelector('.nav .nav-item:nth-child(3) .nav-link');
        if (firstNavLink) {
            firstNavLink.classList.add('active');
        }
    });
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" />

<div class="container">
    <section class="content-section">
        <h1>${vo.title}</h1>
        <p><strong>제목:</strong> ${vo.title}</p>
        <p><strong>작성일:</strong> ${vo.regDt} | <strong>조회수:</strong> ${vo.readCnt}</p>
        <hr>
        <img src="<c:url value='/resources/page/${vo.contents}'/>" alt="예방법 이미지" style="width: 100%; height: auto; margin-bottom: 20px;">
    </section>

    <div class="content-footer">
        <a href="${CP}/prevent/doRetrieve.do" class="btn">목록으로 돌아가기</a>
    </div>
</div>

<jsp:include page="/WEB-INF/views/footer.jsp" />
<script src="${CP}/resources/js/bootstrap.bundle.min.js"></script>
</body>
</html>
