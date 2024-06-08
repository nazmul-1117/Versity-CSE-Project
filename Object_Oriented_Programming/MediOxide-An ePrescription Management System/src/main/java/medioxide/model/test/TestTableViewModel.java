package medioxide.model.test;

import javafx.beans.property.*;

public class TestTableViewModel {
    private IntegerProperty id;
    private StringProperty name;
    private StringProperty category;
    private StringProperty description;

    private FloatProperty normalRange;
    private FloatProperty price;

    public TestTableViewModel() {
    }

    public TestTableViewModel(int id, String name, String category, String description, float normalRange, float price) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.category = new SimpleStringProperty(category);
        this.description = new SimpleStringProperty(description);
        this.normalRange = new SimpleFloatProperty(normalRange);
        this.price = new SimpleFloatProperty(price);
    }

    public String getCategory() {
        return category.get();
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public float getNormalRange() {
        return normalRange.get();
    }

    public FloatProperty normalRangeProperty() {
        return normalRange;
    }

    public void setNormalRange(float normalRange) {
        this.normalRange.set(normalRange);
    }

    public float getPrice() {
        return price.get();
    }

    public FloatProperty priceProperty() {
        return price;
    }

    public void setPrice(float price) {
        this.price.set(price);
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

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

}
