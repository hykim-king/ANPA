PClass = {
    pAjax : function(url, params,dataType="html",type="GET",async = true, _callback){
        
        return  $.ajax({
                    type: type, 
                    url:url,
                    asyn:async,
                    dataType:dataType,
                    data: params,
                    success:function(response){//통신 성공
                        _callback(response)
                        
                    },
                    error:function(response){//실패시 처리
                        console.error("error:"+response);
                    }
                });
        
    }


}


/**
 * 입력 값이 비어있는지 확인하는 함수 
 * @param {any} value : 입력값
 * @returns {boolean} 비어 있으면 true, 그렇치 않으면 false
 */ 
let isEmpty  = function(value){
  
  if(null === value || value == undefined){
    return true;
  }
  
  if(typeof value === 'string' && value.trim() === ''){
    return true;
  }
  
  if(Array.isArray(value) && value.length === 0){
    return true;
  }
  
  return false;
}


let pager = function (maxNum, currentPageNo, rowPerPage, bottomCount, url, scriptName) {
    let html = [];
    
    let maxPageNo = Math.floor((maxNum - 1) / rowPerPage) + 1;
    let startPageNO = Math.floor((currentPageNo - 1) / bottomCount) * bottomCount + 1;
    let endPageNo = Math.floor((currentPageNo - 1) / bottomCount + 1) * bottomCount;
    
    let nowBlockNo = Math.floor((currentPageNo - 1) / bottomCount) + 1;
    let maxBlockNo = Math.floor((maxNum - 1) / bottomCount) + 1;

    if (currentPageNo > maxPageNo) {
        return '';
    }

    html.push('<ul class="pagination justify-content-center">');
    
    // <<
    if (nowBlockNo > 1 && nowBlockNo <= maxBlockNo) {
        html.push('<li class="page-item">');
        html.push('<a class="page-link" href="javascript:' + scriptName + '(\'' + url + '\', 1);">');
        html.push('<span>&laquo;</span>');
        html.push('</a>');
        html.push('</li>');
    }
    
    // <
    if (startPageNO > bottomCount) {
        html.push('<li class="page-item">');
        html.push('<a class="page-link" href="javascript:' + scriptName + '(\'' + url + '\',' + (startPageNO - bottomCount) + ');">');
        html.push('<span>&lt;</span>');
        html.push('</a>');
        html.push('</li>');
    }
    
    // 1 2 3 ... 10
    for (let inx = startPageNO; inx <= maxPageNo && inx <= endPageNo; inx++) {
        if (inx == currentPageNo) {
            html.push('<li class="page-item">');
            html.push('<a class="page-link active" href="#">' + inx + '</a>');
            html.push('</li>');
        } else {
            html.push('<li class="page-item">');
            html.push('<a class="page-link" href="javascript:' + scriptName + '(\'' + url + '\',' + inx + ');">' + inx + '</a>');
            html.push('</li>');
        }
    }
    
    // >
    if (maxPageNo > endPageNo) {
        html.push('<li class="page-item">');
        html.push('<a class="page-link" href="javascript:' + scriptName + '(\'' + url + '\',' + ((nowBlockNo * bottomCount) + 1) + ');">');
        html.push('<span>&gt;</span>');
        html.push('</a>');
        html.push('</li>');
    }
    
    // >>
    if (maxPageNo > endPageNo) {
        html.push('<li class="page-item">');
        html.push('<a class="page-link" href="javascript:' + scriptName + '(\'' + url + '\',' + maxPageNo + ');">');
        html.push('<span>&raquo;</span>');
        html.push('</a>');
        html.push('</li>');
    }
    
    html.push('</ul>');

    return html.join('');
}

// JWT를 디코딩하는 방법 (예: base64url 디코딩)
function parseJwt(token) {
    if (!token) return null;

    const base64Url = token.split('.')[1];
    const base64 = decodeURIComponent(atob(base64Url).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    return JSON.parse(base64);
}

// JWT가 존재하는지 확인
function isTokenAvailable() {
    const token = localStorage.getItem('jwtToken');
    return token !== null;
}

// JWT를 로컬 스토리지에서 가져오고 디코딩한 후, 사용자 정보 반환
function getUserInfo() {
    const token = localStorage.getItem('jwtToken');
    if (token) {
        const decodedToken = parseJwt(token);
        if (decodedToken) {
            return {
                userId: decodedToken.userId,
                username: decodedToken.username
            };
        }
    }
    return null;
}

// 사용자가 로그인 되어 있는지 확인
function isUserLoggedIn() {
    return isTokenAvailable();
}

// 사용자 정보 표시 함수
function displayUserInfo() {
    const userInfo = getUserInfo();
    if (userInfo) {
    	console.log("JWT 정보 : " + userInfo.username + " / " + userInfo.userId);
    }else{
		console.log("사용자 정보 출력불가 규격 외 오류발생");
	}
}

// 초기화 함수, 페이지 로드 시 호출
function initializeUserInfo() {
    if (isUserLoggedIn()) {
        displayUserInfo();
    } else {
        console.log('GUEST 모드입니다');
    }
}

// 페이지 로드 시 초기화 호출
document.addEventListener('DOMContentLoaded', function() {
	initializeUserInfo();
}); 