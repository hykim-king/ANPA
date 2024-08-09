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
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<title>ANPA</title>
<script>
document.addEventListener('DOMContentLoaded', function() {
    const checkAll = document.querySelector('#checkAll');

    // "전체 선택" 체크박스 클릭 시 처리
    checkAll.addEventListener('click', function() {
        const isChecked = checkAll.checked;
        const checkboxes = document.querySelectorAll('.chk');

        for (const checkbox of checkboxes) {
            checkbox.checked = isChecked;
        }
    });

    // 개별 체크박스 클릭 시 처리
    document.addEventListener('change', function(event) {
        if (event.target.classList.contains('chk')) {
            const checkboxes = document.querySelectorAll('.chk');
            const allChecked = Array.from(checkboxes).every(checkbox => checkbox.checked);
            checkAll.checked = allChecked;

            // 만약 모든 체크박스가 선택되지 않았다면, #checkAll 체크박스의 체크 상태를 해제합니다.
            if (Array.from(checkboxes).some(checkbox => !checkbox.checked)) {
                checkAll.checked = false;
            }
        }
    });
});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" />

<section class="board_con content content2 content3 align-items-center">
    <h3>    
<%--         <c:choose>
            <c:when test="${search.getDiv() == '10' }">관리자 페이지 - 화재 정보</c:when>
            <c:when test="${search.getDiv() == '20' }">관리자 페이지 - 유저 정보</c:when>
            <c:otherwise>
                         관리자 페이지
            </c:otherwise>            
        </c:choose> --%>
    </h3>

    <div class="row g-1 align-items-center mt-2">
        <div class="col-md-1">
            <button class="btn btn-danger">삭제</button>
        </div>
        <div class="col-md-auto"></div>
        
        <form name="bRfrm_user" id="bRfrm_user" class="col-md-auto ms-auto">
            <div class="row g-1">
                <input type="hidden" name="work_div" id="work_div">
                <input type="hidden" name="page_no" id="page_no" placeholder="페이지 번호">
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

    <div class="container ms-0 mt-3 p-0 text-center">
        <table class="table table-bordered full-width-table">
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
    
</section>


<jsp:include page="/WEB-INF/views/footer.jsp" />
<script src = "${CP}/resources/js/bootstrap.bundle.min.js"></script>     
</body>
</html>