package medioxide.databaseConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {
    private static final String URL = "jdbc:mysql://localhost:3306/mexioxidetemp";
    private static final String ADMIN = "root";
    private static final String PASSWORD = "";
    private static Connection connection;
    private static Statement statement;


    public static void connectionJDBC() {
        //Step 2: Driver Load;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            System.out.println("ForName class found failed");
        }

        try {
            connection = DriverManager.getConnection(URL, ADMIN, PASSWORD);
            statement = connection.createStatement();

        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("JDBC Connection Failed");
        }

    }

    public static Statement getStatement() {
        connectionJDBC();
        return statement;
    }

    public static Connection getConnection() {
        connectionJDBC();
        return connection;
    }
}