package medioxide.components;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import medioxide.helper.OnClickListener;
import medioxide.java.Main;
import medioxide.model.ModelPatients;

import java.util.Objects;

public class DataTableListView<T> extends TableView<ModelPatients> {
    OnClickListener listener;
    public DataTableListView(ObservableList<ModelPatients> observableList, OnClickListener listener) {
        super(observableList);
        this.listener = listener;
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


        var column7 = new TableColumn<ModelPatients, Boolean>("Action");
        column7.setPrefWidth(40);
        column7.setCellValueFactory(cellData -> cellData.getValue().actionProperty());
        column7.setCellFactory(col -> new ButtonCell<>());


        ObservableList<TableColumn<ModelPatients, ?>> tableHeaders = FXCollections.observableArrayList(
                column1,
                column2,
                column3,
                column4,
                column5,
                column6,
                column7
        );

        getSelectionModel().clearSelection();
        getColumns().clear();
        getColumns().setAll(tableHeaders);
        setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }


    private class ButtonCell<S, T> extends TableCell<S, T> {
        private final Button editButton;
        private final Button deleteButton;

        ButtonCell() {
            editButton = new Button();
            editButton.setStyle("-fx-background-color: #007F80 ;");
            var eyeImageView = new ImageView(new Image(Objects.requireNonNull(Main.class.getResource("image/edit.png")).toExternalForm()));
            eyeImageView.setFitWidth(14);
            eyeImageView.setFitHeight(14);
            editButton.setGraphic(eyeImageView);

            deleteButton = new Button();
            deleteButton.setStyle("-fx-background-color: #E34E3B ;");
            var printImageView = new ImageView(new Image(Objects.requireNonNull(Main.class.getResource("image/delete.png")).toExternalForm()));
            printImageView.setFitWidth(14);
            printImageView.setFitHeight(14);
            deleteButton.setGraphic(printImageView);
            deleteButton.setOnMouseClicked(mouseEvent -> {
                var currentItem = getTableRow().getItem();
                if (currentItem instanceof ModelPatients m) {
                    listener.onDeleteClick(m.getId());
                }
            });

        }

        @Override
        protected void updateItem(T item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setGraphic(null);
            } else {
                HBox hBox = new HBox(10, editButton, deleteButton);
                hBox.setAlignment(Pos.CENTER_RIGHT);
                setGraphic(hBox);
            }
        }
    }

}
