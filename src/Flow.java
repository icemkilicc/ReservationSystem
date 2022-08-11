import javafx.scene.control.Button;

import java.util.ArrayList;

public class Flow {

    private String film_name;
    private Film film;

    private String ActiveHall;

    private String max_error;

    private String title;

    private String discount;

    private String block_time;

    private User active_player;

    private int time_control = 0;

    private int retries =0;

    public int getRetries() {
        return retries;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }

    public int getTime_control() {
        return time_control;
    }

    public void setTime_control(int time_control) {
        this.time_control = time_control;
    }

    public ArrayList<Seat> seat_list = new ArrayList<>();

    public ArrayList<Button> button_list = new ArrayList<>();

    public User getActive_player() {
        return active_player;
    }

    public void setActive_player(User active_player) {
        this.active_player = active_player;
    }

    public String getMax_error() {
        return max_error;
    }

    public void setMax_error(String max_error) {
        this.max_error = max_error;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getBlock_time() {
        return block_time;
    }

    public void setBlock_time(String block_time) {
        this.block_time = block_time;
    }

    public String getActiveHall() {
        return ActiveHall;
    }

    public void setActiveHall(String activeHall) {
        ActiveHall = activeHall;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public String getFilm_name() {
        return film_name;
    }

    public void setFilm_name(String film_name) {
        this.film_name = film_name;
    }
}
