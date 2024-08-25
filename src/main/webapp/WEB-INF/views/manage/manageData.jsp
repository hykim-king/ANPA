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
	// 관리자 여부 확인 시작
	function adminCheck(){
	    const isAdmin = "${sessionScope.user.adminYn}";
	    if(isAdmin != 1 && isAdmin != "1"){
	      window.location.replace("/ehr/main/index.do");
	      alert("관리자가 아닙니다");
	    }   		
	}
	
    setInterval(function() {
    	adminCheck();
    }, 2500);    
    // 관리자 여부 확인 끝
    
	// 변수 선언
	const popUp = document.querySelector(".popup");
	const doSaveDataBtn = document.querySelector("#doSaveData");
    const frmSaveBtn = document.querySelector("#frmSaveBtn");
	const doDeleteDataBtn = document.querySelector("#doDeleteData");
    const frmUpdate = document.querySelector("#frmDataUpdate");
    const frmUpdateBtn = document.querySelector("#frmUpdateBtn");
    const checkAll = document.querySelector('#checkAll');
    const doRetrieveBtn = document.querySelector("#doRetrieve");
    const frmUpCloseBtn = document.querySelector("#frmUpCloseBtn");
    const cBselect = document.querySelector("#cBselect");
    const cMselect = document.querySelector("#cMselect");
    const lBselect = document.querySelector("#lBselect");
    const lMselect = document.querySelector("#lMselect");
    const fBselect = document.querySelector("#fBselect");
    const fMselect = document.querySelector("#fMselect");
    const UcBselect = document.querySelector("#UcBselect");
    const UcMselect = document.querySelector("#UcMselect");
    const UlBselect = document.querySelector("#UlBselect");
    const UlMselect = document.querySelector("#UlMselect");
    const UfBselect = document.querySelector("#UfBselect");
    const UfMselect = document.querySelector("#UfMselect");
    const rows = document.querySelectorAll("#manageDataTable tbody tr");
    let fireSeq = "";
    const fireSeqs = [];  
    
	// 업데이트 버튼 클릭 실행
	updateBtnsClick();
	midOptionSelectDelSession();
	
	// 클릭 이벤트 시작
	// 신규 데이터 저장 버튼
	frmSaveBtn.addEventListener("click",function(event){
		doSaveData();        
        event.stopPropagation(); // 이벤트 버블링 방지        
    });
	// 신규 데이터 저장 열기
	doSaveDataBtn.addEventListener("click",function(event){
		doSaveDataOpen();        
        event.stopPropagation(); // 이벤트 버블링 방지        
    });
	// 수정 버튼
	frmUpdateBtn.addEventListener("click",function(event){
        doUpdateData();        
        event.stopPropagation(); // 이벤트 버블링 방지        
    });
	// 삭제 버튼
	doDeleteDataBtn.addEventListener("click",function(event){
        doDeleteData();        
        event.stopPropagation(); // 이벤트 버블링 방지        
    });
	// 검색 버튼
	doRetrieveBtn.addEventListener('click', function(event) {
		doRetrieve(1);
		event.stopPropagation(); // 이벤트 버블링 방지
	});
	// 정보 수정 닫기버튼
    frmUpCloseBtn.addEventListener('click', function(event) {
        popUp.classList.add('popupHide');
        event.stopPropagation(); // 이벤트 버블링 방지
        event.stopImmediatePropagation(); 
    });
	// 클릭 이벤트 끝

	// 변화 감지 이벤트 시작
	// 시도 change이벤트
    cBselect.addEventListener("change",function(){
        cityCodeSet("", cBselect, cMselect);        
        const subCityMidNm = sessionStorage.getItem("cMselectValue");
        if (subCityMidNm != null && subCityMidNm !== '') {
            sessionStorage.removeItem("cMselectValue"); // 삭제   
        } 
    });	
    UcBselect.addEventListener("change",function(){
        cityCodeSet("", UcBselect, UcMselect);     
    });	
	// 화재요인 change이벤트
    fBselect.addEventListener("change",function(){
        factorCodeSet("", fBselect, fMselect);     
        const factorMidNm  = sessionStorage.getItem("fMselectValue");
        if (factorMidNm != null && factorMidNm !== '') {
	        sessionStorage.removeItem("fMselectValue"); // 삭제
        }
    });	
    UfBselect.addEventListener("change",function(){
        factorCodeSet("", UfBselect, UfMselect);        
    });	
	// 화재장소 change이벤트
    lBselect.addEventListener("change",function(){
        locCodeSet("", lBselect, lMselect);  
        const locMidNm = sessionStorage.getItem("lMselectValue");
        if (locMidNm != null && locMidNm !== '') {
	        sessionStorage.removeItem("lMselectValue"); // 삭제
        }
    });	
    UlBselect.addEventListener("change",function(){
        locCodeSet("", UlBselect, UlMselect);        
    });	
	// 변화 감지 이벤트 끝
	
	// 함수 시작
	// 신규 데이터 저장 버튼
	function doSaveData(){
		if(isEmpty(frmUpdate.UcBselect.value) == true){
            alert("지역을 선택 하세요.");
            frmUpdate.UcBselect.focus();
            return;
        }
        if(isEmpty(frmUpdate.UfBselect.value) == true){
            alert("요인을 선택 하세요.");
            frmUpdate.UfBselect.focus();
            return;
        }
        if(isEmpty(frmUpdate.UlBselect.value) == true){
            alert("장소를 선택 하세요.");
            frmUpdate.UlBselect.focus();
            return;
        }
        
        if(confirm("저장 하시겠습니까?")===false) return;
        
        // 지역
        var UcselectValue = "";        
        if(isEmpty(frmUpdate.UcMselect.value) == true){
            UcselectValue = frmUpdate.UcBselect.value;
        }else{
            UcselectValue = frmUpdate.UcMselect.value;          
        }        
        console.log(UcselectValue);
        
        // 요인
        var UfselectValue = "";        
        if(isEmpty(frmUpdate.UfMselect.value) == true){
            UfselectValue = frmUpdate.UfBselect.value;
        }else{
            UfselectValue = frmUpdate.UfMselect.value;          
        }        
        console.log(UfselectValue);
        
        // 장소
        var UlselectValue = "";        
        if(isEmpty(frmUpdate.UlMselect.value) == true){
            UlselectValue = frmUpdate.UlBselect.value;
        }else{
            UlselectValue = frmUpdate.UlMselect.value;          
        }        
        console.log(UlselectValue);
        
        const userIdSession = "${sessionScope.user.userId}";        
        console.log(userIdSession);
        
        let type = "POST"; 
        let url = "/ehr/manage/doSaveData.do";
        let async = "true";
        let dataType = "html";
        let params = {
            "injuredSum" : frmUpdate.injuredSum.value,
            "dead" : frmUpdate.dead.value,
            "injured" : frmUpdate.injured.value,
            "amount" : frmUpdate.amount.value,
            "subFactor" : UfselectValue,
            "subLoc" : UlselectValue,
            "subCity" : UcselectValue,
            "regId" : userIdSession
        };
       
        PClass.pAjax(url,params,dataType,type,async,function(data){
            console.log("data: ",data);

            if(data){
                let message = JSON.parse(data); 
                try{
                    if(isEmpty(message) === false && 1 === message.messageId){                        
                        alert(message.messageContents);
                        doRetrieve(1);
                    }else{
                        alert(message.messageContents);
                    }

                }catch(error){
                    alert("널 혹은 언디파인드임");
                }
            }

        }); 
	}	
	// 데이터 설정 리셋
	function popUpReset(){
		frmUpdate.UfireSeq.value = "";
        frmUpdate.injuredSum.value = "";
        frmUpdate.dead.value       = "";
        frmUpdate.injured.value    = "";
        frmUpdate.amount.value     = "";
        frmUpdate.UcBselect.value  = "";
        cityCodeSet("", frmUpdate.UcBselect, frmUpdate.UcMselect);
        frmUpdate.UcMselect.value  = "";
        frmUpdate.UfBselect.value  = "";
        factorCodeSet("", frmUpdate.UfBselect, frmUpdate.UfMselect);
        frmUpdate.UfMselect.value  = "";
        frmUpdate.UlBselect.value  = "";
        locCodeSet("", frmUpdate.UlBselect, frmUpdate.UlMselect);
        frmUpdate.UlMselect.value  = "";
	}	
	// 신규 데이터 등록 열기
	function doSaveDataOpen(){
        console.log('doSaveData');
        
        popUp.classList.remove('popupHide');
        popUp.classList.add('popupSave');
        popUp.classList.remove('popupUpdate');
        
        popUpReset();        
	}	
	// 화재정보 삭제
	function doDeleteData(){
        console.log('doDeleteData');
        
        // 각 행을 반복 처리합니다
        rows.forEach(function(row) {
            // 현재 행의 체크박스를 찾습니다
            const checkbox = row.querySelector("td.checkbox input.chk");

            // 체크박스가 체크되어 있는지 확인합니다
            if (checkbox.checked) {
                // 현재 행의 fireSeq를 찾습니다
                fireSeq = row.querySelector("td.fireSeqTd input").value;
                fireSeqs.push(fireSeq);
            }
        });
        
        // fireSeqs 체크 여부
        if(isEmpty(fireSeqs) == true){
            alert('체크된 화재정보가 존재하지 않습니다. 잘못된 경로!');
        }else if(false == confirm('삭제 하시겠습니까?')){
              return;
        }  
        
        let type = "GET"; 
        let url = "/ehr/manage/doDeleteData.do";
        let async = "true";
        let dataType = "html";
        let params = {
            "fireSeqs": JSON.stringify(fireSeqs)
        };
        
        PClass.pAjax(url,params,dataType,type,async,function(data){
            console.log("data: ",data);

            if(data){
                let message = JSON.parse(data);

                if(isEmpty(message)==false){
                    alert(message.messageContents);

                    doRetrieve(1);
                }else{
                    alert(message.messageContents);
                }
            }else{
            	alert("개발자에게 문의하세요. 확인 불가능한 오류입니다");
            }

        }); 
	}
    // 전체 조회
	function doRetrieve(pageNo){    	
		const frm = document.querySelector("#frmDataSearch");
        frm.pageNo.value = pageNo;
        console.log("pageNo: "+pageNo);     
        
        const fMselectValue = frm.fMselect.value; 
        const lMselectValue = frm.lMselect.value; 
        const cMselectValue = frm.cMselect.value; 
        
        sessionStorage.setItem("fMselectValue", fMselectValue); // 세션에 저장
        sessionStorage.setItem("lMselectValue", lMselectValue); // 세션에 저장
        sessionStorage.setItem("cMselectValue", cMselectValue); // 세션에 저장
        
        frm.action = "/ehr/manage/doRetrieveData.do";    
        frm.submit();
    }
	// 단건 조회
	function doSelectOne(fireSeq){            
		const allMessageBox = document.querySelector(".allMessage");
		const firedataBox = document.querySelector(".firedata");
		const messageBox = document.querySelector(".message");
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
		      
		      frmUpdate.UfireSeq.value = firedata.fireSeq;
		      frmUpdate.injuredSum.value = firedata.injuredSum;
		      frmUpdate.dead.value       = firedata.dead;
		      frmUpdate.injured.value    = firedata.injured;
		      frmUpdate.amount.value     = firedata.amount;
		      frmUpdate.UcBselect.value  = firedata.todayInjured;
		      cityCodeSet(jsonObj.firedata.subCity, frmUpdate.UcBselect, frmUpdate.UcMselect);
		      if (!isEmpty(firedata.todayFireCount) && 
		    		    firedata.todayFireCount !== 0 && 
		    		    firedata.todayFireCount !== '0'){
		    	    frmUpdate.UfBselect.value  = firedata.todayFireCount;	
		      }else{
		    	    frmUpdate.UfBselect.value  = firedata.subFactor;	
		      }
		      factorCodeSet(jsonObj.firedata.subFactor, frmUpdate.UfBselect, frmUpdate.UfMselect);
              if (!isEmpty(firedata.todayDead) && 
                      firedata.todayDead !== 0 && 
                      firedata.todayDead !== '0'){  
			      frmUpdate.UlBselect.value  = firedata.todayDead;
              }else{
			      frmUpdate.UlBselect.value  = firedata.subLoc;   
		      }
		      locCodeSet(jsonObj.firedata.subLoc, frmUpdate.UlBselect, frmUpdate.UlMselect);
		      
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
	// 수정 메서드
	function doUpdateData(){		
        if(isEmpty(frmUpdate.UcBselect.value) == true){
            alert("지역을 선택 하세요.");
            frmUpdate.UcBselect.focus();
            return;
        }
        if(isEmpty(frmUpdate.UfBselect.value) == true){
            alert("요인을 선택 하세요.");
            frmUpdate.UfBselect.focus();
            return;
        }
        if(isEmpty(frmUpdate.UlBselect.value) == true){
            alert("장소를 선택 하세요.");
            frmUpdate.UlBselect.focus();
            return;
        }
		
        if(confirm("수정 하시겠습니까?")===false) return;
        
        // 지역
        var UcselectValue = "";        
        if(isEmpty(frmUpdate.UcMselect.value) == true){
        	UcselectValue = frmUpdate.UcBselect.value;
        }else{
        	UcselectValue = frmUpdate.UcMselect.value;        	
        }        
        console.log(UcselectValue);
        
        // 요인
        var UfselectValue = "";        
        if(isEmpty(frmUpdate.UfMselect.value) == true){
        	UfselectValue = frmUpdate.UfBselect.value;
        }else{
        	UfselectValue = frmUpdate.UfMselect.value;        	
        }        
        console.log(UfselectValue);
        
        // 장소
        var UlselectValue = "";        
        if(isEmpty(frmUpdate.UlMselect.value) == true){
        	UlselectValue = frmUpdate.UlBselect.value;
        }else{
        	UlselectValue = frmUpdate.UlMselect.value;        	
        }        
        console.log(UlselectValue);
        
        const userIdSession = "${sessionScope.user.userId}";        
        console.log(userIdSession);
        
        let type = "POST"; 
        let url = "/ehr/manage/doUpdateData.do";
        let async = "true";
        let dataType = "html";
        let params = {
            "injuredSum" : frmUpdate.injuredSum.value,
            "dead" : frmUpdate.dead.value,
            "injured" : frmUpdate.injured.value,
            "amount" : frmUpdate.amount.value,
            "subFactor" : UfselectValue,
            "subLoc" : UlselectValue,
            "subCity" : UcselectValue,
            "modId" : userIdSession,
            "fireSeq" : frmUpdate.UfireSeq.value
        };
       
        PClass.pAjax(url,params,dataType,type,async,function(data){
            console.log("data: ",data);

            if(data){
                let message = JSON.parse(data); 
                try{
                    if(isEmpty(message) === false && 1 === message.messageId){                        
                        alert(message.messageContents);
                        doRetrieve(1);
                    }else{
                        alert(message.messageContents);
                    }

                }catch(error){
                    alert("널 혹은 언디파인드임");
                }
            }else{
            	alert("개발자에게 문의하세요. 확인 불가능한 오류입니다");
            }

        }); 
	}
	
	// 테이블 수정 버튼 함수
	function updateBtnsClick() {
	    const updateBtns = document.querySelectorAll("#manageDataTable tbody tr .btn");
	
	    updateBtns.forEach(function(updateBtn) {
	        updateBtn.addEventListener("click", function() {
	            if (!confirm('해당 정보를 수정 하시겠습니까?')) return;
	            
	            // 클릭된 버튼이 속한 행을 찾습니다
	            const row = this.closest('tr');
	
	            // 해당 행에서 .fireSeqTd 클래스의 td를 찾아서 그 내부의 input 요소의 value를 읽어옵니다
	            const fireSeqInput = row.querySelector('.fireSeqTd input');
	            fireSeq = fireSeqInput.value.trim();
	            
	            console.log(fireSeq);
	            doSelectOne(fireSeq);
	            popUp.classList.remove('popupHide');
	            popUp.classList.remove('popupSave');
	            popUp.classList.add('popupUpdate');
	        });
	    });
	}
	// 중분류 옵션 자동 선택 및 중분류 세션 정보 삭제
	function midOptionSelectDelSession(){
        const factorMidNm  = sessionStorage.getItem("fMselectValue");
        const locMidNm     = sessionStorage.getItem("lMselectValue");
        const subCityMidNm = sessionStorage.getItem("cMselectValue");
		
        if (factorMidNm != null && factorMidNm !== '') {
            const factorMidNm = sessionStorage.getItem("fMselectValue");
			factorCodeSet(factorMidNm, fBselect, fMselect);
        }else if (isEmpty(fBselect) === false) {          
            factorCodeSet("", fBselect, fMselect); 
        }
        if (locMidNm != null && locMidNm !== '') {
            const locMidNm = sessionStorage.getItem("lMselectValue");
            locCodeSet(locMidNm, lBselect, lMselect);
        }else if (isEmpty(lBselect) === false) {          
            locCodeSet("", lBselect, lMselect);  
        }
        if (subCityMidNm != null && subCityMidNm !== '') {
            const subCityMidNm = sessionStorage.getItem("cMselectValue");
            cityCodeSet(subCityMidNm, cBselect, cMselect);
        }else if (isEmpty(cBselect) === false) {          
            cityCodeSet("", cBselect, cMselect);
        }        
	}
	// 시도 선택 시 시군구 변경
	function cityCodeSet(subCityMidNm, cBselect, cMselect) {	
	    const cityCode = cBselect.value;
	    const url = "/ehr/manage/doSelectCode.do";
	    const type = "GET";
	    
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
                        if (subCityMidNm == item.subCode) {
                            option.selected = true;
                        }else{
                            option.selected = false;                            
                        } 
	                    option.text = item.midList;
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
	function factorCodeSet(factorMidNm, fBselect, fMselect) {    
        const factorCode = fBselect.value;
        const url = "/ehr/manage/doSelectCode.do";
        const type = "GET";
        
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
                        if (factorMidNm == item.subCode) {
                            option.selected = true;
                        }else{
                            option.selected = false;                            
                        }
                        option.text = item.midList;
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
	
	function locCodeSet(locMidNm, lBselect, lMselect) {    
        const locCode = lBselect.value;
        const url = "/ehr/manage/doSelectCode.do";
        const type = "GET";
        
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
                        if (locMidNm == item.subCode) {
                        	option.selected = true;
                        }else{
                        	option.selected = false;                        	
                        }
                        option.text = item.midList;
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
    <h3>관리자 페이지 - 화재 정보 </h3>

    <div class="row g-1 align-items-center mt-2">
        <div class="col-md-auto">
           <button id="doDeleteData" class="btn btn-danger">삭제</button>
           <button id="doSaveData" class="btn btn-success">추가</button>
        </div>
        
        <form name="frmDataSearch" id="frmDataSearch" class="col-md-auto ms-auto">
            <div class="row g-1">
                <input type="hidden" name="work_div" id="work_div">
                <input type="hidden" name="pageNo" id="pageNo" value="${search.pageNo}" placeholder="페이지 번호">
                <input type="hidden" name="seq" id="seq">
    
                <div class="col-md-auto">
                    <label for="dateStart" style="padding-top : 6px">검색 날짜 : </label>
                </div>
                <div class="col-md-auto">
				    <input class="form-control" type="date" value="${not empty search.searchDateStart ? search.searchDateStart : ''}" id="dateStart" name="dateStart">
                </div>
                <div class="col-md-auto">
                    <label for="dateEnd" style="padding-top : 6px">종료 날짜 : </label>
                </div>
                <div class="col-md-auto">
					<input class="form-control" type="date" value="${not empty search.searchDateEnd ? search.searchDateEnd : ''}" id="dateEnd" name="dateEnd">
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

    <div class="mt-3 p-0 text-center">
        <div class="table-responsive">
        <table id="manageDataTable" class="table table-bordered full-width-table">
            <thead>
                <tr>
                    <th class="align-middle" data-label="전체 체크"><input id="checkAll" type="checkbox"></th>
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
	                    <td class="align-middle checkbox" data-label="체크박스"><input type="checkbox" class="chk"></td>
                        <td class="align-middle" data-label="날짜">${item.regDt}</td>
	                    <td class="align-middle d-none fireSeqTd"><input type="password" value="${item.fireSeq}"></td>
	                    <td class="align-middle" data-label="사상자">${item.injuredSum}</td>
	                    <td class="align-middle" data-label="사망자">${item.dead}</td>
	                    <td class="align-middle" data-label="부상자">${item.injured}</td>
	                    <td class="align-middle" data-label="화재요인">${item.subFactorBigNm} - ${item.subFactorMidNm}</td>
	                    <td class="align-middle" data-label="화재장소">${item.subLocBigNm} - ${item.subLocMidNm}</td>
	                    <td class="align-middle" data-label="화재지역">${item.subCityBigNm} ${item.subCityMidNm}</td>
	                    <td class="align-middle" data-label="등록자">${item.regId}</td>
	                    <td class="align-middle d-none">${item.modDt}</td>
	                    <td class="align-middle d-none">${item.modId}</td>
	                    <td class="align-middle" data-label="수정"><button class="btn btn-secondary">수정</button></td>
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
<div class = "popup popupHide">
<form action="#" name="frmDataUpdate" id="frmDataUpdate" class="form-horizontal">
  <div class="row m-0">
      <div class="col-sm-12 p-0">
        <button class="btn btn-danger" id="frmUpCloseBtn" type="button">X</button>
      </div>
  </div>

  <div class="row m-0 mb-2">
      <div class="col-sm-auto d-none">
        <input type="password" class="form-control" name="UfireSeq" id="UfireSeq"   required="required">
      </div> 
  </div>
  
  <div class="row m-0 mb-2">
      <label for="injuredSum" class="pLabel col-sm-3 col-form-label">사상자</label>
      <input type="number" class="pInput form-control" name="injuredSum" id="injuredSum"   required="required">
  </div>
  
  <div class="row m-0 mb-2">
      <label for="dead" class="pLabel col-sm-3 col-form-label">사망자</label>
      <input type="number" class="pInput form-control" name="dead" id="dead" required="required">
  </div>  
  
  <div class="row m-0 mb-2">
      <label for="injured" class="pLabel col-sm-3 col-form-label">부상자</label>
      <input type="number" class="pInput form-control" name="injured" id="injured" required="required">
  </div>
  
  <div class="row m-0 mb-2">
      <label for="amount" class="pLabel col-sm-3 col-form-label">피해금액 (단위 : 천원)</label>
      <input type="number" class="pInput form-control" name="amount" id="amount" required="required">
  </div>    
  
  <div class="row m-0 mb-2">
      <select class="form-select" name="UcBselect" id="UcBselect">
          <option value="">시도 전체</option>
          <c:forEach var="item" items="${cityCode}">
              <c:if test="${item.mainCode == 0}">
                  <option value="${item.subCode}">${item.bigList}</option>
              </c:if>
          </c:forEach>
      </select>
  </div>
  <div class="row m-0 mb-2">
      <select class="form-select" name="UcMselect" id="UcMselect">
          <option value="">시군구 전체</option>
      </select>
  </div>
  <div class="row m-0 mb-2">
      <select class="form-select" name="UfBselect" id="UfBselect">
          <option value="">화재요인 전체</option>
          <c:forEach var="item" items="${factorCode}">
            <c:if test="${item.mainCode == 0}">
                <option value="${item.subCode}">${item.bigList}</option>
            </c:if>
          </c:forEach>
      </select>
  </div>
  <div class="row m-0 mb-2">
      <select class="form-select" name="UfMselect" id="UfMselect">
          <option value="">화재요인 미선택</option>
      </select>
  </div>
  <div class="row m-0 mb-2">
      <select class="form-select" name="UlBselect" id="UlBselect">
          <option value="">화재장소 전체</option>
          <c:forEach var="item" items="${LocCode}">
            <c:if test="${item.mainCode == 0}">
                <option value="${item.subCode}">${item.bigList}</option>
            </c:if>
          </c:forEach>
      </select>
  </div>
  <div class="row m-0 mb-2">
      <select class="form-select" name="UlMselect" id="UlMselect">
          <option value="">화재장소 미선택</option>
      </select>
  </div>

  <div class="row m-0 mb-2">
      <div class="col-sm-12 p-0">
        <button class="btn btn-success" id="frmUpdateBtn" type="button">수정</button>
        <button class="btn btn-success" id="frmSaveBtn" type="button">저장</button>
      </div>
  </div>    
</form>
</div>
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