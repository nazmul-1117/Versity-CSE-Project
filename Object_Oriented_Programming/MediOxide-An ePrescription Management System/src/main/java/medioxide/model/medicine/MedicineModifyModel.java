package medioxide.model.medicine;

public class MedicineModifyModel {
    private int id;
    private String name;
    private String types;
    private String generic;
    private String brands;
    private String description;

    public MedicineModifyModel(int id, String name, String types, String generic, String brands, String description) {
        this.id = id;
        this.name = name;
        this.types = types;
        this.generic = generic;
        this.brands = brands;
        this.description = description;
    }

    public MedicineModifyModel(String name, String types, String generic, String brands, String description) {
        this.name = name;
        this.types = types;
        this.generic = generic;
        this.brands = brands;
        this.description = description;
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

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getGeneric() {
        return generic;
    }

    public void setGeneric(String generic) {
        this.generic = generic;
    }

    public String getBrands() {
        return brands;
    }

    public void setBrands(String brands) {
        this.brands = brands;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
