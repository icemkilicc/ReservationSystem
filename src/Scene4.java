
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class Scene4 extends Pane{

    public Scene Work(ReadingBackUp list, Stage primaryStage,Flow control) {

        //add

        Pane grid = new Pane();
        grid.setMinSize(495,300);

        Text textWelcomeR = new Text("Please give name, relative path of the trailer and duration of the film.\n");
        textWelcomeR.relocate(20,30);
        grid.getChildren().add(textWelcomeR);

        Label userName = new Label("Name:");
        userName.relocate(80,70);
        grid.getChildren().add(userName);

        TextField userTextField = new TextField();
        userTextField.relocate(180,70);
        grid.getChildren().add(userTextField);

        Label trailer = new Label("Trailer (Path):");
        trailer.relocate(80,115);
        grid.getChildren().add(trailer);

        TextField trailerTextField = new TextField();
        trailerTextField.relocate(180,110);
        grid.getChildren().add(trailerTextField);

        Label duration = new Label("Duration (m):");
        duration.relocate(80,165);
        grid.getChildren().add(duration);

        TextField durationTextField = new TextField();
        durationTextField.relocate(180,150);
        grid.getChildren().add(durationTextField);

        Button button = new Button("   BACK");
        button.relocate(80,200);
        grid.getChildren().add(button);

        Button button2 = new Button("OK");
        button2.relocate(320,200);
        grid.getChildren().add(button2);

        //functions

        button.setOnAction(event -> {
            Scene2 scene2 = new Scene2();
            primaryStage.setScene(scene2.Work(list,primaryStage,control));
            primaryStage.show();
        });

        button2.setOnAction(event -> {
            File f = new File("assets/effects/error.mp3");
            Media sound = new Media(f.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);

            if(Objects.equals(userTextField.getText(), "")){
                if(grid.getChildren().size()==10){
                    grid.getChildren().remove(grid.getChildren().size()-1);
                }
                mediaPlayer.play();
                Label textWelcome = new Label("ERROR: Film name could not be empty!");
                textWelcome.relocate(100,240);
                grid.getChildren().add(textWelcome);
            }
            else if(Objects.equals(trailerTextField.getText(), "")){
                if(grid.getChildren().size()==10){
                    grid.getChildren().remove(grid.getChildren().size()-1);
                }
                mediaPlayer.play();
                Label textWelcome = new Label("ERROR: Trailer path could not be empty!");
                textWelcome.relocate(100,240);
                grid.getChildren().add(textWelcome);
            }
            else {
                if(grid.getChildren().size()==10){
                    grid.getChildren().remove(grid.getChildren().size()-1);
                }
                try{
                    if(!Files.exists(Paths.get("assets/trailers/"+trailerTextField.getText()))) {
                        if(grid.getChildren().size()==10){
                            grid.getChildren().remove(grid.getChildren().size()-1);
                        }
                        mediaPlayer.play();
                        Label textWelcome = new Label("ERROR: There is no such a trailer!");
                        textWelcome.relocate(100,240);
                        grid.getChildren().add(textWelcome);
                    }
                    else {
                        list.film.add(new Film(userTextField.getText(),trailerTextField.getText(),durationTextField.getText()));
                        Label textWelcome = new Label("SUCCESS: Film added successfully!");
                        textWelcome.relocate(100,240);
                        grid.getChildren().add(textWelcome);
                        try {
                            Function.ChangeTxt(list);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }

                } catch (Exception e) {
                    mediaPlayer.play();
                    Label textWelcome = new Label("ERROR: Duration has to be a integer!");
                    textWelcome.relocate(100,240);
                    grid.getChildren().add(textWelcome);
                }
            }

        });



        return new Scene(grid);

    }
}
