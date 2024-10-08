<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="CP" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="kor">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<head>
<!-- 파비콘 추가 -->
<link rel="icon" type="image/x-icon" href="${CP}/resources/img/favicon.ico">
<link rel="stylesheet" href="${CP}/resources/css/bootstrap.css">
<script src="${CP}/resources/js/common.js"></script>
<title>ANPA</title>
</head>
<body>
<style>
	* {
	    -webkit-box-sizing: border-box;
	    -moz-box-sizing: border-box;
	    box-sizing: border-box;
	}

	html,body {
	    height: 100%;
	    overflow: hidden;
	}

	body {
	    margin: 0;
	    padding: 0;
	    font-weight: 400;
	    font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
	    font-size: 1rem;
	    line-height: 1.58;
	    color: #333;
	    background-color: #f4f4f4;
	    height: 100%;
	}

	body:before {
	    height: 50%;
	    width: 100%;
	    position: absolute;
	    top: 0;
	    left: 0;
	    background: #128ff2;
	    content: "";
	    z-index: 0;
	}

	.clearfix:after {
	    display: block;
	    content: "";
	    clear: both;
	}

	.hidden {
	    display: none;
	}

	.form-control {
	    width: 100%;
	    min-height: 38px;
	    font-size: 15px;
	    border: 1px solid #c8c8c8;
	}

	.form-group {
	    margin-bottom: 15px;
	}

	input {
	    padding-left: 10px;
	    outline: none;
	}

	h1, h2, h3, h4, h5, h6 {
	    margin-top: 20px;
	    margin-bottom: 20px;
	}

	h1 {
	    font-size: 1.7em;
	}

	a {
	    color: #128ff2;
	}

	button {
	    box-shadow: none;
	    border: 1px solid transparent;
	    font-size: 14px;
	    outline: none;
	    line-height: 100%;
	    white-space: nowrap;
	    vertical-align: middle;
	    padding: 0.6rem 1rem;
	    border-radius: 2px;
	    transition: all 0.2s ease-in-out;
	    cursor: pointer;
	    min-height: 38px;
	}

	button.default {
	    background-color: #e8e8e8;
	    color: #333;
	    box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.12);
	}

	button.primary {
	    background-color: #128ff2;
	    box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.12);
	    color: #fff;
	}

	button.accent {
	    background-color: #ff4743;
	    box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.12);
	    color: #fff;
	}

	#username-page {
	    text-align: center;
	}

	.username-page-container {
	    background: #fff;
	    box-shadow: 0 1px 11px rgba(0, 0, 0, 0.27);
	    border-radius: 2px;
	    width: 100%;
	    max-width: 500px;
	    display: inline-block;
	    margin-top: 42px;
	    vertical-align: middle;
	    position: relative;
	    padding: 35px 55px 35px;
	    min-height: 250px;
	    position: absolute;
	    top: 50%;
	    left: 0;
	    right: 0;
	    margin: 0 auto;
	    margin-top: -160px;
	}

	.username-page-container .username-submit {
	    margin-top: 10px;
	}


	#chat-page {
	    position: relative;
	    height: 100%;
	}

	.chat-container {
	    max-width: 700px;
	    margin-left: auto;
	    margin-right: auto;
	    background-color: #fff;
	    box-shadow: 0 1px 11px rgba(0, 0, 0, 0.27);
	    margin-top: 30px;
	    height: calc(100% - 60px);
	    max-height: 600px;
	    position: relative;
	}

	#chat-page ul {
	    list-style-type: none;
	    background-color: #FFF;
	    margin: 0;
	    overflow: auto;
	    overflow-y: scroll;
	    padding: 0 20px 0px 20px;
	    height: calc(100% - 150px);
	}

	#chat-page #messageForm {
	    padding: 20px;
	}

	#chat-page ul li {
	    line-height: 1.5rem;
	    padding: 10px 20px;
	    margin: 0;
	    border-bottom: 1px solid #f4f4f4;
	}

	#chat-page ul li p {
	    margin: 0;
	}

	#chat-page .event-message {
	    width: 100%;
	    text-align: center;
	    clear: both;
	}

	#chat-page .event-message p {
	    color: #777;
	    font-size: 14px;
	    word-wrap: break-word;
	}

	#chat-page .chat-message {
	    padding-left: 68px;
	    position: relative;
	}

	#chat-page .chat-message i {
	    position: absolute;
	    width: 42px;
	    height: 42px;
	    overflow: hidden;
	    left: 10px;
	    display: inline-block;
	    vertical-align: middle;
	    font-size: 18px;
	    line-height: 42px;
	    color: #fff;
	    text-align: center;
	    border-radius: 50%;
	    font-style: normal;
	    text-transform: uppercase;
	}

	#chat-page .chat-message span {
	    color: #333;
	    font-weight: 600;
	}

	#chat-page .chat-message p {
	    color: #43464b;
	}

	#messageForm .input-group input {
	    float: left;
	    width: calc(100% - 85px);
	}

	#messageForm .input-group button {
	    float: left;
	    width: 80px;
	    height: 38px;
	    margin-left: 5px;
	}

	.chat-header {
	    text-align: center;
	    padding: 15px;
	    border-bottom: 1px solid #ececec;
	}

	.chat-header h2 {
	    margin: 0;
	    font-weight: 500;
	}

	.connecting {
	    padding-top: 5px;
	    text-align: center;
	    color: #777;
	    position: absolute;
	    top: 65px;
	    width: 100%;
	}


	@media screen and (max-width: 730px) {

	    .chat-container {
	        margin-left: 10px;
	        margin-right: 10px;
	        margin-top: 10px;
	    }
	}

	@media screen and (max-width: 480px) {
	    .chat-container {
	        height: calc(100% - 30px);
	    }

	    .username-page-container {
	        width: auto;
	        margin-left: 15px;
	        margin-right: 15px;
	        padding: 25px;
	    }

	    #chat-page ul {
	        height: calc(100% - 120px);
	    }

	    #messageForm .input-group button {
	        width: 65px;
	    }

	    #messageForm .input-group input {
	        width: calc(100% - 70px);
	    }

	    .chat-header {
	        padding: 10px;
	    }

	    .connecting {
	        top: 60px;
	    }

	    .chat-header h2 {
	        font-size: 1.1em;
	    }
	}	
</style>
<div id="username-page">
    <div class="username-page-container">
        <h1 class="title">닉네임을 입력하세요</h1>
        <form id="usernameForm" name="usernameForm">
            <div class="form-group">
                <input type="text" id="name" placeholder="Username" autocomplete="off" class="form-control" />
            </div>
            <div class="form-group">
                <button type="submit" class="accent username-submit">채팅 시작하기</button>
            </div>
        </form>
    </div>
</div>

<div id="chat-page" class="hidden">
    <div class="chat-container">
        <div class="chat-header">
            <h2>상담 요청</h2>
        </div>
        <div class="connecting">
            연결중...
        </div>
        <ul id="messageArea">

        </ul>
        <form id="messageForm" name="messageForm">
            <div class="form-group">
                <div class="input-group clearfix">
                    <input type="text" id="message" placeholder="Type a message..." autocomplete="off" class="form-control"/>
                    <button type="submit" class="primary">보내기</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<script type="text/javascript">
	var usernamePage = document.querySelector('#username-page');
	var chatPage = document.querySelector('#chat-page');
	var usernameForm = document.querySelector('#usernameForm');
	var messageForm = document.querySelector('#messageForm');
	var messageInput = document.querySelector('#message');
	var messageArea = document.querySelector('#messageArea');
	var connectingElement = document.querySelector('.connecting');

	var stompClient = null;
	var username = null;

	var colors = [
	    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
	    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
	];

	function connect(event) {
		const userInfo = getUserInfo();
	    /*username = document.querySelector('#name').value.trim();*/
		console.log("userInfo.userId : " + userInfo.userId);
	    username = userInfo.userId;
		console.log("username : " + username);

	    if(username) {
	        usernamePage.classList.add('hidden');
	        chatPage.classList.remove('hidden');
			
			var socket = new SockJS('/ehr/chat');
	        stompClient = Stomp.over(socket);

	        stompClient.connect({}, onConnected, onError);
	    }
	    event.preventDefault();
	}


	function onConnected() {
		console.log("┌──────────────┐");
		console.log("│onConnected");
		console.log("└──────────────┘");
	    // Subscribe to the Public Topic
	    stompClient.subscribe('/sub/chat/room/1', onMessageReceived);
		console.log("┌──────────────┐");
		console.log("│Subscribe to the Public Topic");
		console.log("└──────────────┘");

	    // Tell your username to the server
	    stompClient.send("/pub/enter",
	        {},
	        JSON.stringify({sender: username, content: 'JOIN'})
	    );
	    connectingElement.classList.add('hidden');
		console.log("┌──────────────┐");
		console.log("│onConnected Finished");
		console.log("└──────────────┘");
		disConnected();
	}
	
	function disConnected(){
		console.log("┌──────────────┐");
		console.log("│disConnectedOn");
		console.log("└──────────────┘");
		// 사용자가 채팅방을 나갈 때
		window.addEventListener('beforeunload', function() {
		    stompClient.send("/pub/leave", {}, JSON.stringify({ sender: username, content: 'LEAVE' }));
		});		
	}

	function onError(error) {
	    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
	    connectingElement.style.color = 'red';
	}


	function sendMessage(event) {
		console.log("┌──────────────┐");
		console.log("│sendMessage");
		console.log("└──────────────┘");
	    var messageContent = messageInput.value.trim();
	    if(messageContent && stompClient) {
	        var chatMessage = {
	            sender: username,
	            content: messageInput.value
	        };
	        stompClient.send("/pub/message", {}, JSON.stringify(chatMessage));
	        messageInput.value = '';
	    }
	    event.preventDefault();
	}


	function onMessageReceived(payload) {
		console.log("┌──────────────┐");
		console.log("│onMessageReceived");
		console.log("└──────────────┘");	
	    var message = JSON.parse(payload.body);
	    console.log("Received message:", message); // 수신한 메시지 출력

	    var messageElement = document.createElement('li');
	    
	    // 메시지 타입에 따라 처리
	    if (message.content == 'JOIN') {
	        messageElement.classList.add('event-message');
	        messageElement.textContent = message.sender + ' 님이 입장하였습니다.';
	    } else if (message.content == 'LEAVE') {
	        messageElement.classList.add('event-message');
	        messageElement.textContent = message.sender + ' 님이 퇴장하였습니다.';
	    } else {
	        messageElement.classList.add('chat-message');

	        // 사용자 이름과 메시지 내용 추가
	        var avatarElement = document.createElement('i');
	        avatarElement.style['background-color'] = getAvatarColor(message.sender);
	        avatarElement.textContent = message.sender[0];
	        messageElement.appendChild(avatarElement);

	        var usernameElement = document.createElement('span');
	        usernameElement.textContent = message.sender + ": ";
	        messageElement.appendChild(usernameElement);
	        
	        var textElement = document.createElement('p');
	        textElement.textContent = message.content;
	        messageElement.appendChild(textElement);
	    }

	    // 메시지를 messageArea에 추가
	    messageArea.appendChild(messageElement);
	    messageArea.scrollTop = messageArea.scrollHeight; // 스크롤을 맨 아래로
	}


	function getAvatarColor(messageSender) {
	    var hash = 0;
	    for (var i = 0; i < messageSender.length; i++) {
	        hash = 31 * hash + messageSender.charCodeAt(i);
	    }
	    var index = Math.abs(hash % colors.length);
	    return colors[index];
	}

	usernameForm.addEventListener('submit', connect, true);
	messageForm.addEventListener('submit', sendMessage, true);
</script>
</body>
</html>
