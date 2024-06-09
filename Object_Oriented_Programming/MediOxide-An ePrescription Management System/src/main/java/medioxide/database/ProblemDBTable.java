package medioxide.database;

import medioxide.model.medicine.MedicineMainModel;
import medioxide.model.medicine.MedicineTableViewModel;
import medioxide.model.problem.ProblemMainModel;
import medioxide.model.problem.ProblemTableViewModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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


    public static List<ProblemTableViewModel> getAllTestList() {
        var problemList = new ArrayList<ProblemTableViewModel>();
        var conn = DatabaseConnector.getConnection();

        try {
            String query = "SELECT * FROM medical_problems;\n";
            var ps = conn.prepareStatement(query);
            var resultSet = ps.executeQuery();

            try {
                while (resultSet.next()) {
                    problemList.add(new ProblemTableViewModel(

                            resultSet.getInt("medical_problem_id"),
                            resultSet.getString("medical_problem_name"),
                            resultSet.getString("medical_department"),

                            resultSet.getString("medical_problem_description"),
                            resultSet.getString("medical_problem_symptoms"),
                            resultSet.getString("medical_problem_treatment"),
                            true
                    ));
                }
            }catch (SQLException e){
                System.out.println("SQL Query Execution Failed for while loop for all medicine list");
            }

        } catch (SQLException e) {
            System.out.println("SQL Query Execution Failed for get all medicine list");
        }

        return problemList;
    }
}
