package medioxide.databaseConnector;

import medioxide.model.ModelPatients;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectFromPatients {
    public static List<ModelPatients> getAllPatientList() {
        var patientsList = new ArrayList<ModelPatients>();
        var conn = DatabaseConnector.getConnection();

        try {
            String query = "SELECT * FROM patients";
            var ps = conn.prepareStatement(query);
            var resultSet = ps.executeQuery();

            while (resultSet.next()) {
                patientsList.add(new ModelPatients(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),

                        resultSet.getString("gender"),
                        resultSet.getString("phone"),
                        resultSet.getString("address")
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL Query Execution Failed");
        }

        return patientsList;
    }

    public static List<ModelPatients> getPatientListById(String searchId) {
        var patientsList = new ArrayList<ModelPatients>();
        var conn = DatabaseConnector.getConnection();

        try {
            String query = "SELECT * FROM patients WHERE id = ?;";
            var ps = conn.prepareStatement(query);
            ps.setString(1, searchId);
            var resultSet = ps.executeQuery();

            while (resultSet.next()) {
                patientsList.add(new ModelPatients(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),

                        resultSet.getString("gender"),
                        resultSet.getString("phone"),
                        resultSet.getString("address")
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
            searchName = "%"+searchName+"%";
            String query = "SELECT * FROM patients WHERE name LIKE ?;";

            var ps = conn.prepareStatement(query);
            ps.setString(1, searchName);
            var resultSet = ps.executeQuery();
            System.out.println("RS "+resultSet);

            while (resultSet.next()) {
                patientsList.add(new ModelPatients(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),

                        resultSet.getString("gender"),
                        resultSet.getString("phone"),
                        resultSet.getString("address")
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL Query Execution Failed");
        }

        return patientsList;
    }
}
