package Bank.DB;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;

public class DBConfig {
//    Singleton Pattern
    private static final Dotenv dotenv = Dotenv.load();

    private static final String HOST = dotenv.get("HOST");
    private static final String PORT = dotenv.get("PORT");
    private static final String DATABASE = dotenv.get("DATABASE");
    private static final String USER = dotenv.get("USER");
    private static final String PASSWORD = dotenv.get("PASSWORD");
    private static final String JDBC_URL = String.format("jdbc:postgresql://%s:%s/%s", HOST, PORT, DATABASE);

    private DBConfig() throws SQLException {

    }

    public static Connection getDBConn() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }
}