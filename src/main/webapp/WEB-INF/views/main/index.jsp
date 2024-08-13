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
<title>ANPA</title>
<script>
document.addEventListener('DOMContentLoaded', function() {
    // header start
    const appMenus = document.querySelector('.appmenu');    
    
    appMenus.addEventListener('click', function(){
        console.log("상단 앱 메뉴 클릭");
    });
    // header end

    function updateMainTitleBox() {
    	const todayFireCount = ${firedata.todayFireCount}
        const todayInjured = ${firedata.todayInjured}
        const todayAmount = ${firedata.todayAmount}
    	const formattedTodayAmount = todayAmount.toLocaleString();
    	
        const mainTitleBox = document.querySelector('.main_title_box');

        const contentHTML =
        '<p class="main_title">' + '대한민국의 미래 ANPA가 함께합니다' + '</p>' +
        '<p class="main_sub_title">' + 'ANPA 시스템을 소개합니다' + '</p>' +
        '<p class="main_fd1">화재건수 : ' + todayFireCount + '</p>' +
        '<p class="main_fd2">인명피해 : ' + todayInjured + '</p>' +
        '<p class="main_fd3">재산피해 : ' + formattedTodayAmount + ' (단위 : 천원)' + '</p>'
        ;

        mainTitleBox.innerHTML = contentHTML;
    }

    function updateGraph() {
    	const todayFireCount = ${firedata.todayFireCount}
        const todayInjured = ${firedata.todayInjured}
        const todayAmount = ${firedata.todayAmount}
        
        const monthFireCount = ${firedata.monthFireCount}
        const monthInjured = ${firedata.monthInjured}
        const monthAmount = ${firedata.monthAmount}    
        
        Highcharts.chart('graphBox', {
            accessibility: {
                enabled: false
            },
            chart: {
                type: 'bar'
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
                categories: ['화재건수', '인명피해', '재산피해(천원)'],
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
                        return Highcharts.numberFormat(this.value, 0, ',', ' ');
                    }
                },
                gridLineWidth: 0
            },
            tooltip: {
                formatter: function () {
                    var suffixes = [' 건', ' 건', ' 천원'];
                    var categoryTooltips = [
                        '화재건수: 이 데이터는 화재 발생 건수를 나타냅니다.',
                        '인명피해: 이 데이터는 인명 피해를 나타냅니다.',
                        '재산피해(천원): 이 데이터는 재산 피해를 천 원 단위로 나타냅니다.'
                    ];
                    const formattedValue = Highcharts.numberFormat(this.y, 0, ',', ' ');
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
                            const formattedValue = Highcharts.numberFormat(this.y, 0, ',', ' ');
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
                data: [monthFireCount, monthInjured, monthAmount],
                color: '#4f81bd' // 파란색
            
            }, {
                name: '현재',
                data: [todayFireCount, todayInjured, todayAmount],
                color: '#ff6161' // 빨간색
            }]
        });
    }

    updateMainTitleBox();
    updateGraph();

    // 1분마다 updateMainTitleBox()와 updateGraph() 함수 실행
    setInterval(function() {
        updateMainTitleBox();
        updateGraph();
    }, 10000); // 60000밀리초 = 1분
});
</script>    
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" />

<section class="container-fluid content align-items-center">
    <div class="mainGraphBox">    
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
	        <div id="graphBox"></div>
	    </div>
    </div>
    
    <div class="preventBox container-fluid">
        <div class="m-0 mt-2 row g-1" style="margin : 0;">
            <div class="mt-0 col-md-3">
                <strong>소화기 사용요령</strong>
                <span>User Guide of
Fire Extinguisher</span>
                <button class="btn btn-danger">바로가기</button>
            </div>
            <div class="mt-0 col-md-3">
                <strong>
				심폐소생술<br>
				행동 요령
                </strong>
                <span>How to Perform CPR</span>
                <button class="btn btn-danger">바로가기</button>
            </div>
            <div class="mt-0 col-md-3">
                <strong>
		                자동심장충격기<br>
		                행동요령
				</strong>
                <span>How to Perform AED</span>
                <button class="btn btn-danger">바로가기</button>
            </div>
            <div class="mt-0 col-md-3">
                <strong>
				옥내소화전<br>
                                    사용방법
                </strong>
                <span>Operation of Fire Wall
Cabinet</span>
                <button class="btn btn-danger">바로가기</button>
            </div>
        </div>
    </div>
    
</section>
<jsp:include page="/WEB-INF/views/footer.jsp" />
<script src = "${CP}/resources/js/bootstrap.bundle.min.js"></script>        
</body>
</html>