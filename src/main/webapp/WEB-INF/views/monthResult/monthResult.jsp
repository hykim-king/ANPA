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
<script src="${CP}/resources/js/common.js"></script>
<title>ANPA</title>
<script>
document.addEventListener('DOMContentLoaded', function() {
    // .nav 클래스의 두 번째 .nav-item의 자식 .nav-link를 선택합니다
    const firstNavLink = document.querySelector('.nav .nav-item:nth-child(2) .nav-link');
    const selectBtn = document.querySelector("#selectBtn");
    console.log("selectBtn"+selectBtn);
    const yearSelectInput = document.querySelector("#yearSelect");
    console.log("yearSelectInput"+yearSelectInput);
    const monthSelectInput = document.querySelector("#monthSelect");
    console.log("monthSelectInput"+monthSelectInput);

    
    // 선택한 요소에 "active" 클래스를 추가합니다
    firstNavLink.classList.add('active');
    
    // 서버에서 JSP로 현재 날짜를 가져옵니다.
    const dateValue = '<%= new SimpleDateFormat("yyyy-MM").format(new Date()) %>';

    // JavaScript를 통해 input 요소의 기본값을 현재 날짜로 설정
/*     document.getElementById('mRselect').value = dateValue; */
   
    
    selectBtn.addEventListener("click",function(event){
    	console.log("selectBtn click");
    	event.stopPropagation();//이벤트 버블링 방지
    	todayMonthData();
    	locBigData();
    	locMidData();
    	factorMidData();

    });
   
   
    // 연도 선택 박스의 값이 변경될 때 호출되는 함수
    yearSelectInput.addEventListener("change", function(event) {
    	console.log("yearSelectInput 연도 선택 : "+yearSelectInput.value);
        const selectedYear = this.value;

        // 월 선택 박스 초기화
        monthSelectInput.innerHTML = '<option value="">월 선택</option>';

        // 연도가 선택된 경우 1월부터 12월까지 추가
        if (selectedYear) {
        	 for (let i = 1; i <= 12; i++) {
                 const option = document.createElement('option');

                 // 1~9월에는 0을 붙이고, 10~12월에는 그대로 설정
                 if (i < 10) {
                     option.value = '0' + i; // 01, 02, ..., 09
                     option.textContent = '0' + i + '월'; // 1~9월에는 0 붙임
                 } else {
                     option.value = i; // 10, 11, 12
                     option.textContent = i + '월'; // 10, 11, 12월에는 그대로 출력
                 }

                 monthSelectInput.appendChild(option);
             }
        }
        
    });
    
    monthSelectInput.addEventListener("click",function(event){
        console.log("monthSelectInput 월 선택 : "+monthSelectInput.value);
        event.stopPropagation();//이벤트 버블링 방지
    });
    
    //--------------------------------------------------------------------------   
    function todayMonthData(regDt){
    	console.log("todayMonthData(regDt):"+selectBtn);
    	
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
            console.log("data: ",data);
            
            if(data){
                try{
                    const jsonData = JSON.parse(data);
                    const allMessage = jsonData.message;
                    console.log(jsonData);
                    console.log(allMessage.messageId);
                    if(isEmpty(allMessage) === false && 1 === allMessage.messageId){
                        alert(allMessage.messageContents);
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
        console.log("locBigData(regDt):"+selectBtn);
        
        //비동기 통신
        let type = "GET";
        let url = "/ehr/monthfiredata/locBigData.do";
        let async = "true";
        let dataType = "html";
        let params = {
             "regDt" : yearSelectInput.value+monthSelectInput.value
        };
        
        PClass.pAjax(url,params,dataType,type,async,function(data){
            console.log("data: ",data);
            
            if(data){
                try{
                    const jsonData = JSON.parse(data);
                    const allMessage = jsonData.message;
                    console.log(jsonData);
                    console.log(allMessage.messageId);

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
       console.log("locMidData(regDt):"+selectBtn);
       
       //비동기 통신
       let type = "GET";
       let url = "/ehr/monthfiredata/locMidData.do";
       let async = "true";
       let dataType = "html";
       let params = {
            "regDt" : yearSelectInput.value+monthSelectInput.value
       };
       
       PClass.pAjax(url,params,dataType,type,async,function(data){
           console.log("data: ",data);
           
           if(data){
               try{
                   const jsonData = JSON.parse(data);
                   const allMessage = jsonData.message;
                   console.log(jsonData);
                   console.log(allMessage.messageId);

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
       console.log("factorMidData(regDt):"+selectBtn);
       
       //비동기 통신
       let type = "GET";
       let url = "/ehr/monthfiredata/factorMidData.do";
       let async = "true";
       let dataType = "html";
       let params = {
            "regDt" : yearSelectInput.value+monthSelectInput.value
       };
       
       PClass.pAjax(url,params,dataType,type,async,function(data){
           console.log("data: ",data);
           
           if(data){
               try{
                   const jsonData = JSON.parse(data);
                   const allMessage = jsonData.message;
                   console.log(jsonData);
                   console.log(allMessage.messageId);

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
       console.log("fmData:"+jsonData.fmData);
       console.log("fmData[0]:"+jsonData.fmData[0].subFactorMidNm);
       //쿼리 화재장소대분류1순위
       const subFactorMidNm1 = jsonData.fmData[0].subFactorMidNm;
       console.log("subFactorMidNm1:"+subFactorMidNm1);
       const monthFireCount1 = jsonData.fmData[0].monthFireCount;
       const monthAvg1 = jsonData.fmData[0].monthAvg;
       //쿼리 화재장소대분류2순위
       const subFactorMidNm2 = jsonData.fmData[1].subFactorMidNm;
       const monthFireCount2 = jsonData.fmData[1].monthFireCount;
       const monthAvg2 = jsonData.fmData[1].monthAvg;
       //쿼리 화재장소대분류3순위
       const subFactorMidNm3 = jsonData.fmData[2].subFactorMidNm;
       const monthFireCount3 = jsonData.fmData[2].monthFireCount;
       const monthAvg3 = jsonData.fmData[2].monthAvg;
       console.log("monthAvg3:"+monthAvg3);

       //--<div>태그 id
       const FactorMidDiv = document.querySelector('#FactorMid');
        //화면에서 화재장소대분류 상위3건 박스
       const FactorMidcontentHTML =
           `
           <h5 class="card-title">발화 원인</h5>
           <hr>
           <b><i class="bi bi-1-square-fill"></i> `+subFactorMidNm1+` `+monthFireCount1+` 건</b>
           <p class="card-text">평균 `+monthAvg1+` 건</p>
           <b><i class="bi bi-2-square-fill"></i> `+subFactorMidNm2+` `+monthFireCount2+` 건</b>
           <p class="card-text">평균 `+monthAvg2+` 건</p>
           <b><i class="bi bi-3-square-fill"></i> `+subFactorMidNm3+` `+monthFireCount3+` 건</b>
           <p class="card-text">평균 `+monthAvg3+` 건</p>         
           `;
       FactorMidDiv.innerHTML = FactorMidcontentHTML;

   }//--fmJSON end
    
   
   function lmJSON(jsonData) {
       console.log("lmData:"+jsonData.lmData);
       console.log("lmData[0]:"+jsonData.lmData[0].subLocMidNm);
       //쿼리 화재장소대분류1순위
       const subLocMidNm1 = jsonData.lmData[0].subLocMidNm;
       console.log("subLocBigNm1:"+subLocMidNm1);
       const monthFireCount1 = jsonData.lmData[0].monthFireCount;
       const monthAvg1 = jsonData.lmData[0].monthAvg;
       //쿼리 화재장소대분류2순위
       const subLocMidNm2 = jsonData.lmData[1].subLocMidNm;
       const monthFireCount2 = jsonData.lmData[1].monthFireCount;
       const monthAvg2 = jsonData.lmData[1].monthAvg;
       //쿼리 화재장소대분류3순위
       const subLocMidNm3 = jsonData.lmData[2].subLocMidNm;
       const monthFireCount3 = jsonData.lmData[2].monthFireCount;
       const monthAvg3 = jsonData.lmData[2].monthAvg;
       console.log("monthAvg3:"+monthAvg3);

       //--<div>태그 id
       const LocMidDiv = document.querySelector('#LocMid');
        //화면에서 화재장소대분류 상위3건 박스
       const LocMidcontentHTML =
           `
           <h5 class="card-title">화재 장소 소분류</h5>
           <hr>
           <b><i class="bi bi-1-square-fill"></i> `+subLocMidNm1+` `+monthFireCount1+` 건</b>
           <p class="card-text">평균 `+monthAvg1+` 건</p>
           <b><i class="bi bi-2-square-fill"></i> `+subLocMidNm2+` `+monthFireCount2+` 건</b>
           <p class="card-text">평균 `+monthAvg2+` 건</p>
           <b><i class="bi bi-3-square-fill"></i> `+subLocMidNm3+` `+monthFireCount3+` 건</b>
           <p class="card-text">평균 `+monthAvg3+` 건</p>         
           `;
       LocMidDiv.innerHTML = LocMidcontentHTML;
   }//--lmJSON end
    
    function lbJSON(jsonData) {
    	console.log("lbJSON:"+jsonData.lbData);
    	console.log("lbJSON[0]:"+jsonData.lbData[0]);
    	//쿼리 화재장소대분류1순위
        const subLocBigNm1 = jsonData.lbData[0].subLocBigNm;
        console.log("subLocBigNm1:"+subLocBigNm1);
        const monthFireCount1 = jsonData.lbData[0].monthFireCount;
        const monthAvg1 = jsonData.lbData[0].monthAvg;
        //쿼리 화재장소대분류2순위
        const subLocBigNm2 = jsonData.lbData[1].subLocBigNm;
        const monthFireCount2 = jsonData.lbData[1].monthFireCount;
        const monthAvg2 = jsonData.lbData[1].monthAvg;
        //쿼리 화재장소대분류3순위
        const subLocBigNm3 = jsonData.lbData[2].subLocBigNm;
        const monthFireCount3 = jsonData.lbData[2].monthFireCount;
        const monthAvg3 = jsonData.lbData[2].monthAvg;
        console.log("monthAvg3:"+monthAvg3);

        //--<div>태그 id
        const LocBigDiv = document.querySelector('#LocBig');
         //화면에서 화재장소대분류 상위3건 박스
        const LocBigcontentHTML =
            `
            <h5 class="card-title">화재 장소</h5>
            <hr>
            <b><i class="bi bi-1-square-fill"></i> `+subLocBigNm1+` `+monthFireCount1+` 건</b>
            <p class="card-text">평균 `+monthAvg1+` 건</p>
            <b><i class="bi bi-2-square-fill"></i> `+subLocBigNm2+` `+monthFireCount2+` 건</b>
            <p class="card-text">평균 `+monthAvg2+` 건</p>
            <b><i class="bi bi-3-square-fill"></i> `+subLocBigNm3+` `+monthFireCount3+` 건</b>
            <p class="card-text">평균 `+monthAvg3+` 건</p>         
            `;
        LocBigDiv.innerHTML = LocBigcontentHTML;
    
        
    }//--lbJSON end
    
    
    function tmJSON(jsonData) {
		//--쿼리 데이터 값
		const todayFireCount = jsonData.tmData.todayFireCount;
		console.log("todayFireCount:"+todayFireCount);
		const monthFireCount = jsonData.tmData.monthFireCount;
		const lastYearDayFireCount = jsonData.tmData.lastYearDayFireCount;
		const lastYearMonthFireCount = jsonData.tmData.lastYearMonthFireCount;
		const todayDead = jsonData.tmData.todayDead;
		const monthDead = jsonData.tmData.monthDead;
		const lastYearDayDead = jsonData.tmData.lastYearDayDead;
		const lastYearMonthDead = jsonData.tmData.lastYearMonthDead;
		
		const todayInjured        =jsonData.tmData.todayInjured ;
		const monthInjured        =jsonData.tmData.monthInjured ;
		const lastYearDayInjured  =jsonData.tmData.lastYearDayInjured ;
		const lastYearMonthInjured=jsonData.tmData.lastYearMonthInjured ;
		
		const todayAmount         =jsonData.tmData.todayAmount ;
		const monthAmount         =jsonData.tmData.monthAmount ;
		const lastYearDayAmount   =jsonData.tmData.lastYearDayAmount ;
		const lastYearMonthAmount =jsonData.tmData.lastYearMonthAmount ;

		
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
	        <p class="card-text">오늘 `+lastYearDayFireCount+` 건 / 월 `+lastYearMonthFireCount+` 건</p>
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
	        <p class="card-text">오늘 `+lastYearDayDead+` 명 / 월 `+lastYearMonthDead+` 명</p>
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
            <p class="card-text">오늘 `+lastYearDayInjured+` 명 / 월 `+lastYearMonthAmount+` 명</p>
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
            <p class="card-text">오늘 `+lastYearDayAmount+` 천원 / 월 `+lastYearMonthInjured+` 천원</p>
            `;
        AmountCountDiv.innerHTML = AmountCountcontentHTML;
	   
        
	    
    }//--tmJSON() end

    
});//--DOMContentLoaded end   
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" />

<section class="content content2 content3 align-items-center">
    <h3>한 눈에 보는 화재 현황</h3>

    <form name = "mRfrm" id = "mRfrm" class="row g-1">
        <input type = "hidden" name = "work_div" id = "work_div">
        <input type="hidden" name="page_no" id="page_no" placeholder="페이지 번호">
        <input type = "hidden" name = "seq" id = "seq">
        <div class="col-md-4 d-grid gap-2 d-md-flex">                   
            <!-- 연 / 월  -->
            <select class="form-control" id="yearSelect">
                <option value="">연도 선택</option>
                <option value="2023">2023년</option>
                <option value="2024">2024년</option>
                <option value="2025">2025년</option>
            </select>  
            <select class="form-control" id="monthSelect">
                <option value="월 선택">월 선택</option>
            </select>    
            
            <input class = "form-control" type = "button" id="selectBtn" name="selectBtn" value="조회">      
        </div>
        <div class="col-md-7 d-flex justify-content-end mRsysdate">
        <%
           Date date = new Date();
           SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
           String strDate = simpleDate.format(date);
        
        %>
            <p style="margin-right : 5px;">오늘은 </p> <p style="color:#4169E1"> <%=strDate %><p> <p style="margin-left : 5px;"> 입니다</p>
        </div>
    </form>

    <div class="row mt-3">
        <div class="col-md-3 mb-2">
            <div class="card text-center">
                <div class="card-body" id="FireCount">
                    <b class="card-icon"><i class="bi bi-fire"></i></b>
                    <h5 class="card-title">화재 건수</h5>
                    <p class="card-text" >오늘  0 건 / 월 0 건</p>
                    <hr>
                    <b>전년 동기</b>
                    <p class="card-text">오늘 0 건 / 월 0 건</p>
                </div>
            </div>
        </div>
        
        <div class="col-md-3 mb-2">
            <div class="card text-center">
                <div class="card-body" id="DeadCount">
                    <b class="card-icon"><i class="bi bi-person-x-fill"></i></b>
                    <h5 class="card-title">사망자 수</h5>
                    <p class="card-text">오늘 0 명 / 월 0 명</p>
                    <hr>
                    <b>전년 동기</b>
                    <p class="card-text">오늘 0 명 / 월 0 명</p>
                </div>
            </div>
        </div>
        
        <div class="col-md-3 mb-2">
            <div class="card text-center">
                <div class="card-body" id="InjuredCount">
                    <b class="card-icon"><i class="bi bi-lungs-fill"></i></b>
                    <h5 class="card-title">부상자 수</h5>
                    <p class="card-text">오늘 0 명 / 월 0 명</p>
                    <hr>
                    <b>전년 동기</b>
                    <p class="card-text">오늘 0 명 / 월 0 명</p>
                </div>
            </div>
        </div>
        
        <div class="col-md-3 mb-2">
            <div class="card text-center">
                <div class="card-body" id="AmountCount">
                    <b class="card-icon"><i class="bi bi-database-fill-x"></i></b>
                    <h5 class="card-title">재산피해</h5>
                    <p class="card-text">오늘 0 천원 / 월 0 천원</p>
                    <hr>
                    <b>전년 동기</b>
                    <p class="card-text">오늘 0 천원 / 월 0 천원</p>
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
                    <b><i class="bi bi-1-square-fill"></i> 비주거 0건</b>
                    <p class="card-text">평균 0 건</p>
                    <b><i class="bi bi-2-square-fill"></i> 주거 0건</b>
                    <p class="card-text">평균 0 건</p>
                    <b><i class="bi bi-3-square-fill"></i> 차량 0건</b>
                    <p class="card-text">평균 0 건</p>
                </div>
            </div>
        </div>

        <div class="col-md-4 mb-2">
            <div class="card">
                <div class="card-body" id="LocMid">
                    <h5 class="card-title">화재 장소 소분류</h5>
                    <hr>
                    <b><i class="bi bi-1-square-fill"></i> 상점가 0건</b>
                    <p class="card-text">평균 0 건</p>
                    <b><i class="bi bi-2-square-fill"></i> 아파트 0건</b>
                    <p class="card-text">평균 0 건</p>
                    <b><i class="bi bi-3-square-fill"></i> 공터 0건</b>
                    <p class="card-text">평균 0 건</p>
                </div>
            </div>
        </div>
        
        <div class="col-md-4 mb-2">
            <div class="card">
                <div class="card-body" id="FactorMid">
                    <h5 class="card-title">발화 원인</h5>
                    <hr>
                    <b><i class="bi bi-1-square-fill"></i> 전기적 요인 0건</b>
                    <p class="card-text">평균 0 건</p>
                    <b><i class="bi bi-2-square-fill"></i> 부주의 0건</b>
                    <p class="card-text">평균 0 건</p>
                    <b><i class="bi bi-3-square-fill"></i> 기계적 0건</b>
                    <p class="card-text">평균 0 건</p>
                </div>
            </div>
        </div>
       
    </div>
</section>

<jsp:include page="/WEB-INF/views/footer.jsp" />
<script src = "${CP}/resources/js/bootstrap.bundle.min.js"></script>   
</body>
</html>