<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value="${pageContext.request.contextPath}" />
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
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #ffffff;
            color: #333;
            margin: 0;
            padding: 0;
        }
        .signup-container {
            background-color: #f9f9f9;
            padding: 50px;
            border-radius: 15px;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            width: 100%;
            text-align: center;
            margin: 100px auto 60px auto;
            min-height: 630px;
        }
        .signup-container h2 {
            font-size: 2.3rem;
            margin-bottom: 30px;
            color: black;
            text-transform: uppercase;
            font-weight: bold;
        }
        .form-group {
            margin-bottom: 25px;
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
        .input-group {
            display: flex;
            align-items: center;
            gap: 10px;
        }
        .input-group .form-control {
            flex: 1;
            margin-right: 10px;
            max-width: 150px; 
        }
        .btn-primary {
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
            color: white;
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
        .text-center a:hover {
            color: #b71c1c;
        }
        hr {
            border-top: 1px solid black;
        }
        .btn-check-duplicate {
            border-radius: 30px;
            font-size: 1rem;
            padding: 10px;
            background-color: #f0f0f0;
            color: #333;
            border: 1px solid #ccc;
            cursor: pointer;
            text-transform: uppercase;
            transition: background-color 0.3s;
            flex-shrink: 0; /* 버튼이 축소되지 않도록 설정 */
            width: 130px; /* 중복 체크 버튼 너비 조정 */
        }
        .btn-check-duplicate:hover {
            background-color: #ddd;
        }
    </style>
</head>
<body>
    <!-- 헤더 포함 -->
    <jsp:include page="/WEB-INF/views/header.jsp" />

    <div class="signup-container">
        <h2>회원가입</h2>
        <form id="signupForm" action="${CP}/signup" method="post">
            <div class="form-group">
                <input type="text" class="form-control" id="name" name="name" placeholder="이름" required>
            </div>
            <div class="form-group d-flex align-items-center">
                <input type="text" class="form-control" id="username" name="username" placeholder="아이디" required>
                <button type="button" class="btn-check-duplicate ms-2" onclick="idCheck()">id 중복 체크</button>
            </div>
            <div class="form-group">
                <input type="email" class="form-control" id="email" name="email" placeholder="이메일" required>
            </div>
            <div class="form-group">
                <input type="email" class="form-control" id="tel" name="tel" placeholder="전화번호" required>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="password" name="password" placeholder="비밀번호" required>
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="비밀번호를 재입력해주세요" required>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-success w-100">회원가입</button>
            </div>
        </form>
    </div>

    <!-- 푸터 포함 -->
    <jsp:include page="/WEB-INF/views/footer.jsp" />
    
    <script src="${CP}/resources/js/bootstrap.bundle.min.js"></script> 
    <script>
        function checkDuplicate() {
            var username = document.getElementById('username').value;
            if (username) {
                // Ajax 요청 또는 페이지 전환을 통해 중복 체크를 수행합니다.
                // 예를 들어:
                alert('사용 가능한 아이디입니다');
                // 실제 중복 확인 로직은 서버 사이드에서 처리해야 합니다.
            } else {
                alert('아이디를 입력해주세요');
            }
        }

        // 회원가입 완료 후 메시지와 리다이렉션 처리
        document.getElementById('signupForm').onsubmit = function(event) {
            event.preventDefault(); // 폼 제출을 막습니다.

            var form = event.target;
            var formData = new FormData(form);

            fetch(form.action, {
                method: 'POST',
                body: formData
            }).then(response => response.text())
              .then(result => {
                  alert('안전파수꾼 회원이 되신 걸 환영합니다!');
                  window.location.href = '${CP}/user/login.do'; // 로그인 페이지로 리다이렉트
              }).catch(error => {
                  console.error('회원가입 오류:', error);
              });
        };
    </script>
</body>
</html>
