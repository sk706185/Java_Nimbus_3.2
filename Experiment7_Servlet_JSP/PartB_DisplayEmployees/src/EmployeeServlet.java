import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class EmployeeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String empIdParam = request.getParameter("empId");

        try (Connection conn = DBConnection.getConnection()) {

            if (empIdParam != null && !empIdParam.isEmpty()) {
                // Search by ID
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM Employee WHERE EmpID = ?");
                ps.setInt(1, Integer.parseInt(empIdParam));
                ResultSet rs = ps.executeQuery();

                out.println("<h2>Employee Details</h2>");
                if (rs.next()) {
                    out.println("<p>ID: " + rs.getInt("EmpID") + "</p>");
                    out.println("<p>Name: " + rs.getString("Name") + "</p>");
                    out.println("<p>Salary: " + rs.getDouble("Salary") + "</p>");
                } else {
                    out.println("<p>No employee found with ID " + empIdParam + ".</p>");
                }

            } else {
                // Display all employees
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM Employee");

                out.println("<h2>All Employees</h2>");
                out.println("<table border='1' cellpadding='5'><tr><th>ID</th><th>Name</th><th>Salary</th></tr>");
                while (rs.next()) {
                    out.println("<tr><td>" + rs.getInt("EmpID") + "</td><td>" +
                            rs.getString("Name") + "</td><td>" + rs.getDouble("Salary") + "</td></tr>");
                }
                out.println("</table>");
            }

        } catch (Exception e) {
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }

        out.println("<br><a href='index.html'>Back to Home</a>");
    }
}
