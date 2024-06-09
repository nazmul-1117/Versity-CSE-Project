package medioxide.model.problem;

import javafx.beans.property.*;

public class ProblemTableViewModel {

    private IntegerProperty id;
    private StringProperty name;
    private StringProperty department;
    private StringProperty description;
    private StringProperty symptoms;
    private StringProperty treatment;
    private BooleanProperty action;

    public ProblemTableViewModel(int id, String name, String department, String description, String symptoms, String treatment, boolean action) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.department = new SimpleStringProperty(department);
        this.description = new SimpleStringProperty(description);
        this.symptoms = new SimpleStringProperty(symptoms);
        this.treatment = new SimpleStringProperty(treatment);
        this.action = new SimpleBooleanProperty(action);
    }

    public ProblemTableViewModel(String name, String department, String description, String symptoms, String treatment) {
        this.name = new SimpleStringProperty(name);
        this.department = new SimpleStringProperty(department);
        this.description = new SimpleStringProperty(description);
        this.symptoms = new SimpleStringProperty(symptoms);
        this.treatment = new SimpleStringProperty(treatment);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDepartment() {
        return department.get();
    }

    public StringProperty departmentProperty() {
        return department;
    }

    public void setDepartment(String department) {
        this.department.set(department);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getSymptoms() {
        return symptoms.get();
    }

    public StringProperty symptomsProperty() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms.set(symptoms);
    }

    public String getTreatment() {
        return treatment.get();
    }

    public StringProperty treatmentProperty() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment.set(treatment);
    }

    public boolean isAction() {
        return action.get();
    }

    public BooleanProperty actionProperty() {
        return action;
    }

    public void setAction(boolean action) {
        this.action.set(action);
    }
}

