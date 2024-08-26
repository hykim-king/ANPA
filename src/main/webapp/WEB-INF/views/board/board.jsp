<%@page import="com.acorn.anpa.member.domain.Member"%>
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
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="${CP}/resources/js/common.js"></script> 
<title>ANPA</title>
<script>
document.addEventListener('DOMContentLoaded', function() {
    // .nav 클래스의 4번째 .nav-item의 자식 .nav-link를 선택합니다
    const firstNavLink = document.querySelector('.nav .nav-item:nth-child(4) .nav-link');

    // 선택한 요소에 "active" 클래스를 추가합니다
    firstNavLink.classList.add('active');
    
    // 변수 선언 시작
    const doRetrieveBtn = document.querySelector('#searchBtn');    
    const frm = document.querySelector('#bRfrm');                                                                                                                             
    const searchWordInput = document.querySelector('#searchWord');    
    const pageSize = document.querySelector('#pageSize');    
    const searchDiv = document.querySelector('#searchDiv');    
    const moveToReg = document.querySelector('#moveToReg');
    const table = document.querySelector('#boardTable tbody');
    //페이징
    let url = '/ehr/board/doRetrieveAjax.do';
    let maxNum = '${list[0].totalCnt}';
    doRetrieve(url,1);
    
    let DivValue = '${search.getDiv()}';
    
    //세션
    const userId = '${user.userId}';
    const adminYn = '${user.adminYn}';
    
    // 이벤트 시작
    searchDiv.addEventListener('change', function(event) {
    	if(searchDiv.value == '') searchWordInput.value = '';
    });
    
    moveToReg.addEventListener('click', function(event) {
        event.stopPropagation(); // 이벤트 버블링 방지
        
        if(userId === ''){
        	alert('로그인 해야 가능한 기능 입니다.');
        	window.location.href = '/ehr/user/login.do';
        	return;
        }
        
        frm.divYn.value = '${search.getDiv()}';
        
        frm.action = "/ehr/board/boardReg.do";
        frm.submit();
    });
    
    //조회
    doRetrieveBtn.addEventListener('click', function(event) {
        event.stopPropagation(); // 이벤트 버블링 방지
        doRetrieve(url,1);
    });
    
    //검색박스 엔터 적용
    searchWordInput.addEventListener("keydown",function(event){
        event.stopPropagation();
        if(event.key !== 'Enter'){
        	return;
        }
        
            doRetrieve(url,1);
    });
    
});   
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" />
<section class="board_con content content2 content3 align-items-center">
    <c:choose>
        <c:when test="${search.getDiv() == 10}">
            <h3>건의사항 / 소통 게시판</h3>
        </c:when>
        <c:otherwise>
            <h3>공지사항 게시판</h3>
        </c:otherwise>
    </c:choose>
    <div class="row g-1 align-items-center">
        <form name="bRfrm" id="bRfrm" class="col-md-5">
            <div class="row g-1">
                <input type="hidden" name="work_div" id="work_div">
                <input type="hidden" name="pageNo" id="pageNo" placeholder="페이지 번호">
                <input type="hidden" name="seq" id="seq">
                <input type="hidden" name="regId" id="regId">
                <input type="hidden" name="divYn" id="divYn">

                <div class="col-md-auto">
                    <select class="form-select mt-3" name="pageSize" id="pageSize">
                        <c:forEach var="item" items="${pageSearch}">
                           <option value="${item.subCode}" <c:if test="${search.pageSize == item.subCode}">selected</c:if>>${item.midList}</option>
                        </c:forEach>
                    </select>
                </div>
                
                <div class="col-md-auto">
                    <select class="form-select mt-3" name="searchDiv" id="searchDiv">
                        <option value="">전체</option>
                        <c:forEach var="item" items="${boardSearch}">
                           <option value="${item.subCode}" <c:if test="${search.searchDiv == item.subCode}">selected</c:if>>${item.midList}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-md-7">
                    <input class="form-control mt-3" type="search" name="searchWord" id="searchWord" placeholder="검색어 입력" value="${search.searchWord}">
                </div>
            </div>
        </form>
        
        <div class="col-md-auto d-flex">
            <button id="searchBtn" type="button" class="btn btn-dark mt-3">
                <i class="bi bi-search"></i>
            </button>
        </div>
        <c:choose>
            <c:when test="${list[0].divYn == '20' && user.adminYn == '1'}">
		        <div class="col-md-1 d-flex">
		            <button id="moveToReg" type="button" class="btn btn-dark mt-3">등록</button>
		        </div>
            </c:when>
            <c:when test="${list[0].divYn == '10'}">
                <div class="col-md-1 d-flex">
                    <button id="moveToReg" type="button" class="btn btn-dark mt-3">등록</button>
                </div>
            </c:when>
            <c:otherwise>
                <div class="col-md-1 d-flex d-none">
                    <button id="moveToReg" type="button" class="btn btn-dark mt-3">등록</button>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
    
    <div class="cnt_page"><span></span></div>

    <div class="table-outset mt-2">
        <table id="boardTable" class="table">
            <tbody>
            </tbody>                      
        </table>
    </div>
    
    <!-- pagenation -->
    <div class="text-center" style="margin-top : 30px;">
	    <div id="page-selection" class="text-center page">
	    
	    </div>
    </div>
    <!--// pagenation -->
</section>
<script>
//페이징
function doRetrieve(url, pageNo){
    const pageSize = document.querySelector('#pageSize'); 
    const searchDiv = document.querySelector('#searchDiv'); 
    const searchWordInput = document.querySelector('#searchWord');
    const table = document.querySelector('#boardTable tbody');
    const cnt_page = document.querySelector('.cnt_page span');
    let DivValue = '${search.getDiv()}';
    let maxNum = '';
    
    let type= "GET";  
    let async = "true";
    let dataType = "json";
    
    let params = {
        'pageNo' : pageNo,
        "pageSize" : pageSize.value,
        "searchDiv" : searchDiv.value,
        "searchWord" : searchWordInput.value,
        "div" : DivValue
    }
    
    PClass.pAjax(url,params,dataType,type,async,function(data){
        let html = '';
        if(isEmpty(data) === false){
            try{
                data.forEach(function(item){
                    html += '<tr class="table-row">';
                    html += '<th class="tbseq" style="display: none;"><input type="text" value="'+item.boardSeq+'"></th>';
                    html += '<th class="table-dark text-center align-middle col-2">';
                    if(item.divYn == 20){
                        html += '공지사항';
                    }else{
                        html += '건의사항 / 소통';
                    }
                    html += '</th>';
                    html += '<td class="table-light">';
                    html += '<div class="row-content">';
                    
                    html += '<div class="title">'+item.title;
                    if(Number(item.no) > 0){
	                    html += '<span class="answerCount">'+item.no+'</span></div>'; 
                    }else{
                        html += '</div>'; 
                    }
                    
                    html += '<div class="details">수정자 : '+item.modId+' 조회수 : '+item.readCnt+' 수정일: '+item.modDt+'</div>';
                    html += '</div>';
                    html += '</td>';
                    html += '</tr>';
                    
                });//for
                maxNum = data[0].totalCnt;
                
                table.innerHTML = html;
                rowClick();
                renderingPaging(maxNum,pageNo,pageSize.value,10, url, 'doRetrieve');
                
                let pageNofmt = new Intl.NumberFormat().format(pageNo);
                let maxNumfmt = new Intl.NumberFormat().format(maxNum);
			    cnt_page.textContent = '총 게시글: '+maxNumfmt+' 건 / '+pageNofmt+' Page';
            }catch(e){
                 alert("data를 확인 하세요.");     
            }
            
        }else{
            html = '<tr><td class="text-center" colspan="99" >데이터가 없습니다.</td></tr>';
            table.innerHTML = html;
            renderingPaging(maxNum,pageNo,pageSize.value,10, url, 'doRetrieve');
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

function rowClick(){
    const rows = document.querySelectorAll("#boardTable tbody tr");
    rows.forEach(function(row){
        row.addEventListener("click",function(event){
            
            let boardSeq = this.querySelector(".tbseq input").value;
            
            if(confirm('게시글을 조회하시겠습니까?') ==false) return;
            
            doSelectOne(boardSeq);
        });
        
    });//단건 선택 
}
function doSelectOne(boardSeq){
    
    const frm = document.querySelector("#bRfrm");
    
    frm.seq.value = boardSeq;
    
    frm.action = "/ehr/board/doSelectOne.do";
    frm.submit();
    event.stopPropagation();
}
</script>
<jsp:include page="/WEB-INF/views/footer.jsp" />
<script src = "${CP}/resources/js/bootstrap.bundle.min.js"></script>   
</body>
</html>