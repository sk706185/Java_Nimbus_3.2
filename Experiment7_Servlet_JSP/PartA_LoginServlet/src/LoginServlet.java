import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Simple authentication logic (you can replace this with database validation)
        if ("admin".equals(username) && "admin123".equals(password)) {
            out.println("<html><body>");
            out.println("<h2>Welcome, " + username + "!</h2>");
            out.println("</body></html>");
        } else {
            out.println("<html><body>");
            out.println("<h3 style='color:red;'>Invalid Username or Password</h3>");
            out.println("<a href='login.html'>Try Again</a>");
            out.println("</body></html>");
        }

        out.close();
    }
}
