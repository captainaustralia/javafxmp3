package sample;

import javafx.beans.property.SimpleStringProperty;

public class Music {
    public Integer number;
    private final SimpleStringProperty name;
    private final SimpleStringProperty path;
    private final SimpleStringProperty refactorPath;


    Music(int number, String name, String path) {
        this.number = number;
        this.name = new SimpleStringProperty(name);
        this.path = new SimpleStringProperty(path);
        this.refactorPath = new SimpleStringProperty();
    }

    public String getName() {
        return name.get();
    }

    public void setName(String value) {
        name.set(value);
    }

    public String getPath() {
        return path.get();
    }

    public void setPath(String value) {
        path.set(value);
    }

    public String getRefactorPath() {
        return refactorPath.get();
    }

    public void setRefactorPathPath(String value) {
        refactorPath.set(value);
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer value) {
        number = value;
    }
}

