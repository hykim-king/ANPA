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
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            width: 100%;
            text-align: center;
            margin: 60px auto;
            min-height: 630px;
        }
        .login-container h2 {
            font-size: 2rem;
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
    <!-- 헤더 포함 -->
    <jsp:include page="/WEB-INF/views/header.jsp" />

    <div class="login-container">
        <h2>로그인</h2>
        <form action="${CP}/login" method="post">
            <div class="form-group">
                <input type="text" class="form-control" id="username" name="username" placeholder="아이디" required>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="password" name="password" placeholder="비밀번호" required>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary w-100">로그인</button>
            </div>
        </form>
        <div class="text-center mt-3">
            <a href="${CP}/findID" class="me-2">아이디 찾기</a> |
            <a href="${CP}/findPW" class="ms-2">비밀번호 찾기</a>
        </div>
        <hr>
        <div class="text-center">
            <a href="${CP}/signup" class="btn btn-success w-100" id="signup">회원가입</a>
        </div>
    </div>

    <!-- 푸터 포함 -->
    <jsp:include page="/WEB-INF/views/footer.jsp" />
    
    <script src="${CP}/resources/js/bootstrap.bundle.min.js"></script>    
</body>
</html>
