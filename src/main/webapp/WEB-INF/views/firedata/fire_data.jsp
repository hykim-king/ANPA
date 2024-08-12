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
<script src="${CP}/resources/js/common.js"></script> 
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
    //날짜시작
    const fRdateStart = document.querySelector('#fRdateStart');
    //날짜끝
    const fRdateEnd = document.querySelector('#fRdateEnd');
    //조회버튼
    const doRetrieveBtn = document.querySelector('#doRetrieve');
    
    let div = '';
    let selectedText = '';
    //검색조건
    const searchConditions = document.querySelector('#searchConditions');
    
    // 시작, 끝 날짜 디폴트 값
    let now = new Date();
	let year = now.getFullYear();
	let month = String(now.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 +1
	let lastmonth = String(now.getMonth()).padStart(2, '0'); // 월은 0부터 시작하므로 +1
	let day = String(now.getDate()).padStart(2, '0'); // 날짜는 1부터 시작
	
    let currentDate = year+'-'+month+'-'+day;
	let lastMonthDate = year+'-'+lastmonth+'-'+day;
    fRdateEnd.value = currentDate;
    fRdateStart.value = lastMonthDate;
    //이벤트
    
    //조회버튼
    doRetrieveBtn.addEventListener("click",function(event){
    	console.log('fRdateStart: '+fRdateStart.value);
    	console.log('fRdateEnd: '+fRdateEnd.value);
    	console.log('bigList: '+bigList.value);
    	console.log('midList: '+midList.value);
    	
    	let url = '/ehr/firedata/totalData.do';
        let params = {
            "searchDateStart": fRdateStart.value,
            "searchDateEnd" : fRdateEnd.value,
            "BigNm" : '',
            "MidNm" : '',
            "subCityBigNm" : '',
            "subCityMidNm" : '',
            "searchDiv" : ""
        };
        dataType = 'json';
        type = 'GET';
        async = 'true';
    	
        PClass.pAjax(url, params,dataType,type,async,function(response){
        	if(response){
        		try{
        			let data = response;
        			console.log('data: '+data);
        			
        			
        		}catch(e){
                    console.error("data가 null혹은, undefined 입니다.",e);
                    alert("data가 null혹은, undefined 입니다.");  
                }
        		
        	}else{
                console.error("통신실패!");
                alert("통신실패!");
            }
        	
        	
        });//아작스
    	
    });
    
    //화재요인버튼
    factorBtn.addEventListener("click",function(event){
    	div = 'factor'
    	let searchDiv = '10';
    	midList.value = '';
    	
    	searchConditions.textContent = factorBtn.textContent;
    	
    	categoryBigList(searchDiv,div);
    });
    
    //화재장소버튼
    locationBtn.addEventListener("click",function(event){
        div = 'location'
        let searchDiv = '10';
        midList.value = '';
        
        searchConditions.textContent = locationBtn.textContent;
        
        categoryBigList(searchDiv,div);
    });
    
    //대분류 선택시
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
    
    //소분류 선택시
    midList.addEventListener("change",function(event){
        let midselectedText = midList.options[midList.selectedIndex].textContent
        
        if(div == 'factor'){
            searchConditions.textContent = factorBtn.textContent + ' - '+ selectedText;
            searchConditions.textContent += ' - ' + midselectedText;
        }else{
            searchConditions.textContent = locationBtn.textContent + ' - '+ selectedText;
            searchConditions.textContent += ' - ' + midselectedText;
        }
        
    });
    
    //코드 리스트 대+소분류 
    function categoryMidList(searchDiv,div,searchWord){
    	let url = '/ehr/firedata/cityList.do';
    	let params = {
   			"searchDiv": searchDiv,
            "div" : div,
            "searchWord" : searchWord
    	};
    	dataType = 'json';
    	type = 'GET';
    	async = 'true';
    	
    	PClass.pAjax(url, params,dataType,type,async,function(response){
    		if(response){
    			try{
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
                        
                    });
    				
    			}catch(e){
    				console.error("data가 null혹은, undefined 입니다.",e);
   				    alert("data가 null혹은, undefined 입니다.");  
    			}
    			
    		}else{
    			console.error("통신실패!");
                alert("통신실패!");
    		}
    		
    	});
    	
    }
    
    //코드리스트 대분류-전체 검색
    function categoryBigList(searchDiv,div){
    	let url = '/ehr/firedata/cityList.do';
        let params = {
      		"searchDiv": searchDiv,
            "div" : div
        };
        dataType = 'json';
        type = 'GET';
        async = 'true';
        
        PClass.pAjax(url, params,dataType,type,async,function(response){
            if(response){
                try{
                	let data = response;
                    
                    bigList.innerHTML = '<option value="">전체</option>';
                    
                    data.forEach(function(item){
                        let html = '<option value="'+item.subCode+'">'+item.bigList+'</option>';
                        bigList.innerHTML += html;
                    });//forEach
                    
                }catch(e){
                    console.error("data가 null혹은, undefined 입니다.",e);
                    alert("data가 null혹은, undefined 입니다.");  
                }
                
            }else{
                console.error("통신실패!");
                alert("통신실패!");
            }
            
        });
    	
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
    <p class="form-control work_div_result">검색 조건 : <span id="searchConditions"></span> </p>
    
    <div class="input_date row g-1 d-flex align-items-center">
        <p class="col-2 m-0"><i class="bi bi-calendar"></i> 검색기간 : </p>
        <input type="date" class="col form-input" name="fRdateStart" id="fRdateStart" value="${currentDate }">
        <p class="col-auto m-0"> - </p>
        <input type="date" class="col form-input" name="fRdateEnd" id="fRdateEnd">
    </div>
    <div class="mt-2 col-auto d-flex justify-content-end">
        <button type="button" class="btn btn-success me-1" id = "doRetrieve">조회</button> 
    </div>
    <form name = "fRfrm" id = "fRfrm" class="row g-1">
            
    
    </form>
</section>

<jsp:include page="/WEB-INF/views/footer.jsp" />
<script src = "${CP}/resources/js/bootstrap.bundle.min.js"></script>        
</body>
</html>