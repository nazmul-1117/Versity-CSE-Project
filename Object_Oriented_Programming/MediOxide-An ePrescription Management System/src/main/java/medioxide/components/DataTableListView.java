package medioxide.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import medioxide.model.ModelPatients;

public class DataTableListView<T> extends TableView<ModelPatients> {
    public DataTableListView(ObservableList<ModelPatients> observableList) {
        super(observableList);
        setup();
        setSelectionModel(null);
    }

    private void setup() {
        columnSetup();
        rowSetup();
    }

    private void rowSetup() {
        setRowFactory(tTableView -> {
            TableRow<ModelPatients> row = new TableRow<>() {
                @Override
                protected void updateItem(ModelPatients t, boolean b) {
                    super.updateItem(t, b);
                    if (t != null) {
                    }
                }
            };
            return row;
        });
    }

    private void columnSetup() {
        var column1 = new TableColumn<ModelPatients, Integer>("ID");
        column1.setPrefWidth(20);
        column1.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());


        var column2 = new TableColumn<ModelPatients, String>("Name");
        column2.setPrefWidth(30);
        column2.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        var column3 = new TableColumn<ModelPatients, Integer>("Age");
        column3.setPrefWidth(40);
        column3.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asObject());


        var column4 = new TableColumn<ModelPatients, String>("Gender");
        column4.setPrefWidth(40);
        column4.setCellValueFactory(cellData -> cellData.getValue().genderProperty());

        var column5 = new TableColumn<ModelPatients, String>("Phone");
        column5.setPrefWidth(40);
        column5.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());

        var column6 = new TableColumn<ModelPatients, String>("Address");
        column6.setPrefWidth(40);
        column6.setCellValueFactory(cellData -> cellData.getValue().addressProperty());


        ObservableList<TableColumn<ModelPatients, ?>> tableHeaders = FXCollections.observableArrayList(
                column1,
                column2,
                column3,
                column4,
                column5,
                column6
        );

        getSelectionModel().clearSelection();
        getColumns().clear();
        getColumns().setAll(tableHeaders);
        setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

}
