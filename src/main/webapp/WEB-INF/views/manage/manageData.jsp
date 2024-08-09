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
<title>ANPA</title>
<script>
document.addEventListener('DOMContentLoaded', function() {
	// 변수 선언
    const checkAll = document.querySelector('#checkAll');

	// 클릭 이벤트 시작
    // 전체 체크박스 선택 클릭 이벤트
    checkAll.addEventListener('click', function() {
        const isChecked = checkAll.checked;
        const checkboxes = document.querySelectorAll('.chk');

        checkboxes.forEach(function(checkbox) {
            checkbox.checked = isChecked;
        });
    });
	// 클릭 이벤트 끝

	// 변화 감지 이벤트 시작
    // 개별 체크박스 클릭 시 처리
    document.addEventListener('change', function(event) {
        if (event.target.classList.contains('chk')) {
            const checkboxes = document.querySelectorAll('.chk');
            const allChecked = Array.from(checkboxes).every(function(checkbox) {
                return checkbox.checked;
            });

            checkAll.checked = allChecked;

            // 만약 모든 체크박스가 선택되지 않았다면, #checkAll 체크박스의 체크 상태를 해제합니다.
            if (!allChecked) {
                checkAll.checked = false;
            }
        }
    });
	// 변화 감지 이벤트 끝
	
	// 함수 시작
    function rowDbClick(){
        const rows = document.querySelectorAll("#manageDataTable tbody tr");
        rows.forEach(function(row){
            row.addEventListener("dblclick",function(){
                if(confirm('상세 조회 하시겠습니까?') === false)return;
                let boardSeq = this.querySelector('#fireSeq').textContent.trim();
                let boardModId = this.querySelector('td:nth-child(4)').textContent.trim();
                console.log("시퀀스 값: ",boardSeq);
                console.log("수정자 값: ",boardModId);
                popUp.classList.toggle('popupHide');
                popUp.classList.remove('popupDoSaveHide');
                doSelectOne(boardSeq, boardModId);
            });
        });
    }
	// 함수 끝
});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" />

<section class="board_con content content2 content3 align-items-center">
    <h3>    
            관리자 페이지 - 화재 정보            
    </h3>

    <div class="row g-1 align-items-center mt-2">
        <div class="col-md-1">
            <button class="btn btn-danger">삭제</button>
        </div>
        <div class="col-md-auto"></div>
        
        <form name="bRfrm_user" id="bRfrm_user" class="col-md-auto ms-auto">
            <div class="row g-1">
                <input type="hidden" name="work_div" id="work_div">
                <input type="hidden" name="pageNo" id="pageNo" placeholder="페이지 번호">
                <input type="hidden" name="seq" id="seq">
    
                <div class="col-md-auto">
                    <label for="dateStart" style="padding-top : 6px">검색 날짜 : </label>
                </div>
                <div class="col-md-auto">
				    <input class="form-control" type="date" id="dateStart" name="dateStart">
                </div>
                <div class="col-md-auto">
                    <label for="dateEnd" style="padding-top : 6px">종료 날짜 : </label>
                </div>
                <div class="col-md-auto">
					<input class="form-control" type="date" id="dateEnd" name="dateEnd">
                </div>
                <div class="col-md-auto">
                    <select class="form-select" name="cBselect" id="cBselect">
                        <option value="">시도 전체</option>
                    <c:forEach var="item" items="${cityCode}">
			            <c:if test="${item.mainCode == 0}">
                            <option value="${item.subCode}">${item.bigList}</option>
                        </c:if>
			        </c:forEach>
                    </select>
                </div>
                <div class="col-md-auto">
                    <select class="form-select" name="cMselect" id="cMselect">
                        <option value="">시군구 전체</option>
                    </select>
                </div>
                <div class="col-md-auto">
                    <select class="form-select" name="fBselect" id="fBselect">
                        <option value="10">화재요인</option>
                    </select>
                </div>
                <div class="col-md-auto">
                    <select class="form-select" name="lBselect" id="lBselect">
                        <option value="10">화재장소</option>
                    </select>
                </div>
                <div class="col-md-auto">
                    <select class="form-select" name="pageSize" id="pageSize">
		                <c:forEach var="item" items="${COM_PAGE_SIZE}">
		                    <option value="${item.subCode}"
		                    <c:if test="${search.pageSize == item.subCode }">selected</c:if> >
		                    ${item.midList}</option>
		                </c:forEach>
                    </select>
                </div>
            </div>
        </form>
    
        <div class="col-md-auto d-flex">
            <button type="button" class="btn btn-dark">
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
                    <th class="align-middle d-none" id="fireSeq"></th>
                    <th class="align-middle">총 사상자</th>
                    <th class="align-middle">사망</th>
                    <th class="align-middle">부상</th>
                    <th class="align-middle">화재요인</th>
                    <th class="align-middle">화재장소</th>
                    <th class="align-middle">화재지역</th>
                    <th class="align-middle">날짜</th>
                    <th class="align-middle">등록자</th>
                    <th class="align-middle">수정일</th>
                    <th class="align-middle">수정자</th>
                    <th class="align-middle">수정</th>
                </tr>
            </thead>
            <tbody>
              <c:choose>
                <c:when test="${list.size() >0 }">
                    <c:forEach var="item" items="${list}">
	                <tr>
	                    <td class="align-middle"><input type="checkbox" class="chk"></td>
	                    <td class="align-middle d-none" id="fireSeqTd"><input type="text" value="item.fireSeq"></td>
	                    <td class="align-middle">${item.injuredSum}</td>
	                    <td class="align-middle">${item.dead}</td>
	                    <td class="align-middle">${item.injured}</td>
	                    <td class="align-middle">${item.subFactorBigNm}</td>
	                    <td class="align-middle">${item.subFactorMidNm}</td>
	                    <td class="align-middle">${item.subCityBigNm} ${item.subCityMidNm}</td>
	                    <td class="align-middle">${item.regDt}</td>
	                    <td class="align-middle">${item.regId}</td>
	                    <td class="align-middle">${item.modDt}</td>
	                    <td class="align-middle">${item.modId}</td>
	                    <td class="align-middle"><button class="btn btn-secondary">수정</button></td>
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
    const frm = document.querySelector("#bRfrm_user");
    frm.pageNo.value = pageNo;
    console.log("pageNo: "+pageNo);
    
    frm.action = url;    
    frm.submit();
}
</script> 
</body>
</html>