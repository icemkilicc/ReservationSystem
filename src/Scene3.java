
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

public class Scene3 extends Pane{

    public Scene Work(ReadingBackUp list, Stage primaryStage,Flow control){

        //remove film

        Pane grid = new Pane();
        grid.setMinSize(475,250);

        Text textWelcome = new Text("     Select the film that you desire to remove and then click OK.\n");
        textWelcome.relocate(20,50);
        grid.getChildren().add(textWelcome);

        //add combobox

        ComboBox Box = new ComboBox();
        Box.relocate(70,80);
        grid.getChildren().add(Box);

        for(Film film : list.film){
            Box.getItems().add(film.getFilm_name());
        }
        try {
            Box.setValue(list.film.get(0).getFilm_name());
        } catch (Exception e) {}


        //OK

        Button button = new Button("   BACK");
        button.relocate(180,120);
        grid.getChildren().add(button);

        //Add film

        Button button2 = new Button("OK");
        button2.relocate(265,120);
        grid.getChildren().add(button2);

        button.setOnAction(event -> {
            Scene2 scene2 = new Scene2();
            primaryStage.setScene(scene2.Work(list,primaryStage,control));
            primaryStage.show();
        });

        button2.setOnAction(event -> {
            for (Film films : list.film){
                if(Objects.equals(Box.getValue(),films.getFilm_name())){
                    list.film.remove(films);
                    break;
                }
            }
            try {
                Function.ChangeTxt(list);
            } catch (IOException e) {
            }
            try {
                Box.getItems().remove(Box.getValue());
                Box.setValue(Box.getItems().get(0).toString());
            } catch (Exception e) {
                File f2 = new File("assets/effects/error.mp3");
                Media sound2 = new Media(f2.toURI().toString());
                MediaPlayer mediaPlayer2 = new MediaPlayer(sound2);
                mediaPlayer2.play();
            }

            try {
                Function.ChangeTxt(list);
            } catch (IOException e) {
            }

        });



        return new Scene(grid);
    }
}
