import java.sql.*;
import java.util.Scanner;

public class ProductCRUD {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("Connected to database!");

            while (true) {
                System.out.println("\n=== PRODUCT MANAGEMENT ===");
                System.out.println("1. Add Product");
                System.out.println("2. View All Products");
                System.out.println("3. Update Product Price");
                System.out.println("4. Delete Product");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                
                int choice;
                try {
                    choice = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a number (1-5).");
                    continue;
                }

                switch (choice) {
                    case 1 -> addProduct(conn, sc);
                    case 2 -> viewProducts(conn);
                    case 3 -> updateProduct(conn, sc);
                    case 4 -> deleteProduct(conn, sc);
                    case 5 -> {
                        System.out.println("Exiting program. Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid choice! Try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addProduct(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter product name: ");
        String name = sc.nextLine().trim();

        System.out.print("Enter product price: ");
        double price;
        try {
            price = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid price! Please enter a valid number.");
            return;
        }

        String sql = "INSERT INTO products (name, price) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setDouble(2, price);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Product added successfully!" : "Product insertion failed.");
        }
    }

    private static void viewProducts(Connection conn) throws SQLException {
        String sql = "SELECT * FROM products";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            boolean found = false;
            System.out.println("\n--- PRODUCT LIST ---");
            while (rs.next()) {
                found = true;
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Price: " + rs.getDouble("price"));
                System.out.println("----------------------");
            }
            if (!found) System.out.println("No products found in the database.");
        }
    }

    private static void updateProduct(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter product ID to update: ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID! Please enter a number.");
            return;
        }

        System.out.print("Enter new price: ");
        double newPrice;
        try {
            newPrice = Double.parseDouble(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid price! Please enter a number.");
            return;
        }

        String sql = "UPDATE products SET price = ? WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, newPrice);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Product updated successfully!" : "No product found with ID " + id);
        }
    }

    private static void deleteProduct(Connection conn, Scanner sc) throws SQLException {
        System.out.print("Enter product ID to delete: ");
        int id;
        try {
            id = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID! Please enter a number.");
            return;
        }

        String sql = "DELETE FROM products WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Product deleted successfully!" : "No product found with ID " + id);
        }
    }
}
