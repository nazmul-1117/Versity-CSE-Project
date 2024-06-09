package medioxide.model.problem;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProblemTableViewModel {

    private IntegerProperty id;
    private StringProperty name;
    private StringProperty department;
    private StringProperty description;
    private StringProperty symptoms;
    private StringProperty treatment;

    public ProblemTableViewModel(int id, String name, String department, String description, String symptoms, String treatment) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.department = new SimpleStringProperty(department);
        this.description = new SimpleStringProperty(description);
        this.symptoms = new SimpleStringProperty(symptoms);
        this.treatment = new SimpleStringProperty(treatment);
    }

    public ProblemTableViewModel(String name, String department, String description, String symptoms, String treatment) {
        this.name = new SimpleStringProperty(name);
        this.department = new SimpleStringProperty(department);
        this.description = new SimpleStringProperty(description);
        this.symptoms = new SimpleStringProperty(symptoms);
        this.treatment = new SimpleStringProperty(treatment);
    }
}
