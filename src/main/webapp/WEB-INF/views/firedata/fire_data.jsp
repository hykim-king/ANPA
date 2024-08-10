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
    
    //화재 요인 버튼
    const factorBtn = document.querySelector('#factor');
    //화재 장소 버튼
    const locationBtn = document.querySelector('#location');
    //대분류
    const bigList = document.querySelector('#bigList');
    //소분류
    const midList = document.querySelector('#midList');
    
    let div = '';
    let selectedText = '';
    //검색조건
    const searchConditions = document.querySelector('#searchConditions');
    /* ----------------------------검색 조건 오류 잡아라 소분류 안떠 */
    /* ----------------------------검색 조건 오류 잡아라 소분류 안떠 */
    /* ----------------------------검색 조건 오류 잡아라 소분류 안떠 */
    /* ----------------------------검색 조건 오류 잡아라 소분류 안떠 */
    //이벤트
    factorBtn.addEventListener("click",function(event){
    	div = 'factor'
    	let searchDiv = '10';
    	
    	searchConditions.textContent = factorBtn.textContent;
    	
    	categoryBigList(searchDiv,div);
    });
    
    locationBtn.addEventListener("click",function(event){
        div = 'location'
        let searchDiv = '10';
        
        searchConditions.textContent = locationBtn.textContent;
        
        categoryBigList(searchDiv,div);
    });
    
    bigList.addEventListener("change",function(event){
        let searchDiv = '20';
        let searchWord = bigList.value;
        
        selectedText = bigList.options[bigList.selectedIndex].textContent
        
        
        if(div == 'factor'){
        	searchConditions.textContent = factorBtn.textContent;
        	searchConditions.textContent += ' - '+ selectedText;
        }else{
        	searchConditions.textContent = locationBtn.textContent;
            searchConditions.textContent += ' - '+ selectedText;
        }
        
        if(bigList.value = '') midList.value = ''
        
        categoryMidList(searchDiv,div,searchWord);
    	
        for (let i = 0; i < bigList.options.length; i++) {
            if (bigList.options[i].value === searchWord) {
                bigList.selectedIndex = i;
                break;
            }
        }
    });
    
    midList.addEventListener("change",function(event){
        let midselectedText = midList.options[bigList.selectedIndex].textContent
        
        if(div == 'factor'){
            searchConditions.textContent = factorBtn.textContent + ' - '+ selectedText;
            searchConditions.textContent += ' - ' + midselectedText;
        }else{
            searchConditions.textContent = locationBtn.textContent + ' - '+ selectedText;
            searchConditions.textContent += ' - ' + midselectedText;
        }
        
    });
    
    function categoryMidList(searchDiv,div,searchWord){
        $.ajax({
            type: "GET", 
            url:"/ehr/firedata/cityList.do",
            asyn:"true",
            dataType:"json",
            data:{
                "searchDiv": searchDiv,
                "div" : div,
                "searchWord" : searchWord
                
            },
            success:function(response){//통신 성공
                let data = response;
                
                if(data ==''){
                    midList.innerHTML = '<option value="" disabled>전체</option>';
                    midList.selectedIndex = 0;
                }else{
                	midList.innerHTML = '<option value="" >전체</option>';
                }
                
                
                data.forEach(function(item){
                    
                    let html = '<option value="'+item.subCode+'">'+item.midList+'</option>';
                    midList.innerHTML += html;
                    
                });//forEach
            },
            error:function(response){//실패시 처리
                    console.log("error:"+response);
            }
        });//ajax
    }
    
    function categoryBigList(searchDiv,div){
        $.ajax({
            type: "GET", 
            url:"/ehr/firedata/cityList.do",
            asyn:"true",
            dataType:"json",
            data:{
                "searchDiv": searchDiv,
                "div" : div
            },
            success:function(response){//통신 성공
                let data = response;
                
                bigList.innerHTML = '<option value="">전체</option>';
                data.forEach(function(item){
                    
                    let html = '<option value="'+item.subCode+'">'+item.bigList+'</option>';
                    bigList.innerHTML += html;
                    
                });//forEach
            },
            error:function(response){//실패시 처리
                    console.log("error:"+response);
            }
        });//ajax
    }
    
});   
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" />

<section class="content content2 align-items-center">
    <h3>화재통계</h3>
    
    <!-- 카테고리 버튼 -->
    <div class="select_date row g-1 d-flex align-items-center">
        <button type="button" class="btn btn-success me-1" id = "factor">화재요인</button> 
        <button type="button" class="btn btn-secondary" id = "location">화재장소</button>
    </div>
    <!-- //카테고리 버튼 -->
    
    <form name = "fRfrm" id = "fRfrm" class="row g-1">
        <select class="me-2 col form-select" name="bigList" id="bigList">
            <option value="">대분류</option>
        </select>
        <select class="col form-select" name="midList" id="midList">
            <option value="">소분류</option>
        </select>
    </form>
        
    <form name = "fRfrm" id = "fRfrm" class="row g-1">
        
    
    </form>
    <p class="form-control work_div_result">검색 조건 : <span id="searchConditions"></span> </p>
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
        <button type="button" class="btn btn-success me-1" id = "doRetrieve">조회</button> 
        <button type="button" class="btn btn-secondary" id = "doRetrieve">초기화</button> 
    </div>
</section>

<jsp:include page="/WEB-INF/views/footer.jsp" />
<script src = "${CP}/resources/js/bootstrap.bundle.min.js"></script>        
</body>
</html>