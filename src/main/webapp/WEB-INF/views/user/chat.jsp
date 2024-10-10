<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value="${pageContext.request.contextPath}" />	
<!DOCTYPE html>
<html lang="kor">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<head>
    <meta charset="UTF-8">
    <title>ANPA</title>
	<!-- 파비콘 추가 -->
	<link rel="icon" type="image/x-icon" href="${CP}/resources/img/favicon.ico">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.5.0/sockjs.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/stompjs@2.3.3/lib/stomp.min.js"></script>
    <style>
		body{overflow-x : hidden;}
        .container {
            text-align: center;
            padding: 30px;
            width: 90%;
            max-width: 1000px;
            margin: 125px auto;
            background-color: white;
            height: 500px; /* 높이 설정 */
            border-radius: 15px; /* 모서리 둥글게 */
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2), 0 0 25px rgba(255, 255, 255, 0.5); /* 흰색과 회색 그림자 */
        }
        .messageBox {
            margin-top: 10px;
            width: 100%;
            height: 350px; /* 높이 설정 */
            overflow-y: auto; /* Y축 스크롤 활성화 */
        }
        #messages {
            padding: 10px 20px;
            text-align: left;
            height: auto;
            min-height: 800px; /* 내용을 길게 설정하여 스크롤 생성 */
            background: linear-gradient(to bottom, #f0f0f0, #d0d0d0);
        }
    </style>
</head>
<body>
<div class="container">
    <h1>ANPA 관리자 문의</h1>
    <input id="nickname" placeholder="이름" />
    <input id="message" placeholder="메세지" />
    <button id="sendButton">전송</button> <!-- 전송 버튼 추가 -->
    <div class="messageBox">
        <div id="messages"></div>
    </div>
</div>

<script>
    var stompClient = null;

    function connect() {
        var socket = new SockJS('/ehr/chat');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/topic/messages', function (message) {
                showMessage(JSON.parse(message.body));
            });
        });
    }

    function sendMessage() {
        var nickname = document.getElementById('nickname').value.trim();
        var messageContent = document.getElementById('message').value.trim();

        if (nickname === '') {
            alert('이름을 입력해 주세요.'); // 이름이 비어있을 경우 재입력 요청
            return;
        }

        if (messageContent === '') {
            alert('메세지를 입력해 주세요.'); // 메세지가 비어있을 경우 재입력 요청
            return;
        }

        var message = {
            sender: nickname,
            content: messageContent
        };
        
        stompClient.send("/app/send", {}, JSON.stringify(message));
        document.getElementById('message').value = ''; // 메세지 입력란 초기화
    }

    function showMessage(message) {
        var messagesDiv = document.getElementById('messages');
        messagesDiv.innerHTML += "<p><strong>" + message.sender + " : </strong>" + message.content + "</p>";
    }

    // Enter 키로 메세지 전송
    document.getElementById('message').addEventListener('keypress', function (event) {
        if (event.key === 'Enter') {
            sendMessage();
        }
    });

    // 버튼 클릭으로 메세지 전송
    document.getElementById('sendButton').addEventListener('click', function () {
        sendMessage();
    });

    connect();
</script>
</body>
</html>
