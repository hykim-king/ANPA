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
<script src="https://code.highcharts.com/modules/accessibility.js"></script>
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
        const monthFireCount = ${firedata.monthFireCount}
        const monthInjured = ${firedata.monthInjured}
        const monthAmount = ${firedata.monthAmount}
        
        const mainGraph = document.querySelector('.content .graph');

        const contentHTML =
        '<p class="main_fd1">화재건수 : ' + monthFireCount + '</p>' +
        '<p class="main_fd2">인명피해 : ' + monthInjured + '</p>' +
        '<p class="main_fd3">재산피해 : ' + monthAmount + '</p>'
        ;

        mainGraph.innerHTML = contentHTML;
    }

    updateMainTitleBox();
    /* updateGraph(); */

    // 1분마다 updateMainTitleBox()와 updateGraph() 함수 실행
    setInterval(function() {
        updateMainTitleBox();
        /* updateGraph(); */
    }, 10000); // 60000밀리초 = 1분
});
</script>    
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp" />

<section class="content align-items-center">
    <div class="main">
        <img src="https://image.ytn.co.kr/general/jpg/2017/1030/201710301151127152_d.jpg" alt="#fire">
        <div class="main_title_box">

        </div>
    </div>
    <div id="graphBox" class="graph">
    </div>
</section>
<script>
Highcharts.chart('graphBox', {
    accessibility: {
        enabled: false
    },
    chart: {
        type: 'bar'
    },
    title: {
        text: '제목 left center right',
        align: 'center'
    },
    subtitle: {
        text: '부제목 left center right',
        align: 'center'
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
            text: 'Population (millions)',
            align: 'high'
        },
        labels: {
            overflow: 'justify'
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
            return '<b>' + this.x + '</b><br/>' +
                categoryTooltips[this.point.index] + ' : ' +
                Highcharts.numberFormat(this.y, 0) + suffixes[this.point.index];
        }
    },
    plotOptions: {
        bar: {
            borderRadius: '0',
            dataLabels: {
                enabled: true
            },
            groupPadding: 0.1
        }
    },
    legend: {
        layout: 'vertical',
        align: 'right',
        verticalAlign: 'top',
        x: -40,
        y: 80,
        floating: true,
        borderWidth: 1,
        backgroundColor:
            Highcharts.defaultOptions.legend.backgroundColor || '#FFFFFF',
        shadow: true
    },
    credits: {
        enabled: false
    },
    series: [{
        name: '1달전',
        data: [632, 727, 3202],
        color: '#4f81bd' // 파란색
    
    }, {
        name: '현재',
        data: [814, 841, 3714],
        color: '#ff6161' // 빨간색
    }]
});

</script>
<jsp:include page="/WEB-INF/views/footer.jsp" />
<script src = "${CP}/resources/js/bootstrap.bundle.min.js"></script>        
</body>
</html>