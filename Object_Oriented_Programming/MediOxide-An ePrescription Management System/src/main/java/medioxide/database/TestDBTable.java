package medioxide.database;

import medioxide.model.patients.PatientsModel;
import medioxide.model.test.TestMainModel;
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
}
