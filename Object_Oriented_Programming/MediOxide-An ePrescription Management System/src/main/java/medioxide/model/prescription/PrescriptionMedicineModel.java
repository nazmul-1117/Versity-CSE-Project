package medioxide.model.prescription;

public class PrescriptionMedicineModel {

    private String type;
    private String name;
    private String power;
    private String dailyRoutine;
    private String beforeAfter;
    private String time;

    public PrescriptionMedicineModel(String type, String name, String power, String dailyRoutine, String beforeAfter, String time) {
        this.type = type;
        this.name = name;
        this.power = power;
        this.dailyRoutine = dailyRoutine;
        this.beforeAfter = beforeAfter;
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getDailyRoutine() {
        return dailyRoutine;
    }

    public void setDailyRoutine(String dailyRoutine) {
        this.dailyRoutine = dailyRoutine;
    }

    public String getBeforeAfter() {
        return beforeAfter;
    }

    public void setBeforeAfter(String beforeAfter) {
        this.beforeAfter = beforeAfter;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
