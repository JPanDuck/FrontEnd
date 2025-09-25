<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  // 실제 구현 시 세션에서 사용자 역할을 가져오는 로직으로 대체
  String userRole = "관리자";
%>
<aside class="sidebar card-white">
  <div class="menu-scroll">
    <div class="menu-cap">MENU</div>
    <ul class="menu-list">
      <!-- 공통 기능 -->
      <li><a href="/pages/index.jsp">대시보드</a></li>
      <li><a href="/pages/common/notification.jsp">알림센터</a></li>
      <li><a href="/pages/common/calendar.jsp">학사일정</a></li>

      <!-- ✅ 역할별 메뉴 분기 처리 -->
      <% if (userRole.equals("관리자")) { %>
      <li><a href="/pages/admin/notice-manage.jsp">공지사항</a></li>
      <li><a href="/pages/admin/users.jsp">사용자 관리</a></li>
      <li><a href="/pages/admin/course.jsp">강좌 관리</a></li>
      <li><a href="/pages/admin/logs.jsp">시스템 관리</a></li>
      <% } %>

      <% if (userRole.equals("교수")) { %>
      <li><a href="/pages/professor/notice.jsp">공지사항</a></li>
      <li><a href="/pages/professor/prof-timetable.jsp">강의 시간표</a></li>
      <li><a href="/pages/professor/grade-input.jsp">성적 입력</a></li>
      <% } %>

      <% if (userRole.equals("학생")) { %>
      <li><a href="/pages/student/notice.jsp">공지사항</a></li>
      <li><a href="/pages/student/enroll.jsp">수강 신청</a></li>
      <li><a href="/pages/student/timetable.jsp">내 시간표</a></li>
      <li><a href="/pages/student/grade.jsp">성적 조회</a></li>
      <li><a href="/pages/student/graduation-req.jsp">졸업 요건</a></li>
      <% } %>

      <% if (userRole.equals("지도교수")) { %>
      <li><a href="/pages/professor/notice.jsp">공지사항</a></li>
      <li><a href="/pages/professor/prof-timetable.jsp">강의 시간표</a></li>
      <li><a href="/pages/professor/grade-input.jsp">성적 입력</a></li>
      <li><a href="/pages/advisor/graduation.jsp">졸업심사</a></li>
      <% } %>

      <!-- 공통 기능 - 설정 -->
      <li><a href="/pages/common/settings.jsp">설정</a></li>
    </ul>
  </div>
</aside>
