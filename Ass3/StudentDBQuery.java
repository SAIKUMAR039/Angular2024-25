import java.sql.*;

public class StudentDBQuery {
    public static void main(String[] args) {
        // PostgreSQL Database connection details
        String url = "jdbc:postgresql://localhost:5432/studentdb"; // Ensure this matches your database name
        String user = "postgres";  // Change to your PostgreSQL username
        String password = "sai"; // Change to your PostgreSQL password

        try {
            // Load PostgreSQL JDBC Driver
            Class.forName("org.postgresql.Driver");

            // Establish connection
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✅ Connected to PostgreSQL successfully!");

            // Query to fetch all students
            String query = "SELECT * FROM Students";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Display student records
            System.out.println("\n🎓 Student Records:");
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                int age = rs.getInt("Age");
                String major = rs.getString("Major");

                System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age + ", Major: " + major);
            }

            // Get metadata about the table
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            System.out.println("\n📊 Table Metadata:");
            for (int i = 1; i <= columnCount; i++) {
                System.out.println("Column " + i + ": " + metaData.getColumnName(i) + " (" + metaData.getColumnTypeName(i) + ")");
            }

            // Close resources
            rs.close();
            stmt.close();
            conn.close();
            System.out.println("\n✅ Connection closed.");

        } catch (ClassNotFoundException e) {
            System.err.println("❌ PostgreSQL JDBC Driver not found! Add the JAR file.");
        } catch (SQLException e) {
            System.err.println("❌ Database error: " + e.getMessage());
        }
    }
}
