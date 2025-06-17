import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String DB_URL = "jdbc:mysql://localhost:3306/userdb";
        final String DB_USER = "root";
        final String DB_PASS = "@UersPassword";


        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String gender = req.getParameter("gender");
        String city = req.getParameter("ciy");


        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            String sql = "INSERT INTO users (name, email, password, gender, city) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, gender);
            ps.setString(5, city);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                out.println("<h2>Registration Successful!</h2>");
                RequestDispatcher rd = req.getRequestDispatcher("/userForm.jsp");
                rd.include(req,resp);
            } else {
                out.println("<h2>Registration Failed!</h2>");

                RequestDispatcher rd = req.getRequestDispatcher("/userForm.jsp");
                rd.include(req,resp);
            }

            ps.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        // Display data
//        out.println("<html><body>");
//        out.println("<h2>Form Submitted Successfully!</h2>");
//        out.println("<p><strong>Name:</strong> " + name + "</p>");
//        out.println("<p><strong>Email:</strong> " + email + "</p>");
//        out.println("<p><strong>Password:</strong> " + password + "</p>");
//        out.println("<p><strong>Gender:</strong> " + gender + "</p>");
//        out.println("<p><strong>City:</strong> " + city + "</p>");
//        out.println("</body></html>");
    }
}
