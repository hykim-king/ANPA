<%@page import="com.acorn.anpa.cmn.StringUtil"%>
<%@page import="com.acorn.anpa.cmn.Search"%>
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
    const answerBtn = document.querySelector('#answerBtn');
    
    // .nav 클래스의 4번째 .nav-item의 자식 .nav-link를 선택합니다
    const firstNavLink = document.querySelector('.nav .nav-item:nth-child(4) .nav-link');
    // 선택한 요소에 "active" 클래스를 추가합니다
    firstNavLink.classList.add('active');
    
    //이벤트
    doDelAnswerClick();
    doUpdateAnswerClick();
    if(answerBtn != null){
	    answerBtn.addEventListener("click", function(event){
	        event.stopPropagation();
	        doSaveAnswer();
	    });    	
    }
    
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
    function doSaveAnswer(){
    	if(confirm('비방이나 모욕적인 댓글은 삭제될 수 있습니다. 등록하시겠습니까?') === false) return;
    	
    	const userId = "${user.userId}";
    	const boardSeq = "${board.boardSeq}";
    	const contents = document.querySelector('.answerContents').value;
    	console.log("userId : " + userId);
    	console.log("boardSeq : " + boardSeq);
    	console.log("contents : " + contents);
    	
        //비동기 통신
        let type= "POST";  
        let url = "/ehr/board/doSaveAnswer.do";
        let async = "true";
        let dataType = "json";
        
        //markdown getter : simplemde.value()
        let params = {
            'contents' : contents,
            'boardSeq' : boardSeq,
            'regId'    : userId
        }
        
        PClass.pAjax(url,params,dataType,type,async,function(data){
            if(data){
                try{
                    if(isEmpty(data) === false && 1 === data.messageId){
                        alert(data.messageContents);
                        doRetrieve('/ehr/board/doAnswerAjax.do', 1);
                        contents.innerHTML = '';
                    }else{
                        alert("에러: "+data.messageContents);
                    }
                    
                }catch(e){
                     alert("댓글 등록 예외 발생 : 개발자한테 문의하세요");     
                }
                
            }
            
        });
    }    
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
                     alert("게시글 삭제 예외 입니다 : 개발자에게 문의 요망");     
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
                     alert("게시글 수정 예외 입니다 : 개발자에게 문의 요망");     
                }
                
            }
            
        });
        
    }
    
let url = '/ehr/board/doAnswerAjax.do';
let maxNum = '${list[0].totalCnt}';
doRetrieve(url, 1);    
});   
function doDelAnswerClick(){
    console.log('┌────────────────')
    console.log('doDeleteAnswer')
    console.log('└────────────────')
    const answerDelBtns = document.querySelectorAll("#answerTable tr .btn-danger");
    console.log('┌────────────────')
    console.log(answerDelBtns)
    console.log('└────────────────')
    
    answerDelBtns.forEach(function(answerDelBtn) {
        answerDelBtn.addEventListener("click", function() {   
            if(confirm('댓글을 삭제 하시겠습니까?') === false) return;             
            // 클릭된 버튼이 속한 행을 찾습니다
            const row = this.closest('tr');
            
            const answerSeq = row.querySelector('.answerSeq').textContent;
            const answerCon = row.querySelector('.answerContents2').textContent;
            
            console.log(answerSeq, answerCon);
            doDelAnswer(answerSeq, answerCon);
        });
    });     
}
function doUpdateAnswerClick(){
    const answerUpBtns = document.querySelectorAll("#answerTable tr .btn-secondary");
    
    answerUpBtns.forEach(function(answerUpBtn) {
        answerUpBtn.addEventListener("click", function() {             
            // 클릭된 버튼이 속한 행을 찾습니다
            const row = this.closest('tr');
            
            const userId = "${user.userId}";
            const answerSeq = row.querySelector('.answerSeq').textContent;
            const answerCon = row.querySelector('.answerContents2').value;
            const answerConTag = row.querySelector('.answerContents2');
            
            console.log(answerSeq, answerCon, userId, answerConTag);
            doUpdateAnswer(answerSeq, answerCon, userId, answerConTag);
        });
    });     
}
function doDelAnswer(answerSeq, answerCon){        
    //비동기 통신
    let type= "GET";  
    let url = "/ehr/board/doDelAnswer.do";
    let async = "true";
    let dataType = "json";
    
    let params = {
        "answerSeq" : answerSeq,
        "contents" : answerCon
    }
    
    PClass.pAjax(url,params,dataType,type,async,function(data){
        if(data){
            try{
                if(isEmpty(data) === false && 1 === data.messageId){
                    alert(data.messageContents);
                    doRetrieve('/ehr/board/doAnswerAjax.do', 1);
                }else{
                    alert("에러발생 : "+data.messageContents);
                }
                
            }catch(e){
                 alert("댓글삭제 예외발생 : 개발자에게 문의 요망");     
            }
            
        }
        
    }); 
}
function doUpdateAnswer(answerSeq, answerCon, userId, answerConTag){        
    if(isEmpty(answerCon) == true){
        alert('내용을 입력 하세요.')
        answerConTag.focus();
        return;
    }  
    
    if(confirm('댓글을 수정 하시겠습니까?') === false) return;
    
    //비동기 통신
    let type= "POST";  
    let url = "/ehr/board/doUpdateAnswer.do";
    let async = "true";
    let dataType = "json";
    
    //markdown getter : simplemde.value()
    let params = { 
        "answerSeq": answerSeq,
        "contents": answerCon,
        "modId": userId
    }
    
    PClass.pAjax(url,params,dataType,type,async,function(data){
        if(data){
            try{
                if(isEmpty(data) === false && 1 === data.messageId){
                    alert(data.messageContents);
                    doRetrieve('/ehr/board/doAnswerAjax.do', 1);
                }else{
                    alert("에러: "+data.messageContents);
                }
                
            }catch(e){
                 alert("예외 발생 : 개발자에게 문의 요망");     
            }
            
        }
        
    });
}
//페이징
function doRetrieve(url, pageNo){
    const boardSeq = "${board.boardSeq}";
    const table = document.querySelector('#answerTable thead');
    
    let type= "GET";  
    let async = "true";
    let dataType = "json";
    
    let params = {
        'pageNo' : pageNo,
        "pageSize" : "10",
        "searchWord" : boardSeq
    }
    
    PClass.pAjax(url,params,dataType,type,async,function(data){
        let html = '';
        if(isEmpty(data) === false){
            try{
                console.log(data);
                data.forEach(function(item){
                	console.log(item);
                	console.log(item.modId);
                    if(item.modId == "${user.userId}" || 1 == "${user.adminYn}"){  	
                    html += '<tr><th class="answerSeq d-none">';
                    html += item.answerSeq;
                    html += '</th><th class="table-dark col-md-2 text-center" style="vertical-align: middle;">';
                    html += item.modId;
                    html += '</th><td class="col-md-7"><p class="col-md-12 m-0 answerCon"><textarea class="form-control answerContents2">';
                    html += item.contents;
                    html += '</textarea></p><div class="col-md-auto m-0 mt-2" style="float : right;"><button class="answerUpbtn btn btn-secondary">수정</button><button class="btn btn-danger">삭제</button></div></td><td class="col-md-1 text-center" style="vertical-align: middle;">';
                    html += item.modDt;
                    html += '</td></tr>';
                    }else{
                    html += '<tr><th class="table-dark col-md-2" style="vertical-align: middle;">';
                    html += item.modId;
                    html += '</th><td class="col-md-auto"><p class="col-md-12 m-0 answerCon">';
                    html += item.contents;
                    html += '</p></td><td class="col-md-1 text-center" style="vertical-align: middle;">';
                    html += item.modDt;
                    html += '</td></tr>';
                    }          
                });//for
                maxNum = data[0].totalCnt;   
                table.innerHTML = html;
                renderingPaging(maxNum, 1, 10, 10, url, 'doRetrieve');
            }catch(e){
                 console.log("data를 확인 하세요.");     
                 alert("data를 확인 하세요.");     
            }
            table.innerHTML = html;
            renderingPaging(maxNum, pageNo,10,10, url, 'doRetrieve');
            doDelAnswerClick();
            doUpdateAnswerClick();
        }else{
            html = '<tr><td class="text-center" colspan="99" >댓글이 없습니다</td></tr>';
            table.innerHTML = html;
            renderingPaging(maxNum, pageNo,10,10, url, 'doRetrieve');
        }
        
    });  
}

function renderingPaging(maxNum,currentPageNo,rowPerPage,bottomCount, url, scriptName){
    const pagenation = document.querySelector('#page-selection');    
    let html = '';
    pagenation.innerHTML = '';

    // 페이지 계산
    let maxPageNo = Math.ceil(maxNum / rowPerPage);
    let startPageNo = Math.floor((currentPageNo - 1) / bottomCount) * bottomCount + 1;
    let endPageNo = Math.min(startPageNo + bottomCount - 1, maxPageNo);

    let nowBlocNo = Math.floor((currentPageNo - 1) / bottomCount) + 1;
    let maxBlockNo = Math.ceil(maxPageNo / bottomCount);

    // 페이징 HTML 생성
    html += '<ul class="pagination justify-content-center">';

    // <<
    if (nowBlocNo > 1 && nowBlocNo <= maxBlockNo) {
        html += '<li class="page-item">';
        html += `<a class="page-link" href="javascript:\${scriptName}('\${url}', 1);">`;
        html += '<span aria-hidden="true">&laquo;</span>';
        html += '</a>';
        html += '</li>';
    }

    // <
    if (startPageNo > 1) {
        html += '<li class="page-item">';
        html += `<a class="page-link" href="javascript:\${scriptName}('\${url}', \${startPageNo - bottomCount});">`;
        html += '<span aria-hidden="true">&lt;</span>';
        html += '</a>';
        html += '</li>';
    }

    // 1 2 3 ... 10
    for (let inx = startPageNo; inx <= maxPageNo && inx <= endPageNo; inx++) {
        if (inx === currentPageNo) {
            html += '<li class="page-item">';
            html += '<a class="page-link active" href="#">';
            html += inx;
            html += '</a>';
            html += '</li>';
        } else {
            html += '<li class="page-item">';
            html += `<a class="page-link" href="javascript:\${scriptName}('\${url}', \${inx});">`;
            html += inx;
            html += '</a>';
            html += '</li>';
        }
    }

    // >
    if (maxPageNo > endPageNo) {
        html += '<li class="page-item">';
        html += `<a class="page-link" href="javascript:\${scriptName}('\${url}', \${endPageNo + 1});">`;
        html += '<span aria-hidden="true">&gt;</span>';
        html += '</a>';
        html += '</li>';
    }

    // >>
    if (maxPageNo > endPageNo) {
        html += '<li class="page-item">';
        html += `<a class="page-link" href="javascript:\${scriptName}('\${url}', \${maxPageNo});">`;
        html += '<span aria-hidden="true">&raquo;</span>';
        html += '</a>';
        html += '</li>';
    }

    html += '</ul>';
    pagenation.innerHTML = html;
}
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" />
<section class="board_info content content2 content3 align-items-center">
    <h3>공지사항</h3>
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
        <thead></thead>
        <tbody>
	        <c:if test="${user != null}">       
	        <tr>
	            <th class="table-dark col-md-2 text-center" colspan="3">댓글 등록</th>   
	        </tr>
	        <tr>
	            <td class="col-md-12" colspan="3"><textarea class="form-control answerContents"></textarea></td>    
	        </tr>
	        <tr>
	            <td class="col-md-12" colspan="3">
	                <p id="answerBtn" class="table-btn btn btn-success answerBtn">등록</p>
	            </td>    
	        </tr>
	        </c:if>
        </tbody>
		<%-- <!-- boardSeq 값을 변수에 저장 -->
		<c:set var="boardSeq" value="${board.boardSeq}" />
		
		<!-- list 배열에서 boardSeq와 일치하는 항목만 출력 -->
		<c:forEach var="answer" items="${list}">
			<c:if test="${answer.boardSeq == boardSeq}">
				<c:choose>
			        <c:when test="${answer.modId == user.userId}">
	                    <!-- 일치하는 항목을 출력 -->
		                <tr>
		                    <th class="answerSeq d-none">${answer.answerSeq}</th>
		                    <th class="table-dark col-md-2 text-center" style="vertical-align: middle;">${answer.modId}</th>
		                    <td class="col-md-7">
		                        <p class="col-md-12 m-0 answerCon">
		                          <textarea class="form-control answerContents2">${answer.contents}</textarea>
		                        </p>
		                        <div class="col-md-auto m-0 mt-2" style="float : right;">
		                            <button class="btn btn-secondary">수정</button>
		                            <button class="btn btn-danger">삭제</button>
		                        </div>
		                    </td>           
		                    <td class="col-md-1 text-center" style="vertical-align: middle;">${answer.modDt}</td>           
		                </tr>
			        </c:when>
			        <c:otherwise>
                        <tr>
                            <th class="table-dark col-md-2" style="vertical-align: middle;">${answer.modId}</th>
                            <td class="col-md-auto">
                                <p class="col-md-12 m-0 answerCon">${answer.contents}</p>
                            </td>           
                            <td class="col-md-1 text-center" style="vertical-align: middle;">${answer.modDt}</td>           
                        </tr>
			        </c:otherwise>
		        </c:choose>
			</c:if>
		</c:forEach>
		<c:if test="${user != null}">		
        <tr>
			<th class="table-dark col-md-2 text-center" colspan="3">댓글 등록</th>   
        </tr>
        <tr>
			<td class="col-md-12" colspan="3"><textarea class="form-control answerContents"></textarea></td>    
        </tr>
        <tr>
			<td class="col-md-12" colspan="3">
			    <p id="answerBtn" class="table-btn btn btn-success answerBtn">등록</p>
			</td>    
        </tr>
        </c:if> --%>
    </table>
    
    <!-- pagenation -->
    <div class="text-center" style="margin : 20px auto;">
        <div id="page-selection" class="text-center page">
        
        </div>
    </div>
    <!--// pagenation -->
</section>
<script>
    var simplemde = new SimpleMDE({ element: document.getElementById("contents")})
</script>
<jsp:include page="/WEB-INF/views/footer.jsp" />
<script src = "${CP}/resources/js/bootstrap.bundle.min.js"></script>     
</body>
</html>