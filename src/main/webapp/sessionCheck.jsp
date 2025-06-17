<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
    HttpSession session = request.getSession(false);
    String userName = (session != null) ? (String) session.getAttribute("userName") : null;

    if (userName == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
