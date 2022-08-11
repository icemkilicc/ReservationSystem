
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
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scene1 extends Function {

    public static int control =0;

    public void Timer(Flow flow){
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Runnable helloRunnable = () -> {
            flow.setTime_control(flow.getTime_control()+1);
            if(flow.getTime_control()%Integer.parseInt(flow.getBlock_time())==0){
                executor.shutdown();
            }
        };
        executor.scheduleAtFixedRate(helloRunnable, 0, 1, TimeUnit.SECONDS);
    }

    public Scene Work(ReadingBackUp list, Stage primaryStage,Flow flow) {

        //login
        control=0;

        Pane grid = new Pane();
        grid.setMinSize(515,350);

        Text textWelcome = new Text("     Welcome to the "+ flow.getTitle()+"!\n    Please enter your credentials below and click LOGIN.\nYou can create a new account by clicking SIGN UP  button.\n");
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

        PasswordField pwBox = new PasswordField();
        pwBox.relocate(190,190);
        grid.getChildren().add(pwBox);

        Button button = new Button("SIGN UP");
        button.relocate(100,240);
        grid.getChildren().add(button);

        Button button2 = new Button("LOG IN");
        button2.relocate(310,240);
        grid.getChildren().add(button2);


        button.setOnAction(event -> {
            Scene10 scene10 = new Scene10();
            primaryStage.setScene(scene10.Work(list,primaryStage,flow));
            primaryStage.show();
        });

        button2.setOnAction(event -> {
            File f = new File("assets/effects/error.mp3");
            Media sound = new Media(f.toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            for(User user : list.user){
                if(Objects.equals(user.username,userTextField.getText())&&flow.getTime_control()%5==0) {
                    if (Objects.equals(user.password, hashPassword(String.valueOf(pwBox.getText())))||Objects.equals(user.password,pwBox.getText())){
                        control++;
                        for (User users : list.user) {
                            if (Objects.equals(users.username, userTextField.getText())) {
                                flow.setActive_player(users);
                            }
                        }
                        Scene2 scene2 = new Scene2();
                        primaryStage.setScene(scene2.Work(list, primaryStage, flow));
                        primaryStage.show();
                        flow.setRetries(0);
                        break;
                    }
                }
            }
            if (flow.getRetries()==4){
                Timer(flow);
                if(grid.getChildren().size()==8){
                    grid.getChildren().remove(grid.getChildren().size()-1);
                }
                mediaPlayer.play();
                Label textWelcome1 = new Label("ERROR: Please wait for "+ flow.getBlock_time() +" seconds to make new operation!");
                textWelcome1.relocate(60,280);
                grid.getChildren().add(textWelcome1);
                flow.setRetries(0);
            }
            else if(flow.getTime_control()%Integer.parseInt(flow.getBlock_time())!=0){
                if(grid.getChildren().size()==8){
                    grid.getChildren().remove(grid.getChildren().size()-1);
                }
                mediaPlayer.play();
                Label textWelcome1 = new Label("ERROR: Please wait until end of the "+ flow.getBlock_time() +" seconds to make new operation!");
                textWelcome1.relocate(20,280);
                grid.getChildren().add(textWelcome1);
            }

            else if(control==0){
                if(grid.getChildren().size()==8){
                    grid.getChildren().remove(grid.getChildren().size()-1);
                }
                flow.setRetries(flow.getRetries()+1);
                mediaPlayer.play();
                Label textWelcome1 = new Label("  ERROR: There is no such a credential!");
                textWelcome1.relocate(100,280);
                grid.getChildren().add(textWelcome1);
            }
        });


        return new Scene(grid);

    }


}
