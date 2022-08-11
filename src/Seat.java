import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Seat {
    public String film_name;
    public String hall_name;
    public String row_of_seat;
    public String column_of_seat;
    public String owner_name;
    public String price_that_it_has_been_bought;

    public StringProperty object;

    public Seat() {
    }

    public Seat(String film_name, String hall_name, String row_of_seat, String column_of_seat, String owner_name, String price_that_it_has_been_bought) {
        this.film_name = film_name;
        this.hall_name = hall_name;
        this.row_of_seat = row_of_seat;
        this.column_of_seat = column_of_seat;
        this.owner_name = owner_name;
        this.price_that_it_has_been_bought = price_that_it_has_been_bought;
        this.object = new SimpleStringProperty(this,film_name);
    }
}
