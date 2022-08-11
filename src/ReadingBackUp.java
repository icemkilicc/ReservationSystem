import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class ReadingBackUp {

    public ArrayList<User> user = new ArrayList<User>();
    public ArrayList<Film> film = new ArrayList<Film>();
    public ArrayList<Hall> hall = new ArrayList<Hall>();
    public ArrayList<Seat> seat = new ArrayList<Seat>();

    public ArrayList<String> halls_name = new ArrayList<>();
    public void Reading() throws FileNotFoundException {

        File myObj = new File("assets\\data\\backup.dat");
        Scanner myReader = new Scanner(myObj);



        while (myReader.hasNext()) {

            String commandline = myReader.nextLine();
            String[] parts = commandline.split("\t");

            if(Objects.equals(parts[0], "user")){
                user.add(new User(parts[1],parts[2],parts[3],parts[4]));
            }
            else if(Objects.equals(parts[0], "film")){
                film.add(new Film(parts[1],parts[2],parts[3]));
            }
            else if(Objects.equals(parts[0], "hall")){
                hall.add(new Hall(parts[1],parts[2],parts[3],parts[4],parts[5]));
                film.get(film.size()-1).halls.add(new Hall(parts[1],parts[2],parts[3],parts[4],parts[5]));
                halls_name.add(parts[3]);
            }
            else if(Objects.equals(parts[0], "seat")){
                seat.add(new Seat(parts[1],parts[2],parts[3],parts[4],parts[5],parts[6]));
                film.get(film.size()-1).halls.get(film.get(film.size()-1).halls.size()-1).seat_list.add(new Seat(parts[1],parts[2],parts[3],parts[4],parts[5],parts[6]));
            }
        }
        ArrayList<String> names = new ArrayList<>();
        for (User users : user){
            names.add(users.username);
        }
        for(Seat seats : seat){
            if(!names.contains(seats.owner_name)){
                seats.owner_name="";
            }
        }
    }

    public void ReadingProperties(Flow control) throws FileNotFoundException {

        File myObj = new File("assets\\data\\properties.dat");
        Scanner myReader = new Scanner(myObj);

        while (myReader.hasNext()) {

            String commandline = myReader.nextLine();
            String[] parts = commandline.split("=");

            if(Objects.equals(parts[0], "maximum-error-without-getting-blocked")){
                control.setMax_error(parts[1]);
            }
            else if(Objects.equals(parts[0], "title")){
                control.setTitle(parts[1]);
            }
            else if(Objects.equals(parts[0], "discount-percentage")){
                control.setDiscount(parts[1]);
            }
            else if(Objects.equals(parts[0], "block-time")){
                control.setBlock_time(parts[1]);
            }

        }

    }

}
