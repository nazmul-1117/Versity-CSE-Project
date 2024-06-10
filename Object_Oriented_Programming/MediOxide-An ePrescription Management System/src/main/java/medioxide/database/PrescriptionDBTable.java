package medioxide.database;

import medioxide.model.patients.PatientsModel;
import medioxide.model.prescription.PrescriptionPatientsModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDBTable {
    public static List<PrescriptionPatientsModel> getPatientListById(int searchId) {
        var patientsList = new ArrayList<PrescriptionPatientsModel>();
        var conn = DatabaseConnector.getConnection();

        try {
            String query = "SELECT * FROM patients_personal_info WHERE patients_id = ?;";
            var ps = conn.prepareStatement(query);
            ps.setInt(1, searchId);
            System.out.println("PS: " + ps);

            var resultSet = ps.executeQuery();

            while (resultSet.next()) {
                patientsList.add(new PrescriptionPatientsModel(
                        resultSet.getInt("patients_id"),
                        resultSet.getString("patients_name"),
                        resultSet.getInt("patients_age"),

                        resultSet.getString("patients_gender")
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL Query Execution Failed");
        }

        return patientsList;
    }
}
