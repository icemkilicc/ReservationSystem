
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Scene2 extends Pane{

    public Scene Work(ReadingBackUp list, Stage primaryStage,Flow control) {

        //admin add remove scene

        Pane grid = new Pane();
        grid.setMinSize(475,250);

        Text textWelcome;
        if(Objects.equals(control.getActive_player().club_member, "false")&&Objects.equals(control.getActive_player().admin, "false")){
            textWelcome = new Text("     Welcome " + control.getActive_player().username + "!\n    You can either select film below or do edits.\n");
        }
        else if (Objects.equals(control.getActive_player().club_member, "true")&&Objects.equals(control.getActive_player().admin, "false")) {
            textWelcome = new Text("     Welcome "+ control.getActive_player().username+" (Club member)!\n    You can either select film below or do edits.\n");
        }
        else if (Objects.equals(control.getActive_player().club_member, "false")&&Objects.equals(control.getActive_player().admin, "true")) {
            textWelcome = new Text("     Welcome "+ control.getActive_player().username+" (Admin)!\n    You can either select film below or do edits.\n");
        }
        else {
            textWelcome = new Text("     Welcome "+ control.getActive_player().username+" (Admin - Club member)!\n    You can either select film below or do edits.\n");
        }
        textWelcome.relocate(80,30);
        grid.getChildren().add(textWelcome);

        //add combobox

        ComboBox Box = new ComboBox();
        Box.relocate(50,80);
        grid.getChildren().add(Box);

        for(Film film : list.film){
            Box.getItems().add(film.getFilm_name());
        }
        try{Box.setValue(list.film.get(0).getFilm_name());} catch (Exception e) {
            Box.relocate(220,80);
        }

        //OK

        Button button = new Button("OK");
        button.relocate(410,80);
        grid.getChildren().add(button);

        //Add film
        if(Objects.equals(control.getActive_player().admin, "true")){
            Button button2 = new Button("Add film");
            button2.relocate(100,120);
            grid.getChildren().add(button2);

            button2.setOnAction(event -> {
                Scene4 scene4 = new Scene4();
                primaryStage.setScene(scene4.Work(list,primaryStage,control));
                primaryStage.show();
            });
        }

        //Remove Film
        if(Objects.equals(control.getActive_player().admin, "true")){
            Button button3 = new Button("Remove film");
            button3.relocate(190,120);
            grid.getChildren().add(button3);

            button3.setOnAction(event -> {
                Scene3 scene3 = new Scene3();
                primaryStage.setScene(scene3.Work(list,primaryStage,control));
                primaryStage.show();
            });

        }
        //Edit Users
        if(Objects.equals(control.getActive_player().admin, "true")){
            Button button4 = new Button("Edit Users");
            button4.relocate(310,120);
            grid.getChildren().add(button4);

            button4.setOnAction(event -> {
                Scene5 scene5 = new Scene5();
                primaryStage.setScene(scene5.Work(list,primaryStage,control));
                primaryStage.show();
            });
        }
        //Log out
        Button button5 = new Button("LOG OUT");
        button5.relocate(360,160);
        grid.getChildren().add(button5);

        //Functions of button
        button.setOnAction(event -> {
            try{
                control.setFilm_name(Box.getValue().toString());
                Scene6 scene6 = new Scene6();
                primaryStage.setScene(scene6.Work(list,primaryStage,control));
                primaryStage.show();

            } catch (Exception e) {
                File f2 = new File("assets/effects/error.mp3");
                Media sound2 = new Media(f2.toURI().toString());
                MediaPlayer mediaPlayer2 = new MediaPlayer(sound2);
                mediaPlayer2.play();
                Text error = new Text("Error! There is no Film!");
                error.relocate(180,200);
                grid.getChildren().add(error);
            }

        });


        button5.setOnAction(event -> {
            Scene1 scene1 = new Scene1();
            primaryStage.setScene(scene1.Work(list,primaryStage,control));
            primaryStage.show();
        });

        try {
            Function.ChangeTxt(list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new Scene(grid);
    }
}
