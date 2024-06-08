package medioxide.database;

import medioxide.model.medicine.MedicineMainModel;
import medioxide.model.medicine.MedicineTableViewModel;
import medioxide.model.test.TestMainModel;
import medioxide.model.test.TestTableViewModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicineDBTable {

    public static void insertIntoTest(MedicineMainModel model){

        String query = "INSERT INTO medicine (\n" +
                "    medicine_name,\n" +
                "    medicine_types,\n" +
                "    medicine_generic,\n" +
                "    medicine_brands,\n" +
                "    medicine_description\n" +
                ")\n" +
                "VALUES (" +
                "    ?," +
                "    ?," +
                "    ?," +
                "    ?," +
                "    ?" +
                ");";

        try {
            Connection connection = DatabaseConnector.getConnection();
            PreparedStatement ps =connection.prepareStatement(query);

            ps.setString(1, model.getName());
            ps.setString(2, model.getTypes());
            ps.setString(3, model.getGeneric());

            ps.setString(4, model.getBrands());
            ps.setString(5, model.getDescription());

            int statement = ps.executeUpdate();

            if (statement < 1){
                System.out.println("Medicine data insert into database failed");
            }
            System.out.println("Data inserted for Medicine add.");

        }catch (SQLException e){

        }
    }



    public static List<MedicineTableViewModel> getAllTestList() {
        var testList = new ArrayList<MedicineTableViewModel>();
        var conn = DatabaseConnector.getConnection();

        try {
            String query = "SELECT * FROM medicine;\n";
            var ps = conn.prepareStatement(query);
            var resultSet = ps.executeQuery();

            try {
                while (resultSet.next()) {
                    testList.add(new MedicineTableViewModel(

                            resultSet.getInt("medicine_id"),
                            resultSet.getString("medicine_name"),
                            resultSet.getString("medicine_types"),

                            resultSet.getString("medicine_generic"),
                            resultSet.getString("medicine_brands"),
                            resultSet.getString("medicine_description"),
                            true
                    ));
                }
            }catch (SQLException e){
                System.out.println("SQL Query Execution Failed for while loop for all medicine list");
            }

        } catch (SQLException e) {
            System.out.println("SQL Query Execution Failed for get all medicine list");
        }

        return testList;
    }

}
