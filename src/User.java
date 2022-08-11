import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
    public String username;
    public String password;
    public String club_member;
    public String admin;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClub_member() {
        return club_member;
    }

    public void setClub_member(String club_member) {
        this.club_member = club_member;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public User(String username, String club_member, String admin) {
        this.username = username;
        this.club_member = club_member;
        this.admin = admin;
    }

    public StringProperty object;

    public User(String username, String password, String club_member, String admin) {
        this.username = username;
        this.password = password;
        this.club_member = club_member;
        this.admin = admin;
        this.object = new SimpleStringProperty(this,username);
    }


}
