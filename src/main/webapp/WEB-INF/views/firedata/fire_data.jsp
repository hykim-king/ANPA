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
<link rel="stylesheet" href="${CP}/resources/css/fRstyle.css">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<title>ANPA</title>
<script>
document.addEventListener('DOMContentLoaded', function() {
    // .nav 클래스의 첫 번째 .nav-item의 자식 .nav-link를 선택합니다
    const firstNavLink = document.querySelector('.nav .nav-item:first-child .nav-link');

    // 선택한 요소에 "active" 클래스를 추가합니다
    firstNavLink.classList.add('active');
});   
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" />

<section class="content content2 align-items-center">
    <h3>화재통계</h3>

    <form name = "fRfrm" id = "fRfrm" class="row g-1">
        <input type = "hidden" name = "work_div" id = "work_div">
        <input type="hidden" name="page_no" id="page_no" placeholder="페이지 번호">
        <input type = "hidden" name = "seq" id = "seq">
        <select class="me-2 col form-select" name="fRselect" id="fRselect" style="width: 100px;">
            <option value="">화재요인</option>
            <option value="">화재장소</option>
        </select>
        <select class="me-2 col form-select" name="fRselect" id="fRselect">
            <option value="">화재원인</option>
        </select>
        <select class="col form-select" name="fRselect" id="fRselect">
            <option value="">지역</option>
        </select>
    </form>
    <p class="form-control work_div_result">검색 조건 : </p>
    <div class="select_date row g-1 d-flex align-items-center">
        <p class="col-2 m-0"><i class="bi bi-calendar"></i> 검색기간 : </p>
        <select class="col form-select" name="fRdateStart" id="fRdateStart">
            <option value="">시작 날짜</option>
        </select>
        <p class="col-auto m-0">-</p>
        <select class="col form-select" name="fRdateEnd" id="fRdateEnd">
            <option value="">종료 날짜</option>
        </select>
    </div>
    <div class="mt-2 col-auto d-flex justify-content-end">
        <button type="button" class="btn btn-success me-1" id = "doRetrieve">조회</button> 
        <button type="button" class="btn btn-secondary" id = "doRetrieve">초기화</button> 
    </div>
</section>

<jsp:include page="/WEB-INF/views/footer.jsp" />
<script src = "${CP}/resources/js/bootstrap.bundle.min.js"></script>        
</body>
</html>