//package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
//
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/userdb";
//    private static final String DB_USER = "root";
//    private static final String DB_PASS = "your_password"; // change this

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final String DB_URL = "jdbc:mysql://localhost:3306/userdb";
        final String DB_USER = "root";
        final String DB_PASS = "@Anshulsingh66";

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");

                // Store user name in session
                HttpSession session = request.getSession();
                session.setAttribute("userName", name);

                response.sendRedirect("dashboard.jsp");
            } else {
                response.setContentType("text/html");
                response.getWriter().println("<h2>Invalid email or password</h2>");
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h2>Error: " + e.getMessage() + "</h2>");
        }
    }
}
