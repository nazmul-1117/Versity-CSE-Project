package medioxide.model.test;

public class TestMainModel {
    private int id;
    private String name;
    private String category;
    private String description;
    private float normalRange;
    private float price;

    public TestMainModel() {
    }

    public TestMainModel(int id, String name, String category, String description, float normalRange, float price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.normalRange = normalRange;
        this.price = price;
    }
    public TestMainModel(String name, String category, String description, float normalRange, float price) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.normalRange = normalRange;
        this.price = price;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getNormalRange() {
        return normalRange;
    }

    public void setNormalRange(float normalRange) {
        this.normalRange = normalRange;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
