package medioxide.database;

import medioxide.model.problem.ProblemMainModel;
import medioxide.model.problem.ProblemModifyModel;
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


    public static List<ProblemTableViewModel> getAllProblemList() {
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

    public static List<ProblemModifyModel> getModifyProblemListById(int searchId) {
        var problemList = new ArrayList<ProblemModifyModel>();
        var conn = DatabaseConnector.getConnection();

        try {
            String query = "SELECT * FROM medical_problems WHERE medical_problem_id = ?;";
            var ps = conn.prepareStatement(query);
            ps.setInt(1, searchId);
//            System.out.println("Medicine PS: " + ps);

            var resultSet = ps.executeQuery();

            while (resultSet.next()) {
                problemList.add(new ProblemModifyModel(

                        resultSet.getInt("medical_problem_id"),
                        resultSet.getString("medical_problem_name"),
                        resultSet.getString("medical_department"),

                        resultSet.getString("medical_problem_description"),
                        resultSet.getString("medical_problem_symptoms"),
                        resultSet.getString("medical_problem_treatment")
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL Query Execution Failed");
        }

        return problemList;
    }

    public static void updateProblemData(ProblemModifyModel model){

        String query = "UPDATE medical_problems\n" +
                "SET\n" +
                "    medical_problem_name = ?,\n" +
                "    medical_department = ?,\n" +
                "    medical_problem_description = ?,\n" +
                "    medical_problem_symptoms = ?,\n" +
                "    medical_problem_treatment = ?\n" +
                "    \n" +
                "WHERE medical_problem_id = ?";

        try {
            Connection connection = DatabaseConnector.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, model.getName());
            ps.setString(2, model.getDepartment());
            ps.setString(3, model.getDescription());

            ps.setString(4, model.getSymptoms());
            ps.setString(5, model.getTreatment());

            ps.setInt(6, model.getId());


            int statement=-1;
            try {
                statement = ps.executeUpdate();

            }catch (SQLException sqlException){
                System.out.println("\nExecuted Statement failed for: "+ sqlException.getMessage()+"\n\n");
            }

            if (statement < 0) {
                System.out.println("Data Update failed " + statement);
            } else {
                System.out.println("Data Update successful " + statement);
            }
        } catch (SQLException e) {
            System.out.println("Data not be Updated");
        }

        System.out.println("Update Successfully for ID: " + model.getId() + "\n");

    }
    public  static  void deleteProblemData(int id) {
        String query = "DELETE FROM medical_problems WHERE medical_problem_id = ?;";
        try {
            var connection = DatabaseConnector.getConnection();

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            var resultSet = ps.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            System.out.println("SQL connection failed for delete query");
        }
    }


    public static List<ProblemTableViewModel> getProblemListById(int searchId) {
        var medicineList = new ArrayList<ProblemTableViewModel>();
        var conn = DatabaseConnector.getConnection();

        try {
            String query = "SELECT * FROM medical_problems WHERE medical_problem_id = ?;";
            var ps = conn.prepareStatement(query);
            ps.setInt(1, searchId);
            System.out.println("PS: " + ps);

            var resultSet = ps.executeQuery();

            while (resultSet.next()) {
                medicineList.add(new ProblemTableViewModel(
                        resultSet.getInt("medical_problem_id"),
                        resultSet.getString("medical_problem_name"),
                        resultSet.getString("medical_department"),

                        resultSet.getString("medical_problem_description"),
                        resultSet.getString("medical_problem_symptoms"),
                        resultSet.getString("medical_problem_treatment"),
                        true
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL Query Execution Failed");
        }

        return medicineList;
    }
    public static List<ProblemTableViewModel> getProblemListByName(String searchName) {
        var medicineList = new ArrayList<ProblemTableViewModel>();
        var conn = DatabaseConnector.getConnection();

        try {
            searchName = "%" + searchName + "%";
            String query = "SELECT * FROM medical_problems WHERE medical_problem_name LIKE ?;";

            var ps = conn.prepareStatement(query);
            ps.setString(1, searchName);
            System.out.println("PS: " + ps);

            var resultSet = ps.executeQuery();
            while (resultSet.next()) {
                medicineList.add(new ProblemTableViewModel(
                        resultSet.getInt("medical_problem_id"),
                        resultSet.getString("medical_problem_name"),
                        resultSet.getString("medical_department"),

                        resultSet.getString("medical_problem_description"),
                        resultSet.getString("medical_problem_symptoms"),
                        resultSet.getString("medical_problem_treatment"),
                        true
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL Query Execution Failed");
        }

        return medicineList;
    }
}
