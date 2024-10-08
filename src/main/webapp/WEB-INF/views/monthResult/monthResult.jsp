<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
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
<link rel="stylesheet" href="${CP}/resources/css/mRstyle.css">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<title>ANPA</title>
<script>
document.addEventListener('DOMContentLoaded', function() {
        
	
    // .nav 클래스의 두 번째 .nav-item의 자식 .nav-link를 선택합니다
    const firstNavLink = document.querySelector('.nav .nav-item:nth-child(2) .nav-link');
    const selectBtn = document.querySelector("#selectBtn");
    //console.log("selectBtn"+selectBtn);
    const yearSelectInput = document.querySelector("#yearSelect");
    //console.log("yearSelectInput"+yearSelectInput);
    const monthSelectInput = document.querySelector("#monthSelect");
    //console.log("monthSelectInput"+monthSelectInput);

    
    // 시작, 끝 날짜 디폴트 값
    let now = new Date(); //현재 날짜 호출
    let currentYear = now.getFullYear(); //년도 호출
    let currentMonth = String(now.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 +1 현재 월
    
    
    // 선택한 요소에 "active" 클래스를 추가합니다
    firstNavLink.classList.add('active');
    
    // 서버에서 JSP로 현재 날짜를 가져옵니다.
    const dateValue = '<%= new SimpleDateFormat("yyyy-MM").format(new Date()) %>';

    //페이지 로드 시 현재날짜로 셋팅
    selectYear();
   
    // 연도 선택 박스의 값이 변경될 때 호출되는 함수
    yearSelectInput.addEventListener("change", function(event) {
    	//console.log("yearSelectInput 연도 선택 : "+yearSelectInput.value);
    	yearChange()
        
    });
    
    monthSelectInput.addEventListener("change",function(event){
        //console.log("monthSelectInput 월 선택 : "+monthSelectInput.value);
        event.stopPropagation();//이벤트 버블링 방지
    });
    
    selectBtn.addEventListener("click",function(event){
	   	//console.log("selectBtn click");
	   	event.stopPropagation();//이벤트 버블링 방지
	   	todayMonthData();
	   	locBigData();
	   	locMidData();
	   	factorMidData();

    });
    
    //--------------------------------------------------------------------------
    //연도 선택시 월 옵션이 나타나게하는 함수
    function yearChange() {
	    const selectedYear = yearSelectInput.value;
	
	    // 월 선택 박스 초기화
	    monthSelectInput.innerHTML = '<option value="">월 선택</option>';
	
	    // 연도가 선택된 경우
	    if (selectedYear) {
	        // 현재 연도와 선택된 연도가 같다면 현재 월까지만 추가
	        if (selectedYear === currentYear.toString()) {
	            for (let i = 1; i <= parseInt(currentMonth); i++) {
	                const option = document.createElement('option');
	                const monthValue = String(i).padStart(2, '0'); // '01', '02', ... '12'
	
	                option.value = monthValue;
	                option.textContent = monthValue + '월';
	
	                // 현재 월을 기본 선택으로 설정
	                if (monthValue === currentMonth) {
	                    option.selected = true;
	                }
	
	                monthSelectInput.appendChild(option);
	            }
	        } else {
	            // 선택된 연도가 현재 연도가 아닌 경우 모든 월을 추가
	            for (let i = 1; i <= 12; i++) {
	                const option = document.createElement('option');
	                const monthValue = String(i).padStart(2, '0'); // '01', '02', ... '12'
	
	                option.value = monthValue;
	                option.textContent = monthValue + '월';
	
	                monthSelectInput.appendChild(option);
	            }
	        }
	    }
	}//--yearChange() end
    
    //DB에 있는 연도 데이터 가져오기
    function selectYear(){
        //console.log("selectYear:"+selectYear);
        
        //비동기 통신
        let type = "GET";
        let url = "/ehr/monthfiredata/selectYear.do";
        let async = "true";
        let dataType = "html";
        let params = {
        };
        
        PClass.pAjax(url,params,dataType,type,async,function(data){
            //console.log("data: ",data);
            
                    
            if(data){
                try{
                    const jsonData = JSON.parse(data);
                    const allMessage = jsonData.message;
                    //console.log(jsonData);
                    //console.log(allMessage.messageId);
                    

                    if(isEmpty(allMessage) === false && 1 === allMessage.messageId){
                       
                    }else{
                        alert("에러야"+allMessage.messageContents);
                    }
                    // 연도 데이터를 기반으로 select 태그에 옵션 추가
                    const yearSelectInput = document.querySelector("#yearSelect");
                    yearSelectInput.innerHTML = '<option value="">연도 선택</option>'; // 초기화

                    jsonData.yearData.forEach(function(year) {
                        const option = document.createElement('option');
                        option.value = year;
                        
                        //현재날짜 연도를 페이지에 기본으로 띄우기
                        if(year == currentYear){
                            option.selected = true;                            
                            //현재날짜 셋팅되면 월 선택에 옵션 나오도록                       
                            yearChange();
                        }

                        option.textContent = year + '년';
                        yearSelectInput.appendChild(option);
                       
                    });
                    
                   //연도선택에 값이 들어가면 월선택에 옵션이 나오도록
                    const monthSelectInput = document.querySelector("#monthSelect");                   
                    yearChange();
                    
                    //페이지 로드시 기본으로 들어가는 날짜의 화재데이터 나오도록 실행
                    todayMonthData();
                    locBigData();
                    locMidData();
                    factorMidData();

                }catch(e){
                  
                }
                
            }
            
        });//--pAjax end 
    }//--selectYear() end
    
    function todayMonthData(regDt){
    	//console.log("todayMonthData(regDt):"+selectBtn);
    	
    	if(isEmpty(yearSelectInput.value)) {
            alert("연도를 선택하세요.");
            yearSelectInput.focus();
            return;
        }
    	if(isEmpty(monthSelectInput.value) == true) {
            alert("월을 선택하세요.");
            monthSelectInput.focus();
            return;
        }
    	
    	//비동기 통신
     	let type = "GET";
    	let url = "/ehr/monthfiredata/todayMonthData.do";
    	let async = "true";
    	let dataType = "html";
        let params = {
             "regDt" : yearSelectInput.value+monthSelectInput.value
        };
        
        PClass.pAjax(url,params,dataType,type,async,function(data){
            //console.log("data: ",data);
            
            if(data){
                try{
                    const jsonData = JSON.parse(data);
                    const allMessage = jsonData.message;
                    //console.log(jsonData);
                    //console.log(allMessage.messageId);
                    if(isEmpty(allMessage) === false && 1 === allMessage.messageId){
                        //alert(allMessage.messageContents);
                        tmJSON(jsonData);
                    
                    }else{
                        alert("에러야"+allMessage.messageContents);
                    }
                }catch(e){
                    alert("화재데이터가 없습니다.");
                }
                
            }
            
        });//--pAjax end 
    }//--todayMonthData() end
    
   function locBigData(regDt){
        //console.log("locBigData(regDt):"+selectBtn);
        
        //비동기 통신
        let type = "GET";
        let url = "/ehr/monthfiredata/locBigData.do";
        let async = "true";
        let dataType = "html";
        let params = {
             "regDt" : yearSelectInput.value+monthSelectInput.value
        };
        
        PClass.pAjax(url,params,dataType,type,async,function(data){
            //console.log("data: ",data);
            
            if(data){
                try{
                    const jsonData = JSON.parse(data);
                    const allMessage = jsonData.message;
                    //console.log(jsonData);
                    //console.log(allMessage.messageId);

                    if(isEmpty(allMessage) === false && 1 === allMessage.messageId){
                        //alert(allMessage.messageContents);
                        lbJSON(jsonData);
                    
                    }else{
                        alert("에러야"+allMessage.messageContents);
                    }
                }catch(e){
                    //alert("화재데이터가 없습니다.");
                }
                
            }
            
        });//--pAjax end 
    }//--locBigData() end
    
   function locMidData(regDt){
       //console.log("locMidData(regDt):"+selectBtn);
       
       //비동기 통신
       let type = "GET";
       let url = "/ehr/monthfiredata/locMidData.do";
       let async = "true";
       let dataType = "html";
       let params = {
            "regDt" : yearSelectInput.value+monthSelectInput.value
       };
       
       PClass.pAjax(url,params,dataType,type,async,function(data){
           //console.log("data: ",data);
           
           if(data){
               try{
                   const jsonData = JSON.parse(data);
                   const allMessage = jsonData.message;
                   //console.log(jsonData);
                   //console.log(allMessage.messageId);

                   if(isEmpty(allMessage) === false && 1 === allMessage.messageId){
                       //alert(allMessage.messageContents);
                       lmJSON(jsonData);
                   
                   }else{
                       alert("에러야"+allMessage.messageContents);
                   }
               }catch(e){
                   //alert("화재데이터가 없습니다.");
               }
               
           }
           
       });//--pAjax end 
   }//--locMidData() end
   
   function factorMidData(regDt){
       //console.log("factorMidData(regDt):"+selectBtn);
       
       //비동기 통신
       let type = "GET";
       let url = "/ehr/monthfiredata/factorMidData.do";
       let async = "true";
       let dataType = "html";
       let params = {
            "regDt" : yearSelectInput.value+monthSelectInput.value
       };
       
       PClass.pAjax(url,params,dataType,type,async,function(data){
           //console.log("data: ",data);
           
           if(data){
               try{
                   const jsonData = JSON.parse(data);
                   const allMessage = jsonData.message;
                   //console.log(jsonData);
                   //console.log(allMessage.messageId);

                   if(isEmpty(allMessage) === false && 1 === allMessage.messageId){
                       //alert(allMessage.messageContents);
                       fmJSON(jsonData);
                   
                   }else{
                       alert("에러야"+allMessage.messageContents);
                   }
               }catch(e){
                   //alert("화재데이터가 없습니다.");
               }
               
           }
           
       });//--pAjax end 
   }//--factorMidData() end
   
    
   function fmJSON(jsonData) {
       //console.log("fmData:"+jsonData.fmData);
       //console.log("fmData[0]:"+jsonData.fmData[0].subFactorMidNm);
       //쿼리 화재요인소분류1순위
       const subFactorMidNm1 = jsonData.fmData[0].subFactorMidNm.toLocaleString();
       //console.log("subFactorMidNm1:"+subFactorMidNm1);
       const monthFireCount1 = jsonData.fmData[0].monthFireCount.toLocaleString();
       const monthAvg1 = jsonData.fmData[0].monthAvg.toLocaleString();
       //쿼리 화재요인소분류2순위
       const subFactorMidNm2 = jsonData.fmData[1].subFactorMidNm.toLocaleString();
       const monthFireCount2 = jsonData.fmData[1].monthFireCount.toLocaleString();
       const monthAvg2 = jsonData.fmData[1].monthAvg.toLocaleString();
       //쿼리 화재요인소분류3순위
       const subFactorMidNm3 = jsonData.fmData[2].subFactorMidNm.toLocaleString();
       const monthFireCount3 = jsonData.fmData[2].monthFireCount.toLocaleString();
       const monthAvg3 = jsonData.fmData[2].monthAvg.toLocaleString();
       //console.log("monthAvg3:"+monthAvg3);

       //--<div>태그 id
       const FactorMidDiv = document.querySelector('#FactorMid');
        //화면에서 화재요인소분류 상위3건 박스
       const FactorMidcontentHTML =
           `
           <h5 class="card-title">발화 원인</h5>
           <hr>
           <b><i class="bi bi-1-square-fill"></i> `+subFactorMidNm1+` `+monthFireCount1+` 건</b>
           <p class="card-text">일 평균 `+monthAvg1+` 건</p>
           <b><i class="bi bi-2-square-fill"></i> `+subFactorMidNm2+` `+monthFireCount2+` 건</b>
           <p class="card-text">일 평균 `+monthAvg2+` 건</p>
           <b><i class="bi bi-3-square-fill"></i> `+subFactorMidNm3+` `+monthFireCount3+` 건</b>
           <p class="card-text">일 평균 `+monthAvg3+` 건</p>         
           `;
       FactorMidDiv.innerHTML = FactorMidcontentHTML;

   }//--fmJSON end
    
   
   function lmJSON(jsonData) {
       //console.log("lmData:"+jsonData.lmData);
       //console.log("lmData[0]:"+jsonData.lmData[0].subLocMidNm);
       //쿼리 화재장소소분류1순위
       const subLocMidNm1 = jsonData.lmData[0].subLocMidNm.toLocaleString();
       //console.log("subLocBigNm1:"+subLocMidNm1);
       const monthFireCount1 = jsonData.lmData[0].monthFireCount.toLocaleString();
       const monthAvg1 = jsonData.lmData[0].monthAvg.toLocaleString();
       //쿼리 화재장소소분류2순위
       const subLocMidNm2 = jsonData.lmData[1].subLocMidNm.toLocaleString();
       const monthFireCount2 = jsonData.lmData[1].monthFireCount.toLocaleString();
       const monthAvg2 = jsonData.lmData[1].monthAvg.toLocaleString();
       //쿼리 화재장소소분류3순위
       const subLocMidNm3 = jsonData.lmData[2].subLocMidNm.toLocaleString();
       const monthFireCount3 = jsonData.lmData[2].monthFireCount.toLocaleString();
       const monthAvg3 = jsonData.lmData[2].monthAvg.toLocaleString();
       //console.log("monthAvg3:"+monthAvg3);

       //--<div>태그 id
       const LocMidDiv = document.querySelector('#LocMid');
        //화면에서 화재장소소분류 상위3건 박스
       const LocMidcontentHTML =
           `
           <h5 class="card-title">화재 장소 소분류</h5>
           <hr>
           <b><i class="bi bi-1-square-fill"></i> `+subLocMidNm1+` `+monthFireCount1+` 건</b>
           <p class="card-text">일 평균 `+monthAvg1+` 건</p>
           <b><i class="bi bi-2-square-fill"></i> `+subLocMidNm2+` `+monthFireCount2+` 건</b>
           <p class="card-text">일 평균 `+monthAvg2+` 건</p>
           <b><i class="bi bi-3-square-fill"></i> `+subLocMidNm3+` `+monthFireCount3+` 건</b>
           <p class="card-text">일 평균 `+monthAvg3+` 건</p>         
           `;
       LocMidDiv.innerHTML = LocMidcontentHTML;
   }//--lmJSON end
    
    function lbJSON(jsonData) {
    	//console.log("lbJSON:"+jsonData.lbData);
    	//console.log("lbJSON[0]:"+jsonData.lbData[0]);
    	//쿼리 화재장소대분류1순위
        const subLocBigNm1 = jsonData.lbData[0].subLocBigNm.toLocaleString();
        //console.log("subLocBigNm1:"+subLocBigNm1);
        const monthFireCount1 = jsonData.lbData[0].monthFireCount.toLocaleString();
        const monthAvg1 = jsonData.lbData[0].monthAvg.toLocaleString();
        //쿼리 화재장소대분류2순위
        const subLocBigNm2 = jsonData.lbData[1].subLocBigNm.toLocaleString();
        const monthFireCount2 = jsonData.lbData[1].monthFireCount.toLocaleString();
        const monthAvg2 = jsonData.lbData[1].monthAvg.toLocaleString();
        //쿼리 화재장소대분류3순위
        const subLocBigNm3 = jsonData.lbData[2].subLocBigNm.toLocaleString();
        const monthFireCount3 = jsonData.lbData[2].monthFireCount.toLocaleString();
        const monthAvg3 = jsonData.lbData[2].monthAvg.toLocaleString();
        //console.log("monthAvg3:"+monthAvg3);

        //--<div>태그 id
        const LocBigDiv = document.querySelector('#LocBig');
         //화면에서 화재장소대분류 상위3건 박스
        const LocBigcontentHTML =
            `
            <h5 class="card-title">화재 장소</h5>
            <hr>
            <b><i class="bi bi-1-square-fill"></i> `+subLocBigNm1+` `+monthFireCount1+` 건</b>
            <p class="card-text">일 평균 `+monthAvg1+` 건</p>
            <b><i class="bi bi-2-square-fill"></i> `+subLocBigNm2+` `+monthFireCount2+` 건</b>
            <p class="card-text">일 평균 `+monthAvg2+` 건</p>
            <b><i class="bi bi-3-square-fill"></i> `+subLocBigNm3+` `+monthFireCount3+` 건</b>
            <p class="card-text">일 평균 `+monthAvg3+` 건</p>         
            `;
        LocBigDiv.innerHTML = LocBigcontentHTML;
    
        
    }//--lbJSON end
    
    
    function tmJSON(jsonData) {
		//--쿼리 데이터 값
		const todayFireCount = jsonData.tmData.todayFireCount.toLocaleString();
		//console.log("todayFireCount:"+todayFireCount);
		const monthFireCount = jsonData.tmData.monthFireCount.toLocaleString();
		const lastYearDayFireCount = jsonData.tmData.lastYearDayFireCount.toLocaleString();
		const lastYearMonthFireCount = jsonData.tmData.lastYearMonthFireCount.toLocaleString();
		const todayDead = jsonData.tmData.todayDead.toLocaleString();
		const monthDead = jsonData.tmData.monthDead.toLocaleString();
		const lastYearDayDead = jsonData.tmData.lastYearDayDead.toLocaleString();
		const lastYearMonthDead = jsonData.tmData.lastYearMonthDead.toLocaleString();
		
		const todayInjured        =jsonData.tmData.todayInjured.toLocaleString(); 
		const monthInjured        =jsonData.tmData.monthInjured.toLocaleString(); 
		const lastYearDayInjured  =jsonData.tmData.lastYearDayInjured.toLocaleString(); 
		const lastYearMonthInjured=jsonData.tmData.lastYearMonthInjured.toLocaleString(); 
		
		// 숫자에 서식 적용
		const todayAmount = jsonData.tmData.todayAmount.toLocaleString();
		
		const monthAmount         =jsonData.tmData.monthAmount.toLocaleString();
		const lastYearDayAmount   =jsonData.tmData.lastYearDayAmount.toLocaleString(); 
		const lastYearMonthAmount =jsonData.tmData.lastYearMonthAmount.toLocaleString();

		
		//--<div>태그 id
		const FireCountDiv = document.querySelector('#FireCount');
		const DeadCountDiv = document.querySelector('#DeadCount');
		const InjuredCountDiv = document.querySelector('#InjuredCount');
		const AmountCountDiv = document.querySelector('#AmountCount');
		
		//화면에서 화재건수 박스
		const FireCountcontentHTML =
			`
			<b class="card-icon"><i class="bi bi-fire"></i></b>
	        <h5 class="card-title">화재 건수</h5>
	        <p class="card-text">오늘 `+todayFireCount+` 건 / 월 `+monthFireCount+` 건</p>
	        <hr>
	        <b>전년 동기</b>
	        <p class="card-text">일 `+lastYearDayFireCount+` 건 / 월 `+lastYearMonthFireCount+` 건</p>
	        `;
		FireCountDiv.innerHTML = FireCountcontentHTML;
		
		//화면에서 사망자수 박스
	    const DeadCountcontentHTML =
	        `
	        <b class="card-icon"><i class="bi bi-person-x-fill"></i></b>
	        <h5 class="card-title">사망자 수</h5>
	        <p class="card-text">오늘 `+todayDead+` 명 / 월 `+monthDead+` 명</p>
	        <hr>
	        <b>전년 동기</b>
	        <p class="card-text">일 `+lastYearDayDead+` 명 / 월 `+lastYearMonthDead+` 명</p>
	        `;
	    DeadCountDiv.innerHTML = DeadCountcontentHTML;
	    
	    //화면에서 부상자수 박스
	    const InjuredCountcontentHTML =
            `
            <b class="card-icon"><i class="bi bi-lungs-fill"></i></b>
            <h5 class="card-title">부상자 수</h5>
            <p class="card-text">오늘 `+todayInjured+` 명 / 월 `+monthInjured+` 명</p>
            <hr>
            <b>전년 동기</b>
            <p class="card-text">일 `+lastYearDayInjured+` 명 / 월 `+lastYearMonthInjured+` 명</p>
            `;
        InjuredCountDiv.innerHTML = InjuredCountcontentHTML;
        
        //화면에서 재산피해액 박스
        const AmountCountcontentHTML =
            `
            <b class="card-icon"><i class="bi bi-database-fill-x"></i></b>
            <h5 class="card-title">재산피해</h5>
            <p class="card-text">오늘 `+todayAmount+` 천원 / 월 `+monthAmount+` 천원</p>
            <hr>
            <b>전년 동기</b>
            <p class="card-text">일 `+lastYearDayAmount+` 천원 / 월 `+lastYearMonthAmount+` 천원</p>
            `;
        AmountCountDiv.innerHTML = AmountCountcontentHTML;
	   
        
	    
    }//--tmJSON() end

    
});//--DOMContentLoaded end   
</script>
</head>
<style>
    .form-control {
        appearance: auto; /* 브라우저 기본 스타일 사용 */
        -webkit-appearance: auto; /* Safari 및 Chrome 브라우저용 */
        -moz-appearance: auto; /* Firefox 브라우저용 */
    }
</style>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" />

<section class="content content2 content3 align-items-center">
    <h3>한 눈에 보는 화재 현황</h3>

    <form name = "mRfrm" id = "mRfrm">
        <input type = "hidden" name = "work_div" id = "work_div">
        <input type="hidden" name="page_no" id="page_no" placeholder="페이지 번호">
        <input type = "hidden" name = "seq" id = "seq">
        <div class="col-md-4 d-grid gap-2 d-md-flex ymSelectBox">                   
            <!-- 연 / 월  -->
            <select class="form-control" id="yearSelect">
            </select>  
            <select class="form-control" id="monthSelect">
                <option value="월 선택">월 선택</option>
            </select>    
            
            <input class="form-control" type="button" id="selectBtn" name="selectBtn" value="조회">     
        </div>
		  <div class="col-md-7 d-flex justify-content-end mRsysdate">
		    <%
		       Date date = new Date();
		       SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
		       String strDate = simpleDate.format(date);
		    %>
		    <div style="text-align: right; padding: 5px; margin-left: 50px;"> <!-- margin-left로 변경 -->
		        <p style="margin-right: 5px; display: inline; margin: 0;">오늘은</p>
		        <p style="color:#4169E1; display: inline; margin: 0;"> <%= strDate %> </p>
		        <p style="margin-left: 5px; display: inline; margin: 0;">입니다</p>
		    </div>
		</div>
    </form>

    <div class="row mt-3">
        <div class="col-md-3 mb-2">
            <div class="card text-center">
                <div class="card-body" id="FireCount">
                    <b class="card-icon"><i class="bi bi-fire"></i></b>
                    <h5 class="card-title">화재 건수</h5>
                    <p class="card-text" ></p>
                    <hr>
                    <b>전년 동기</b>
                    <p class="card-text"></p>
                </div>
            </div>
        </div>
        
        <div class="col-md-3 mb-2">
            <div class="card text-center">
                <div class="card-body" id="DeadCount">
                    <b class="card-icon"><i class="bi bi-person-x-fill"></i></b>
                    <h5 class="card-title">사망자 수</h5>
                    <p class="card-text"></p>
                    <hr>
                    <b>전년 동기</b>
                    <p class="card-text"></p>
                </div>
            </div>
        </div>
        
        <div class="col-md-3 mb-2">
            <div class="card text-center">
                <div class="card-body" id="InjuredCount">
                    <b class="card-icon"><i class="bi bi-lungs-fill"></i></b>
                    <h5 class="card-title">부상자 수</h5>
                    <p class="card-text"></p>
                    <hr>
                    <b>전년 동기</b>
                    <p class="card-text"></p>
                </div>
            </div>
        </div>
        
        <div class="col-md-3 mb-2">
            <div class="card text-center">
                <div class="card-body" id="AmountCount">
                    <b class="card-icon"><i class="bi bi-database-fill-x"></i></b>
                    <h5 class="card-title">재산피해</h5>
                    <p class="card-text"></p>
                    <hr>
                    <b>전년 동기</b>
                    <p class="card-text"></p>
                </div>
            </div>
        </div>
    </div>

    <div class="row mt-3">
        <div class="col-md-4 mb-2">
            <div class="card">
                <div class="card-body" id="LocBig">
                    <h5 class="card-title">화재 장소</h5>
                    <hr>
                    <b><i class="bi bi-1-square-fill"></i></b>
                    <p class="card-text">일 평균 0 건</p>
                    <b><i class="bi bi-2-square-fill"></i></b>
                    <p class="card-text">일 평균 0 건</p>
                    <b><i class="bi bi-3-square-fill"></i></b>
                    <p class="card-text">일 평균 0 건</p>
                </div>
            </div>
        </div>

        <div class="col-md-4 mb-2">
            <div class="card">
                <div class="card-body" id="LocMid">
                    <h5 class="card-title">화재 장소 소분류</h5>
                    <hr>
                    <b><i class="bi bi-1-square-fill"></i></b>
                    <p class="card-text">일 평균 0 건</p>
                    <b><i class="bi bi-2-square-fill"></i></b>
                    <p class="card-text">일 평균 0 건</p>
                    <b><i class="bi bi-3-square-fill"></i></b>
                    <p class="card-text">일 평균 0 건</p>
                </div>
            </div>
        </div>
        
        <div class="col-md-4 mb-2">
            <div class="card">
                <div class="card-body" id="FactorMid">
                    <h5 class="card-title">발화 원인</h5>
                    <hr>
                    <b><i class="bi bi-1-square-fill"></i></b>
                    <p class="card-text">일 평균 0 건</p>
                    <b><i class="bi bi-2-square-fill"></i></b>
                    <p class="card-text">일 평균 0 건</p>
                    <b><i class="bi bi-3-square-fill"></i></b>
                    <p class="card-text">일 평균 0 건</p>
                </div>
            </div>
        </div>
       
    </div>
</section>

<jsp:include page="/WEB-INF/views/footer.jsp" />
<script src = "${CP}/resources/js/bootstrap.bundle.min.js"></script>   
</body>
</html>