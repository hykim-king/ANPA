<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<c:set var="CP"  value="${pageContext.request.contextPath}"  />
<!DOCTYPE html>
<html lang="kor">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<head>
  <!-- 파비콘 추가 -->
    <link rel="icon" type="image/x-icon" href="${CP}/resources/img/favicon.ico">
    <link rel="stylesheet" href="${CP}/resources/css/bootstrap.css">
    <!-- bootstrap icon -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <!-- 기본 스타일 -->
    <link rel="stylesheet" href="${CP}/resources/css/basic_style.css">
    <link rel="stylesheet" href="${CP}/resources/css/main_style.css">
    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<title>ANPA | 회원가입</title>
</head>
<body>
    <!-- 헤더 포함 -->
    <jsp:include page="/WEB-INF/views/header.jsp" />

     <!-- 푸터 포함 -->
    <jsp:include page="/WEB-INF/views/footer.jsp" />
    
    <script src="${CP}/resources/js/bootstrap.bundle.min.js"></script>    
</body>
</html>
