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
<link rel="stylesheet" href="${CP}/resources/css/main_style.css">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<title>ANPA</title>
<script>
document.addEventListener('DOMContentLoaded', function() {
    // header start
    const appMenus = document.querySelector('.appmenu');    
    
    appMenus.addEventListener('click', function(){
        console.log("상단 앱 메뉴 클릭");
    });
    // header end

    function updateMainTitleBox() {
        const mainTitleBox = document.querySelector('.main_title_box');

        const contentHTML =
        '<p class="main_title">' + '대한민국의 미래 ANPA가 함께합니다' + '</p>' +
        '<p class="main_sub_title">' + 'ANPA 시스템을 소개합니다' + '</p>' +
        '<p class="main_fd1">화재건수 : ' + '${}' + '</p>' +
        '<p class="main_fd2">인명피해 : ' + '${}' + '</p>' +
        '<p class="main_fd3">재산피해 : ' + '${}' + '</p>'
        ;

        mainTitleBox.innerHTML = contentHTML;
    }

    function updateGraph() {
        const mainGraph = document.querySelector('.content .graph');

        const contentHTML =
        '<p class="main_fd1">화재건수 : ' + '${}' + '</p>' +
        '<p class="main_fd2">인명피해 : ' + '${}' + '</p>' +
        '<p class="main_fd3">재산피해 : ' + '${}' + '</p>'
        ;

        mainGraph.innerHTML = contentHTML;
    }

    updateMainTitleBox();
    updateGraph();

    // 1분마다 updateMainTitleBox()와 updateGraph() 함수 실행
    setInterval(function() {
        updateMainTitleBox();
        updateGraph();
    }, 10000); // 60000밀리초 = 1분
});
</script>    
</head>
<body>
<jsp:include page="${CP}/WEB-INF/views/header.jsp" />

<section class="content align-items-center">
    <div class="main">
        <img src="https://image.ytn.co.kr/general/jpg/2017/1030/201710301151127152_d.jpg" alt="#fire">
        <div class="main_title_box">

        </div>
    </div>
    <div class="graph">

    </div>
</section>

<jsp:include page="${CP}/WEB-INF/views/footer.jsp" />
<script src = "${CP}/resources/js/bootstrap.bundle.min.js"></script>        
</body>
</html>