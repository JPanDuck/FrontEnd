<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>로그인</title>

    <!-- 외부 CDN -->
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700&family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"/>

    <!-- 정적 리소스 -->
    <link rel="stylesheet" href="<c:url value='/css/style.css'/>"/>
</head>
<body class="bg-page">
<div class="login-wrapper">
    <div class="card-white p-4 login-card">
        <div class="text-center mb-3">
            <div class="login-logo-wrap">
                <img class="login-logo" src="<c:url value='/img/logo.png'/>" alt="Winston College 로고"/>
            </div>
            <div class="brand-title">학사정보관리시스템</div>
            <div class="brand-sub">University Management System</div>
        </div>

        <!-- 로그인 폼 -->
        <form id="loginForm">
            <input class="form-control mb-2" id="username" name="username" placeholder="아이디" required/>
            <input class="form-control mb-3" id="password" type="password" name="password" placeholder="비밀번호" required/>
            <button type="submit" class="btn btn-navy w-100 rounded-pill mb-2">로그인</button>
        </form>

        <div class="text-center">
            <a href="#" class="xsmall text-navy">비밀번호 찾기</a>
        </div>
    </div>
</div>

<!-- 외부 JS -->
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<script>
    document.getElementById("loginForm").addEventListener("submit", async function (e) {
        e.preventDefault();

        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;

        try {
            // ✅ 반드시 /api/auth/login 으로 POST
            const res = await fetch("${pageContext.request.contextPath}/api/auth/login", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ username, password })
            });

            if (!res.ok) {
                alert("로그인 실패: 아이디 또는 비밀번호를 확인 해 주세요.");
                return;
            }

            const data = await res.json();

            // ✅ 토큰 저장
            localStorage.setItem("accessToken", data.accessToken);
            localStorage.setItem("refreshToken", data.refreshToken);

            // ✅ 임시 비밀번호 여부 확인
            if (data.isTempPassword) {
                alert("임시 비밀번호입니다. 비밀번호를 변경해주세요.");
                window.location.href = "${pageContext.request.contextPath}/auth/change-password";
                return;
            }

            // ✅ 로그인 성공 → index 페이지로 이동
            window.location.href = "${pageContext.request.contextPath}/auth/common/index";

        } catch (err) {
            console.error(err);
            alert("서버 오류가 발생했습니다.");
        }
    });
</script>


</body>
</html>
