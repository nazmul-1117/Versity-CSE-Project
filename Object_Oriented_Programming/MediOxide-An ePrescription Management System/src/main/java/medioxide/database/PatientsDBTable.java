package medioxide.database;

import medioxide.model.patients.PatientsModel;
import medioxide.model.patients.PatientsModifyModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientsDBTable {
    public static List<PatientsModel> getAllPatientList() {
        var patientsList = new ArrayList<PatientsModel>();
        var conn = DatabaseConnector.getConnection();

        try {
            String query = "SELECT * FROM patients_personal_info\n";
            var ps = conn.prepareStatement(query);
            var resultSet = ps.executeQuery();

            while (resultSet.next()) {
                patientsList.add(new PatientsModel(

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
            System.out.println("SQL Query Execution Failed for get all patients list");
        }

        return patientsList;
    }

    public static List<PatientsModel> getPatientListById(int searchId) {
        var patientsList = new ArrayList<PatientsModel>();
        var conn = DatabaseConnector.getConnection();

        try {
            String query = "SELECT * FROM patients_personal_info WHERE patients_id = ?;";
            var ps = conn.prepareStatement(query);
            ps.setInt(1, searchId);
            System.out.println("PS: " + ps);

            var resultSet = ps.executeQuery();

            while (resultSet.next()) {
                patientsList.add(new PatientsModel(
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

    public static List<PatientsModifyModel> getModifyPatientListById(int searchId) {
        var patientsList = new ArrayList<PatientsModifyModel>();
        var conn = DatabaseConnector.getConnection();

        try {
            String query = "SELECT * FROM patients_personal_info WHERE patients_id = ?;";
            var ps = conn.prepareStatement(query);
            ps.setInt(1, searchId);
            System.out.println("PS: " + ps);

            var resultSet = ps.executeQuery();

            while (resultSet.next()) {
                patientsList.add(new PatientsModifyModel(
                        resultSet.getInt("patients_id"),
                        resultSet.getString("patients_name"),
                        resultSet.getString("patients_surname"),
                        resultSet.getInt("patients_age"),

                        resultSet.getString("patients_gender"),
                        resultSet.getString("patients_mobile"),
                        resultSet.getString("patients_email"),
                        resultSet.getString("patients_address")
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL Query Execution Failed");
        }

        return patientsList;
    }

    public static List<PatientsModel> getPatientListByName(String searchName) {
        var patientsList = new ArrayList<PatientsModel>();
        var conn = DatabaseConnector.getConnection();

        try {
            searchName = "%" + searchName + "%";
            String query = "SELECT * FROM patients_personal_info WHERE patients_name LIKE ?;";

            var ps = conn.prepareStatement(query);
            ps.setString(1, searchName);
            System.out.println("PS: " + ps);

            var resultSet = ps.executeQuery();
            while (resultSet.next()) {
                patientsList.add(new PatientsModel(
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
        var patientsList = new ArrayList<PatientsModel>();
        var conn = DatabaseConnector.getConnection();

        try {
            String query = "SELECT * FROM patients_personal_info WHERE patients_id = ?;";
            var ps = conn.prepareStatement(query);
            ps.setInt(1, searchId);
            var resultSet = ps.executeQuery();

            if (resultSet.next()) {
                System.out.println(resultSet.getString("patients_name"));
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Search by ID SQL Query Execution Failed");
        }

        return false;
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

        System.out.println("Update Successfully for ID: " + id + "\n");
        
    }

    public static void insertData(int age, int weight, int s_bp, int d_bp, float body_temp, String ... s){

        String query1 = "START TRANSACTION;";
        String query2 = "INSERT INTO patients_personal_info " +
                "(patients_name, patients_surname, patients_age, patients_gender, " +
                "patients_mobile, patients_email, patients_address)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?);\n";

        String query3 = "SET @patients_id = LAST_INSERT_ID(); " ;

        String query4 = "INSERT INTO patients_medical_info (patients_id, patients_visiting_department," +
                " patients_diseases, patients_weight, patients_systolic_bp, patients_diastolic_bp, " +
                "patients_body_temp, patients_previous_problem, patients_family_problem)\n" +
                "VALUES (@patients_id, ?, ?, ?, ?, ?, ?, ?, ?);\n";

        String query5 = "COMMIT;";

        try {
            Connection connection = DatabaseConnector.getConnection();
            PreparedStatement ps1=null;
            PreparedStatement ps2=null;
            PreparedStatement ps3=null;
            PreparedStatement ps4=null;
            PreparedStatement ps5=null;

            ps1 = connection.prepareStatement(query1);

            ps2 = connection.prepareStatement(query2);

            ps2.setString(1, s[0]); //name
            ps2.setString(2, s[1]); //surname
            ps2.setInt(3, age); //age
            ps2.setString(4, s[2]); //gender
            System.out.println("first 4 column");

            ps2.setString(5, s[3]); //phone
            ps2.setString(6, s[4]); //email
            ps2.setString(7, s[5]); //address
            System.out.println("second 3 column");

            ps3 = connection.prepareStatement(query3);
            System.out.println("PS3 executed");

            ps4 = connection.prepareStatement(query4);
            System.out.println("PS4 executed");

//            s[6] = "Neurology";

            ps4.setString(1, s[6]); //dept
            ps4.setString(2, s[7]);

            ps4.setInt(3, weight);
            ps4.setInt(4, s_bp);
            ps4.setInt(5, d_bp);
            ps4.setFloat(6, body_temp);
            System.out.println("weight, s dp, d dp saved");

            ps4.setString(7, s[8]);
            ps4.setString(8, s[9]);
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
                System.out.println("Query: " + ps4);
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
    public  static  void deletePatientsData(int id){
        String query = "DELETE FROM patients_personal_info WHERE patients_id = ?;";
        try {
            var connection = DatabaseConnector.getConnection();

            PreparedStatement ps = connection.prepareStatement(query);
            System.out.println("Delete id(database): " + id);

            ps.setInt(1, id);
            System.out.println("ID seated done, Query: " + ps);

            var resultSet = ps.executeUpdate();
        }catch (SQLException sqlException){
            System.out.println(sqlException.getMessage());
            System.out.println("SQL connection failed for delete query");
        }

    }
}
