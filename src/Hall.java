import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Hall {
    public String film_name;
    public String hall_name;
    public String price_per_seat;
    public String row;
    public String column;
    public StringProperty object;

    public ArrayList<Seat> seat_list = new ArrayList<>();

    public Hall(String film_name, String hall_name, String price_per_seat, String row, String column) {
        this.film_name = film_name;
        this.hall_name = hall_name;
        this.price_per_seat = price_per_seat;
        this.row = row;
        this.column = column;
        this.object = new SimpleStringProperty(this,film_name);
    }
}
