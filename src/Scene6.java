
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Scene6 extends Pane{

    public Scene Work(ReadingBackUp list, Stage primaryStage,Flow control) {

        //PLAY VİDEO

        Pane grid = new Pane();
        grid.setMinSize(1000,550);

        Film active_film =null;

        for (Film films : list.film){
            if(Objects.equals(films.getFilm_name(), control.getFilm_name())){
                active_film=films;
                control.setFilm(films);
            }
        }
        //creating the scene items and give them functionally
        File f = new File("assets/trailers/"+active_film.getTrailer_path());
        Media media = new Media(f.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setFitHeight(500);
        mediaView.setFitWidth(750);
        mediaPlayer.setAutoPlay(true);
        mediaView.relocate(75,75);
        mediaPlayer.pause();
        grid.getChildren().add(mediaView);

        Text textWelcome = new Text(active_film.getFilm_name()+" ("+active_film.getDuration()+" minutes)\n");
        textWelcome.relocate(350,40);
        grid.getChildren().add(textWelcome);

        Button button = new Button("    |>   ");
        button.relocate(870,130);
        grid.getChildren().add(button);

        Button button2 = new Button("  <<   ");
        button2.relocate(870,180);
        grid.getChildren().add(button2);

        Button button3 = new Button("  >>   ");
        button3.relocate(870,230);
        grid.getChildren().add(button3);

        Button button4 = new Button("  |<<  ");
        button4.relocate(870,280);
        grid.getChildren().add(button4);

        Button button5 = new Button("   BACK");
        button5.relocate(190,520);
        grid.getChildren().add(button5);


        if(Objects.equals(control.getActive_player().admin, "true")) {
            Button button6 = new Button("Add Hall");
            button6.relocate(280, 520);
            grid.getChildren().add(button6);
            button6.setOnAction(event -> {
                Scene7 scene7 = new Scene7();
                primaryStage.setScene(scene7.Work(list,primaryStage,control));
                primaryStage.show();
                mediaPlayer.pause();
            });
        }
        if(Objects.equals(control.getActive_player().admin, "true")) {
            Button button7 = new Button("Remove Hall");
            button7.relocate(370, 520);
            grid.getChildren().add(button7);

            button7.setOnAction(event -> {
                Scene9 scene9 = new Scene9();
                primaryStage.setScene(scene9.Work(list,primaryStage,control));
                primaryStage.show();
                mediaPlayer.pause();
            });
        }

        Button button8 = new Button("OK");
        button8.relocate(750,520);
        grid.getChildren().add(button8);

        Button button9 = new Button("Go to Log In");
        button9.relocate(10,10);
        grid.getChildren().add(button9);

        button9.setOnAction(event -> {
            Scene1 scene1 = new Scene1();
            primaryStage.setScene(scene1.Work(list,primaryStage,control));
            primaryStage.show();
        });

        ComboBox Box = new ComboBox();
        Box.setMinSize(140,10);
        Box.relocate(480,520);
        grid.getChildren().add(Box);



        if(Objects.equals(control.getActive_player().admin, "false")) {
            button8.relocate(550,520);
            button5.relocate(290,520);
            Box.relocate(390,520);
        }

        //set first value to title

        for(Hall halls : control.getFilm().halls){
            Box.getItems().add(halls.hall_name);
        }

        if(!(control.getFilm().halls.size() ==0)){
            Box.setValue(control.getFilm().halls.get(0).hall_name);
        }


        button.setOnAction(event -> {
            if(Objects.equals(button.getText(), "    |>   ")){
                button.setText("    ||    ");
                mediaPlayer.play();
            }
            else{
                mediaPlayer.pause();
                button.setText("    |>   ");
            }
        });

        button2.setOnAction(event -> mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(-5))));

        button3.setOnAction(event -> mediaPlayer.seek(mediaPlayer.getCurrentTime().add(Duration.seconds(5))));

        button4.setOnAction(event -> mediaPlayer.seek(Duration.ZERO));

        button5.setOnAction(event -> {
            Scene2 scene2 = new Scene2();
            primaryStage.setScene(scene2.Work(list,primaryStage,control));
            primaryStage.show();
            mediaPlayer.pause();
        });

        button8.setOnAction(event -> {
            try {
                control.setActiveHall(Box.getValue().toString());
                Scene8 scene8 = new Scene8();
                control.setActiveHall(Box.getValue().toString());
                primaryStage.setScene(scene8.Work(list, primaryStage, control));
                primaryStage.show();
                mediaPlayer.pause();
            } catch (Exception e) {
                File f2 = new File("assets/effects/error.mp3");
                Media sound2 = new Media(f2.toURI().toString());
                MediaPlayer mediaPlayer2 = new MediaPlayer(sound2);
                mediaPlayer2.play();
                Text error = new Text("ERROR: There is no hall!\n");
                error.relocate(800,525);
                if(Objects.equals(control.getActive_player().admin, "false")){
                    error.relocate(770,805);
                }
                grid.getChildren().add(error);

            }
        });

        //volume settings

        Slider Slide = new Slider(0,100,50);
        Slide.setRotate(90);
        Slide.relocate(830,400);
        grid.getChildren().add(Slide);
        mediaPlayer.setVolume(0.5);

        Slide.valueProperty().addListener(ov -> {
            if (Slide.isValueChanging()) {
                mediaPlayer.setVolume(1-(Slide.getValue()/100));
            }
        });
        try {
            Function.ChangeTxt(list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return new Scene(grid);
    }
}
