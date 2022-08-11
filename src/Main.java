import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;

public class Main extends Application {

    public static void main(String[] args) throws FileNotFoundException {

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        ReadingBackUp read = new ReadingBackUp();
        read.Reading();

        Flow control = new Flow();
        read.ReadingProperties(control);

        primaryStage.setTitle(control.getTitle());
        File f = new File("assets/icons/logo.png");
        primaryStage.getIcons().add(new Image(f.toURI().toString()));


        Scene1 scene1 = new Scene1();
        primaryStage.setScene(scene1.Work(read,primaryStage,control));
        primaryStage.show();

    }
}