package medioxide.model;

import javafx.beans.property.*;

public class ModelPatients {
    private IntegerProperty id;
    private IntegerProperty age;
    private StringProperty name;
    private StringProperty gender;
    private StringProperty phone;
    private StringProperty address;
    private BooleanProperty action;

    public int getWeight() {
        return weight.get();
    }

    public IntegerProperty weightProperty() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight.set(weight);
    }

    private IntegerProperty weight;
    private IntegerProperty systolic_bp;
    private IntegerProperty diastolic_bp;


    public ModelPatients(int id, String name, int age,
                         String gender, String phone, String address, boolean action) {
        int weight=10;
        int systolic_bp=20;
        int diastolic_bp=30;

        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
        this.gender = new SimpleStringProperty(gender);
        this.phone = new SimpleStringProperty(phone);
        this.address = new SimpleStringProperty(address);
        this.action = new SimpleBooleanProperty(action);
        this.weight = new SimpleIntegerProperty(weight);
        this.systolic_bp = new SimpleIntegerProperty(systolic_bp);
        this.diastolic_bp = new SimpleIntegerProperty(diastolic_bp);
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

    public int getAge() {
        return age.get();
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
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

    public String getGender() {
        return gender.get();
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
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
