package tietokanta.com.example.tietokanta;

import javafx.application.Application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;


import java.io.IOException;
import java.util.Stack;

public class tietokanta extends Application {
    @Override
    public void start(Stage stage) throws IOException {

       TableView tableView = new TableView();

       TableColumn<person, String> column1 = new TableColumn<>("nimi");
       column1.setCellValueFactory(new PropertyValueFactory<>("Name"));
        TableColumn<person, String> column2 = new TableColumn<>("hautaustapa");
        column2.setCellValueFactory(new PropertyValueFactory<>("hautaustapa"));
        TableColumn<person, String> column3 = new TableColumn<>("paikka");
        column3.setCellValueFactory(new PropertyValueFactory<>("paikka"));
        TableColumn<person, String> column4 = new TableColumn<>("tuhkauspaikka");
        column4.setCellValueFactory(new PropertyValueFactory<>("tuhkausPaikka"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);

        tableView.getItems().add(new person("paavo", "arkku", "saapasmäki", false ));

        Button addPerson = new Button("button");

        addPerson.setOnAction(new EventHandler<ActionEvent>(){


            public void handle(ActionEvent event){

                Label label = new Label("i'm a label on new window");
                StackPane secLayout = new StackPane();
                secLayout.getChildren().add(label);
                Scene newScene = new Scene(secLayout, 300,300);

                Stage newWindow = new Stage();
                newWindow.setTitle("jou");
                newWindow.setScene(newScene);
                newWindow.initModality(Modality.WINDOW_MODAL);
                newWindow.initOwner(stage);

                newWindow.show();


            }});
       VBox vbox = new VBox(tableView);

        vbox.getChildren().add(addPerson);
        Scene scene = new Scene(vbox, 600, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}