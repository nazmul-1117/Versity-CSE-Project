package medioxide.model.prescription;

public class PrescriptionDoctorModel {

    private int dId;
    private String dName;
    private String dEmail;
    private String dDept;
    private String dSpec;

    public PrescriptionDoctorModel(int dId, String dName, String dEmail, String dDept, String dSpec) {
        this.dId = dId;
        this.dName = dName;
        this.dEmail = dEmail;
        this.dDept = dDept;
        this.dSpec = dSpec;
    }

    public PrescriptionDoctorModel(String dName, String dEmail, String dDept, String dSpec) {
        this.dName = dName;
        this.dEmail = dEmail;
        this.dDept = dDept;
        this.dSpec = dSpec;
    }

    public int getdId() {
        return dId;
    }

    public void setdId(int dId) {
        this.dId = dId;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getdEmail() {
        return dEmail;
    }

    public void setdEmail(String dEmail) {
        this.dEmail = dEmail;
    }

    public String getdDept() {
        return dDept;
    }

    public void setdDept(String dDept) {
        this.dDept = dDept;
    }

    public String getdSpec() {
        return dSpec;
    }

    public void setdSpec(String dSpec) {
        this.dSpec = dSpec;
    }
}
