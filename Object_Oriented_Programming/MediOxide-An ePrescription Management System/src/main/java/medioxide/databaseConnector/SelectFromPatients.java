package medioxide.databaseConnector;

import medioxide.model.ModelPatients;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectFromPatients {
    private static Statement statement;
    private static ResultSet resultSet;

    public static void showAllDataFromDatabase(ObservableList<ModelPatients> patientsList,
                                               TableView<ModelPatients> tableView) {
        String query = "SELECT * FROM patients;";
        selectQuery(patientsList, tableView, query);
    }

    public static void searchByID(ObservableList<ModelPatients> patientsList,
                                  TableView<ModelPatients> tableView, String searchID) {
        String query = "SELECT * FROM patients WHERE id = " + searchID + ";";
        selectQuery(patientsList, tableView, query);
    }

    private static void selectQuery(ObservableList<ModelPatients> patientsList,
                                    TableView<ModelPatients> tableView, String q) {
        statement = DatabaseConnector.getStatement();
        String query = q;
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            System.out.println("SQL Query Execution Failed");
            throw new RuntimeException(e);
        }
        try {
            while (resultSet.next()) {
                patientsList.add(new ModelPatients(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),

                        resultSet.getString("gender"),
                        resultSet.getString("phone"),
                        resultSet.getString("address")
                ));
            }
        } catch (SQLException sqlException) {
            System.out.println("SQL Failed");
        }
        tableView.setItems(patientsList);
    }
}
