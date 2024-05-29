package medioxide.databaseConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertIntoPatients {

    public static void insertData(int age, String ... s){

        String query = "INSERT INTO patients(name, age, gender, phone, address )" +
                "VALUES (?, ?, ?, ?, ?)";
        try {
            var connection = DatabaseConnector.getConnection();
            var ps = connection.prepareStatement(query);
             var resultSet = ps.executeQuery();
        }catch (SQLException sqlException){
            System.out.println("SQL connection failed");
        }

        try {

            PreparedStatement preparedStatement = DatabaseConnector.getConnection().
                    prepareStatement(query);
            preparedStatement.setString(1, s[0]);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, s[1]);

            preparedStatement.setString(4, s[2]);
            preparedStatement.setString(5, s[3]);

            int rowNum = preparedStatement.executeUpdate();

            if (rowNum < 0) {
                System.out.println("Data Insert failed");
            } else {
                System.out.println("Data insert successful");
            }
        } catch (SQLException e) {
            System.out.println("Data not be inserted");
        }

    }

    public  static  void deleteData(int id){
        String query = "DELETE FROM patients WHERE id = ?";
        try {
            var connection = DatabaseConnector.getConnection();
            var ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            var resultSet = ps.executeUpdate();
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
            System.out.println("SQL connection failed");
        }

    }
}
