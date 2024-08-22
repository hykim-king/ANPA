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
<script src="https://code.highcharts.com/highcharts.js"></script>
<!-- <script src="https://code.highcharts.com/modules/accessibility.js"></script> -->
<link rel="stylesheet" href="${CP}/resources/css/basic_style.css">
<link rel="stylesheet" href="${CP}/resources/css/main_style.css">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="${CP}/resources/js/common.js"></script> 
<title>ANPA</title>
<script>
document.addEventListener('DOMContentLoaded', function() {
	
    // header start
    const appMenus = document.querySelector('.appmenu');    
    
    appMenus.addEventListener('click', function(){
        console.log("상단 앱 메뉴 클릭");
    });
    // header end
    
    // 함수 시작
    // 서브 차트 함수 시작    
    const monFireCnt00 = parseFloat("${monthFrData00.monthFireCount}"); // 현재 
    const monFireCnt01 = parseFloat("${monthFrData01.monthFireCount}"); // 1달전 
    const monFireCnt02 = parseFloat("${monthFrData02.monthFireCount}"); // 2달전 
    const monFireCnt03 = parseFloat("${monthFrData03.monthFireCount}"); // 3달전 
    const monFireCnt04 = parseFloat("${monthFrData04.monthFireCount}"); // 4달전
    const monFireCnt05 = parseFloat("${monthFrData05.monthFireCount}"); // 5달전 
    const monFireCnt06 = parseFloat("${monthFrData06.monthFireCount}"); // 6달전

    const lyFireCnt00 = parseFloat("${monthFrData00.lastYearMonthFireCount}"); // 1년전 
    const lyFireCnt01 = parseFloat("${monthFrData01.lastYearMonthFireCount}"); // 1년 1달전 
    const lyFireCnt02 = parseFloat("${monthFrData02.lastYearMonthFireCount}"); // 1년 2달전 
    const lyFireCnt03 = parseFloat("${monthFrData03.lastYearMonthFireCount}"); // 1년 3달전 
    const lyFireCnt04 = parseFloat("${monthFrData04.lastYearMonthFireCount}"); // 1년 4달전
    const lyFireCnt05 = parseFloat("${monthFrData05.lastYearMonthFireCount}"); // 1년 5달전 
    const lyFireCnt06 = parseFloat("${monthFrData06.lastYearMonthFireCount}"); // 1년 6달전
    
 // 데이터 포인트를 정의하는 함수
    function getMarkerSymbol(value, data) {
        var highest = Math.max.apply(Math, data);
        return value === highest ? 'url(${CP}/resources/img/fire.png)' : 'circle';
    }

    // 데이터 포인트를 배열로 설정
    const lyFireCnts = [lyFireCnt06, lyFireCnt05, lyFireCnt04, lyFireCnt03, lyFireCnt02, lyFireCnt01, lyFireCnt00];
    const monFireCnts = [monFireCnt06, monFireCnt05, monFireCnt04, monFireCnt03, monFireCnt02, monFireCnt01, monFireCnt00];
    
    Highcharts.chart('graphBox2', {
        chart: {
            type: 'spline'
        },
        title: {
            text: null
        },
        subtitle: {
            text: null
        },
        xAxis: {
            categories: [
                '6달전(현재)/6달전(작년)', '5달전(현재)/5달전(작년)', '4달전(현재)/4달전(작년)', '3달전(현재)/3달전(작년)', '2달전(현재)/2달전(작년)', '1달전(현재)/1달전(작년)', '오늘(현재)/작년의 오늘'
            ],
            accessibility: {
                description: 'Months of the year'
            }
        },
        yAxis: {
            title: {
                text: '화재건수 (현재/작년)'
            },
            labels: {
                overflow: 'justify',
                formatter: function () {
                    // y축 레이블을 천 단위로 쉼표를 넣어 표시
                    return Highcharts.numberFormat(this.value, 0, '.', ',');
                }
            }
        },
        tooltip: {
            crosshairs: true,
            shared: true
        },
        plotOptions: {
            spline: {
                marker: {
                    radius: 4,
                    lineColor: '#666666',
                    lineWidth: 1
                }
            }
        },
        credits: {
            enabled: false
        },
        series: [{
            name: '현재 - 6개월전',
            data: monFireCnts.map(function (value) {
                return {
                    y: value,
                    marker: {
                        symbol: getMarkerSymbol(value, monFireCnts)
                    }
                };
            }),
            color: '#ff6161',
            dataLabels: {
                enabled: true
            }
        }, {
            name: '1년전의 오늘 - 6개월전(작년기준)',
            data: lyFireCnts.map(function (value) {
                return {
                    y: value,
                    marker: {
                        symbol: getMarkerSymbol(value, lyFireCnts)
                    }
                };
            }),
            color: '#4f81bd',
            dataLabels: {
                enabled: true
            }
        }]
    });
    // 서브 차트 함수 끝
    
    sessionStorage.removeItem("tFCntSess"); // 삭제
    
    //비동기 통신
    function doRetrieveAjax(){
        
        let url = "/ehr/main/mainData.do";
        let dataType = "html";
        let type = "GET";
        let params = "";
        let async = "true";
        
        PClass.pAjax(url,params,dataType,type,async,function(data){
            if(data){
                try{
                    const jsonObj = JSON.parse(data);
                    const todayFireCount = jsonObj.todayFireCount;
                    const todayInjured = jsonObj.todayInjured;
                    const todayAmount = jsonObj.todayAmount;
                    const monthFireCount = jsonObj.monthFireCount;
                    const monthInjured = jsonObj.monthInjured;
                    const monthAmount = jsonObj.monthAmount;   
                    
                    // 세션에서 가져온 값이 null이면 0으로 초기화
                    let tFCntSessValue = sessionStorage.getItem("tFCntSess");
                    if (tFCntSessValue !== null) {
                        tFCntSessValue = Number(tFCntSessValue); // 문자열을 숫자로 변환
                    } else {
                        tFCntSessValue = 0; // 초기 값
                    }
                    
                    if(jsonObj !== null && jsonObj !== undefined || jsonObj || Object.keys(jsonObj).length > 0){
                    	if (todayFireCount != tFCntSessValue) {							
	                    	updateMainTitleBox(todayFireCount, todayInjured, todayAmount);
	                    	updateGraph(todayFireCount, todayInjured, todayAmount, monthFireCount, monthInjured, monthAmount);
						}else{
							console.log("nothing to happen");
						}
                    }else{ //데이터 없는 경우
                    	updateMainTitleBox(0, 0, 0);
                        updateGraph(0, 0, 0, 0, 0, 0);
                        alert("데이터 확인 불가");
                    }                   
                    
                    sessionStorage.setItem("tFCntSess", todayFireCount); // 세션에 저장
                    console.log("todayCnt : " + sessionStorage.getItem("tFCntSess"));
                        
                }catch(e){
                    console.error("실시간 데이터 오류입니다. 관리자에게 문의 주세요");
                }
            }        
        });
        
    }
    
    // 메인 타이틀 실시간 변화
    function updateMainTitleBox(todayFireCountData, todayInjuredData, todayAmountData) {
    	const todayFireCount = todayFireCountData;
        const todayInjured = todayInjuredData;
        const todayAmount = todayAmountData;
    	const formattedTodayAmount = todayAmount.toLocaleString();
    	
        const mainTitleBox = document.querySelector('.main_title_box');

        const contentHTML =
        '<p class="main_title">' + '작은 대비가 큰 안전을 만듭니다!' + '</p>' +
        '<p class="main_sub_title">' + '안녕하세요 안전파수꾼입니다' + '</p>' +
        '<p class="main_fd1">화재건수 : ' + todayFireCount + '</p>' +
        '<p class="main_fd2">인명피해 : ' + todayInjured + '</p>' +
        '<p class="main_fd3">재산피해 : ' + formattedTodayAmount + ' (단위 : 천원)' + '</p>'
        ;

        mainTitleBox.innerHTML = contentHTML;
    }
    
    // 메인차트 실시간 변화
    function updateGraph(todayFireCountData, todayInjuredData, todayAmountData, monthFireCountData, monthInjuredData, monthAmountData) {
        const todayFireCount = todayFireCountData;
        const todayInjured = todayInjuredData;
        const todayAmount = todayAmountData;
        
        const monthFireCount = monthFireCountData;
        const monthInjured = monthInjuredData;
        const monthAmount = monthAmountData;    
        
        Highcharts.chart('graphBox1_1', {
            accessibility: {
                enabled: false
            },
            chart: {
                type: 'column'
            },
            title: {
                text: null
                /* ,align: 'center' */
            },
            subtitle: {
                text: null
                /* ,align: 'center' */
            },
            xAxis: {
                categories: ['화재건수', '인명피해'],
                title: {
                    text: null
                },
                gridLineWidth: 1,
                lineWidth: 0
            },
            yAxis: {
                min: 0,
                title: {
                    text: '건 (명)',
                    align: 'high'
                },
                labels: {
                    overflow: 'justify',
                    formatter: function () {
                        // y축 레이블을 천 단위로 쉼표를 넣어 표시
                        return Highcharts.numberFormat(this.value, 0, '.', ',');
                    }
                },
                gridLineWidth: 0
            },
            tooltip: {
                formatter: function () {
                    var suffixes = [' 건', ' 명'];
                    var categoryTooltips = [
                        '화재건수: 이 데이터는 화재 발생 건수를 나타냅니다.',
                        '인명피해: 이 데이터는 인명 피해를 나타냅니다.'
                    ];
                    const formattedValue = Highcharts.numberFormat(this.y, 0, '.', ',');
                    // 툴팁에 천 단위로 쉼표를 넣어 표시
                    return '<b>' + this.x + '</b><br/>' +
                        categoryTooltips[this.point.index] + ' : ' +
                        Highcharts.numberFormat(this.y, 0) + suffixes[this.point.index];
                }
            },
            plotOptions: {
                bar: {
                    borderRadius: '0',
                    dataLabels: {
                        enabled: false,
                        formatter: function () {
                            const formattedValue = Highcharts.numberFormat(this.y, 0, '.', ',');
                            return formattedValue;
                        }
                    },
                    groupPadding: 0.1
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: 10000,
                y: 0,
                floating: true,
                borderWidth: 1,
                backgroundColor:
                    Highcharts.defaultOptions.legend.backgroundColor || '#FFFFFF',
                shadow: true,
                symbolRadius: 0   // 아이콘의 모서리 반경을 0으로 설정하여 네모 모양으로 변경합니다.
            },
            credits: {
                enabled: false
            },
            series: [{
                name: '1달전',
                data: [monthFireCount, monthInjured],
                color: '#4f81bd' // 파란색
            
            }, {
                name: '현재',
                data: [todayFireCount, todayInjured],
                color: '#ff6161' // 빨간색
            }]
        });
        Highcharts.chart('graphBox1_2', {
            accessibility: {
                enabled: false
            },
            chart: {
                type: 'column'
            },
            title: {
                text: null
                /* ,align: 'center' */
            },
            subtitle: {
                text: null
                /* ,align: 'center' */
            },
            xAxis: {
                categories: ['재산피해(천원)'],
                title: {
                    text: null
                },
                gridLineWidth: 1,
                lineWidth: 0
            },
            yAxis: {
                min: 0,
                title: {
                    text: '단위 (천원)',
                    align: 'high'
                },
                labels: {
                    overflow: 'justify',
                    formatter: function () {
                        // y축 레이블을 천 단위로 쉼표를 넣어 표시
                        return Highcharts.numberFormat(this.value, 0, '.', ',');
                    }
                },
                gridLineWidth: 0
            },
            tooltip: {
                formatter: function () {
                    var suffixes = [' 천원'];
                    var categoryTooltips = [
                        '재산피해(천원): 이 데이터는 재산 피해를 천 원 단위로 나타냅니다.'
                    ];
                    const formattedValue = Highcharts.numberFormat(this.y, 0, '.', ',');
                    // 툴팁에 천 단위로 쉼표를 넣어 표시
                    return '<b>' + this.x + '</b><br/>' +
                        categoryTooltips[this.point.index] + ' : ' +
                        Highcharts.numberFormat(this.y, 0) + suffixes[this.point.index];
                }
            },
            plotOptions: {
                bar: {
                    borderRadius: '0',
                    dataLabels: {
                        enabled: true,
                        formatter: function () {
                            const formattedValue = Highcharts.numberFormat(this.y, 0, '.', ',');
                            return formattedValue;
                        }
                    },
                    groupPadding: 0.1
                }
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'top',
                x: 0,
                y: 0,
                floating: true,
                borderWidth: 1,
                backgroundColor:
                    Highcharts.defaultOptions.legend.backgroundColor || '#FFFFFF',
                shadow: true,
                symbolRadius: 0   // 아이콘의 모서리 반경을 0으로 설정하여 네모 모양으로 변경합니다.
            },
            credits: {
                enabled: false
            },
            series: [{
                name: '1달전',
                data: [monthAmount],
                color: '#4f81bd' // 파란색
            
            }, {
                name: '현재',
                data: [todayAmount],
                color: '#ff6161' // 빨간색
            }]
        });
    }


    // 1분마다 updateMainTitleBox()와 updateGraph() 함수 실행
    setInterval(function() {
    	doRetrieveAjax();
    }, 10000); // 60000밀리초 = 1분
    // 함수 끝

    // 메인, 메인 차트 함수 실행
    doRetrieveAjax();
});
</script>    
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" />

<section class="container-fluid content align-items-center">
    <div class="mainGraphBox mt-3">    
	    <div class="main">
	        <img src="https://image.ytn.co.kr/general/jpg/2017/1030/201710301151127152_d.jpg" alt="#fire">
	        <div class="main_title_box">
	
	        </div>
	    </div>
	    
	    <div class="graph">
	        <div class="graphTitle">
	            <h3 class="m-0">전국</h3>
	            <div class="graphDate">2024년 08월</div>
	        </div>
	        <div id="graphBox1_1"></div>
	        <div id="graphBox1_2"></div>
	    </div>
    </div>
    
    <div class="preventBox container-fluid">
        <div class="m-0 mt-3 p-0">
            <div class="card">
                <strong>소화기 사용요령</strong>
                <span>User Guide of Fire<br>Extinguisher</span>
                <button class="btn btn-danger">바로가기 +</button>
            </div>
            <div class="card">
                <strong>
				심폐소생술 행동 요령
                </strong>
                <span>How to Perform CPR</span>
                <button class="btn btn-danger">바로가기 +</button>
            </div>
            <div class="card">
                <strong>
		                  자동 심장 충격기 행동요령
				</strong>
                <span>How to Perform<br>AED</span>
                <button class="btn btn-danger">바로가기 +</button>
            </div>
            <div class="card">
                <strong>
				옥내소화전 사용방법
                </strong>
                <span>Operation of<br> Fire Wall Cabinet</span>
                <button class="btn btn-danger">바로가기 +</button>
            </div>
        </div>
    </div>

    <div class="rankBox container-fluid">
        <div class="m-0 mt-4">
            <div class="card">
            <h5>화재 장소 - 대분류</h5>
				<c:choose>
					<c:when test="${rankLbData.size() >0 }">
					  <c:forEach var="item" items="${rankLbData}">
					   <div>
						   <p><i class="bi bi-1-square-fill"></i>  ${item.subLocBigNm} : ${item.monthFireCount} 건</p>
						   <p>평균 : ${item.monthAvg} 건</p>
					   </div>
					  </c:forEach>
					</c:when>
					<c:otherwise>
					   <p>화재 발생이 없습니다 :)</p>
					</c:otherwise>
				</c:choose>
            </div>
            <div class="card">
            <h5>화재 장소 - 중분류</h5>
                <c:choose>
                    <c:when test="${rankLmData.size() >0 }">
                      <c:forEach var="item" items="${rankLmData}">
                       <div>
                           <p><i class="bi bi-2-square-fill"></i> ${item.subLocMidNm} : ${item.monthFireCount} 건</p>
                           <p>평균 : ${item.monthAvg} 건</p>
                       </div>
                      </c:forEach>
                    </c:when>
                    <c:otherwise>
                       <p>화재 발생이 없습니다 :)</p>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="card">
            <h5>화재 요인 - 중분류</h5>
                <c:choose>
                    <c:when test="${rankFmData.size() >0 }">
                      <c:forEach var="item" items="${rankFmData}">
                       <div>
                           <p><i class="bi bi-3-square-fill"></i> ${item.subFactorMidNm} : ${item.monthFireCount} 건</p>
                           <p>평균 : ${item.monthAvg} 건</p>
                       </div>
                      </c:forEach>
                    </c:when>
                    <c:otherwise>
                       <p>화재 발생이 없습니다 :)</p>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    
    <div class="subGraphBox container-fluid">
        <div class="m-0 mt-3 row g-1">
            <div id="graphBox2"></div>
        </div>
    </div>
    
</section>
<jsp:include page="/WEB-INF/views/footer.jsp" />
<script src = "${CP}/resources/js/bootstrap.bundle.min.js"></script>        
</body>
</html>