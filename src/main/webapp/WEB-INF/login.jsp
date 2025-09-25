<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>로그인</title>

    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700&family=Noto+Sans+KR:wght@400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css"/>

    <style>
        .login-wrapper { min-height: 100vh; display: flex; align-items: center; justify-content: center; padding: 24px; }
        .login-card { width: 100%; max-width: 420px; }
        .login-logo-wrap{
            width: 104px; height: 104px; margin: 0 auto 12px auto; border-radius: 50%;
            background: var(--navy-50); display: grid; place-items: center;
            box-shadow: 0 4px 12px rgba(15,41,64,.10), inset 0 0 0 1px rgba(15,41,64,.08);
        }
        .login-logo{ width: 76px; height: 76px; object-fit: contain; filter: drop-shadow(0 1px 1px rgba(0,0,0,.08)); }
        .brand-title { font-size: 18px; font-weight: 700; letter-spacing: -.3px; }
        .brand-sub { font-size: 10px; color: var(--gray-500); margin-top: 2px; }
    </style>
</head>
<body class="bg-page">
<div class="login-wrapper">
    <div class="card-white p-4 login-card">
        <div class="text-center mb-3">
            <div class="login-logo-wrap">
                <img class="login-logo" src="${pageContext.request.contextPath}/static/img/logo.png" alt="Winston College 로고">
            </div>
            <div class="brand-title">학사정보관리시스템</div>
            <div class="brand-sub">University Management System</div>
        </div>

        <form id="loginForm">
            <input class="form-control mb-2" id="username" name="username" placeholder="아이디" aria-label="아이디" required/>
            <input class="form-control mb-3" id="password" type="password" name="password" placeholder="비밀번호" aria-label="비밀번호" required/>
            <button type="submit" class="btn btn-navy w-100 rounded-pill mb-2">로그인</button>
        </form>

        <div class="text-center">
            <a href="#" class="xsmall text-navy">비밀번호 찾기</a>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<script>
    document.getElementById("loginForm").addEventListener("submit", function (e) {
        e.preventDefault();
        // 로그인 성공 시 대시보드(index.jsp)로 이동하는 로직
        // 예시: window.location.href = '${pageContext.request.contextPath}/index.jsp';
        alert("로그인 버튼 클릭됨 (실제 로직 연결 필요)");
    });
</script>

</body>
</html>