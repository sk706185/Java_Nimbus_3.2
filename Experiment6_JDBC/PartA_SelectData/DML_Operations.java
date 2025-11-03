import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DML_Operations {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/java_experiments";
        String username = "root";
        String password = "Sejal@mysql";

        try {
            // 1. Load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Establish connection
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database!");

            // 3. Create statement
            Statement stmt = con.createStatement();

            // INSERT Operation
            String insertQuery = "INSERT INTO Employee (Name, Salary) VALUES ('Rohan Das', 38000.00)";
            int inserted = stmt.executeUpdate(insertQuery);
            System.out.println(inserted + " record inserted.");

            // UPDATE Operation
            String updateQuery = "UPDATE Employee SET Salary = 55000.00 WHERE Name = 'Rahul Verma'";
            int updated = stmt.executeUpdate(updateQuery);
            System.out.println(updated + " record updated.");

            // DELETE Operation
            String deleteQuery = "DELETE FROM Employee WHERE Name = 'Priya Sharma'";
            int deleted = stmt.executeUpdate(deleteQuery);
            System.out.println(deleted + " record deleted.");

            // Display remaining data
            System.out.println("\nUpdated Employee Table:");
            ResultSet rs = stmt.executeQuery("SELECT * FROM Employee");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("EmpID"));
                System.out.println("Name: " + rs.getString("Name"));
                System.out.println("Salary: " + rs.getDouble("Salary"));
                System.out.println("----------------------");
            }

            // Close everything
            rs.close();
            stmt.close();
            con.close();
            System.out.println("Connection closed.");

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database error occurred!");
            e.printStackTrace();
        }
    }
}
