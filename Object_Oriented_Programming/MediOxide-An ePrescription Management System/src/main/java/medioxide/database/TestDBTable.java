package medioxide.database;

import medioxide.model.test.TestMainModel;
import medioxide.model.test.TestModifyModel;
import medioxide.model.test.TestTableViewModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestDBTable {

    public static List<TestTableViewModel> getAllTestList() {
        var testList = new ArrayList<TestTableViewModel>();
        var conn = DatabaseConnector.getConnection();

        try {
            String query = "SELECT * FROM medical_tests;\n";
            var ps = conn.prepareStatement(query);
            var resultSet = ps.executeQuery();

            try {
                while (resultSet.next()) {
                    testList.add(new TestTableViewModel(

                            resultSet.getInt("medical_test_id"),
                            resultSet.getString("medical_test_name"),
                            resultSet.getString("medical_test_category"),

                            resultSet.getString("medical_test_description"),
                            resultSet.getFloat("medical_test_normal_range"),
                            resultSet.getFloat("medical_test_price"),
                            true
                    ));
                }
            }catch (SQLException e){
                System.out.println("SQL Query Execution Failed for while loop for all test list");
            }

        } catch (SQLException e) {
            System.out.println("SQL Query Execution Failed for get all test list");
        }

        return testList;
    }

    public static void insertIntoTest(TestMainModel model){


        String query = "INSERT INTO medical_tests(\n" +
                "    medical_test_name,\n" +
                "    medical_test_category,\n" +
                "    medical_test_description,\n" +
                "    medical_test_normal_range,\n" +
                "    medical_test_price\n" +
                ")\n" +
                "VALUES\n" +
                "(?, ?, ?, ?, ?);";

        try {
            Connection connection = DatabaseConnector.getConnection();
            PreparedStatement ps =connection.prepareStatement(query);

            ps.setString(1, model.getName());
            ps.setString(2, model.getCategory());
            ps.setString(3, model.getDescription());

            ps.setFloat(4, model.getNormalRange());
            ps.setFloat(5, model.getPrice());

            int statement = ps.executeUpdate();

            if (statement < 1){
                System.out.println("Test data insert into database failed");
            }
            System.out.println("Data inserted for Test add.");


        }catch (SQLException e){

        }
    }

    public static void updateTestIntoDatabase(TestModifyModel model){

        String query = "UPDATE medical_tests\n" +
                "SET \n" +
                "    medical_test_name = ?,\n" +
                "    medical_test_category = ?,\n" +
                "    medical_test_description = ?,\n" +
                "    medical_test_normal_range = ?,\n" +
                "    medical_test_price = ?\n" +
                "WHERE medical_test_id = ?;";

        try {
            Connection connection = DatabaseConnector.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, model.getName());
            ps.setString(2, model.getCategory());
            ps.setString(3, model.getDescription());

            ps.setFloat(4, model.getNormalRange());
            ps.setFloat(5, model.getPrice());

            ps.setInt(6, model.getId());



            int statement=-1;
            try {
                statement = ps.executeUpdate();

            }catch (SQLException sqlException){
                System.out.println("\nExecuted Statement failed for: "+ sqlException.getMessage()+"\n\n");
            }

            if (statement < 0) {
                System.out.println("Data Insert failed " + statement);
            } else {
                System.out.println("Data Update successful " + statement);
            }
        } catch (SQLException e) {
            System.out.println("Data not be inserted");
        }

        System.out.println("Update Successfully for ID: " + model.getId() + "\n");

    }

    public static List<TestModifyModel> getModifyTestListById(int searchId){
        var testList = new ArrayList<TestModifyModel>();
        var conn = DatabaseConnector.getConnection();

        try {
            String query = "SELECT * FROM medical_tests WHERE medical_test_id = ?;";
            var ps = conn.prepareStatement(query);
            ps.setInt(1, searchId);
            System.out.println("PS: " + ps);

            var resultSet = ps.executeQuery();

            while (resultSet.next()) {
                testList.add(new TestModifyModel(
                        resultSet.getInt("medical_test_id"),
                        resultSet.getString("medical_test_name"),
                        resultSet.getString("medical_test_category"),

                        resultSet.getString("medical_test_description"),
                        resultSet.getFloat("medical_test_normal_range"),
                        resultSet.getFloat("medical_test_price")
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL Query Execution Failed");
        }

        return testList;
    }

    public static List<TestTableViewModel> getTestListById(int searchId) {
        var testList = new ArrayList<TestTableViewModel>();
        var conn = DatabaseConnector.getConnection();

        try {
            String query = "SELECT * FROM medical_tests WHERE medical_test_id = ?;";
            var ps = conn.prepareStatement(query);
            ps.setInt(1, searchId);
            System.out.println("PS: " + ps);

            var resultSet = ps.executeQuery();

            while (resultSet.next()) {
                testList.add(new TestTableViewModel(
                        resultSet.getInt("medical_test_id"),
                        resultSet.getString("medical_test_name"),
                        resultSet.getString("medical_test_category"),

                        resultSet.getString("medical_test_description"),
                        resultSet.getFloat("medical_test_normal_range"),
                        resultSet.getFloat("medical_test_price"),
                        true
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL Query Execution Failed");
        }

        return testList;
    }
    public static List<TestTableViewModel> getTestListByName(String searchName) {
        var testList = new ArrayList<TestTableViewModel>();
        var conn = DatabaseConnector.getConnection();

        try {
            searchName = "%" + searchName + "%";
            String query = "SELECT * FROM medical_tests WHERE medical_test_name LIKE ?;";

            var ps = conn.prepareStatement(query);
            ps.setString(1, searchName);
            System.out.println("PS: " + ps);

            var resultSet = ps.executeQuery();
            while (resultSet.next()) {
                testList.add(new TestTableViewModel(
                        resultSet.getInt("medical_test_id"),
                        resultSet.getString("medical_test_name"),
                        resultSet.getString("medical_test_category"),

                        resultSet.getString("medical_test_description"),
                        resultSet.getFloat("medical_test_normal_range"),
                        resultSet.getFloat("medical_test_price"),
                        true
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL Query Execution Failed");
        }

        return testList;
    }
}
