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
    const firstNavLink = document.querySelector('.nav .nav-item:nth-child(4) .nav-link');

    // 선택한 요소에 "active" 클래스를 추가합니다
    firstNavLink.classList.add('active');
});   
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" />

<section class="board_con content content2 content3 align-items-center">
    <h3>소통마당</h3>

    <div class="row g-1 align-items-center">
        <form name="bRfrm_user" id="bRfrm_user" class="col-md-4">
            <div class="row g-1">
                <input type="hidden" name="work_div" id="work_div">
                <input type="hidden" name="page_no" id="page_no" placeholder="페이지 번호">
                <input type="hidden" name="seq" id="seq">

                <div class="col-md-4">
                    <select class="form-select mt-3" name="bRselect" id="bRselect">
                        <option value="10">제목</option>
                        <option value="20">내용</option>
                    </select>
                </div>

                <div class="col-md-8">
                    <input class="form-control mt-3" type="search" name="bRsearch" id="bRsearch" placeholder="검색어 입력">
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
        <table class="table">
            <tbody>
                <tr class="table-row">
                    <th class="tbseq" style="display: none;"></th>
                    <th class="table-dark text-center align-middle col-2">건의사항 / 소통</th>
                    <td class="table-light">
                        <div class="row-content">
                            <div class="title">제목1</div>
                            <div class="details">수정자 : ${userId} 조회수 : ${cnt}</div>
                        </div>
                    </td>
                </tr>
                <tr class="table-row">
                    <th class="tbseq" style="display: none;"></th>
                    <th class="table-dark text-center align-middle col-2">건의사항 / 소통</th>
                    <td class="table-light">
                        <div class="row-content">
                            <div class="title">제목1</div>
                            <div class="details">수정자 : ${userId} 조회수 : ${cnt}</div>
                        </div>
                    </td>
                </tr>
            </tbody>                      
        </table>
    </div>
    
</section>

<jsp:include page="/WEB-INF/views/footer.jsp" />
<script src = "${CP}/resources/js/bootstrap.bundle.min.js"></script>     
</body>
</html>