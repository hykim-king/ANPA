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
<link rel="stylesheet" href="${CP}/resources/css/mRstyle.css">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<title>ANPA</title>
<script>
document.addEventListener('DOMContentLoaded', function() {
    // .nav 클래스의 두 번째 .nav-item의 자식 .nav-link를 선택합니다
    const firstNavLink = document.querySelector('.nav .nav-item:nth-child(2) .nav-link');

    // 선택한 요소에 "active" 클래스를 추가합니다
    firstNavLink.classList.add('active');
});   
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" />

<section class="content content2 content3 align-items-center">
    <h3>한 눈에 보는 화재 현황</h3>

    <form name = "mRfrm" id = "mRfrm" class="row g-1">
        <input type = "hidden" name = "work_div" id = "work_div">
        <input type="hidden" name="page_no" id="page_no" placeholder="페이지 번호">
        <input type = "hidden" name = "seq" id = "seq">
        <div class="col-md-2">            
            <select class="form-select" name="mRselect" id="mRselect">
                <option value="">2024년 07월</option>
            </select>        
        </div>
        <div class="col-md-10 d-flex justify-content-end mRsysdate">
            <p>오늘은 SYSDATE 입니다</p>
        </div>
    </form>

    <div class="row mt-3">
        <div class="col-md-3 mb-2">
            <div class="card text-center">
                <div class="card-body">
                    <b class="card-icon"><i class="bi bi-fire"></i></b>
                    <h5 class="card-title">화재 건수</h5>
                    <p class="card-text">오늘 n 건 / 월 n 건</p>
                    <hr>
                    <b>전년 동기</b>
                    <p class="card-text">오늘 n 건 / 월 n 건</p>
                </div>
            </div>
        </div>
        
        <div class="col-md-3 mb-2">
            <div class="card text-center">
                <div class="card-body">
                    <b class="card-icon"><i class="bi bi-person-x-fill"></i></b>
                    <h5 class="card-title">사망자 수</h5>
                    <p class="card-text">오늘 n 명 / 월 n 명</p>
                    <hr>
                    <b>전년 동기</b>
                    <p class="card-text">오늘 n 명 / 월 n 명</p>
                </div>
            </div>
        </div>
        
        <div class="col-md-3 mb-2">
            <div class="card text-center">
                <div class="card-body">
                    <b class="card-icon"><i class="bi bi-lungs-fill"></i></b>
                    <h5 class="card-title">부상자 수</h5>
                    <p class="card-text">오늘 n 명 / 월 n 명</p>
                    <hr>
                    <b>전년 동기</b>
                    <p class="card-text">오늘 n 명 / 월 n 명</p>
                </div>
            </div>
        </div>
        
        <div class="col-md-3 mb-2">
            <div class="card text-center">
                <div class="card-body">
                    <b class="card-icon"><i class="bi bi-database-fill-x"></i></b>
                    <h5 class="card-title">재산피해</h5>
                    <p class="card-text">오늘 n 천원 / 월 n 천원</p>
                    <hr>
                    <b>전년 동기</b>
                    <p class="card-text">오늘 n 천원 / 월 n 천원</p>
                </div>
            </div>
        </div>
    </div>

    <div class="row mt-3">
        <div class="col-md-4 mb-2">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">화재 장소</h5>
                    <hr>
                    <b><i class="bi bi-1-square-fill"></i> 비주거 n건</b>
                    <p class="card-text">평균 n 건</p>
                    <b><i class="bi bi-2-square-fill"></i> 주거 n건</b>
                    <p class="card-text">평균 n 건</p>
                    <b><i class="bi bi-3-square-fill"></i> 차량 n건</b>
                    <p class="card-text">평균 n 건</p>
                </div>
            </div>
        </div>

        <div class="col-md-4 mb-2">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">화재 장소 소분류</h5>
                    <hr>
                    <b><i class="bi bi-1-square-fill"></i> 상점가 n건</b>
                    <p class="card-text">평균 n 건</p>
                    <b><i class="bi bi-2-square-fill"></i> 아파트 n건</b>
                    <p class="card-text">평균 n 건</p>
                    <b><i class="bi bi-3-square-fill"></i> 공터 n건</b>
                    <p class="card-text">평균 n 건</p>
                </div>
            </div>
        </div>
        
        <div class="col-md-4 mb-2">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">발화 원인</h5>
                    <hr>
                    <b><i class="bi bi-1-square-fill"></i> 전기적 요인 n건</b>
                    <p class="card-text">평균 n 건</p>
                    <b><i class="bi bi-2-square-fill"></i> 부주의 n건</b>
                    <p class="card-text">평균 n 건</p>
                    <b><i class="bi bi-3-square-fill"></i> 기계적 n건</b>
                    <p class="card-text">평균 n 건</p>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="/WEB-INF/views/footer.jsp" />
<script src = "${CP}/resources/js/bootstrap.bundle.min.js"></script>   
</body>
</html>