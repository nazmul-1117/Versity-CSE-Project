package medioxide.controller.prescription;

import com.itextpdf.kernel.colors.Lab;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.JobSettings;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import medioxide.controller.patients.PatientsModifyController;
import medioxide.controller.problem.ProblemModifyController;
import medioxide.database.PatientsDBTable;
import medioxide.database.PrescriptionDBTable;
import medioxide.database.ProblemDBTable;
import medioxide.java.Main;
import medioxide.model.prescription.PrescriptionMedicineModel;
import medioxide.model.prescription.PrescriptionPatientsModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrescriptionController implements Initializable {

    public TextField patientsID;
    public TextField patientsName;
    public TextField patientsAge;
    public TextField patientsGender;

    public TextField doctorID;
    public TextField doctorName;
    public TextField doctorDept;
    public TextField doctorSpec;

    public JFXButton addPatientsButtonID;
    public JFXButton addDoctorButtonID;

    private PrescriptionPatientsModel patientsModel;

    @FXML
    private VBox vbPrescribeMedicineList;


    @FXML
    private ScrollPane scrollPane;
    @FXML
    private BorderPane borderPaneScene;


    private ObservableList<PrescriptionMedicineModel> medicineList = FXCollections.observableArrayList();


    private void setData() {
        patientsID.clear();
        patientsID.setText(Integer.toString(patientsModel.getId()));
        patientsName.setText(patientsModel.getName());
        patientsAge.setText(Integer.toString(patientsModel.getAge()));
        patientsGender.setText(patientsModel.getGender());
    }

    public void addPatientsButtonClicked() {

        try {
            String fxml = "prescription_patients_search.fxml";
            URL fxmlURL = Main.class.getResource(fxml);

            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            Scene scene = new Scene(fxmlLoader.load());

            PrescriptionSearchPatientsController pspc = fxmlLoader.getController();
            pspc.init(patientsModel -> {
                setPatientsInfo(patientsModel);
                return null;
            });
            patientsModel = pspc.getPatientsModel();

            Stage stage = new Stage();
            stage.setScene(scene);

            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

        } catch (IOException ioException) {
            System.out.println("modify_patients.fxml failed");
        }
    }

    private void setPatientsInfo(PrescriptionPatientsModel prescriptionPatientsModel) {
        patientsID.setText(String.valueOf(prescriptionPatientsModel.getId()));
        patientsName.setText(prescriptionPatientsModel.getName());
    }

    public void addDoctorButtonClicked(ActionEvent event) {
    }

    public void onTapNextVisit(MouseEvent mouseEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //addPatientsButtonClicked();


        medicineList.addListener((ListChangeListener<PrescriptionMedicineModel>) change -> {
            populateSelectedMedicine();

        });

    }

    private void populateSelectedMedicine() {

        vbPrescribeMedicineList.getChildren().clear();

        for (PrescriptionMedicineModel medicineModel : medicineList) {
            var medicineName = new Label(medicineModel.getName());
            var routine = new Label(medicineModel.getDailyRoutine());

            vbPrescribeMedicineList.getChildren().add(new VBox(2, medicineName, routine));
        }

    }

    @FXML
    public void generatePrescriptionButtonClicked(ActionEvent event) {
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if (printerJob != null && printerJob.showPrintDialog(null)) {
            JobSettings jobSettings = printerJob.getJobSettings();
            jobSettings.setPageLayout(printerJob.getPrinter().createPageLayout(javafx.print.Paper.A4, javafx.print.PageOrientation.PORTRAIT, Printer.MarginType.EQUAL_OPPOSITES));

            try {

                Node copy = borderPaneScene.getParent();
                copy.setLayoutX((jobSettings.getPageLayout().getPrintableWidth() - borderPaneScene.getWidth()) / 2.0);
                Scale scale = new Scale(0.6, 0.6, 0, 0); // 80% scale
                copy.getTransforms().add(scale);

                boolean success = printerJob.printPage(copy);
                if (success) {
                    printerJob.endJob();
                }
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
    }


    public void addNewMedicine(ActionEvent event) {
        try {
            String fxml = "prescription_medicine.fxml";
            URL fxmlURL = Main.class.getResource(fxml);
            System.out.println("URL: " + fxmlURL);

            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            Parent node = fxmlLoader.load();
            PrescriptionMedicineController controller = fxmlLoader.getController();
            controller.init(medicineModel -> {
                medicineList.add(medicineModel);
                return null;
            });

            Scene scene = new Scene(node);

            Stage stage = new Stage();
            stage.setScene(scene);

            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

        } catch (IOException ioException) {
            System.out.println("modify_patients.fxml failed");
        }
    }
}
