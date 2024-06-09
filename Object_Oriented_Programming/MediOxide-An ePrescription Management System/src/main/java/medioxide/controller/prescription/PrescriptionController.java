package medioxide.controller.prescription;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import medioxide.controller.others.ChipSingleItemController;
import medioxide.database.ProblemDBTable;
import medioxide.java.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import medioxide.model.problem.ProblemTableViewModel;
import org.controlsfx.control.textfield.TextFields;

public class PrescriptionController implements Initializable {
    @FXML
    private FlowPane flowPanChiefComplaints;
    private final ObservableList<ProblemTableViewModel> selectedProblem = FXCollections.observableArrayList();
    private final List<ProblemTableViewModel> problemList = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateViewModelData();

        populateProblemsView();


        selectedProblem.addListener((ListChangeListener<? super ProblemTableViewModel>) c -> {
            populateProblemsView();
        });
    }

    private void populateProblemsView() {
        List<Node> children = new ArrayList<>();
        for (var model : selectedProblem) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("chip_single_item_view.fxml"));
                Node node = fxmlLoader.load();
                ChipSingleItemController controller = fxmlLoader.getController();
                Runnable action = () -> selectedProblem.remove(model);
                controller.init(model.getName(), action);
                children.add(node);
            } catch (IOException e) {
            }
        }
        TextField textField = new TextField();
        textField.autosize();
        textField.setPrefWidth(220);
        textField.setMaxWidth(220);


        var autoCompletionBinding = TextFields.bindAutoCompletion(textField, suggestionRequest -> {
            String enteredText = suggestionRequest.getUserText().toLowerCase();

            if (enteredText.trim().isEmpty()) {
                return problemList;
            }

            return problemList.stream().filter(item -> item.getName().toLowerCase().contains(enteredText)).collect(Collectors.toList());
        });

        autoCompletionBinding.setOnAutoCompleted(event -> {
            var selectedModel = event.getCompletion();
            if (selectedModel == null) {
                return;
            }
            selectedProblem.add(selectedModel);

            flowPanChiefComplaints.requestFocus();

        });

        textField.focusedProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal) {
                autoCompletionBinding.setUserInput("");

                Bounds bounds = textField.localToScreen(textField.getBoundsInLocal());
                autoCompletionBinding.setPrefWidth(bounds.getWidth());
            }
        });
        children.add(textField);

        flowPanChiefComplaints.getChildren().clear();
        flowPanChiefComplaints.getChildren().addAll(children);


    }
    private void populateViewModelData() {
         problemList.clear();
         problemList.addAll( ProblemDBTable.getAllProblemList());

    }
    @FXML
    public void onTapDateOfBirth(MouseEvent mouseEvent) {
    }
    @FXML
    public void onTapIssueDate(MouseEvent mouseEvent) {
    }
    public void onTapNextVisit(MouseEvent mouseEvent) {
    }
}
