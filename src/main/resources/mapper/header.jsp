<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  // 실제 구현 시 세션에서 사용자 정보와 알림 수를 가져오는 로직으로 대체
  String userName = "admin";
  String userRole = "관리자";
  int unreadNotifications = 2; // 미확인 알림 수
%>
<header class="app-header bg-header border-bottom">
  <div class="container-1200 d-flex align-items-center gap-3">
    <!-- 좌: 로고 -->
    <div class="me-auto">
      <div class="brand-title">학사정보관리시스템</div>
      <div class="brand-sub">University Management System</div>
    </div>

    <!-- 중앙: 페이지 내 카드 검색 -->
    <div class="header-search mx-auto">
      <i class="bi bi-search"></i>
      <input type="text" placeholder="검색어를 입력하세요." />
    </div>

    <!-- 우: 알림 / 로그아웃 / 프로필 -->
    <div class="d-flex align-items-center gap-2">
      <button class="icon-pill" title="알림">
        <i class="bi bi-bell"></i>
        <% if (unreadNotifications > 0) { %>
        <span class="badge-dot"><%= unreadNotifications %></span>
        <% } %>
      </button>
      <a href="login.html" class="btn btn-white rounded-pill h-40 px-3 d-flex align-items-center gap-1">
        <i class="bi bi-box-arrow-right"></i><span>로그아웃</span>
      </a>
      <div class="btn btn-white rounded-pill h-40 px-2 d-flex align-items-center gap-2">
        <div class="avatar">U</div>
        <div class="text-start lh-1">
          <div class="text-navy fw-600 small"><%= userName %></div>
          <div class="text-gray-500 xsmall"><%= userRole %></div>
        </div>
      </div>
    </div>
  </div>
</header>
