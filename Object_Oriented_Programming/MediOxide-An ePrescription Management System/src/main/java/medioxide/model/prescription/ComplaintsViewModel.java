package medioxide.model.prescription;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ComplaintsViewModel {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty title;

    public ComplaintsViewModel(int id, String title) {
        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    @Override
    public String toString() {
        return title.get();
    }
}
