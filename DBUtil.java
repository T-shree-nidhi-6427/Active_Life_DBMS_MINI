import java.sql.*;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/active_life";
    private static final String USER = "root"; // your MySQL username
    private static final String PASS = "Supersonu.17"; // your MySQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static void insertUser(String name, int age, String health, String serious) {
        String query = "INSERT INTO users (name, age, health_issues, serious_conditions) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, health);
            ps.setString(4, serious);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
