package medioxide.database;

import medioxide.model.patients.PatientsModel;
import medioxide.model.prescription.PrescriptionDoctorModel;
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

    public static List<PrescriptionDoctorModel> geDoctorListById(int searchId) {
        var doctorList = new ArrayList<PrescriptionDoctorModel>();
        var conn = DatabaseConnector.getConnection();

        try {
            String query = "SELECT di.doctor_id, di.doctor_name, di.doctor_email,\n" +
                    "dp.doctor_department, dp.doctor_specialty\n" +
                    "\n" +
                    "FROM doctor_personal_info AS di\n" +
                    "INNER JOIN doctor_professional_info AS dp\n" +
                    "ON di.doctor_id = dp.doctor_id\n" +
                    "\n" +
                    "WHERE di.doctor_id = ?;";


            var ps = conn.prepareStatement(query);
            ps.setInt(1, searchId);
            System.out.println("PS: " + ps);

            var resultSet = ps.executeQuery();

            while (resultSet.next()) {
                doctorList.add(new PrescriptionDoctorModel(
                        resultSet.getInt("doctor_id"),
                        resultSet.getString("doctor_name"),
                        resultSet.getString("doctor_email"),

                        resultSet.getString("doctor_department"),
                        resultSet.getString("doctor_specialty")
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL Query Execution Failed");
        }

        return doctorList;
    }
}
