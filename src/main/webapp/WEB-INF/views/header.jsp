<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<c:set var="CP"  value="${pageContext.request.contextPath}"  />    
<!DOCTYPE html>
<html lang="kor">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">    
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
</head>
<body>
<header id="top_header">
    <div class="d-flex justify-content-between align-items-center top_header_il">
        <div class="text-center top_header_il_obj top_logo">
            <img src="${CP}/resources/img/logo_x.png" alt="#logo">                 
        </div>
        <h2 class="title d-flex align-items-center justify-content-center">ANPA</h2>
        <div class="text-center top_header_il_obj appmenu">
          <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960"  width="24px" fill="#dfdfdf"><path d="M240-160q-33 0-56.5-23.5T160-240q0-33 23.5-56.5T240-320q33 0 56.5 23.5T320-240q0 33-23.5 56.5T240-160Zm240 0q-33 0-56.5-23.5T400-240q0-33 23.5-56.5T480-320q33 0 56.5 23.5T560-240q0 33-23.5 56.5T480-160Zm240 0q-33 0-56.5-23.5T640-240q0-33 23.5-56.5T720-320q33 0 56.5 23.5T800-240q0 33-23.5 56.5T720-160ZM240-400q-33 0-56.5-23.5T160-480q0-33 23.5-56.5T240-560q33 0 56.5 23.5T320-480q0 33-23.5 56.5T240-400Zm240 0q-33 0-56.5-23.5T400-480q0-33 23.5-56.5T480-560q33 0 56.5 23.5T560-480q0 33-23.5 56.5T480-400Zm240 0q-33 0-56.5-23.5T640-480q0-33 23.5-56.5T720-560q33 0 56.5 23.5T800-480q0 33-23.5 56.5T720-400ZM240-640q-33 0-56.5-23.5T160-720q0-33 23.5-56.5T240-800q33 0 56.5 23.5T320-720q0 33-23.5 56.5T240-640Zm240 0q-33 0-56.5-23.5T400-720q0-33 23.5-56.5T480-800q33 0 56.5 23.5T560-720q0 33-23.5 56.5T480-640Zm240 0q-33 0-56.5-23.5T640-720q0-33 23.5-56.5T720-800q33 0 56.5 23.5T800-720q0 33-23.5 56.5T720-640Z"/>
          </svg>
        </div>
    </div>

    <ul class="nav nav-tabs text-center justify-content-center">
        <li class="nav-item d-flex justify-content-center align-items-center">
            <a class="nav-link" aria-current="page" href="#">화재통계</a>
        </li>
        <li class="nav-item d-flex justify-content-center align-items-center">
            <a class="nav-link" href="#">1달 화재현황</a>
        </li>
        <li class="nav-item d-flex justify-content-center align-items-center">
            <a class="nav-link" href="#">화재 예방법</a>
        </li>
        <li class="nav-item dropdown d-flex justify-content-center align-items-center">
            <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button" aria-expanded="false">알림마당</a>
            <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="#">공지사항</a></li>
                <li><hr class="dropdown-divider"></li>
                <li><a class="dropdown-item" href="#">건의사항</a></li>
            </ul>
        </li>
    </ul>
</header>
<script src = "${CP}/resources/js/bootstrap.bundle.min.js"></script>    
</body>
</html>