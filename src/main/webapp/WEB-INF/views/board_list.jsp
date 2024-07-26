<%--
/**
	Class Name: 
	Description:
	Modification information
	
	수정일     수정자      수정내용
    -----   -----  -------------------------------------------
    2024. 7. 26        최초작성 
    
    author eclass 개발팀
    since 2020.11.23
    Copyright (C) by KandJang All right reserved.
*/
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="kor">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<head>
<!-- 파비콘 추가 -->
<link rel="icon" type="image/png" href="assest/img/favicon.ico">
<link rel="stylesheet" href="assest/css/bootstrap.css">
<!-- bootstrap icon -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<!-- bootstrap icon -->
<link rel="stylesheet" href="assest/css/basic_style.css">
<link rel="stylesheet" href="assest/css/board_style.css">
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
    <header id = "top_header">
        <div class = "d-flex justify-content-between align-items-center top_header_il">
            <div class="text-center top_header_il_obj top_logo">
                <img src="assest/img/logo_x.png" alt="#logo">                 
            </div>
            <h2 class="title d-flex align-items-center justify-content-center">ANPA</h2>
            <div class="text-center top_header_il_obj appmenu">
                <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960"  width="24px" fill="#dfdfdf"><path d="M240-160q-33 0-56.5-23.5T160-240q0-33 23.5-56.5T240-320q33 0 56.5 23.5T320-240q0 33-23.5 56.5T240-160Zm240 0q-33 0-56.5-23.5T400-240q0-33 23.5-56.5T480-320q33 0 56.5 23.5T560-240q0 33-23.5 56.5T480-160Zm240 0q-33 0-56.5-23.5T640-240q0-33 23.5-56.5T720-320q33 0 56.5 23.5T800-240q0 33-23.5 56.5T720-160ZM240-400q-33 0-56.5-23.5T160-480q0-33 23.5-56.5T240-560q33 0 56.5 23.5T320-480q0 33-23.5 56.5T240-400Zm240 0q-33 0-56.5-23.5T400-480q0-33 23.5-56.5T480-560q33 0 56.5 23.5T560-480q0 33-23.5 56.5T480-400Zm240 0q-33 0-56.5-23.5T640-480q0-33 23.5-56.5T720-560q33 0 56.5 23.5T800-480q0 33-23.5 56.5T720-400ZM240-640q-33 0-56.5-23.5T160-720q0-33 23.5-56.5T240-800q33 0 56.5 23.5T320-720q0 33-23.5 56.5T240-640Zm240 0q-33 0-56.5-23.5T400-720q0-33 23.5-56.5T480-800q33 0 56.5 23.5T560-720q0 33-23.5 56.5T480-640Zm240 0q-33 0-56.5-23.5T640-720q0-33 23.5-56.5T720-800q33 0 56.5 23.5T800-720q0 33-23.5 56.5T720-640Z"/></svg>
            </div>
        </div>
    
        <ul class="nav nav-tabs text-center justify-content-center">
            <li class="nav-item d-flex justify-content-center align-items-center">
                <a class="nav-link" aria-current="page" href="#">화재통계</a>
            </li>
            <li class="nav-item d-flex justify-content-center align-items-center">
                <a class="nav-link" href="#">1달 화재현황</a>
            </li>
            <li class="nav-item d-flex justify-content-center align-items-center">
                <a class="nav-link" href="#">화재 예방법</a>
            </li>
            <li class="nav-item dropdown d-flex justify-content-center align-items-center">
                <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">알림마당</a>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="#">공지사항</a></li>
                    <li><hr class="dropdown-divider"></li>
                    <li><a class="dropdown-item" href="#">건의사항</a></li>
                </ul>
            </li>
        </ul>
    </header>

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
                <tr>
                    <th class = "tbseq" style="display: none;"></th>
                    <th rowspan="2" class="table-dark text-center align-middle col-2">건의사항 / 소통</th>
                    <td class="table-light">제목1</td>
                </tr>
                <tr>
                    <td class="table-light">수정자 : ${userId} 조회수 : ${cnt}</td>                
                </tr>
            </tbody>                      
        </table>
    </div>
    
</section>


<footer class="footer">
    <p>(주)HBI기술연구소 에이콘아카데미<br>
    서울특별시 마포구 양화로 122 LAB7 빌딩 3층, 4층
    전화 : 02-2231-6412</p>            
    <p>Copyright(C) 2024 National Fire Data System. All rights reserved.</p>
</footer>    
<script src = "assest/js/bootstrap.bundle.min.js"></script>     
</body>
</html>