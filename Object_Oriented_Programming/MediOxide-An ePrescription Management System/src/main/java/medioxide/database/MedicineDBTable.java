package medioxide.database;

import medioxide.model.medicine.MedicineMainModel;
import medioxide.model.medicine.MedicineModifyModel;
import medioxide.model.medicine.MedicineTableViewModel;
import medioxide.model.patients.PatientsModel;

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
    public static List<MedicineModifyModel> getModifyMedicineListById(int searchId) {
        var medicineList = new ArrayList<MedicineModifyModel>();
        var conn = DatabaseConnector.getConnection();

        try {
            String query = "SELECT * FROM medicine WHERE medicine_id = ?;";
            var ps = conn.prepareStatement(query);
            ps.setInt(1, searchId);
            System.out.println("Medicine PS: " + ps);

            var resultSet = ps.executeQuery();

            while (resultSet.next()) {
                medicineList.add(new MedicineModifyModel(
                        resultSet.getInt("medicine_id"),
                        resultSet.getString("medicine_name"),
                        resultSet.getString("medicine_types"),

                        resultSet.getString("medicine_generic"),
                        resultSet.getString("medicine_brands"),
                        resultSet.getString("medicine_description")
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL Query Execution Failed");
        }

        return medicineList;
    }


    public static void updateMedicineData(MedicineModifyModel model){

        String query = "UPDATE medicine\n" +
                "SET \n" +
                "\tmedicine_name = ?,\n" +
                "    medicine_types = ?,\n" +
                "    medicine_generic = ?,\n" +
                "    medicine_brands = ?,\n" +
                "    medicine_description = ?\n" +
                "WHERE medicine_id = ?;";

        try {
            Connection connection = DatabaseConnector.getConnection();
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, model.getName());
            ps.setString(2, model.getTypes());
            ps.setString(3, model.getGeneric());
            ps.setString(4, model.getBrands());
            ps.setString(5, model.getDescription());

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
                System.out.println("Data insert successful " + statement);
            }
        } catch (SQLException e) {
            System.out.println("Data not be inserted");
        }

        System.out.println("Update Successfully for ID: " + model.getId() + "\n");

    }
    public  static  void deleteMedicineData(int id) {
        String query = "DELETE FROM medicine WHERE medicine_id = ?;";
        try {
            var connection = DatabaseConnector.getConnection();

            PreparedStatement ps = connection.prepareStatement(query);
            System.out.println("Delete id(database): " + id);

            ps.setInt(1, id);
            System.out.println("ID seated done, Query: " + ps);

            var resultSet = ps.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
            System.out.println("SQL connection failed for delete query");
        }
    }


    public static List<MedicineTableViewModel> getPatientListById(int searchId) {
        var medicineList = new ArrayList<MedicineTableViewModel>();
        var conn = DatabaseConnector.getConnection();

        try {
            String query = "SELECT * FROM medicine WHERE medicine_id = ?;";
            var ps = conn.prepareStatement(query);
            ps.setInt(1, searchId);
            System.out.println("PS: " + ps);

            var resultSet = ps.executeQuery();

            while (resultSet.next()) {
                medicineList.add(new MedicineTableViewModel(
                        resultSet.getInt("medicine_id"),
                        resultSet.getString("medicine_name"),
                        resultSet.getString("medicine_types"),

                        resultSet.getString("medicine_generic"),
                        resultSet.getString("medicine_brands"),
                        resultSet.getString("medicine_description"),
                        true
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL Query Execution Failed");
        }

        return medicineList;
    }
    public static List<MedicineTableViewModel> getPatientListByName(String searchName) {
        var medicineList = new ArrayList<MedicineTableViewModel>();
        var conn = DatabaseConnector.getConnection();

        try {
            searchName = "%" + searchName + "%";
            String query = "SELECT * FROM medicine WHERE medicine_name LIKE ?;";

            var ps = conn.prepareStatement(query);
            ps.setString(1, searchName);
            System.out.println("PS: " + ps);

            var resultSet = ps.executeQuery();
            while (resultSet.next()) {
                medicineList.add(new MedicineTableViewModel(
                        resultSet.getInt("medicine_id"),
                        resultSet.getString("medicine_name"),
                        resultSet.getString("medicine_types"),

                        resultSet.getString("medicine_generic"),
                        resultSet.getString("medicine_brands"),
                        resultSet.getString("medicine_description"),
                        true
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL Query Execution Failed");
        }

        return medicineList;
    }

}
