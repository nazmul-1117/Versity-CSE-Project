package medioxide.database;

import medioxide.model.medicine.MedicineMainModel;
import medioxide.model.problem.ProblemMainModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProblemDBTable {

    public static void insertIntoProblem(ProblemMainModel model){

        String query = "INSERT INTO medical_problems (\n" +
                "    medical_problem_name,\n" +
                "    medical_department,\n" +
                "    medical_problem_description,\n" +
                "    medical_problem_symptoms,\n" +
                "    medical_problem_treatment\n" +
                ")\n" +
                "VALUES (\n" +
                "    ?,\n" +
                "    ?,\n" +
                "    ?,\n" +
                "    ?,\n" +
                "    ?\n" +
                ");\n";

        try {
            Connection connection = DatabaseConnector.getConnection();
            PreparedStatement ps =connection.prepareStatement(query);

            ps.setString(1, model.getName());
            ps.setString(2, model.getDepartment());
            ps.setString(3, model.getDescription());

            ps.setString(4, model.getSymptoms());
            ps.setString(5, model.getTreatment());

            int statement = ps.executeUpdate();

            if (statement < 1){
                System.out.println("Problem data insert into database failed");
            }
            System.out.println("Data inserted for Problem add.");

        }catch (SQLException e){

        }
    }
}
