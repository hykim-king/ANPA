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
    const firstNavLink = document.querySelector('.nav .nav-item:nth-child(5) .nav-link');

    // 선택한 요소에 "active" 클래스를 추가합니다
    firstNavLink.classList.add('active');
    
    rowClick(); // 게시글 선택
    
    // 변수 선언 시작
    
    // 함수 시작
    function rowClick(){
        const rows = document.querySelectorAll("#boardTable tbody tr");
        rows.forEach(function(row){
            row.addEventListener("click",function(event){
                
                let boardSeq = this.querySelector(".tbseq input").value;
                console.log("seq: "+boardSeq);
                
                if(confirm('게시글을 조회하시겠습니까?') ==false) return;
                
                doSelectOne(boardSeq);
            });
            
        });//단건 선택 
    }
    function doSelectOne(boardSeq){
	    console.log("doSelectOne seq: "+boardSeq);
	    
	    const frm = document.querySelector("#bRfrm");
	    
	    frm.seq.value = boardSeq;
	    
	    frm.action = "/ehr/board/doSelectOne.do";
	    frm.submit();
	    event.stopPropagation();
	}
    // 함수 끝

});   
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" />

<section class="board_con content content2 content3 align-items-center">
    <h3>${blTitle}</h3>
    <div class="row g-1 align-items-center">
        <form name="bRfrm" id="bRfrm" class="col-md-4">
            <div class="row g-1">
                <input type="hidden" name="work_div" id="work_div">
                <input type="hidden" name="page_no" id="page_no" placeholder="페이지 번호">
                <input type="hidden" name="seq" id="seq">
                <input type="hidden" name="regId" id="regId">

                <div class="col-md-4">
                    <select class="form-select mt-3" name="searchDiv" id="searchDiv">
                        <option value="">전체</option>
                        <c:forEach var="item" items="${boardSearch}">
                           <option value="${item.subCode}" <c:if test="${search.searchDiv == item.subCode}">selected</c:if>>${item.midList}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-md-8">
                    <input class="form-control mt-3" type="search" name="searchWord" id="searchWord" placeholder="검색어 입력" value="${search.searchWord}">
                </div>
            </div>
        </form>

        <div class="col-md-1 d-flex">
            <button type="button" class="btn btn-dark mt-3">
                <i class="bi bi-search"></i>
            </button>
        </div>
    </div>

    <div class="table-outset mt-2">
        <table id="boardTable" class="table">
            <tbody>
              <c:choose>
                <c:when test="${list.size() >0 }">
                  <c:forEach var="item" items="${list}">
	                <tr class="table-row">
	                    <th class="tbseq" style="display: none;"><input type="password" value="${item.boardSeq}"></th>
	                    <th class="table-dark text-center align-middle col-2">	                    
						<c:choose>
						    <c:when test="${item.divYn eq '20' || item.divYn == 20}">
			                                               공지사항
						    </c:when>
						    <c:otherwise>
		            	                    건의사항 / 소통
						    </c:otherwise>
						</c:choose>	                    
	                    </th>
	                    <td class="table-light">
	                        <div class="row-content">
	                            <div class="title">${item.title}</div>
	                            <div class="details">수정자 : ${item.modId} 조회수 : ${item.readCnt}</div>
	                        </div>
	                    </td>
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
    
    String url = "/ehr/board/{div}";
    String scriptName = "pageRetrieve";
    
    out.print(StringUtil.renderingPaging(maxNum, currentPageNo, rowPerPage, bottomCount, url, scriptName));
    %>      
      </div>
    </div> 
    <!--// pagenation -->
</section>

<jsp:include page="/WEB-INF/views/footer.jsp" />
<script src = "${CP}/resources/js/bootstrap.bundle.min.js"></script>   
<script>
function pageRetrieve(url,pageNo){
    const frm = document.querySelector("#bRfrm");
    frm.pageNo.value = pageNo;
    console.log("pageNo: "+pageNo);
    
    frm.action = url;    
    frm.submit();
}
</script>   
</body>
</html>