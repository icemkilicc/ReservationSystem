import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Scene5 extends Pane{
    public Scene Work(ReadingBackUp list, Stage primaryStage,Flow control) {

        //Edit

        Pane grid = new Pane();
        grid.setMinSize(530,500);

        //TableView

        TableView<User> tableView = new TableView<>();
        tableView.relocate(10,10);
        grid.getChildren().add(tableView);
        tableView.setMinSize(500,430);

        TableColumn<User, String> firstNameCol = new TableColumn("Username");
        TableColumn<User, String> lastNameCol = new TableColumn("Club Member");
        TableColumn<User, String> emailCol = new TableColumn("Admin");

        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("club_member"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("admin"));

        tableView.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

        ObservableList<User> teamMembers = FXCollections.observableArrayList(list.user);

        for(User users : teamMembers){
            if(!Objects.equals(users.username, "admin")){
                tableView.getItems().add(users);
            }
        }

        //BACK

        Button button = new Button("   BACK");
        button.relocate(10,450);
        grid.getChildren().add(button);

        //PROMOTE

        Button button2 = new Button("Promote/Demote Club Member");
        button2.relocate(90,450);
        grid.getChildren().add(button2);

        //PROME

        Button button3 = new Button("Promote/Demote Admin");
        button3.relocate(330,450);
        grid.getChildren().add(button3);

        //functions

        button.setOnAction(event -> {
            Scene2 scene2 = new Scene2();
            primaryStage.setScene(scene2.Work(list,primaryStage,control));
            primaryStage.show();
        });

        button2.setOnAction(event -> {
            User person = tableView.getSelectionModel().getSelectedItem();
            if(Objects.equals(person.club_member, "false")){
                person.club_member = "true";
            }
            else {
                person.club_member = "false";
            }
            tableView.refresh();
            try {
                Function.ChangeTxt(list);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        button3.setOnAction(event -> {
            User person = tableView.getSelectionModel().getSelectedItem();
            if(Objects.equals(person.admin, "false")){
                person.admin = "true";
            }
            else {
                person.admin = "false";
            }
            tableView.refresh();
            try {
                Function.ChangeTxt(list);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        tableView.getSelectionModel().select(list.user.get(0));


        return new Scene(grid);
    }
}
