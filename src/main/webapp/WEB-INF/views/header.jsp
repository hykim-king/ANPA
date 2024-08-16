<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<c:set var="CP" value="${pageContext.request.contextPath}" />    
<!DOCTYPE html>
<html lang="kor">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">    
<head>
<style>

    .nav { 
        flex-grow: 1;
        font-size: 1.2rem; 
    }

    .top_logo {
        height: auto;
        margin-right: 20px;
    }

    .top_logo img {
        height: 75px; 
    }

    .nav-item {
        margin: 0 20px; 
    }

    /* 로그인 버튼 스타일 */
    .login-btn {
        color: #000; /* 버튼 텍스트 색상 검정 */
        background: none; /* 배경색 제거 */
        border: none; /* 테두리 제거 */
        padding: 10px 15px; /* 버튼 패딩 */
        font-size: 1rem; /* 버튼 텍스트 크기 */
        text-decoration: none; /* 링크 밑줄 제거 */
        display: flex;
        align-items: center; /* 아이콘과 텍스트 정렬 */
    }

    .login-btn:hover {
        text-decoration: underline; /* 마우스를 올리면 밑줄 표시 */
    }

    .login-btn svg {
        margin-right: 8px; /* 아이콘과 텍스트 사이의 여백 */
        fill: #000; /* 아이콘 색상 검정 */
    }
</style>
</head>
<body>
<header id="top_header">
    <div class="d-flex justify-content-between align-items-center top_header_il">

        <!-- 네비게이션 바 시작 -->
        <ul class="nav nav-tabs text-center justify-content-center">
            <!-- 로고를 첫 번째 항목으로 추가 -->
            <li class="nav-item d-flex align-items-center top_logo">
                <img src="${CP}/resources/img/logo_x.png" alt="#logo">
            </li>
            <li class="nav-item d-flex justify-content-center align-items-center">
                <a class="nav-link" aria-current="page" href="#">화재 통계</a>
            </li>
            <li class="nav-item d-flex justify-content-center align-items-center">
                <a class="nav-link" href="#">화재 현황</a>
            </li>
            <li class="nav-item d-flex justify-content-center align-items-center">
                <a class="nav-link" href="#">화재 예방법</a>
            </li>
            <li class="nav-item dropdown d-flex justify-content-center align-items-center">
                <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">알림마당</a>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="#">공지사항</a></li>
                    <li><hr class="dropdown-divider"></li>
                    <li><a class="dropdown-item" href="#">건의사항</a></li>
                </ul>
            </li>
            <!-- 로그인 버튼 수정 -->
            <li class="nav-item d-flex align-items-center">
                <a class="login-btn" href="${CP}/user/login.do">
                    <svg xmlns="http://www.w3.org/2000/svg" height="16px" viewBox="0 0 24 24" width="16px" fill="#000000">
					    <path d="M10.09 15.59L8.67 14.17 11.5 11.34H2V9.34H11.5L8.67 6.5L10.09 5.08L15.17 10.16L10.09 15.59ZM19 3H5C3.9 3 3 3.9 3 5V8H5V5H19V19H5V16H3V19C3 20.1 3.9 21 5 21H19C20.1 21 21 20.1 21 19V5C21 3.9 20.1 3 19 3Z"/>
					</svg>
                    로그인 / 회원가입
                </a>
            </li>
        </ul>
        <!-- 네비게이션 바 끝 -->

        <!-- 앱 메뉴 섹션 -->
        <div class="text-center top_header_il_obj appmenu">
            <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#dfdfdf">
                <path d="M240-160q-33 0-56.5-23.5T160-240q0-33 23.5-56.5T240-320q33 0 56.5 23.5T320-240q0 33-23.5 56.5T240-160Zm240 0q-33 0-56.5-23.5T400-240q0-33 23.5-56.5T480-320q33 0 56.5 23.5T560-240q0 33-23.5 56.5T480-160Zm240 0q-33 0-56.5-23.5T640-240q0-33 23.5-56.5T720-320q33 0 56.5 23.5T800-240q0 33-23.5 56.5T720-160ZM240-400q-33 0-56.5-23.5T160-480q0-33 23.5-56.5T240-560q33 0 56.5 23.5T320-480q0 33-23.5 56.5T240-400Zm240 0q-33 0-56.5-23.5T400-480q0-33 23.5-56.5T480-560q33 0 56.5 23.5T560-480q0 33-23.5 56.5T480-400Zm240 0q-33 0-56.5-23.5T640-480q0-33 23.5-56.5T720-560q33 0 56.5 23.5T800-480q0 33-23.5 56.5T720-400ZM240-640q-33 0-56.5-23.5T160-720q0-33 23.5-56.5T240-800q33 0 56.5 23.5T320-720q0 33-23.5 56.5T240-640Zm240 0q-33 0-56.5-23.5T400-720q0-33 23.5-56.5T480-800q33 0 56.5 23.5T560-720q0 33-23.5 56.5T480-640Zm240 0q-33 0-56.5-23.5T640-720q0-33 23.5-56.5T720-800q33 0 56.5 23.5T800-720q0 33-23.5 56.5T720-640Z"/>
            </svg>
        </div>
    </div>
</header>
</body>
</html>
