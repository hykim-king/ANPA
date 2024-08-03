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
<!-- bootstrap icon -->
<link rel="stylesheet" href="${CP}/resources/css/basic_style.css">
<link rel="stylesheet" href="${CP}/resources/css/board_style.css">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<title>ANPA</title>
<script>
document.addEventListener('DOMContentLoaded', function() {
    // .nav 클래스의 4번째 .nav-item의 자식 .nav-link를 선택합니다
    const firstNavLink = document.querySelector('.nav .nav-item:nth-child(4) .nav-link');

    // 선택한 요소에 "active" 클래스를 추가합니다
    firstNavLink.classList.add('active');
});   
</script>
</head>
<body>
<jsp:include page="${CP}/WEB-INF/views/header.jsp" />

<section class="board_info content content2 content3 align-items-center">
    <h3>공지사항</h3>
    
    <table class="table table-bordered">
        <thead>
            <tr>
                <th style="display: none;">${seq}</th>           
                <th class="table-dark col-md-2">제목</th>
                <td colspan="3">${title}</td>                
            </tr>
        </thead>
        <tbody>
            <tr>   
                <th class="table-dark col-md-2">수정자</th>
                <td class="col-md-auto">${regId}</td>
                <th class="table-dark col-md-2">수정일</th>
                <td class="col-md-auto">${regDt}</td>
            </tr>
            <tr>
               <td class="table_info" colspan="4">${contents}</td> 
            </tr>        
        </tbody>
    </table>
    <div class="d-flex justify-content-end">
        <p class="table-btn btn btn-success">수정</p>                
        <p class="table-btn btn btn-danger">삭제</p>   
    </div>
</section>

<jsp:include page="${CP}/WEB-INF/views/footer.jsp" />
<script src = "${CP}/resources/js/bootstrap.bundle.min.js"></script>     
</body>
</html>