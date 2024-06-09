package medioxide.database;

import medioxide.model.doctor.DoctorMainModel;
import medioxide.model.medicine.MedicineMainModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
            System.out.println("first 4 column");

            ps2.setString(5, model.getEmail()); //email
            ps2.setInt(6, model.getNid()); //nid
            ps2.setString(7, model.getLicence()); //licence
            ps2.setString(8, model.getHospital()); //hospital
            System.out.println("second 4 column");

            ps3 = connection.prepareStatement(query3);
            System.out.println("PS3 executed");

            ps4 = connection.prepareStatement(query4);
            System.out.println("PS4 executed");


            ps4.setString(1, model.getDept()); //dept
            ps4.setString(2, model.getSpeciality());
            ps4.setString(3, model.getRoomNo());
            ps4.setString(4, model.getDegree());
            ps4.setInt(5, model.getExperience());

            System.out.println("third 4 column");

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
}
