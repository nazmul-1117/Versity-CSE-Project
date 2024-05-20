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
//                        if (t.getSl() % 2 == 0) {
//                            setStyle("-fx-background-color:#F9F9F9;");
//                        } else {
//                            setStyle("-fx-background-color:#C9F0F0;");
//                        }
                    }
                }
            };

//            row.setOnMouseClicked(event -> {
//                // Handle row click event if needed
//            });

            return row;
        });
    }

    private void columnSetup() {
        var column1 = new TableColumn<ModelPatients, Integer>("id");
        column1.setPrefWidth(20);
        column1.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());


        var column2 = new TableColumn<ModelPatients, String>("name");
        column2.setPrefWidth(30);
        column2.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        var column3 = new TableColumn<ModelPatients, Integer>("age");
        column3.setPrefWidth(40);
        column3.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asObject());


        var column4 = new TableColumn<ModelPatients, String>("gender");
        column4.setPrefWidth(40);
        column4.setCellValueFactory(cellData -> cellData.getValue().genderProperty());

        var column5 = new TableColumn<ModelPatients, String>("phone");
        column5.setPrefWidth(40);
        column5.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());

        var column6 = new TableColumn<ModelPatients, String>("address");
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
