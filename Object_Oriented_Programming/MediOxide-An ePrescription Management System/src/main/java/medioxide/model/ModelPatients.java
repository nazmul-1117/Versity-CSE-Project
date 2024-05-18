package medioxide.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ModelPatients {
    private IntegerProperty id, age;
    private StringProperty name;
    private String gender;
    private String phone;
    private String address;

    public ModelPatients(int id, String name, int age, String gender, String phone, String address) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
        this.gender = gender;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public int getAge() {
        return age.get();
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public IntegerProperty ageProperty() {
        return age;
    }
}
