package medioxide.database;

import medioxide.model.medicine.MedicineMainModel;
import medioxide.model.test.TestMainModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
