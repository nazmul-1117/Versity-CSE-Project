package medioxide.model.home;

public class HomePatientsModel {

    private int totalPatients;
    public HomePatientsModel() {
    }
    public HomePatientsModel(int totalPatients) {
        this.totalPatients = totalPatients;
    }

    public int getTotalPatients() {
        return totalPatients;
    }

    public void setTotalPatients(int totalPatients) {
        this.totalPatients = totalPatients;
    }

}
