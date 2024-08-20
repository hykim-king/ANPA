<%--
/**
    Class Name: board_reg.jsp
    Description: 부트스트랩 template
    Author: acorn
    Modification information
    
    수정일                    수정자      수정내용
    -----        -----  -------------------------------------------
    2024. 7. 18         최초작성 
    
    
    author eclass 개발팀
    since 2020.11.23
    Copyright (C) by KandJang All right reserved.
*/
 --%>
<%@page import="com.acorn.anpa.member.domain.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<c:set var="CP"  value="${pageContext.request.contextPath}"  /> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%-- favicon  --%>
<link rel="shortcut icon" href="${CP}/resources/img/favicon.ico" type="image/x-icon">

<%-- bootstrap css --%>
<link rel="stylesheet" href="${CP}/resources/css/bootstrap.css">

<%-- jquery --%>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>

<%-- common js --%>
<script src="${CP}/resources/js/common.js"></script> 

<%-- google Nanum+Gothic --%>
<link rel="stylesheet"  href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap">

<%-- FontAwesome for icons --%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">


<%-- simplemde --%>
<link rel="stylesheet" href="${CP}/resources/css/simplemde.min.css">
<script src="${CP}/resources/js/simplemde.min.js"></script>

<title>오늘 사람 프로그램</title>
<script>
document.addEventListener("DOMContentLoaded", function(){
    const doSaveBtn = document.querySelector("#doSave");
    
    const titleInput = document.querySelector("#title");
    const contentsTextArea = document.querySelector("#contents");
    const divInput = document.querySelector("#divYn");  
    const userId = '${user.userId}';
    console.log('userId: '+userId);
    
    
    
    //Event감지
    doSaveBtn.addEventListener("click", function(event){
        console.log("doSaveBtn click");     
        doSave();
    });
    
    function doSave(){
        console.log("doSave()");
        
        if(isEmpty(titleInput.value) == true){
            alert('제목을 입력 하세요.')
            titleInput.focus();
            return;
            
        }
        
        //markdown getter : simplemde.value()
        if(isEmpty(simplemde.value()) == true){
            alert('내용을 입력 하세요.')
            simplemde.codemirror.focus();
            return;
        }  
        
        console.log("simplemde",simplemde.value());
        if(confirm('등록 하시겠습니까?') === false) return;
        
        //비동기 통신
        let type= "POST";  
        let url = "/ehr/board/doSave.do";
        let async = "true";
        let dataType = "json";
        
        //markdown getter : simplemde.value()
        let params = { 
            "title": titleInput.value,
            "regId": userId,  
            "contents": simplemde.value(),
            "divYn": divInput.value
        }
        
        PClass.pAjax(url,params,dataType,type,async,function(data){
            if(data){
                try{
                    if(isEmpty(data) === false && 1 === data.messageId){
                        alert(data.messageContents);
                        console.log('divYn: '+divYn);
                        window.location.href = `/ehr/board/${divYn}.do`;
                    }else{
                        alert("에러: "+data.messageContents);
                    }
                    
                }catch(e){
                     alert("data를 확인 하세요.");     
                }
                
            }
            
        });
        
        
    }
    
});
</script>
</head>
<body>
<!-- container -->
<div class="container">
  
  <!-- 제목 -->
  <div class="page-header">
      <h2>
        <c:choose>
            <c:when test="${ '20'==divYn }">공지사항-등록</c:when>
            <c:when test="${ '10'==divYn }">건의사항/소통-등록</c:when>
            <c:otherwise>
                                 공지사항/자유게시판
            </c:otherwise>
        </c:choose>
      </h2>  
  </div>
  <!--// 제목 end ------------------------------------------------------------->
  
  <!-- 버튼 -->
  <div class="mb-2 d-grid gap-2 d-md-flex justify-content-md-end">
      <input type="button" value="목록" class="btn btn-primary" onclick="window.location.href ='/ehr/board/'+${divYn}+'.do'">
      <input type="button" value="등록"  id="doSave" class="btn btn-primary">
  </div>
  <!--// 버튼 ----------------------------------------------------------------->
  
  <!-- form -->
  <form action="#" class="form-horizontal"  name="regForm" id="regForm">
    <input type="hidden" name="divYn"    id="divYn" value="${divYn }">
    <div class="row mb-2">
        <label for="title" class="col-sm-2 col-form-label">제목</label>
        <div class="col-sm-10">
          <input type="text" class="form-control" name="title" id="title"  maxlength="75" required="required">
        </div>      
    </div>
    <div class="row mb-2"">
        <label for="contents" class="col-sm-2 col-form-label">내용</label>
        <div class="col-sm-10">    
         <textarea style="height: 200px"  class="form-control" id="contents" name="contents"></textarea>
        </div> 
    </div>    
    
  </form>
  
  <!--// form end -->
</div>
<!--// container end ---------------------------------------------------------->
<script>
    var simplemde = new SimpleMDE({ element: document.getElementById("contents") })
</script>
<%-- bootstrap js --%>
<script src = "${CP}/resources/js/bootstrap.bundle.min.js"></script> 
</body>
</html>