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
<link rel="stylesheet" href="${CP}/resources/css/board_style.css">
<script src="${CP}/resources/js/common.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<%-- simplemde --%>
<link rel="stylesheet" href="${CP}/resources/css/simplemde.min.css">
<script src="${CP}/resources/js/simplemde.min.js"></script>
<title>ANPA</title>
<script>
document.addEventListener('DOMContentLoaded', function() {
    const doUpdateBtn = document.querySelector('#doUpdate');
    const doDeleteBtn = document.querySelector('#doDelete');
    const titleInput = document.querySelector('#title');
    const moveList = document.querySelector('#moveList');

    // .nav 클래스의 4번째 .nav-item의 자식 .nav-link를 선택합니다
    const firstNavLink = document.querySelector('.nav .nav-item:nth-child(5) .nav-link');
    // 선택한 요소에 "active" 클래스를 추가합니다
    firstNavLink.classList.add('active');
    
    //이벤트
    doDeleteBtn.addEventListener("click", function(event){
        event.stopPropagation();
        doDelete();
    });
    
    moveList.addEventListener("click", function(event){
    	event.stopPropagation();
    	window.location.href = `/ehr/board/${board.divYn}.do`;
    });
    
    doUpdateBtn.addEventListener("click", function(event){
    	event.stopPropagation();
    	doUpdate();
    });
   
    //함수
    function doDelete(){
        if(confirm('삭제 하시겠습니까?') === false) return;
        
        //비동기 통신
        let type= "GET";  
        let url = "/ehr/board/doDelete.do";
        let async = "true";
        let dataType = "json";
        
        //markdown getter : simplemde.value()
        let params = {
        	'title' : titleInput.value,
            "boardSeq" : '${board.boardSeq}'
        }
        
        PClass.pAjax(url,params,dataType,type,async,function(data){
            if(data){
                try{
                    if(isEmpty(data) === false && 1 === data.messageId){
                        alert(data.messageContents);
                        window.location.href = `/ehr/board/${board.divYn}.do`;
                    }else{
                        alert("에러: "+data.messageContents);
                    }
                    
                }catch(e){
                     alert("data를 확인 하세요.");     
                }
                
            }
            
        });
    }
    
    function doUpdate(){
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
        if(confirm('수정 하시겠습니까?') === false) return;
        
        //비동기 통신
        let type= "POST";  
        let url = "/ehr/board/doUpdate.do";
        let async = "true";
        let dataType = "json";
        
        //markdown getter : simplemde.value()
        let params = { 
            "title": titleInput.value,
            "modId": '${user.userId}',  
            "contents": simplemde.value(),
            "divYn": '${board.divYn}',
            "boardSeq" : '${board.boardSeq}'
        }
        
        PClass.pAjax(url,params,dataType,type,async,function(data){
            if(data){
                try{
                    if(isEmpty(data) === false && 1 === data.messageId){
                        alert(data.messageContents);
                        window.location.href = `/ehr/board/${board.divYn}.do`;
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
<jsp:include page="/WEB-INF/views/header.jsp" />

<section class="board_info content content2 content3 align-items-center">
    <h3>공지사항</h3>
    ${board }
    ${user }
    <div class="d-flex justify-content-end">             
        <p class="table-btn btn btn-success" id="moveList">목록</p>                
    </div>
    <c:choose>
        <c:when test="${board.regId == user.userId || user.adminYn == '1'}">
            <table class="table table-bordered">
		        <thead>
		            <tr>
		                <th style="display: none;">${board.boardSeq}</th>           
		                <th class="table-dark col-md-2">제목</th>
		                <td colspan="3"><input id="title" type="text" value="${board.title}"></td>                
		            </tr>
		        </thead>
		        <tbody>
		            <tr>   
		                <th class="table-dark col-md-2">수정자</th>
		                <td class="col-md-auto">${board.modId}</td>
		                <th class="table-dark col-md-2">수정일</th>
		                <td class="col-md-auto">${board.modDt}</td>
		            </tr>
		            <tr>
		               <td class="table_info" colspan="4">
		                  <textarea style="height: 200px"  class="form-control" id="contents" name="contents">${board.contents}</textarea>
		               </td> 
		            </tr>        
		        </tbody>
		    </table>
            <div class="d-flex justify-content-end">             
                <p class="table-btn btn btn-success" id="doUpdate">수정</p>                
                <p class="table-btn btn btn-danger" id="doDelete">삭제</p>   
            </div>
        </c:when>
        <c:otherwise>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th style="display: none;">${board.boardSeq}</th>           
                        <th class="table-dark col-md-2">제목</th>
                        <td colspan="3">${board.title}</td>                
                    </tr>
                </thead>
                <tbody>
                    <tr>   
                        <th class="table-dark col-md-2">수정자</th>
                        <td class="col-md-auto">${board.modId}</td>
                        <th class="table-dark col-md-2">수정일</th>
                        <td class="col-md-auto">${board.modDt}</td>
                    </tr>
                    <tr>
                       <td class="table_info" colspan="4">${board.contents}</td> 
                    </tr>        
                </tbody>
            </table>
            <div class="d-flex justify-content-end">             
		        <p class="table-btn btn btn-success d-none" id="doUpdate">수정</p>                
		        <p class="table-btn btn btn-danger d-none" id="doDelete">삭제</p>   
		    </div>
        </c:otherwise>
    </c:choose>
    
    <table class="table table-bordered" id="answerTable">
		<!-- boardSeq 값을 변수에 저장 -->
		<c:set var="boardSeq" value="${board.boardSeq}" />
		
		<!-- list 배열에서 boardSeq와 일치하는 항목만 출력 -->
		<c:forEach var="answer" items="${list}">
		    <c:if test="${answer.boardSeq == boardSeq}">
		        <!-- 일치하는 항목을 출력 -->
				<tr>
				    <th class="table-dark col-md-2">${answer.modId}</th>
				    <td class="col-md-7">${answer.contents}</td>           
				    <td class="col-md-3 text-center">${answer.modDt}</td>           
				</tr>
		    </c:if>
		</c:forEach>

        <tr>
			<th class="table-dark col-md-2 text-center" colspan="3">댓글 등록</th>   
        </tr>
        <tr>
			<td class="col-md-12" colspan="3"><textarea class="form-control answerContents"></textarea></td>    
        </tr>
        <tr>
			<td class="col-md-12" colspan="3">
			    <p class="table-btn btn btn-success answerBtn">등록</p>
			</td>    
        </tr>
    </table>
</section>
<script>
    var simplemde = new SimpleMDE({ element: document.getElementById("contents")})
</script>
<jsp:include page="/WEB-INF/views/footer.jsp" />
<script src = "${CP}/resources/js/bootstrap.bundle.min.js"></script>     
</body>
</html>