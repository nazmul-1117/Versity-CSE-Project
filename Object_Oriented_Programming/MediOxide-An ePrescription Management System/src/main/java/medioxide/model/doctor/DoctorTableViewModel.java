package medioxide.model.doctor;

import javafx.beans.property.*;

public class DoctorTableViewModel {
    private IntegerProperty id;
    private StringProperty name;
    private StringProperty email;
    private StringProperty licence;

    private StringProperty dept;
    private StringProperty speciality;
    private StringProperty roomNo;
    private BooleanProperty action;

    public DoctorTableViewModel(int id, String name, String email, String licence, String dept, String speciality, String roomNo, boolean action) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.licence = new SimpleStringProperty(licence);
        this.dept = new SimpleStringProperty(dept);
        this.speciality = new SimpleStringProperty(speciality);
        this.roomNo = new SimpleStringProperty(roomNo);
        this.action = new SimpleBooleanProperty(action);
    }

    public DoctorTableViewModel(String name, String email, String licence, String dept, String speciality, String roomNo, boolean action) {
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.licence = new SimpleStringProperty(licence);
        this.dept = new SimpleStringProperty(dept);
        this.speciality = new SimpleStringProperty(speciality);
        this.roomNo = new SimpleStringProperty(roomNo);
        this.action = new SimpleBooleanProperty(action);
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

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getLicence() {
        return licence.get();
    }

    public StringProperty licenceProperty() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence.set(licence);
    }

    public String getDept() {
        return dept.get();
    }

    public StringProperty deptProperty() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept.set(dept);
    }

    public String getSpeciality() {
        return speciality.get();
    }

    public StringProperty specialityProperty() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality.set(speciality);
    }

    public String getRoomNo() {
        return roomNo.get();
    }

    public StringProperty roomNoProperty() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo.set(roomNo);
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
