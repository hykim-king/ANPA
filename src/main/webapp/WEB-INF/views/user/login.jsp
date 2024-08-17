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
    <title>ANPA | 로그인</title>
    <script>
    document.addEventListener("DOMContentLoaded", function(){
        // 로그인 버튼 & 입력 필드
        const loginInfoBtn = document.querySelector("#loginInfo");
        const userIdInput  = document.querySelector("#userId");
        const passwordInput  = document.querySelector("#password"); 
        
        const form = document.querySelector("form");
        form.addEventListener("submit", function(event) {
            event.preventDefault();
            loginInfo(); 
        });
        
        // 로그인 처리 함수
        function loginInfo(){
            if (isEmpty(userIdInput.value)) {
                alert("아이디를 입력하세요.");
                userIdInput.focus();
                return;
            }
            
            if (isEmpty(passwordInput.value)) {
                alert("비밀번호를 입력하세요.");
                passwordInput.focus();
                return;
            }       

            let type= "POST";  
            let url = "/ehr/user/login.do";
            let dataType = "json";  
            let async = true;
            let params = {  
                "userId": userIdInput.value.trim(),
                "password": passwordInput.value.trim()      
            };        
            
            $.ajax({
                type: type,
                url: url,
                data: params,
                dataType: dataType,
                async: async,
                success: function(data) {
                    if (data) {
                        if (data.messageId === 30) {  // 로그인 성공
                            window.location.href = "/ehr/main/index.do";
                        } else {  // 로그인 실패
                            alert(data.messageContents);
                            userIdInput.focus(); 
                        }
                    }
                },
                error: function(xhr, status, error) {
                    alert("로그인 처리 중 오류가 발생했습니다.");
                    console.error("Error: " + error);
                }
            });
        }
        
        // 유효성 검사 함수
        function isEmpty(value) {
            return value === null || value.trim() === "";
        }
    });
</script>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #ffffff;
            color: #333;
            margin: 0;
            padding: 0;
        }
        .login-container {
            background-color: #f9f9f9;
            padding: 50px;
            border-radius: 15px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
            text-align: center;
            margin: 100px auto 60px auto;
            min-height: 640px;
        }
        .login-container h2 {
            font-size: 2.3rem;
            margin-bottom: 30px;
            color: black;
            text-transform: uppercase;
            font-weight: bold;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-control {
            border: 1px solid #ccc;
            border-radius: 30px;
            padding: 10px 15px;
            font-size: 1rem;
        }
        .form-control:focus {
            box-shadow: none;
            outline: none;
            border: 2px solid #424242;
        }
        
        .btn-primary{
        border-radius: 30px;
        padding: 10px 20px; 
        font-size: 1.1rem;
        background-color: #FAFAFA;
        color: black;
        border: 1px solid black;
        text-transform: uppercase;
        transition: background-color 0.3s;
        font-weight: bold;
        box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.2); 
        }
        
        .info-message {
		    margin-top: 30px; 
		    margin-bottom: 20px; 
		    font-size: 1rem; 
		    color: #555; 
		    line-height: 1.5; 
		}
		.info-message strong {
		    font-weight: bold; 
		    font-size: 1.3rem; 
		}
		.info-message p {
		    margin: 0;
		}
         .btn-success {
            border-radius: 30px;
            padding: 10px;
            font-size: 1.1rem;
            background-color: #B71C1C;
            color: black;
            text-transform: uppercase;
            transition: background-color 0.3s;
            font-weight: bold;
        }
        
        
        .btn-primary:hover {
           background-color: #B71C1C;
        }
        
        .btn-success:hover {
            background-color: #800303;
          
        }
        .btn-primary:active, .btn-success:active {
            background-color: #9a0007;
            outline: none;
        }
        .text-center a {
            color: black;
            text-decoration: none;
            transition: color 0.3s;
        }
        
        #signup{
            color : #fff;
        }
        .text-center a:hover {
            color: #b71c1c;
        }
        hr {
            border-top: 1px solid black;
        }
    </style>
</head>
<body>
    <jsp:include page="/WEB-INF/views/header.jsp" />

    <div class="login-container">
        <h2>로그인</h2>
        <form action="${CP}/user/login.do" method="post">
            <div class="form-group">
                <input type="text" class="form-control" id="userId" name="userId" placeholder="아이디" required>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="password" name="password" placeholder="비밀번호" required>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary w-100" id="loginInfo">로그인</button>
            </div>
        </form>
        <div class="text-center mt-3">
            <a href="${CP}/user/findId.do" class="me-2">아이디 찾기</a> |
            <a href="${CP}/user/findPw.do" class="ms-2">비밀번호 찾기</a>
        </div>
        <hr>
        <div class="info-message text-center">
            <p><strong>아직 회원이 아니신가요?</strong> <br>
               안전파수꾼 회원이 되셔서 다양한 정보와 <br> 
               커뮤니티 이용을 해보세요!</p>
        </div>
        <div class="text-center">
            <a href="${CP}/user/signup.do" class="btn btn-success w-100" id="signup">회원가입</a>
        </div>
    </div>
    
    <jsp:include page="/WEB-INF/views/footer.jsp" />   
    <script src="${CP}/resources/js/bootstrap.bundle.min.js"></script>    
</body>
</html>
