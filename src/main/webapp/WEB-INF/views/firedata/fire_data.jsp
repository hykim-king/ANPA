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
<link rel="stylesheet" href="${CP}/resources/css/fRstyle.css">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<title>ANPA</title>
<script>
document.addEventListener('DOMContentLoaded', function() {
    // .nav 클래스의 첫 번째 .nav-item의 자식 .nav-link를 선택합니다
    const firstNavLink = document.querySelector('.nav .nav-item:first-child .nav-link');

    // 선택한 요소에 "active" 클래스를 추가합니다
    firstNavLink.classList.add('active');
});   
</script>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>화재 통계</title>
    <link href="${CP}/resources/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .btn-group .btn.active {
            background-color: #007bff;
            color: white;
            border-color: #007bff;
        }
        .category-container {
            margin-top: 20px;
        }
        .category-item {
            cursor: pointer;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            margin-bottom: 5px;
            background-color: #f8f9fa;
        }
        .category-item.active {
            background-color: #007bff;
            color: white;
        }
        .subcategory {
            padding-left: 20px;
            display: none;
            margin-top: 10px;
        }
        .subcategory .category-item {
            background-color: #e9ecef;
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" />

<section class="content content2 align-items-center">
    <h3>화재통계</h3>

    <form name="fRfrm" id="fRfrm" class="row g-1">
        <input type="hidden" name="work_div" id="work_div">
        <input type="hidden" name="page_no" id="page_no" placeholder="페이지 번호">
        <input type="hidden" name="seq" id="seq">
        
        <!-- 화재 유형 버튼 -->
        <div class="btn-group" role="group" aria-label="화재 유형">
            <button type="button" class="btn btn-outline-primary" id="fireTypeBtn" onclick="toggleCategory()">화재유형</button>
        </div>

        <!-- 화재 유형 대분류 및 소분류 -->
        <div id="category-container" class="category-container" style="display: none;">
            <h5>화재 유형 대분류</h5>
            <div class="category-item" onclick="showSubcategory('type1')">대분류 1</div>
            <div class="category-item" onclick="showSubcategory('type2')">대분류 2</div>
            
            <div id="subcategory-type1" class="subcategory">
                <div class="category-item">소분류 1-1</div>
                <div class="category-item">소분류 1-2</div>
            </div>
            <div id="subcategory-type2" class="subcategory">
                <div class="category-item">소분류 2-1</div>
                <div class="category-item">소분류 2-2</div>
            </div>
        </div>

        <!-- 지역 셀렉트 박스 -->
        <select class="me-2 col form-select" name="fRselect" id="fRselect" style="width: 100px;">
            <option value="">지역</option>
        </select>
    </form>
    
    <p class="form-control work_div_result">검색 조건 : </p>
    <div class="select_date row g-1 d-flex align-items-center">
        <p class="col-2 m-0"><i class="bi bi-calendar"></i> 검색기간 : </p>
        <select class="col form-select" name="fRdateStart" id="fRdateStart">
            <option value="">시작 날짜</option>
        </select>
        <p class="col-auto m-0">-</p>
        <select class="col form-select" name="fRdateEnd" id="fRdateEnd">
            <option value="">종료 날짜</option>
        </select>
    </div>
    <div class="mt-2 col-auto d-flex justify-content-end">
        <button type="button" class="btn btn-success me-1" id="doRetrieve">조회</button> 
        <button type="button" class="btn btn-secondary" id="doReset">초기화</button> 
    </div>
</section>

<jsp:include page="/WEB-INF/views/footer.jsp" />
<script src="${CP}/resources/js/bootstrap.bundle.min.js"></script>
<script>
    function toggleCategory() {
        const categoryContainer = document.getElementById('category-container');
        if (categoryContainer.style.display === 'none') {
            categoryContainer.style.display = 'block';
        } else {
            categoryContainer.style.display = 'none';
        }
    }

    function showSubcategory(type) {
        // Hide all subcategories
        document.querySelectorAll('.subcategory').forEach(subcat => {
            subcat.style.display = 'none';
        });

        // Remove 'active' class from all category items
        document.querySelectorAll('.category-item').forEach(item => {
            item.classList.remove('active');
        });

        // Show the selected subcategory
        document.getElementById(`subcategory-${type}`).style.display = 'block';

        // Set the clicked category item as active
        document.querySelector(`.category-item[onclick="showSubcategory('${type}')"]`).classList.add('active');
    }

    document.getElementById('doRetrieve').addEventListener('click', function() {
        // Perform the search action here
        alert('조회 버튼이 클릭되었습니다.');
    });

    document.getElementById('doReset').addEventListener('click', function() {
        // Reset the form and category display
        document.getElementById('fRfrm').reset();
        document.querySelectorAll('.category-item').forEach(item => {
            item.classList.remove('active');
        });
        document.querySelectorAll('.subcategory').forEach(subcat => {
            subcat.style.display = 'none';
        });
        document.getElementById('category-container').style.display = 'none';
    });
</script>
</body>
</html>

</html>