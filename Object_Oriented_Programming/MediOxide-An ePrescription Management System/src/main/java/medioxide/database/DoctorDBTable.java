package medioxide.database;

import medioxide.model.doctor.DoctorMainModel;
import medioxide.model.doctor.DoctorModifyModel;
import medioxide.model.doctor.DoctorTableViewModel;
import medioxide.model.medicine.MedicineMainModel;
import medioxide.model.medicine.MedicineModifyModel;
import medioxide.model.medicine.MedicineTableViewModel;
import medioxide.model.patients.PatientsModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDBTable {

    public static void insertDataIntoDoctor(DoctorMainModel model){

        String query1 = "START TRANSACTION;";

        String query2 = "INSERT INTO doctor_personal_info " +
                "( doctor_name, doctor_surname, doctor_gender, doctor_phone, doctor_email, doctor_nid, " +
                "doctor_license_number, doctor_current_hospital ) " +
                "VALUES ( ?, ?, ?, ?, ?, ?, ?, ? );";

        String query3 = "SET @doctor_id = LAST_INSERT_ID(); " ;

        String query4 = "INSERT INTO doctor_professional_info ( doctor_id, doctor_department, " +
                "doctor_specialty, doctor_room_number, doctor_degree, doctor_experience ) " +
                "VALUES ( @doctor_id, ?, ?, ?, ?, ?);";

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

            ps2.setString(1, model.getName()); //name
            ps2.setString(2, model.getSurname()); //surname
            ps2.setString(3, model.getGender()); //gender
            ps2.setString(4, model.getPhone()); //phone

            ps2.setString(5, model.getEmail()); //email
            ps2.setInt(6, model.getNid()); //nid
            ps2.setString(7, model.getLicence()); //licence
            ps2.setString(8, model.getHospital()); //hospital

            ps3 = connection.prepareStatement(query3);

            ps4 = connection.prepareStatement(query4);


            ps4.setString(1, model.getDept()); //dept
            ps4.setString(2, model.getSpeciality());
            ps4.setString(3, model.getRoomNo());
            ps4.setString(4, model.getDegree());
            ps4.setInt(5, model.getExperience());

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

    public static List<DoctorTableViewModel> getAllDoctorList() {
        var doctorList = new ArrayList<DoctorTableViewModel>();
        var connection = DatabaseConnector.getConnection();

        try {
            String query = "SELECT di.doctor_id, di.doctor_name, di.doctor_email, di.doctor_license_number,\n" +
                    "dp.doctor_department, dp.doctor_specialty, dp.doctor_room_number \n" +
                    "FROM doctor_personal_info AS di INNER JOIN doctor_professional_info AS dp \n" +
                    "ON di.doctor_id = dp.doctor_id";

            var ps = connection.prepareStatement(query);
            var resultSet = ps.executeQuery();

            //                System.out.println("ID: "+resultSet.getInt("doctor_id"));
            while (resultSet.next())
                doctorList.add(new DoctorTableViewModel(

                    resultSet.getInt("doctor_id"),
                    resultSet.getString("doctor_name"),
                    resultSet.getString("doctor_email"),
                    resultSet.getString("doctor_license_number"),

                    resultSet.getString("doctor_department"),
                    resultSet.getString("doctor_specialty"),
                    resultSet.getString("doctor_room_number"),
                    true
            ));
        } catch (SQLException e) {
            System.out.println("SQL Query Execution Failed for get all patients list");
        }

        return doctorList;
    }

    public static List<DoctorModifyModel> getModifyDoctorListById(int searchId) {
        var doctorList = new ArrayList<DoctorModifyModel>();
        var conn = DatabaseConnector.getConnection();

        try {
            String query = "SELECT \n" +
                    "di.doctor_id, di.doctor_name, di.doctor_phone, di.doctor_email, \n" +
                    "dp.doctor_department, dp.doctor_specialty, dp.doctor_room_number, dp.doctor_degree\n" +
                    "\n" +
                    "FROM doctor_personal_info AS di INNER JOIN doctor_professional_info AS dp \n" +
                    "ON di.doctor_id = dp.doctor_id\n" +
                    "\n" +
                    "WHERE di.doctor_id = ?";

            var ps = conn.prepareStatement(query);
            ps.setInt(1, searchId);
//            System.out.println("Medicine PS: " + ps);

            var resultSet = ps.executeQuery();

            while (resultSet.next()) {
                doctorList.add(new DoctorModifyModel(
                        resultSet.getInt("doctor_id"),
                        resultSet.getString("doctor_name"),
                        resultSet.getString("doctor_phone"),
                        resultSet.getString("doctor_email"),

                        resultSet.getString("doctor_department"),
                        resultSet.getString("doctor_specialty"),
                        resultSet.getString("doctor_room_number"),
                        resultSet.getString("doctor_degree")
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL Query Execution Failed");
        }

        return doctorList;
    }

    public static void updateDoctorData(DoctorModifyModel model){

        String query1 = "UPDATE doctor_personal_info\n" +
                "SET \n" +
                "\tdoctor_name = ?,\n" +
                "    doctor_phone = ?,\n" +
                "    doctor_email = ?\n" +
                "WHERE doctor_id = ?;";

        String query2 = "UPDATE doctor_professional_info\n" +
                "SET \n" +
                "\tdoctor_department = ?,\n" +
                "    doctor_specialty = ?,\n" +
                "    doctor_room_number = ?,\n" +
                "    doctor_degree = ?\n" +
                "WHERE doctor_id = ?;";

        try {
            Connection connection = DatabaseConnector.getConnection();
            PreparedStatement ps = connection.prepareStatement(query1);

            ps.setString(1, model.getName());
            ps.setString(2, model.getPhone());
            ps.setString(3, model.getEmail());
            ps.setInt(4, model.getId());
            System.out.println("1st Table Query: " + ps);

            PreparedStatement ps2 = connection.prepareStatement(query2);
            ps2.setString(1, model.getDept());
            ps2.setString(2, model.getSpeciality());
            ps2.setString(3, model.getRoomNo());
            ps2.setString(4, model.getDegree());
            ps2.setInt(5, model.getId());
            System.out.println("2nd Table Query: " + ps);


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

        System.out.println("Update Successfully for ID: " + model.getId() + "\n");

    }

    public static List<DoctorTableViewModel> getDoctorListById(int searchId) {
        var doctorList = new ArrayList<DoctorTableViewModel>();
        var conn = DatabaseConnector.getConnection();

        try {
            String query = "SELECT di.doctor_id, di.doctor_name, di.doctor_email, di.doctor_license_number,\n" +
                    "dp.doctor_department, dp.doctor_specialty, dp.doctor_room_number \n" +
                    "FROM doctor_personal_info AS di INNER JOIN doctor_professional_info AS dp \n" +
                    "ON di.doctor_id = dp.doctor_id" +
                    " Where di.doctor_id = ?";

            var ps = conn.prepareStatement(query);
            ps.setInt(1, searchId);
            System.out.println("PS: " + ps);

            var resultSet = ps.executeQuery();

            while (resultSet.next()) {
                doctorList.add(new DoctorTableViewModel(

                        resultSet.getInt("doctor_id"),
                        resultSet.getString("doctor_name"),
                        resultSet.getString("doctor_email"),
                        resultSet.getString("doctor_license_number"),

                        resultSet.getString("doctor_department"),
                        resultSet.getString("doctor_specialty"),
                        resultSet.getString("doctor_room_number"),
                        true
                ));
                System.out.println("ID: " + resultSet.getInt("doctor_id"));
            }
        } catch (SQLException e) {
//            System.out.println("SQL Query Execution Failed for get by id");
            System.out.println(e.getMessage());
        }

        return doctorList;
    }

    public static List<DoctorTableViewModel> getDoctorListByName(String searchName) {
        var doctorList = new ArrayList<DoctorTableViewModel>();
        var conn = DatabaseConnector.getConnection();

        try {
            searchName = "%" + searchName + "%";
            String query = "SELECT di.doctor_id, di.doctor_name, di.doctor_email, di.doctor_license_number,"+
                    "                    dp.doctor_department, dp.doctor_specialty, dp.doctor_room_number " +
                    "                    FROM doctor_personal_info AS di INNER JOIN doctor_professional_info AS dp"+
                    "WHERE doctor_name LIKE ?;";

            var ps = conn.prepareStatement(query);
            ps.setString(1, searchName);
            System.out.println("PS: " + ps);

            var resultSet = ps.executeQuery();
            while (resultSet.next()) {
                doctorList.add(new DoctorTableViewModel(

                        resultSet.getInt("doctor_id"),
                        resultSet.getString("doctor_name"),
                        resultSet.getString("doctor_email"),
                        resultSet.getString("doctor_license_number"),

                        resultSet.getString("doctor_department"),
                        resultSet.getString("doctor_specialty"),
                        resultSet.getString("doctor_room_number"),
                        true
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL Query Execution Failed for get by name");
        }

        return doctorList;
    }
}
