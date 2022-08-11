
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Scene7 extends Pane{

    public Scene Work(ReadingBackUp list, Stage primaryStage,Flow control) {

        //ADD HALL

        Pane grid = new Pane();
        grid.setMinSize(495,300);

        Text textWelcome = new Text(control.getFilm().getFilm_name()+" ("+control.getFilm().getDuration()+" minutes)\n");
        textWelcome.relocate(150,30);
        grid.getChildren().add(textWelcome);


        Label Row = new Label("Row:");
        Row.relocate(80,70);
        grid.getChildren().add(Row);

        ComboBox BoxR = new ComboBox();
        BoxR.relocate(250,70);
        grid.getChildren().add(BoxR);

        Label column = new Label("Column:");
        column.relocate(80,115);
        grid.getChildren().add(column);

        ComboBox BoxC = new ComboBox();
        BoxC.relocate(250,110);
        grid.getChildren().add(BoxC);

        for (int i = 3; i < 11; i++) {
            BoxR.getItems().add(String.valueOf(i));
            BoxC.getItems().add(String.valueOf(i));
        }
        BoxR.setValue(3);
        BoxC.setValue(3);

        Label userName = new Label("Name:");
        userName.relocate(80,150);
        grid.getChildren().add(userName);

        TextField userTextField = new TextField();
        userTextField.relocate(180,150);
        grid.getChildren().add(userTextField);

        Label trailer = new Label("Price:");
        trailer.relocate(80,195);
        grid.getChildren().add(trailer);

        TextField trailerTextField = new TextField();
        trailerTextField.relocate(180,190);
        grid.getChildren().add(trailerTextField);

        Button button = new Button("   BACK");
        button.relocate(80,230);
        grid.getChildren().add(button);

        Button button2 = new Button("OK");
        button2.relocate(320,230);
        grid.getChildren().add(button2);

        Button button9 = new Button("Go to Log In");
        button9.relocate(10,10);
        grid.getChildren().add(button9);

        //functions

        button9.setOnAction(event -> {
            Scene1 scene1 = new Scene1();
            primaryStage.setScene(scene1.Work(list,primaryStage,control));
            primaryStage.show();
        });

        button.setOnAction(event -> {
            Scene6 scene6 = new Scene6();
            primaryStage.setScene(scene6.Work(list,primaryStage,control));
            primaryStage.show();
        });

        //Controlling the given value is valid

        button2.setOnAction(event -> {
            File f = new File("assets/effects/error.mp3");
            Media sound = new Media(f.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);

            if(Objects.equals(userTextField.getText(), "")){
                if(grid.getChildren().size()==13){
                    grid.getChildren().remove(grid.getChildren().size()-1);
                }
                mediaPlayer.play();
                Label textWelcome1 = new Label("ERROR: Hall name could not be empty!");
                textWelcome1.relocate(90,270);
                grid.getChildren().add(textWelcome1);
            }
            else if(Objects.equals(trailerTextField.getText(), "")){
                if(grid.getChildren().size()==13){
                    grid.getChildren().remove(grid.getChildren().size()-1);
                }
                mediaPlayer.play();
                Label textWelcome1 = new Label("ERROR: Price could not be empty!");
                textWelcome1.relocate(90,270);
                grid.getChildren().add(textWelcome1);
            }
            else if(list.halls_name.contains(userTextField.getText())){
                if(grid.getChildren().size()==13){
                    grid.getChildren().remove(grid.getChildren().size()-1);
                }
                mediaPlayer.play();
                Label textWelcome1 = new Label("ERROR: Hall name has already defined!");
                textWelcome1.relocate(90,270);
                grid.getChildren().add(textWelcome1);
            }
            else {
                try{
                    Integer.valueOf(trailerTextField.getText().toString());
                    if(grid.getChildren().size()==13){
                        grid.getChildren().remove(grid.getChildren().size()-1);
                    }
                    control.getFilm().halls.add(new Hall(control.getFilm_name(),userTextField.getText(),trailerTextField.getText(),BoxR.getValue().toString(),BoxC.getValue().toString()));
                    Label textWelcome1 = new Label("SUCCESS: Hall successfully created!");
                    textWelcome1.relocate(90,270);
                    grid.getChildren().add(textWelcome1);

                    for (int i = 0; i < Integer.parseInt(BoxR.getValue().toString()); i++) {
                        for (int j = 0; j <Integer.parseInt(BoxC.getValue().toString()) ; j++) {
                            control.getFilm().halls.get(control.getFilm().halls.size()-1).seat_list.add(new Seat(control.getFilm_name(),userTextField.getText(),String.valueOf(i),String.valueOf(j),"null","0"));
                            list.seat.add(new Seat(control.getFilm_name(),userTextField.getText(),String.valueOf(i),String.valueOf(j),"null","0"));
                        }
                    }
                } catch (NumberFormatException e) {
                    if(grid.getChildren().size()==13){
                        grid.getChildren().remove(grid.getChildren().size()-1);
                    }
                    mediaPlayer.play();
                    Label textWelcome1 = new Label("ERROR: Price must be positive integer!");
                    textWelcome1.relocate(90,270);
                    grid.getChildren().add(textWelcome1);
                }

                try {
                    Function.ChangeTxt(list);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });



        return new Scene(grid);

    }
}
