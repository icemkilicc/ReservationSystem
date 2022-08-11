import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Film {
    private String film_name;
    private String trailer_path;
    private String duration;

    public ArrayList<Hall> halls = new ArrayList<>();

    private StringProperty object;

    public Film() {
    }

    public String getFilm_name() {
        return film_name;
    }

    public void setFilm_name(String film_name) {
        this.film_name = film_name;
    }

    public String getTrailer_path() {
        return trailer_path;
    }

    public void setTrailer_path(String trailer_path) {
        this.trailer_path = trailer_path;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getObject() {
        return object.get();
    }

    public StringProperty objectProperty() {
        return object;
    }

    public void setObject(String object) {
        this.object.set(object);
    }

    public Film(String film_name, String trailer_path, String duration) {
        this.film_name = film_name;
        this.trailer_path = trailer_path;
        this.duration = duration;
        this.object = new SimpleStringProperty(this,film_name);
    }
}
