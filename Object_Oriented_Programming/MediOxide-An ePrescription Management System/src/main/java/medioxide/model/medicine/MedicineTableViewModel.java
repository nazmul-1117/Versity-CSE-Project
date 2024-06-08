package medioxide.model.medicine;

import javafx.beans.property.*;

public class MedicineTableViewModel {

    private IntegerProperty id;
    private StringProperty name;
    private StringProperty type;
    private StringProperty generic;
    private StringProperty brand;
    private StringProperty description;
    private BooleanProperty action;

    public MedicineTableViewModel(int id, String name, String type, String generic, String brand, String description, boolean action) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.type = new SimpleStringProperty(type);
        this.generic = new SimpleStringProperty(generic);
        this.brand = new SimpleStringProperty(brand);
        this.description = new SimpleStringProperty(description);
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

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getGeneric() {
        return generic.get();
    }

    public StringProperty genericProperty() {
        return generic;
    }

    public void setGeneric(String generic) {
        this.generic.set(generic);
    }

    public String getBrand() {
        return brand.get();
    }

    public StringProperty brandProperty() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
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
