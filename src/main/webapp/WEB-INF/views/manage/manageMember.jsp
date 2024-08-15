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
<link rel="stylesheet" href="${CP}/resources/css/manage_style.css">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="${CP}/resources/js/common.js"></script> 
<title>ANPA</title>
<script>
document.addEventListener('DOMContentLoaded', function() {
	// 변수 선언
    const checkAll = document.querySelector('#checkAll');
    const doRetrieveBtn = document.querySelector("#doRetrieve");
    const doDeleteMemberBtn = document.querySelector("#doDeleteMember");
    const frm = document.querySelector("#frmMemberSearch");
    const rows = document.querySelectorAll("#manageDataTable tbody tr")
    let userId = "";
    const userIds = [];    
    
    // 클릭 이벤트 시작
    doRetrieveBtn.addEventListener("click",function(event){
        console.log("doRetrieveBtn click : "+doRetrieveBtn);
        event.stopPropagation(); // 이벤트 버블링 방지
        
        doRetrieve();        
    }); //조회버튼
    doDeleteMemberBtn.addEventListener("click",function(event){
        event.stopPropagation(); // 이벤트 버블링 방지
        
        doDeleteMember();
        
    }); //삭제버튼
    // 클릭 이벤트 끝
    
    // 키다운 이벤트 시작
    frm.searchWord.addEventListener("keydown", function(event) {
        if (event.which == 13 ){
        	console.log("야이 반동분자 새기야");
        	
            event.preventDefault();
            doRetrieve();
        }
     });
    // 키다운 이벤트 끝
    
    // 변화 감지 이벤트 시작
    frm.searchDiv.addEventListener("change",function(event){
        console.log("searchDiv change : "+ frm.searchDiv.value);        
        if(frm.searchDiv.value == ""){
        	frm.searchWord.value = "";
        }        
    });
    // 변화 감지 이벤트 끝
    
    // 함수 시작
    // 회원 검색
    function doRetrieve(){
        console.log("searchDiv : " + frm.searchDiv.value);
        console.log("searchWord : " + frm.searchWord.value);
        console.log("pageSize : " + frm.pageSize.value);  
        console.log("pageNo : " + frm.pageNo.value);  
        
        frm.action = "/ehr/manage/doRetrieveMember.do";    
        frm.submit();
    }
    // 회원 삭제
    function doDeleteMember(){
    	console.log('doDeleteMember');
    	
        // 각 행을 반복 처리합니다
        rows.forEach(function(row) {
            // 현재 행의 체크박스를 찾습니다
            const checkbox = row.querySelector("td.checkbox input.chk");

            // 체크박스가 체크되어 있는지 확인합니다
            if (checkbox.checked) {
                // 현재 행의 user_id를 찾습니다
                userId = row.querySelector("td.userIdTd").innerText;
                userIds.push(userId);
            }
        });
    	
        // userIds를 출력합니다 (필요에 따라 이 배열을 사용할 수 있습니다)
        console.log(userId);        
        console.log(userIds);  
        
        // userIds 체크 여부
        if(isEmpty(userIds) == true){
            alert('체크된 회원이 존재하지 않습니다. 잘못된 경로!');
        }else if(false == confirm('삭제 하시겠습니까?')){
              return;
        }  
        
        let type = "GET"; 
        let url = "/ehr/manage/doDeleteMember.do";
        let async = "true";
        let dataType = "html";
        let params = {
       	    "userIds": JSON.stringify(userIds)
        };
        
        PClass.pAjax(url,params,dataType,type,async,function(data){
            console.log("data: ",data);

            if(data){
                let message = JSON.parse(data);

                if(isEmpty(message)==false){
                    alert(message.messageContents);

                    doRetrieve();
                }else{
                    alert(message.messageContents);
                }
            }

        });
        
    }
    // 함수 끝
});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" />

<section class="content content2 content3 align-items-center">
    <h3>관리자 페이지 - 회원 정보</h3>

    <div class="row g-1 align-items-center mt-2">
        <div class="col-md-auto">
            <button class="btn btn-danger" id="doDeleteMember">삭제</button>
        </div>
        <div class="col-md-auto"></div>
        
        <form name="frmMemberSearch" id="frmMemberSearch" class="col-md-auto ms-auto">
            <div class="row g-1">
                <input type="hidden" name="work_div" id="work_div" value="${search.getDiv()}">
                <input type="hidden" name="pageNo" id="pageNo" value="${search.pageNo}" placeholder="페이지 번호">
                <input type="hidden" name="seq" id="seq">
                
                <div class="col-md-auto">
                    <select class="form-select" name="pageSize" id="pageSize">
                        <c:forEach var="item" items="${COM_PAGE_SIZE}">
                            <option value="${item.subCode}"
                            <c:if test="${search.pageSize == item.subCode }">selected</c:if> >
                            ${item.midList}</option>
                        </c:forEach>
                    </select>
                </div>
    
                <div class="col-md-auto">
                    <select class="form-select" name="searchDiv" id="searchDiv">
                            <option value="">전체</option>
                        <c:forEach var="item" items="${MEMBER_SEARCH}">
                            <option value="${item.subCode}"
                            <c:if test="${search.searchDiv == item.subCode }">selected</c:if> >
                            ${item.midList}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-md-6">
                    <input class="form-control" type="search" name="searchWord" id="searchWord" placeholder="검색어 입력" value="${search.searchWord}">
                </div>
            </div>
        </form>
    
        <div class="col-md-auto d-flex">
            <button type="button" class="btn btn-dark" id="doRetrieve">
                <i class="bi bi-search"></i>
            </button>
        </div>
    </div>

    <div class="container mt-3 p-0 text-center">
        <div class="table-responsive">
        <table id="manageDataTable" class="table table-bordered full-width-table">
            <thead>
                <tr>
                    <th class="align-middle"><input id="checkAll" type="checkbox"></th>
                    <th class="align-middle" id = "userId">아이디</th>
                    <th class="align-middle">이름</th>
                    <th class="align-middle">이메일</th>
                    <th class="align-middle">관리자여부</th>
                    <th class="align-middle">거주지</th>
                    <th class="align-middle">전화번호</th>
                    <th class="align-middle">가입일</th>
                </tr>
            </thead>
            <tbody>
              <c:choose>
                <c:when test="${list.size() >0 }">
                    <c:forEach var="item" items="${list}">
	                <tr>
	                    <td class="align-middle checkbox"><input type="checkbox" class="chk"></td>
	                    <td class="align-middle userIdTd">${item.userId}</td>
	                    <td class="align-middle">${item.userName}</td>
	                    <td class="align-middle">${item.email}</td>
	                    <td class="align-middle">${item.adminYnNm}</td>
	                    <td class="align-middle">
	                        <c:choose>
							    <c:when test="${empty item.subCityBnm}">
							        없음
							    </c:when>
							    <c:otherwise>
							        ${item.subCityBnm} ${item.subCityMnm}
							    </c:otherwise>
							</c:choose>
                        </td>
	                    <td class="align-middle">${item.tel}</td>
	                    <td class="align-middle">${item.regDt}</td>
	                </tr>     
	                </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td class="text-center" colspan="99" >데이터가 없습니다.</td>
                    </tr>
                </c:otherwise>
                </c:choose>           
            </tbody>
        </table>
        </div>
        
		<!-- pagenation -->
		<div class="text-center">
		  <div id="page-selection" class="text-center page">
		    <%
		//총글수 :
		int maxNum = (int) request.getAttribute("totalCnt");
		
		Search search = (Search) request.getAttribute("search");
		//페이지 번호:
		int currentPageNo = search.getPageNo();
		//페이지 사이즈:
		int rowPerPage = search.getPageSize();
		//바닥글 :
		int bottomCount = search.BOTTOM_COUNT;
		
		String url = "/ehr/manage/doRetrieveData.do";
		String scriptName = "pageRetrieve";
		
		out.print(StringUtil.renderingPaging(maxNum, currentPageNo, rowPerPage, bottomCount, url, scriptName));
		%>		
		  </div>
		</div> 
		<!--// pagenation -->
    </div>
    
</section>

<jsp:include page="/WEB-INF/views/footer.jsp" />
<script src = "${CP}/resources/js/bootstrap.bundle.min.js"></script>    
<script>
function pageRetrieve(url,pageNo){
    const frm = document.querySelector("#frmMemberSearch");
    
    let searchDiv = frm.searchDiv.value;
    console.log("searchDiv" + searchDiv);

    let searchWord = frm.searchWord.value;
    console.log("searchWord" + searchWord);

    let pageSize = frm.pageSize.value;
    console.log("pageSize" + pageSize);
    
    frm.pageNo.value = pageNo;
    console.log("pageNo: "+pageNo);
    
    frm.action = url;    
    frm.submit();
}
</script>
<script src="${CP}/resources/js/checkbox.js"></script>  
</body>
</html>