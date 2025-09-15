import java.sql.Connection;
import java.sql.DriverManager;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
    private static final String URL = "jdbc:mysql://localhost:3306/smartstudent?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // change if your MySQL user is different
    private static final String PASSWORD = "password"; // put your real MySQL password

    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Explicitly load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Database connected successfully!");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found. Add mysql-connector-j.jar to your classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
        return conn;
    }
}



