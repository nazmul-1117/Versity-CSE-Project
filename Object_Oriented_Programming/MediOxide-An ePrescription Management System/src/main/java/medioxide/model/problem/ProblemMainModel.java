package medioxide.model.problem;

public class ProblemMainModel {
    private int id;
    private String name;
    private String department;
    private String description;
    private String symptoms;
    private String treatment;

    public ProblemMainModel() {
    }

    public ProblemMainModel(int id, String name, String department, String description, String symptoms, String treatment) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.description = description;
        this.symptoms = symptoms;
        this.treatment = treatment;
    }

    public ProblemMainModel(String name, String department, String description, String symptoms, String treatment) {
        this.name = name;
        this.department = department;
        this.description = description;
        this.symptoms = symptoms;
        this.treatment = treatment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
}
