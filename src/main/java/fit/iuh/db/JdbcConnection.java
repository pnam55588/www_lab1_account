package fit.iuh.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
    private static  JdbcConnection instance;
    private Connection connection;

    private static final String URL = "jdbc:mariadb://127.0.0.1:3306/mydb";
    private static final String DB = "mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "123";

    private JdbcConnection() throws SQLException {
        connection = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to establish a database connection.", e);
        }
    }
    public Connection getConnection() {
        return connection;
    }

    public static synchronized JdbcConnection getInstance() {
        if (instance == null) {
            try {
                instance = new JdbcConnection();
            } catch (SQLException e) {
                throw new RuntimeException("Failed to create JdbcConnection instance.", e);
            }
        }
        return instance;
    }
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException("Failed to close the database connection.", e);
            }
        }
    }

}
