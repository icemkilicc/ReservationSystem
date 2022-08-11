
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

public class Scene9 extends Pane{
    public Scene Work(ReadingBackUp list, Stage primaryStage,Flow control) {

        //REMOVE HALL

        Pane grid = new Pane();
        grid.setMinSize(550,300);

        Text textWelcome = new Text("Select the hall that you desire to remove from "+ control.getFilm_name() +" and then click OK.\n");
        textWelcome.relocate(10,80);
        grid.getChildren().add(textWelcome);

        ComboBox Box = new ComboBox();
        Box.relocate(170,120);
        Box.setMinSize(170,35);
        grid.getChildren().add(Box);

        for(Hall halls : control.getFilm().halls){
            Box.getItems().add(halls.hall_name);
        }
        if(!(control.getFilm().halls.size() ==0)){
            Box.setValue(control.getFilm().halls.get(0).hall_name);
        }

        //buttons

        Button button = new Button("   BACK");
        button.relocate(180,165);
        grid.getChildren().add(button);

        Button button2 = new Button("OK");
        button2.relocate(290,165);
        grid.getChildren().add(button2);

        Button button9 = new Button("Go to Log In");
        button9.relocate(10,10);
        grid.getChildren().add(button9);

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

        button2.setOnAction(event -> {
            try {
                for (Hall halls : control.getFilm().halls) {
                    if (Objects.equals(halls.hall_name, Box.getItems().get(0))) {
                        control.getFilm().halls.remove(halls);
                        break;
                    }
                }
            } catch (Exception e) {

            }
            try {
                Function.ChangeTxt(list);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                Box.getItems().remove(Box.getValue().toString());
            } catch (Exception e) {
                File f = new File("assets/effects/error.mp3");
                Media sound = new Media(f.toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.play();
                Text error = new Text("Error: There is no Hall\n");
                error.relocate(180,200);
                grid.getChildren().add(error);
            }
        });


        Scene scene = new Scene(grid);


        return scene;

    }
}
