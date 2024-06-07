package medioxide.database;

import medioxide.model.ModelPatients;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectFromPatients {
    public static List<ModelPatients> getAllPatientList() {
        var patientsList = new ArrayList<ModelPatients>();
        var conn = DatabaseConnector.getConnection();

        try {
            String query = "SELECT * FROM patients_personal_info\n";
            var ps = conn.prepareStatement(query);
            var resultSet = ps.executeQuery();

            while (resultSet.next()) {
                patientsList.add(new ModelPatients(

                        resultSet.getInt("patients_id"),
                        resultSet.getString("patients_name"),
                        resultSet.getInt("patients_age"),

                        resultSet.getString("patients_gender"),
                        resultSet.getString("patients_mobile"),
                        resultSet.getString("patients_address"),
                        true
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL Query Execution Failed");
        }

        return patientsList;
    }

    public static List<ModelPatients> getPatientListById(int searchId) {
        var patientsList = new ArrayList<ModelPatients>();
        var conn = DatabaseConnector.getConnection();

        try {
            String query = "SELECT * FROM patients_personal_info WHERE id = ?;";
            var ps = conn.prepareStatement(query);
            ps.setInt(1, searchId);
            var resultSet = ps.executeQuery();

            while (resultSet.next()) {
                patientsList.add(new ModelPatients(
                        resultSet.getInt("patients_id"),
                        resultSet.getString("patients_name"),
                        resultSet.getInt("patients_age"),

                        resultSet.getString("patients_gender"),
                        resultSet.getString("patients_mobile"),
                        resultSet.getString("patients_address"),
                        true
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL Query Execution Failed");
        }

        return patientsList;
    }

    public static List<ModelPatients> getPatientListByName(String searchName) {
        var patientsList = new ArrayList<ModelPatients>();
        var conn = DatabaseConnector.getConnection();

        try {
            searchName = "%" + searchName + "%";
            String query = "SELECT * FROM patients_personal_info WHERE name LIKE ?;";

            var ps = conn.prepareStatement(query);
            ps.setString(1, searchName);
            var resultSet = ps.executeQuery();
            System.out.println("RS " + resultSet);

            while (resultSet.next()) {
                patientsList.add(new ModelPatients(
                        resultSet.getInt("patients_id"),
                        resultSet.getString("patients_name"),
                        resultSet.getInt("patients_age"),

                        resultSet.getString("patients_gender"),
                        resultSet.getString("patients_mobile"),
                        resultSet.getString("patients_address"),
                        true
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL Query Execution Failed");
        }

        return patientsList;
    }

    public static boolean searchById(int searchId){
        var patientsList = new ArrayList<ModelPatients>();
        var conn = DatabaseConnector.getConnection();

        try {
            String query = "SELECT * FROM patients_personal_info WHERE id = ?;";
            var ps = conn.prepareStatement(query);
            ps.setInt(1, searchId);
            var resultSet = ps.executeQuery();

            if (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
                return true;
            }
        } catch (SQLException e) {
            System.out.println("SQL Query Execution Failed");
        }

        return false;
    }

    public static List<ModelPatients> getModifyPatientList() {
        var patientsList = new ArrayList<ModelPatients>();
        var conn = DatabaseConnector.getConnection();

        try {
            String query = "SELECT * FROM patients_personal_info";
            var ps = conn.prepareStatement(query);
            var resultSet = ps.executeQuery();

            while (resultSet.next()) {
                patientsList.add(new ModelPatients(
                        resultSet.getInt("patients_id"),
                        resultSet.getString("patients_name"),
                        resultSet.getInt("patients_age"),

                        resultSet.getString("patients_gender"),
                        resultSet.getString("patients_mobile"),
                        resultSet.getString("patients_address"),
                        true
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL Query Execution Failed");
        }

        return patientsList;
    }
    
    public static void updatePatientsIntoDatabase(int id, int age, String ... s){
        
        String query = "UPDATE patients_personal_info\n" +
                "SET\n" +
                "    patients_name = ?,\n" +
                "    patients_surname = ?,\n" +
                "    patients_age = ?,\n" +
                "    patients_gender = ?,\n" +
                "    patients_mobile = ?,\n" +
                "    patients_email = ?,\n" +
                "    patients_address = ?\n" +
                "WHERE\n" +
                "    patients_id = ?\n";
        
        try {
            Connection connection = DatabaseConnector.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);

            ps = connection.prepareStatement(query);

            ps.setString(1, s[0]); //name
            ps.setString(2, s[1]);  //surname
            ps.setInt(3, age);
            ps.setString(4, s[2]); //gender
//            System.out.println("first 4 column");

            ps.setString(5, s[3]);  //phone
            ps.setString(6, s[4]);  //email
            ps.setString(7, s[5]);  //address
            ps.setInt(8, id);  //id
//            System.out.println("Lasy 3 column");


            int statement=-1;
            try {
                statement = ps.executeUpdate();

            }catch (SQLException sqlException){
                System.out.println("\nExecuted Statement failed for: "+ sqlException.getMessage()+"\n\n");
            }

            if (statement < 0) {
                System.out.println("Data Insert failed " + statement);
            } else {
                System.out.println("Data insert successful " + statement);
            }
        } catch (SQLException e) {
            System.out.println("Data not be inserted");
        }
        
    }
}
