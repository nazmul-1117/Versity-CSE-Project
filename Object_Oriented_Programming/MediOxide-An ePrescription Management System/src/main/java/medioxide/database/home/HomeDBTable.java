package medioxide.database.home;

import medioxide.database.DatabaseConnector;
import medioxide.model.home.HomeDoctorModel;
import medioxide.model.home.HomePatientsModel;
import medioxide.model.home.HomeTestModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomeDBTable {

    public static List<HomePatientsModel> getPatientsNumber(){
        var testList = new ArrayList<HomePatientsModel>();
        var conn = DatabaseConnector.getConnection();

        try {
            String query1 = "SELECT COUNT(patients_id) AS total_patients\n" +
                    "FROM patients_personal_info;";

            String query2 = "SELECT COUNT(doctor_id) AS total_doctors\n" +
                    "FROM doctor_personal_info;";

            String query3 = "SELECT COUNT(medical_test_id) AS total_test\n" +
                    "FROM medical_tests;";


            var ps1 = conn.prepareStatement(query1);
            var ps2 = conn.prepareStatement(query2);
            var ps3 = conn.prepareStatement(query3);

            var resultSet1 = ps1.executeQuery();
            var resultSet2 = ps2.executeQuery();
            var resultSet3 = ps3.executeQuery();

            while (resultSet1.next()) {
                testList.add(new HomePatientsModel(
                        resultSet1.getInt("total_patients")
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL Query Execution Failed");
        }

        return testList;
    }

    public static List<HomeDoctorModel> getDoctorNumber(){
        var testList = new ArrayList<HomeDoctorModel>();
        var conn = DatabaseConnector.getConnection();

        try {
            String query = "SELECT COUNT(doctor_id) AS total_doctors\n" +
                    "FROM doctor_personal_info;";

            var ps = conn.prepareStatement(query);

            var resultSet = ps.executeQuery();


            while (resultSet.next()) {
                testList.add(new HomeDoctorModel(
                        resultSet.getInt("total_doctors")
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL Query Execution Failed");
        }

        return testList;
    }

    public static List<HomeTestModel> getTestNumber(){
        var testList = new ArrayList<HomeTestModel>();
        var conn = DatabaseConnector.getConnection();

        try {
            String query = "SELECT COUNT(medical_test_id) AS total_tests\n" +
                    "FROM medical_tests;";

            var ps = conn.prepareStatement(query);

            var resultSet = ps.executeQuery();


            while (resultSet.next()) {
                testList.add(new HomeTestModel(
                        resultSet.getInt("total_tests")
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL Query Execution Failed");
        }

        return testList;
    }


}
