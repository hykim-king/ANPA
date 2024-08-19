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
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<title>ANPA</title>
<script>
document.addEventListener('DOMContentLoaded', function() {
    // .nav 클래스의 4번째 .nav-item의 자식 .nav-link를 선택합니다
    const firstNavLink = document.querySelector('.nav .nav-item:nth-child(5) .nav-link');
    const doUpdate = document.querySelector('#doUpdate');
    const doDelete = document.querySelector('#doDelete');

    // 선택한 요소에 "active" 클래스를 추가합니다
    firstNavLink.classList.add('active');
    
    //이벤트
    doUpdate.addEventListener("click", function(event){
    	
    	
    });
   
});   
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" />

<section class="board_info content content2 content3 align-items-center">
    <h3>공지사항</h3>
    
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
        <p class="table-btn btn btn-success" id="doUpdate">수정</p>                
        <p class="table-btn btn btn-danger" id="doDelete">삭제</p>   
    </div>
    
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

<jsp:include page="/WEB-INF/views/footer.jsp" />
<script src = "${CP}/resources/js/bootstrap.bundle.min.js"></script>     
</body>
</html>