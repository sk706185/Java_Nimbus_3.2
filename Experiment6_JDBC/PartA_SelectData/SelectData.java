import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectData {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/java_experiments";
        String username = "root";
        String password = "Sejal@mysql";

        try {
            // Load driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to database
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to database!");

            // Create statement
            Statement stmt = con.createStatement();

            // Execute query (you can change table name)
            String query = "SELECT * FROM Employee";
            ResultSet rs = stmt.executeQuery(query);

            // Display data
            System.out.println("\nEmployee Details:");
            System.out.println("----------------------");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("EmpID"));
                System.out.println("Name: " + rs.getString("Name"));
                System.out.println("Salary: " + rs.getDouble("Salary"));
                System.out.println("----------------------");
            }

            // Close resources
            rs.close();
            stmt.close();
            con.close();
            System.out.println("Connection closed.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
