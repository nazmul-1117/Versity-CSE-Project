package medioxide.model.home;

public class HomeModel {
    private int totalPrescription;
    private int totalPatients;
    private int totalDoctor;
    private int totalTest;
    private int totalAdmin;
    private int totalRevenue;

    public HomeModel() {
    }
    public HomeModel(int totalPatients) {
        this.totalPatients = totalPatients;
    }

    public HomeModel(int totalPatients, int totalDoctor, int totalTest) {
        this.totalPatients = totalPatients;
        this.totalDoctor = totalDoctor;
        this.totalTest = totalTest;
    }

    public HomeModel(int totalPrescription, int totalPatients, int totalDoctor, int totalTest, int totalAdmin, int totalRevenue) {
        this.totalPrescription = totalPrescription;
        this.totalPatients = totalPatients;
        this.totalDoctor = totalDoctor;
        this.totalTest = totalTest;
        this.totalAdmin = totalAdmin;
        this.totalRevenue = totalRevenue;
    }

    public int getTotalPrescription() {
        return totalPrescription;
    }

    public void setTotalPrescription(int totalPrescription) {
        this.totalPrescription = totalPrescription;
    }

    public int getTotalPatients() {
        return totalPatients;
    }

    public void setTotalPatients(int totalPatients) {
        this.totalPatients = totalPatients;
    }

    public int getTotalDoctor() {
        return totalDoctor;
    }

    public void setTotalDoctor(int totalDoctor) {
        this.totalDoctor = totalDoctor;
    }

    public int getTotalTest() {
        return totalTest;
    }

    public void setTotalTest(int totalTest) {
        this.totalTest = totalTest;
    }

    public int getTotalAdmin() {
        return totalAdmin;
    }

    public void setTotalAdmin(int totalAdmin) {
        this.totalAdmin = totalAdmin;
    }

    public int getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(int totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
