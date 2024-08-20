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
    <title>ANPA | 아이디 찾기</title>
<script>
    document.addEventListener("DOMContentLoaded", function(){
	    const findIdBtn = document.querySelector("#findId");
	    const userNameInput = document.querySelector("#userName");
	    const telInput = document.querySelector("#tel");
	    const emailInput = document.querySelector("#email"); 
	    const resultDiv = document.querySelector("#result");
	    
	    const form = document.querySelector("form");
	    form.addEventListener("submit", function(event) {
	        event.preventDefault();
	        findUserId(); 
    });
    
    //전화번호 000-1234-5678 형식으로 입력
    function formatPhoneNumber(value) {
        // 숫자만 추출
        value = value.replace(/\D/g, '');
        
        if (value.length < 4) return value;
        if (value.length < 7) return value.slice(0, 3) + '-' + value.slice(3);
        return value.slice(0, 3) + '-' + value.slice(3, 7) + '-' + value.slice(7);
    }

    $('#tel').on('input', function() {
        let formattedValue = formatPhoneNumber($(this).val());
        $(this).val(formattedValue);
    });

    // backspace와 delete 키 처리
    $('#tel').on('keydown', function(event) {
        const key = event.key;
        const cursorPos = this.selectionStart;
        const currentValue = $(this).val();
        
        // backspace 또는 delete 키가 눌린 경우는 포맷을 적용하지 않음
        if (key === 'Backspace' || key === 'Delete') {
            if (key === 'Backspace' && currentValue[cursorPos - 1] === '-') {
                this.setSelectionRange(cursorPos - 1, cursorPos - 1); // 하이픈을 넘어 커서 이동
            } else if (key === 'Delete' && currentValue[cursorPos] === '-') {
                this.setSelectionRange(cursorPos + 1, cursorPos + 1); // 하이픈을 넘어 커서 이동
            }
        }
    });
   
    function findUserId(){
        if (isEmpty(userNameInput.value)) {
            alert("이름을 입력하세요.");
            userNameInput.focus();
            return;
        }
        
        if (isEmpty(telInput.value)) {
            alert("전화번호를 입력하세요.");
            telInput.focus();
            return;
        }       

        if (isEmpty(emailInput.value)) {
            alert("이메일을 입력하세요.");
            emailInput.focus();
            return;
        }       

        let type= "POST";  
        let url = "/ehr/user/findUserId.do";
        let dataType = "json";  
        let async = true;
        let params = {  
            "userName": userNameInput.value.trim(),
            "tel": telInput.value.trim(),
            "email": emailInput.value.trim()     
        };        
        
        $.ajax({
            type: type,
            url: url,
            data: params,
            dataType: dataType,
            async: async,
            success: function (data) {
                if (data.success) {  // 아이디 찾기 성공
                    resultDiv.innerHTML = `<p>찾으신 아이디는: <strong>${data.userId}</strong></p><a href="${CP}/user/login.do" class="btn btn-primary w-100">로그인</a>`;
                } else {  // 아이디 찾기 실패
                    resultDiv.innerHTML = `<p>일치하지 않은 항목이 있습니다.</p>`;
                }
            },
            error: function(xhr, status, error) {
                resultDiv.innerHTML = `<p>아이디 찾기 처리 중 오류가 발생했습니다.</p>`;
                console.error("Error: " + error);
            }
        });
    }
    
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
        <h2>아이디 찾기</h2>
        <form>
            <div class="form-group">
                <input type="text" class="form-control" id="userName" name="userName" placeholder="이름" required>
            </div>
            <div class="form-group">
                <input type="tel" class="form-control" id="tel" name="tel" placeholder="전화번호" required>
            </div>
            <div class="form-group">
                <input type="email" class="form-control" id="email" name="email" placeholder="이메일" required>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary w-100" id="findUserId">아이디 찾기</button>
            </div>
        </form>
        <div id="result" class="mt-3"></div>
    </div>
    
    <jsp:include page="/WEB-INF/views/footer.jsp" />   
    <script src="${CP}/resources/js/bootstrap.bundle.min.js"></script>    
</body>
</html>
