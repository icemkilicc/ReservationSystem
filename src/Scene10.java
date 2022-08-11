
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Scene10 extends Pane{
    public Scene Work(ReadingBackUp list, Stage primaryStage,Flow control) {

        //SIGN UP

        ArrayList<String> user_names = new ArrayList<>();

        for(User users: list.user){
            user_names.add(users.username);
        }

        Pane grid = new Pane();
        grid.setMinSize(485,380);

        Text textWelcome = new Text("     Welcome to the "+control.getTitle()+"!\n    \tFill the form below to create a new a account.\n    You can go Log In page by clicking LOG IN button.\n");
        textWelcome.relocate(50,50);
        grid.getChildren().add(textWelcome);

        Label userName = new Label("Username:");
        userName.relocate(100,140);
        grid.getChildren().add(userName);

        TextField userTextField = new TextField();
        userTextField.relocate(190,140);
        grid.getChildren().add(userTextField);

        Label pw = new Label("Password:");
        pw.relocate(100,190);
        grid.getChildren().add(pw);

        Label pw2 = new Label("Password:");
        pw2.relocate(100,240);
        grid.getChildren().add(pw2);

        PasswordField pwBox = new PasswordField();
        pwBox.relocate(190,190);
        grid.getChildren().add(pwBox);

        PasswordField pwBox1 = new PasswordField();
        pwBox1.relocate(190,240);
        grid.getChildren().add(pwBox1);

        Button button = new Button("LOG IN");
        button.relocate(100,290);
        grid.getChildren().add(button);

        Button button2 = new Button("SIGN UP");
        button2.relocate(300,290);
        grid.getChildren().add(button2);

        //Functions of button

        button.setOnAction(event -> {
            Scene1 scene1 = new Scene1();
            primaryStage.setScene(scene1.Work(list,primaryStage,control));
            primaryStage.show();
        });

        //check if it is true sing up button2

        button2.setOnAction(event -> {

            File f = new File("assets/effects/error.mp3");
            Media sound = new Media(f.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);

            if(Objects.equals(userTextField.getText(), "")){
                if(grid.getChildren().size()==10){
                    grid.getChildren().remove(grid.getChildren().size()-1);
                }
                mediaPlayer.play();
                Label textWelcome1 = new Label("ERROR: Username cannot be empty!");
                textWelcome1.relocate(120,330);
                grid.getChildren().add(textWelcome1);
            }
            else if(Objects.equals(pwBox.getText(), "")){
                if(grid.getChildren().size()==10){
                    grid.getChildren().remove(grid.getChildren().size()-1);
                }
                mediaPlayer.play();
                Label textWelcome1 = new Label("ERROR: Password cannot be empty!");
                textWelcome1.relocate(120,330);
                grid.getChildren().add(textWelcome1);
            }
            else if(!Objects.equals(pwBox.getText(), pwBox1.getText())){
                if(grid.getChildren().size()==10){
                    grid.getChildren().remove(grid.getChildren().size()-1);
                }
                mediaPlayer.play();
                Label textWelcome1 = new Label("ERROR: Password do not match!");
                textWelcome1.relocate(120,330);
                grid.getChildren().add(textWelcome1);
            }
            else if(user_names.contains(userTextField.getText())){
                if(grid.getChildren().size()==10){
                    grid.getChildren().remove(grid.getChildren().size()-1);
                }
                mediaPlayer.play();
                Label textWelcome1 = new Label("ERROR: This username already exist!");
                textWelcome1.relocate(120,330);
                grid.getChildren().add(textWelcome1);
            }
            else {
                if(grid.getChildren().size()==10){
                    grid.getChildren().remove(grid.getChildren().size()-1);
                }
                Label textWelcome1 = new Label("SUCCESS: You have successfully registered with your new credentials!");
                textWelcome1.relocate(20,330);
                grid.getChildren().add(textWelcome1);
                list.user.add(new User(userTextField.getText(),Function.hashPassword(pwBox.getText()),"false","false"));
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
