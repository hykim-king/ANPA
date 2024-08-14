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
    
    console.log(sessionStorage.getItem("fBselectValue"));
    console.log(sessionStorage.getItem("fMselectValue"));
    console.log(sessionStorage.getItem("lBselectValue"));
    console.log(sessionStorage.getItem("lMselectValue"));
    console.log(sessionStorage.getItem("cBselectValue"));
    console.log(sessionStorage.getItem("cMselectValue"));
	
	// 변수 선언
    const frmUpdate = document.querySelector("#frmDataUpdate");
    const checkAll = document.querySelector('#checkAll');
    const doRetrieveBtn = document.querySelector("#doRetrieve");
    const frmUpCloseBtn = document.querySelector("#frmUpCloseBtn");
    const cBselect = document.querySelector("#cBselect");
    const cMselect = document.querySelector("#cMselect");
    const lBselect = document.querySelector("#lBselect");
    const lMselect = document.querySelector("#lMselect");
    const fBselect = document.querySelector("#fBselect");
    const fMselect = document.querySelector("#fMselect");
    
	// 업데이트 버튼 클릭 실행
	updateBtnsClick();
    cityCodeSet();
	
	// 클릭 이벤트 시작
	// 검색 버튼
	doRetrieveBtn.addEventListener('click', function() {
		doRetrieve(1);
	});
	// 정보 수정 닫기버튼
    frmUpCloseBtn.addEventListener('click', function() {
    	frmUpdate.classList.add('d-none');
    });
	// 클릭 이벤트 끝

	// 변화 감지 이벤트 시작
	// 시도 change이벤트
    cBselect.addEventListener("change",function(){
        console.log("cBselect change : "+cBselect.value);
        cityCodeSet();        
    });	
	// 화재요인 change이벤트
    fBselect.addEventListener("change",function(){
        factorCodeSet();        
    });	
	// 화재장소 change이벤트
    lBselect.addEventListener("change",function(){
        locCodeSet();        
    });	
	// 변화 감지 이벤트 끝

	// 함수 시작
    // 전체 조회
	function doRetrieve(pageNo){
		const frm = document.querySelector("#frmDataSearch");
        frm.pageNo.value = pageNo;
        console.log("pageNo: "+pageNo);     
        
        const fBselectValue = frm.fBselect.value; 
        const fMselectValue = frm.fMselect.value; 
        const lBselectValue = frm.lBselect.value; 
        const lMselectValue = frm.lMselect.value; 
        const cBselectValue = frm.cBselect.value; 
        const cMselectValue = frm.cMselect.value; 
        
        sessionStorage.setItem("fBselectValue", fBselectValue); // 세션에 저장
        sessionStorage.setItem("fMselectValue", fMselectValue); // 세션에 저장
        sessionStorage.setItem("lBselectValue", lBselectValue); // 세션에 저장
        sessionStorage.setItem("lMselectValue", lMselectValue); // 세션에 저장
        sessionStorage.setItem("cBselectValue", cBselectValue); // 세션에 저장
        sessionStorage.setItem("cMselectValue", cMselectValue); // 세션에 저장
        
        frm.action = "/ehr/manage/doRetrieveData.do";    
        frm.submit();
    }
	// 단건 조회
	function doSelectOne(fireSeq){            
		console.log("doSelectOne(fireSeq) : " + fireSeq);
		// 비동기 통신
		let type = "GET";
		let url = "/ehr/manage/doSelectOne.do";
		let async = "true";
		let dataType = "html";   
		let params = {
		   "fireSeq" : fireSeq    
		};
		
		PClass.pAjax(url, params, dataType, type, async, function(data){
		
		// null, undefined
		if(data){
		  try{
		    // JSON문자열을 Json object로 변환
		    const jsonObj = JSON.parse(data)
		    console.log("data : " + data);
		    
		    // 메시지
		    const message = jsonObj.message;
		    console.log("message.messageId: ",message.messageId);
            console.log("message.messageContents: ",message.messageContents);
            console.log("isEmpty(message) : ",isEmpty(message));
		    if(isEmpty(message) === false && 1 === message.messageId){
		      alert(message.messageContents);		      
		      
		      const firedata = jsonObj.firedata;
		      
		      console.log("firedata : ", firedata);
		      console.log("frmUpdate : ", frmUpdate);
		      console.log("frmUpdate.injuredSum : ", frmUpdate.injuredSum);
		      
		      frmUpdate.injuredSum.value = firedata.injuredSum;
		      frmUpdate.dead.value       = firedata.dead;
		      frmUpdate.injured.value    = firedata.injured;
		      frmUpdate.amount.value     = firedata.amount;
		      
		    }else{
		      alert("조회 실패입니다.");
		    }
		    
		    
		  }catch(e){
		    console.error("data가 null혹은 undefined입니다");
		    alert("data가 null혹은 undefined입니다");
		  }
		}else{
		    alert("seq 값을 확인해주세요");	    
		}
		
		}); // PClass 끝
	} // doSelectOne 끝
	
	// 수정버튼 함수
	function updateBtnsClick() {
	    const updateBtns = document.querySelectorAll("#manageDataTable tbody tr .btn");
	    console.log("btn click");
	
	    updateBtns.forEach(function(updateBtn) {
	        updateBtn.addEventListener("click", function() {
	            if (!confirm('해당 정보를 수정 하시겠습니까?')) return;
	            
	            // 클릭된 버튼이 속한 행을 찾습니다
	            const row = this.closest('tr');
	
	            // 해당 행에서 .fireSeqTd 클래스의 td를 찾아서 그 내부의 input 요소의 value를 읽어옵니다
	            const fireSeqInput = row.querySelector('.fireSeqTd input');
	            let fireSeq = fireSeqInput.value.trim();
	            
	            console.log(fireSeq);
	            doSelectOne(fireSeq);
	            frmUpdate.classList.remove('d-none');
	        });
	    });
	}
	// 시도 선택 시 시군구 변경
	function cityCodeSet() {	
	    const cityCode = cBselect.value;
	    const url = "/ehr/manage/doSelectCode.do";
	    const type = "GET";
/*         const subCityMidNm = ${search.subCityMidNm} */
	    
	    console.log("cBselect.value :" + cBselect.value);
	    
	    if (cBselect.value === "") {
	        cMselect.innerHTML = '<option value="">' + "시군구 전체" + '</option>';
	    } else {
	        const param1 = "masterCode=city"
	        const param2 = "&subCode=" + encodeURIComponent(cityCode);
	        
	        const xhr = new XMLHttpRequest();
	        xhr.open(type, url + "?" + param1 + param2, false); // `false` for synchronous requests
	        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	        
	        xhr.onload = function() {
	            if (xhr.status >= 200 && xhr.status < 300) {
	                const optionCodeData = JSON.parse(xhr.responseText);
	                console.log(optionCodeData);
	                cMselect.innerHTML = '<option value="">' + "시군구 전체" + '</option>';
	                optionCodeData.forEach(function(item) {
	                    const option = document.createElement("option");
	                    option.value = item.subCode;
/*                         if (subCityMidNm == item.subCode) {
                            option.selected = true;
                        }else{
                            option.selected = false;                            
                        } */
	                    option.text = item.midList;
	                    console.log(item.subCode);
	                    console.log(item.midList);
	                    cMselect.appendChild(option);
	                });
	            } else {
	                console.error("Request failed with status: " + xhr.status);
	            }
	        };
	        
	        xhr.onerror = function() {
	            console.error("Request error");
	        };
	        
	        xhr.send();
	    }
	}
	function factorCodeSet() {    
        const factorCode = fBselect.value;
        const url = "/ehr/manage/doSelectCode.do";
        const type = "GET";
        
        console.log("함수fBselect.value :" + fBselect.value);
        
        if (fBselect.value === "") {
            fMselect.innerHTML = '<option value="">' + "화재요인 미선택" + '</option>';
        } else {
            const param1 = "masterCode=factor"
            const param2 = "&subCode=" + encodeURIComponent(factorCode);
            
            const xhr = new XMLHttpRequest();
            xhr.open(type, url + "?" + param1 + param2, false); // `false` for synchronous requests
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            
            xhr.onload = function() {
                if (xhr.status >= 200 && xhr.status < 300) {
                    const optionCodeData = JSON.parse(xhr.responseText);
                    console.log(optionCodeData);
                    fMselect.innerHTML = '<option value="">' + "화재요인 미선택" + '</option>';
                    optionCodeData.forEach(function(item) {
                        const option = document.createElement("option");
                        option.value = item.subCode;
/*                         if (searchWord == item.subCode) {
                            option.selected = true;
                        }else{
                            option.selected = false;                            
                        } */
                        option.text = item.midList;
                        console.log(item.subCode);
                        console.log(item.midList);
                        fMselect.appendChild(option);
                    });
                } else {
                    console.error("Request failed with status: " + xhr.status);
                }
            };
            
            xhr.onerror = function() {
                console.error("Request error");
            };
            
            xhr.send();
        }
    }
	
	function locCodeSet() {    
        const locCode = lBselect.value;
        const url = "/ehr/manage/doSelectCode.do";
        const type = "GET";
        
        console.log("함수lBselect.value :" + lBselect.value);
        
        if (lBselect.value === "") {
            lMselect.innerHTML = '<option value="">' + "화재장소 미선택" + '</option>';
        } else {
            const param1 = "masterCode=location"
            const param2 = "&subCode=" + encodeURIComponent(locCode);
            
            const xhr = new XMLHttpRequest();
            xhr.open(type, url + "?" + param1 + param2, false); // `false` for synchronous requests
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            
            xhr.onload = function() {
                if (xhr.status >= 200 && xhr.status < 300) {
                    const optionCodeData = JSON.parse(xhr.responseText);
                    console.log(optionCodeData);
                    lMselect.innerHTML = '<option value="" >' + "화재장소 미선택" + '</option>';
                    optionCodeData.forEach(function(item) {
                        const option = document.createElement("option");
                        option.value = item.subCode;
/*                         if (MidNm == item.subCode) {
                        	option.selected = true;
                        }else{
                        	option.selected = false;                        	
                        } */
                        option.text = item.midList;
                        console.log(item.subCode);
                        console.log(item.midList);
                        lMselect.appendChild(option);
                    });
                } else {
                    console.error("Request failed with status: " + xhr.status);
                }
            };
            
            xhr.onerror = function() {
                console.error("Request error");
            };
            
            xhr.send();
        }
    }
	// 함수 끝
});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" />

<section class="content content2 content3 align-items-center">
    <h3>    
            관리자 페이지 - 화재 정보    
            ${search}        
            ${search.subCityMidNm}        
    </h3>

    <div class="row g-1 align-items-center mt-2">
        <div class="col-md-1">
            <button class="btn btn-danger">삭제</button>
        </div>
        <div class="col-md-auto"></div>
        
        <form name="frmDataSearch" id="frmDataSearch" class="col-md-auto ms-auto">
            <div class="row g-1">
                <input type="hidden" name="work_div" id="work_div">
                <input type="hidden" name="pageNo" id="pageNo" value="${search.pageNo}" placeholder="페이지 번호">
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
	                            <option value="${item.subCode}" <c:if test="${search.subCityBigNm == item.subCode}">selected</c:if>>${item.bigList}</option>
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
                        <option value="">화재요인 전체</option>
                        <c:forEach var="item" items="${factorCode}">
                            <c:if test="${item.mainCode == 0}">
                                <option value="${item.subCode}" <c:if test="${search.searchDiv == item.subCode}">selected</c:if>>${item.bigList}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-auto">
                    <select class="form-select" name="fMselect" id="fMselect">
                        <option value="">화재요인 미선택</option>
                    </select>
                </div>
                <div class="col-md-auto">
                    <select class="form-select" name="lBselect" id="lBselect">
                        <option value="">화재장소 전체</option>
                        <c:forEach var="item" items="${LocCode}">
                            <c:if test="${item.mainCode == 0}">
                                <option value="${item.subCode}" <c:if test="${search.bigNm == item.subCode}">selected</c:if>>${item.bigList}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-auto">
                    <select class="form-select" name="lMselect" id="lMselect">
                        <option value="">화재장소 미선택</option>
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
                    <th class="align-middle d-none" id="fireSeq"></th>
                    <th class="align-middle">날짜</th>
                    <th class="align-middle">총 사상자</th>
                    <th class="align-middle">사망</th>
                    <th class="align-middle">부상</th>
                    <th class="align-middle">화재요인</th>
                    <th class="align-middle">화재장소</th>
                    <th class="align-middle">화재지역</th>
                    <th class="align-middle">등록자</th>
                    <th class="align-middle d-none">수정일</th>
                    <th class="align-middle d-none">수정자</th>
                    <th class="align-middle">수정</th>
                </tr>
            </thead>
            <tbody>
              <c:choose>
                <c:when test="${list.size() >0 }">
                    <c:forEach var="item" items="${list}">
	                <tr>
	                    <td class="align-middle"><input type="checkbox" class="chk"></td>
                        <td class="align-middle">${item.regDt}</td>
	                    <td class="align-middle d-none fireSeqTd"><input type="password" value="${item.fireSeq}"></td>
	                    <td class="align-middle">${item.injuredSum}</td>
	                    <td class="align-middle">${item.dead}</td>
	                    <td class="align-middle">${item.injured}</td>
	                    <td class="align-middle">${item.subFactorBigNm}</td>
	                    <td class="align-middle">${item.subFactorMidNm}</td>
	                    <td class="align-middle">${item.subCityBigNm} ${item.subCityMidNm}</td>
	                    <td class="align-middle">${item.regId}</td>
	                    <td class="align-middle d-none">${item.modDt}</td>
	                    <td class="align-middle d-none">${item.modId}</td>
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

<!-- form -->
<form action="#" name="frmDataUpdate" id="frmDataUpdate" class="form-horizontal d-none">
  <div class="row m-0 mb-2">
      <div class="col-sm-10"></div>
      <div class="col-sm-2">
        <button class="btn btn-danger" id="frmUpCloseBtn">X</button>
      </div>
  </div>

  <div class="row m-0 mb-2">
      <label for="injuredSum" class="col-sm-3 col-form-label">사상자</label>
      <div class="col-sm-auto">
        <input type="number" class="form-control" name="injuredSum" id="injuredSum"   required="required">
      </div> 
  </div>
  
  <div class="row m-0 mb-2">
      <label for="dead" class="col-sm-3 col-form-label">사망자</label>
      <div class="col-sm-auto">
        <input type="number" class="form-control" name="dead" id="dead" required="required">
      </div>  
  </div>  
  
  <div class="row m-0 mb-2">
      <label for="injured" class="col-sm-3 col-form-label">부상자</label>
      <div class="col-sm-auto">
        <input type="number" class="form-control" name="injured" id="injured" required="required">
      </div> 
  </div>
  
  <div class="row m-0 mb-2">
      <label for="amount" class="col-sm-3 col-form-label">피해금액 (단위 : 천원)</label>
      <div class="col-sm-auto">
        <input type="number" class="form-control" name="amount" id="amount" required="required">
      </div> 
  </div>    
</form>
<!--// form -->

<jsp:include page="/WEB-INF/views/footer.jsp" />
<script src = "${CP}/resources/js/bootstrap.bundle.min.js"></script>    
<script>
function pageRetrieve(url,pageNo){
    const frm = document.querySelector("#frmDataSearch");
    frm.pageNo.value = pageNo;
    console.log("pageNo: "+pageNo);
    
    frm.action = url;    
    frm.submit();
}
</script> 
<script src="${CP}/resources/js/checkbox.js"></script> 
</body>
</html>