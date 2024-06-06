package medioxide.databaseConnector;

import java.sql.*;

public class InsertIntoPatients {

    public static void insertData(int age, int weight, int s_bp, int d_bp, float body_temp,
                                  String ... s){

        String query1 = "START TRANSACTION;";
        String query2 = "INSERT INTO patients(name, age, gender, phone, address ) VALUES (?, ?, ?, ?, ?); ";

        String query3 = "SET @patients_id = LAST_INSERT_ID(); " ;

        String query4 = "INSERT INTO medical_info( patients_id, weight, s_bp, d_bp, visiting_dept, body_temp, prev_problem, family_problem ) " +
                "VALUES (@patients_id, ?, ?, ?, ?, ?, ?, ?);";

        String query5 = "COMMIT;";

//        try {
//            var connection = DatabaseConnector.getConnection();
//            PreparedStatement ps = connection.prepareStatement(query4);
//
//            var resultSet = ps.executeQuery();
//
////            System.out.println("Resultset: " + resultSet +"\n\n\n");
//        }catch (SQLException sqlException){
//            System.out.println("SQL connection failed");
//            sqlException.printStackTrace();
//        }

        try {
            Connection connection = DatabaseConnector.getConnection();
            PreparedStatement ps1=null;
            PreparedStatement ps2=null;
            PreparedStatement ps3=null;
            PreparedStatement ps4=null;
            PreparedStatement ps5=null;

            ps1 = connection.prepareStatement(query1);

            ps2 = connection.prepareStatement(query2);

                ps2.setString(1, s[0]);
                ps2.setInt(2, age);
                ps2.setString(3, s[1]);
                System.out.println("first 3 column");

                ps2.setString(4, s[2]);
                ps2.setString(5, s[3]);
                System.out.println("second 2 column");

            ps3 = connection.prepareStatement(query3);
            System.out.println("PS3 executed");

            ps4 = connection.prepareStatement(query4);
            System.out.println("PS4 executed");

                ps4.setInt(1, weight);
                ps4.setInt(2, s_bp);
                ps4.setInt(3, d_bp);
            System.out.println("weight, s dp, d dp saved");

                ps4.setString(4, s[4]);
                ps4.setFloat(5, body_temp);
                ps4.setString(6, s[5]);
                ps4.setString(7, s[6]);
                System.out.println("third 3 column");

            ps5 = connection.prepareStatement(query5);

            int statement1=-1;
            int statement2=-1;
            int statement3=-1;
            int statement4=-1;
            int statement5=-1;

            try {
                statement1 = ps1.executeUpdate();
                statement2 = ps2.executeUpdate();
                statement3 = ps3.executeUpdate();
                statement4 = ps4.executeUpdate();
                statement5 = ps5.executeUpdate();

            }catch (SQLException sqlException){
                System.out.println("\nExecuted Statement failed for: "+ sqlException.getMessage()+"\n\n");
            }

            if (statement1 < 0) {
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
