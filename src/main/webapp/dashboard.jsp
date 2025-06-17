<%@ include file="sessionCheck.jsp" %>
<html>
<head>
    <title>Dashboard</title>
</head>
<body>
    <h2>Welcome, <%= userName %>!</h2>
    <p>This is your dashboard.</p>

    <form action="LogoutServlet" method="post">
        <input type="submit" value="Logout" onclick="return confirm('Are you sure you want to logout?');">
    </form>
</body>
</html>
