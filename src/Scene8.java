import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Scene8 extends Pane{


    public Scene Work(ReadingBackUp list, Stage primaryStage,Flow control) {

        //SEAT

        Hall active_hall = null;
        for(Hall halls: control.getFilm().halls){
            if(Objects.equals(halls.hall_name, control.getActiveHall())){
                active_hall = halls;
            }
        }

        Pane grid = new Pane();
        grid.setMinSize(600+(Integer.parseInt(active_hall.column)-5)*80,780+(Integer.parseInt(active_hall.row)-7)*80);
        if(Objects.equals(control.getActive_player().admin, "false")){
            grid.setMinSize(600+(Integer.parseInt(active_hall.column)-5)*80,680+(Integer.parseInt(active_hall.row)-7)*80);
        }

        Text textWelcome = new Text(control.getFilm().getFilm_name()+" ("+control.getFilm().getDuration()+" minutes) "+"Hall: "+control.getActiveHall());
        textWelcome.relocate(145+(Integer.parseInt(active_hall.column)-5)*40,30);
        textWelcome.setTextAlignment(TextAlignment.CENTER);
        grid.getChildren().add(textWelcome);

        int Firstx = 95;
        int Firsty = 70;
        int seat_number =0;

        //Creating the seats

        if((control.button_list.size()==0||control.button_list.size()!=Integer.parseInt(active_hall.column)*Integer.parseInt(active_hall.row))&&active_hall.seat_list.size()==0) {

            control.button_list.clear();
            control.seat_list.clear();

            for (int i = 0; i < Integer.parseInt(active_hall.row); i++) {

                for (int j = 0; j < Integer.parseInt(active_hall.column); j++) {

                    File f = new File("assets/icons/empty_seat.png");
                    Image img = new Image(f.toURI().toString());
                    ImageView view = new ImageView(img);
                    view.setFitHeight(50);
                    view.setFitWidth(50);

                    Button button = new Button();
                    button.setGraphic(view);
                    button.relocate(Firstx, Firsty);
                    grid.getChildren().add(button);

                    active_hall.seat_list.add(new Seat(control.getFilm_name(), control.getActiveHall(), String.valueOf(i + 1), String.valueOf(j + 1), "", active_hall.price_per_seat));
                    control.button_list.add(button);
                    control.seat_list.add(new Seat(control.getFilm_name(), control.getActiveHall(), String.valueOf(i + 1), String.valueOf(j + 1), "", active_hall.price_per_seat));

                    Firstx += 80;
                }
                Firstx = 95;
                Firsty += 70;
            }
        }

        else if (active_hall.seat_list.size()!=0) {
            control.button_list.clear();
            control.seat_list.clear();

            for (int i = 0; i < Integer.parseInt(active_hall.row); i++) {

                for (int j = 0; j < Integer.parseInt(active_hall.column); j++) {

                    File f = new File("assets/icons/empty_seat.png");
                    Image img2 = new Image(f.toURI().toString());
                    ImageView view2 = new ImageView(img2);
                    view2.setFitHeight(50);
                    view2.setFitWidth(50);

                    File f2 = new File("assets/icons/reserved_seat.png");
                    Image img = new Image(f2.toURI().toString());
                    ImageView view = new ImageView(img);
                    view.setFitHeight(50);
                    view.setFitWidth(50);

                    Button button = new Button();
                    button.setGraphic(view2);
                    button.relocate(Firstx, Firsty);
                    grid.getChildren().add(button);

                    if(!Objects.equals(active_hall.seat_list.get(seat_number).owner_name, "null")&&!Objects.equals(active_hall.seat_list.get(seat_number).owner_name, "")){
                        button.setGraphic(view);
                    }
                    else {
                        active_hall.seat_list.get(seat_number).owner_name="";
                    }

                    control.button_list.add(button);

                    active_hall.seat_list.get(seat_number).row_of_seat=String.valueOf(i + 1);
                    active_hall.seat_list.get(seat_number).column_of_seat=String.valueOf(j + 1);

                    control.seat_list.add(active_hall.seat_list.get(seat_number));

                    Firstx += 80;
                    seat_number++;
                }
                Firstx = 95;
                Firsty += 70;
            }
            if(Objects.equals(control.getActive_player().admin, "false")) {
                for (Button buttons : control.button_list) {
                    if (!Objects.equals(control.seat_list.get(control.button_list.indexOf(buttons)).owner_name, "")&&!Objects.equals(control.seat_list.get(control.button_list.indexOf(buttons)).owner_name,control.getActive_player().username)) {
                        buttons.setDisable(true);
                    }
                }
            }
            seat_number=0;
        }
        else {
            if(Objects.equals(control.getActive_player().admin, "false")) {
                for (Button buttons : control.button_list) {
                    if (!Objects.equals(control.seat_list.get(control.button_list.indexOf(buttons)).owner_name, "")&&!Objects.equals(control.seat_list.get(control.button_list.indexOf(buttons)).owner_name,control.getActive_player().username)) {
                        buttons.setDisable(true);
                        grid.getChildren().add(buttons);
                    } else {
                        grid.getChildren().add(buttons);
                    }
                }
            }
            else{
                grid.getChildren().addAll(control.button_list);
            }
        }
        //adding back button

        Button button = new Button("   BACK");
        button.relocate(95,815+(Integer.parseInt(active_hall.row)-8)*80);
        grid.getChildren().add(button);
        if(Objects.equals(control.getActive_player().admin, "false")){
            button.relocate(95,715+(Integer.parseInt(active_hall.row)-8)*80);
        }

        //add combobox

        ComboBox Box = null;
        if(Objects.equals(control.getActive_player().admin, "true")){
            Box = new ComboBox();
            Box.relocate((605+(Integer.parseInt(active_hall.column)-5)*80)/2-90,680+(Integer.parseInt(active_hall.row)-8)*80);
            grid.getChildren().add(Box);

            for(User user : list.user){
                Box.getItems().add(user.username);
                if(Objects.equals(user.username, control.getActive_player().username)){
                    Box.setValue(user.username);
                }
            }
        }

        //button setonMouse

        Hall activates =active_hall ;

        for (Button buttons: control.button_list){
            ComboBox finalBox1 = Box;
            if(Objects.equals(control.getActive_player().admin, "true")) {
                buttons.setOnMouseEntered(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        User selected = null;
                        for (User users : list.user) {
                            if (Objects.equals(users.username, control.seat_list.get(control.button_list.indexOf(buttons)).owner_name)) {
                                selected = users;
                            }
                        }

                        if (Objects.equals(control.seat_list.get(control.button_list.indexOf(buttons)).owner_name, "")) {
                            Text textWelcome = new Text("No bought yet!");
                            textWelcome.relocate((640 + (Integer.parseInt(activates.column) - 5) * 80) / 2 - 90, 740 + (Integer.parseInt(activates.row) - 8) * 80);
                            grid.getChildren().add(textWelcome);
                        } else {
                            String price = control.seat_list.get(control.button_list.indexOf(buttons)).price_that_it_has_been_bought;
                            Text textWelcome = new Text("Bought by " + control.seat_list.get(control.button_list.indexOf(buttons)).owner_name + " for " + price + " TL!");
                            textWelcome.relocate((510 + (Integer.parseInt(activates.column) - 5) * 80) / 2 - 90, 740 + (Integer.parseInt(activates.row) - 8) * 80);
                            grid.getChildren().add(textWelcome);
                        }

                    }
                });

                buttons.setOnMouseExited(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        for (int i = 0; i < grid.getChildren().size(); i++) {
                            if (grid.getChildren().get(i).toString().toLowerCase().contains("bought") && !grid.getChildren().get(i).toString().contains("successfully")) {
                                grid.getChildren().remove(i);
                            }
                        }
                    }
                });
            }
        }

        //button sen on clicking

        for (Button buttons: control.button_list) {

            ComboBox finalBox = Box;
            buttons.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    for (int i = 0; i < grid.getChildren().size(); i++) {
                        if(grid.getChildren().get(i).toString().toLowerCase().contains("seat")||grid.getChildren().get(i).toString().contains("refund")){
                            grid.getChildren().remove(i);
                        }
                    }
                    User selected = null;
                    if(Objects.equals(control.getActive_player().admin, "true")){
                        for (User users: list.user){
                            if(Objects.equals(users.username, finalBox.getValue().toString())){
                                selected=users;
                            }
                        }
                    }
                    else {
                        selected=control.getActive_player();
                    }


                    File f3 = new File("assets/icons/empty_seat.png");
                    Image img2 = new Image(f3.toURI().toString());
                    ImageView view2 = new ImageView(img2);
                    view2.setFitHeight(50);
                    view2.setFitWidth(50);

                    File f4 = new File("assets/icons/reserved_seat.png");
                    Image img = new Image(f4.toURI().toString());
                    ImageView view = new ImageView(img);
                    view.setFitHeight(50);
                    view.setFitWidth(50);


                    if (!Objects.equals(control.seat_list.get(control.button_list.indexOf(buttons)).owner_name, "")) {
                        buttons.setGraphic(view2);
                        Text textWelcome = new Text("Seat at " + control.seat_list.get(control.button_list.indexOf(buttons)).row_of_seat + "-" + control.seat_list.get(control.button_list.indexOf(buttons)).column_of_seat + " refunded successfully");
                        textWelcome.relocate((550 + (Integer.parseInt(activates.column) - 5) * 80) / 2 - 90, 780 + (Integer.parseInt(activates.row) - 8) * 80);
                        control.seat_list.get(control.button_list.indexOf(buttons)).owner_name = "";
                        grid.getChildren().add(textWelcome);
                        if(Objects.equals(control.getActive_player().admin, "false")){
                            textWelcome.relocate((530 + (Integer.parseInt(activates.column) - 5) * 80) / 2 - 90, 680 + (Integer.parseInt(activates.row) - 8) * 80);
                        }

                    } else {
                        buttons.setGraphic(view);
                        Text text_refund;
                        String price =activates.price_per_seat;

                        if(Objects.equals(selected.club_member, "true")){
                            price= String.valueOf((Integer.parseInt(price)*(100-Integer.parseInt(control.getDiscount())))/100);
                        }

                        if(Objects.equals(control.getActive_player().admin, "true")){
                            text_refund = new Text("Seat at " + control.seat_list.get(control.button_list.indexOf(buttons)).row_of_seat + "-" + control.seat_list.get(control.button_list.indexOf(buttons)).column_of_seat + " is " + " bought for " + finalBox.getValue().toString() + " for " + price + " TL successfully!");
                            control.seat_list.get(control.button_list.indexOf(buttons)).owner_name = finalBox.getValue().toString();
                        }
                        else {
                            text_refund = new Text("Seat at " + control.seat_list.get(control.button_list.indexOf(buttons)).row_of_seat + "-" + control.seat_list.get(control.button_list.indexOf(buttons)).column_of_seat + " is " + " bought for " + control.getActive_player().username + " for " + price + " TL successfully!");
                            control.seat_list.get(control.button_list.indexOf(buttons)).owner_name = control.getActive_player().username;
                        }
                        control.seat_list.get(control.button_list.indexOf(buttons)).price_that_it_has_been_bought=price;

                        text_refund.relocate((360 + (Integer.parseInt(activates.column) - 5) * 80) / 2 - 90, 780 + (Integer.parseInt(activates.row) - 8) * 80);
                        grid.getChildren().add(text_refund);
                        if(Objects.equals(control.getActive_player().admin, "false")){
                            text_refund.relocate((360 + (Integer.parseInt(activates.column) - 5) * 80) / 2 - 90, 680 + (Integer.parseInt(activates.row) - 8) * 80);
                        }
                    }
                    for(Seat seat1 : control.seat_list){
                        for(Seat seat2 : list.seat){
                            if(Objects.equals(seat1.hall_name, seat2.hall_name)&& Objects.equals(seat1.column_of_seat, seat2.column_of_seat)&& Objects.equals(seat1.row_of_seat, seat2.row_of_seat)){
                                seat2 = seat1;
                            }
                        }
                    }
                    try {
                        Function.ChangeTxt(list);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

            });

        }

        //functions

        button.setOnAction(event -> {
            Scene6 scene6 = new Scene6();
            primaryStage.setScene(scene6.Work(list,primaryStage,control));
            primaryStage.show();
        });



        return new Scene(grid);

    }
}
