package medioxide.database.home;

import medioxide.database.DatabaseConnector;
import medioxide.model.home.HomeModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomeDBTable {

    public static List<HomeModel> getPatientsNumber(){
        var testList = new ArrayList<HomeModel>();
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
                testList.add(new HomeModel(
                        resultSet1.getInt("total_patients")
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL Query Execution Failed");
        }

        return testList;
    }

    public static List<HomeModel> getDoctorNumber(){
        var testList = new ArrayList<HomeModel>();
        var conn = DatabaseConnector.getConnection();

        try {
            String query = "SELECT COUNT(doctor_id) AS total_doctors\n" +
                    "FROM doctor_personal_info;";


            var ps = conn.prepareStatement(query);


            var resultSet = ps.executeQuery();


            while (resultSet.next()) {
                testList.add(new HomeModel(
                        resultSet.getInt("total_patients")
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL Query Execution Failed");
        }

        return testList;
    }


}
