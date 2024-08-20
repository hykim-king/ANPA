<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<c:set var="CP" value="${pageContext.request.contextPath}" />    
<!DOCTYPE html>
<html lang="kor">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">    
<head>
<script>
document.addEventListener("DOMContentLoaded", function(){
    console.log("DOMContentLoaded");    
                           
    if(document.querySelector("#logoutBtn")){
        const logoutAnker = document.querySelector("#logoutBtn");
        console.log("logoutAnker",logoutAnker);
        
        logoutAnker.addEventListener("click", function(event){
            console.log("logoutAnker click", event);
            event.stopPropagation();
            if (confirm('로그아웃 하시겠습니까?') === false) return;
            logout();
        });
    }

    function logout() {
        console.log("logout()");
        $.ajax({
            type: "GET",
            url: "/ehr/user/logout.do",
            dataType: "json",
            async: true,
            success: function(data) {
                if (data) {
                    try {
                        const message = data;
                        console.log(message);
                        if (message.messageId === 1) { // 로그아웃 성공
                            alert(message.messageContents);
                            window.location.href = "/ehr/main/index.do"; // 메인 페이지로 리다이렉트
                        } else {
                            alert(message.messageContents); // 오류 메시지 출력
                        }
                    } catch (e) {
                        alert("잘못된 데이터 형식입니다.");
                    }
                }
            },
            error: function(xhr, status, error) {
                console.error("AJAX Error: ", status, error);
                alert("로그아웃 요청에 실패했습니다.");
            }
        });
    }    
});
</script>
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

    .login-btn {
        font-weight: bold;
        color: #000; 
        background: none; 
        border: none; 
        padding: 10px 15px; 
        font-size: 1.1rem; 
        text-decoration: none; 
        display: flex;
        align-items: center; 
    }

    .login-btn:hover {
        text-decoration: underline; 
    }

    .login-btn svg {
        margin-right: 8px; 
        fill: #000; 
    }
    
     /* 사용자 정보 및 로그아웃 버튼 스타일 */
    .user-info {
        display: flex;
        align-items: center;
        gap: 10px;
    }

    .user-name {
        font-weight: bold;
	    font-size: 1.2rem; /* 필요에 따라 폰트 크기 조정 */
	    margin-right: 10px; /* logout-btn과의 간격 */
    }

    .logout-btn {
        font-weight: bold;
	    color: #000;
	    background: none;
	    border: none;
	    padding: 8px 15px; /* 패딩을 조정하여 시각적 일관성 유지 */
	    font-size: 1rem;
	    text-decoration: none;
	    cursor: pointer;
	    margin-left: auto; /* 오른쪽 정렬 */
	    margin: 0;
	}

    .logout-btn:hover {
        text-decoration: underline;
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
                <a href="${CP}/main/index.do">
                <img src="${CP}/resources/img/logo_x.png" alt="#logo">
                </a>
            </li>
            <li class="nav-item d-flex justify-content-center align-items-center">
                <a class="nav-link" aria-current="page" href="${CP}/monthfiredata/monthFireData.do">화재 통계</a>
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
        </ul>
        <!-- 네비게이션 바 끝 -->

        <!-- 유저 정보 및 로그아웃 버튼 -->
        <c:choose>
            <c:when test="${not empty sessionScope.user}">
                <div class="user-info">
                    <span class="user-name">${sessionScope.user.userName}님</span>
                    <a id="logoutBtn" class="logout-btn">🔥 로그아웃</a>
                </div>
            </c:when>
            <c:otherwise>
                <a id="loginBtn" class="login-btn" href="${CP}/user/login.do">
                                                    🔥로그인 / 회원가입
                </a>
            </c:otherwise>
        </c:choose>
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
