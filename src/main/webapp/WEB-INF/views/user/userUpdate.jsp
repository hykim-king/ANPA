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
<script src="${CP}/resources/js/common.js"></script> 
<title>ANPA | 회원정보 수정</title>
<script>  
$(document).ready(function(){
	
	//시군구 코드
    const cBselect = document.querySelector("#cBselect");
    const cMselect = document.querySelector("#cMselect");
    const subCityValue = "${outVO.subCity}"
    const sido = '${outVO.subCity}';
    const sidoValue = sido.substring(0, 2)+'000';
    let idDuplicatedClick = 0; // 변수 초기화
	
    cBselect.value = sidoValue;
    cityCodeSet("", cBselect, cMselect); 
    
    setTimeout(function() {
    	cMselect.value = subCityValue;
    }, 200);
    
    cBselect.addEventListener("change",function(){
        cityCodeSet("", cBselect, cMselect);  
        
    }); 
    
    // 회원정보수정
    $("#doUpdate").on("click", function(event){
        event.preventDefault();         
        console.log("doUpdate click");        
        doUpdate();
    });
	
	
	
	//--------------------------------------------------------------------------
	
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
    });//--formatPhoneNumber()end
    
    //회원정보 수정 입력
	function doUpdate(){
    	console.log("${outVO.userId}");
    	if(isEmpty($("#email").val())){
            alert("이메일을 입력하세요.");
            $("#email").focus();
            return;
        }
    	
    	const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test($("#email").val())) {
            alert("유효한 이메일 주소를 입력하세요.");
            $("#email").focus();
            return;
        }
        
        if(isEmpty($("#tel").val())){
            alert("전화번호를 입력하세요.");
            $("#tel").focus();
            return;
        }      

        const telPattern = /^(\d{3})-(\d{3,4})-(\d{4})$/;
        if (!telPattern.test($("#tel").val())) {
            alert("전화번호는 000-1234-5678 형식으로 입력하세요.");
            $("#tel").focus();
            return;
        }

        if(isEmpty($("#password").val())){
            alert("비밀번호를 입력하세요.");
            $("#password").focus();
            return;
        }        
        
        // 비밀번호 조건: 8자리 이상, 영문자, 숫자, 특수문자
        const passwordPattern = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*()_+{}\[\]:;"'<>,.?\/|~\-]).{8,}$/;
        if (!passwordPattern.test($("#password").val())) {
            alert("비밀번호는 8자리 이상이며 영문자, 숫자, 특수문자를 포함해야 합니다.");
            $("#password").focus();
            return;
        }
  
        if(isEmpty($("#confirmPassword").val())){
            alert("비밀번호를 재입력하세요.");
            $("#confirmPassword").focus();
            return;
        }
    
        if($("#confirmPassword").val() != $("#password").val()){
            alert("비밀번호가 서로 다릅니다.");
            $("#confirmPassword").focus();
            return;
        }
        
        if (cBselect.value === "") {
            alert("시도를 선택하세요.");
            cBselect.focus();
            return;
        }

        if (cMselect.value === "") {
            alert("시군구를 선택하세요.");
            cMselect.focus();
            return;
        }
            
         // 회원정보수정 요청 비동기 통신
         let type = "POST"; 
         let url = "/ehr/user/doUpdate.do";
         let async = "true";
         let dataType = "html";
         let params = {                
                 password: $("#password").val(),
                 email: $("#email").val(),         
                 emailYn: $("#emailYn").is(":checked") ? 1 : 0,  
                 subCity: $("#cMselect").val(),
                 tel: $("#tel").val(), 
                 userId: "${outVO.userId}"
         };    
         
         if(confirm("회원정보를 수정하시겠습니까?") === false) return;
         
         PClass.pAjax(url,params,dataType,type,async,function(data){
             console.log("data: ",data);
             if(data){
                 let message = JSON.parse(data); 
                 try{
                     if(1 == message.messageId){    
                    	 console.log("message.messageId:"+message.messageId);
                         //alert(message.messageContents);
                         window.location.href = "/ehr/main/index.do";
                         alert("회원정보 수정이 완료되었습니다.");
                     }else{
                         alert(message.messageContents);
                     }

                 }catch(error){
                     alert("널 혹은 언디파인드임");
                 }
             }else{
                 alert("개발자에게 문의하세요. 확인 불가능한 오류입니다");
             }

         });//--pAjax end
         
       
		  
	}//--doUpdate()end
	
	function isEmpty(value) {
        return value == null || value.trim() === '';
    }
	
	function cityCodeSet(subCityMidNm, cBselect, cMselect) {   
        const cityCode = cBselect.value;
        const url = "/ehr/manage/doSelectCode.do";
        
        if (cBselect.value === "") {
            cMselect.innerHTML = '<option value="">' + "시군구 전체" + '</option>';
        } else {
            const param1 = "masterCode=city"
            const param2 = "&subCode=" + encodeURIComponent(cityCode);
            
            $.ajax({
                url: url,
                type: "GET",
                data: param1 + param2,
                contentType: "application/x-www-form-urlencoded",
                success: function(response) {
                    const optionCodeData = JSON.parse(response);
                    console.log(optionCodeData);
                    cMselect.innerHTML = '<option value="">' + "시군구 전체" + '</option>';
                    optionCodeData.forEach(function(item) {
                    	
                        const option = document.createElement("option");
                        option.value = item.subCode;
                        option.selected = subCityMidNm == item.subCode;
                        option.text = item.midList;
                        cMselect.appendChild(option);
                    });
                },
                error: function(xhr, status, error) {
                    console.error("Request failed with status: " + xhr.status);
                }
            });
        }
    }//--cityCodeSet()end
      
	
	
})//--DOMContentLoaded end

</script>
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
        .form-control, .form-select {
            border: 1px solid #ccc;
            border-radius: 30px;
            padding: 10px 15px;
            font-size: 1rem;
        }
        .form-control:focus, .form-select:focus {
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
            max-width: 200px; 
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
            flex-shrink: 0; 
            width: 130px; 
        }
        .btn-check-duplicate:hover {
            background-color: #ddd;
        }
        .checkbox-group {
            display: flex;
            align-items: center;
            gap: 10px;
            justify-content: flex-start;
        }
        .checkbox-group .form-control {
            margin: 0;
            max-width: 300px; 
        }
        .checkbox-group input[type="checkbox"] {
            margin: 0;
        }
        .checkbox-group label {
            margin: 0;
            font-size: 1rem;
        }
    </style>
</head>
<body>
    <jsp:include page="/WEB-INF/views/header.jsp" />
    <div class="signup-container">
        <h2>회원정보 수정</h2>
        <form id="signupForm" action="${CP}/user/signup.do" method="post">
            <div class="form-group">
                <input type="text" class="form-control" id="userName" name="userName" 
                       placeholder="이름"  maxlength="17" required="required" value="${outVO.userName}" disabled>
            </div>
            <div class="form-group d-flex align-items-center">
                <input type="text" class="form-control" id="userId" name="userId" 
                       placeholder="아이디"  maxlength="20" required="required" value="${outVO.userId}"disabled>                
            </div>
            <div class="form-group d-flex align-items-center checkbox-group">
                <input type="email" class="form-control" id="email" name="email" 
                       placeholder="이메일"  maxlength="320" required="required" value="${outVO.email }">
                <input type="checkbox" id="emailYn" name="emailYn" <c:if test="${outVO.emailYn == 1 }">checked</c:if> >
                <label for="emailYn">수신동의</label>
            </div>
            <div class="form-group">
                <input type="tel" class="form-control" id="tel" name="tel" 
                       placeholder="전화번호"  maxlength="15" required="required" value="${outVO.tel }">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="password" name="password" 
                       placeholder="비밀번호"  maxlength="32" required="required">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" 
                       placeholder="비밀번호를 재입력해주세요"  maxlength="32" required="required">
            </div>
            <div class="form-group">
               <select class="form-select" name="cBselect" id="cBselect">
                    <option value="">시도 전체</option>
                    <c:forEach var="item" items="${cityCode}">
                        <c:if test="${item.mainCode == 0}">
                            <option value="${item.subCode}" <c:if test="${search.subCityBigNm == item.subCode}">selected</c:if>>${item.bigList}</option>
                        </c:if>
                    </c:forEach>
               </select>  
            </div>
            <div class="form-group">
                <select class="form-select" name="cMselect" id="cMselect">
                    <option value="">시군구 전체</option>
                </select>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-success w-100" id="doUpdate">수정</button>
            </div>
        </form>
    </div>

    <jsp:include page="/WEB-INF/views/footer.jsp" />   
    <script src="${CP}/resources/js/bootstrap.bundle.min.js"></script> 
</body>
</html>
