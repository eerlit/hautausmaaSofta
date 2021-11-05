package tietokanta.com.example.tietokanta;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class tietokanta extends Application {
    @Override
    public void start(Stage stage) throws IOException {

       TableView tableView = new TableView();

        TextField nimiKenttä = new TextField();
        TextField hautaustapaKenttä = new TextField();
        TextField paikkaKenttä = new TextField();
        TextField tuhkausPaikkaKenttä = new TextField();
        TextField hautausPvmKenttä = new TextField();



       TableColumn<person, String> column1 = new TableColumn<>("nimi");
       column1.setCellValueFactory(new PropertyValueFactory<>("Name"));
        TableColumn<person, String> column2 = new TableColumn<>("hautaustapa");
        column2.setCellValueFactory(new PropertyValueFactory<>("hautaustapa"));
        TableColumn<person, String> column3 = new TableColumn<>("paikka");
        column3.setCellValueFactory(new PropertyValueFactory<>("paikka"));
        TableColumn<person, String> column4 = new TableColumn<>("tuhkauspaikka");
        column4.setCellValueFactory(new PropertyValueFactory<>("tuhkausPaikka"));
        TableColumn<person, String> column5 = new TableColumn<>("hautauspäivämäärä");
        column5.setCellValueFactory(new PropertyValueFactory<>("hautausPvm"));

        tableView.getColumns().addAll(column1, column2, column3, column4, column5);

        tableView.getItems().add(new person("paavo", "arkku", "saapasmäki", "","2002" ));

        Button addPerson = new Button("lisää");

        Button saveToExcel = new Button("tallenna");
        saveToExcel.setMaxSize(200, 200);
        saveToExcel.setMinHeight(100);

        addPerson.setOnAction(new EventHandler<ActionEvent>(){

            public void handle(ActionEvent event){


                Button sendButton = new Button("lisää");
                Button saveButton = new Button("tallenna");
                Label nimi = new Label("nimi");
                Label hautausTapa = new Label("hautaustapa");
                Label paikka = new Label("paikka");
                Label tuhkausPaikka = new Label("tuhkauspaikka");
                Label hautauspvm = new Label("hautauspäivämäärä");
                VBox layout = new VBox();
                layout.getChildren().addAll(nimi,nimiKenttä,hautausTapa, hautaustapaKenttä,paikka, paikkaKenttä, tuhkausPaikka, tuhkausPaikkaKenttä,hautauspvm,hautausPvmKenttä, saveButton);

                saveButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                       tableView.getItems().add(new person(nimiKenttä.getText(),hautaustapaKenttä.getText(),paikkaKenttä.getText(),tuhkausPaikkaKenttä.getText(),hautausPvmKenttä.getText()));
                       nimiKenttä.setText("");
                       hautaustapaKenttä.setText("");
                       paikkaKenttä.setText("");
                       tuhkausPaikkaKenttä.setText("");
                       hautausPvmKenttä.setText("");
                    }
                });

                Scene newScene = new Scene(layout, 600,600);

                Stage newWindow = new Stage();
                newWindow.setTitle("lisää tietokantaan");
                newWindow.setScene(newScene);
                newWindow.initModality(Modality.WINDOW_MODAL);
                newWindow.initOwner(stage);

                newWindow.setX(stage.getX()+200);
                newWindow.setY(stage.getY()+100);
                newWindow.show();

            }});

       VBox vbox = new VBox(tableView);
        VBox vbox2 = new VBox(0);
        vbox2.setAlignment(Pos.BOTTOM_CENTER);

        vbox2.getChildren().add(saveToExcel);
        vbox.getChildren().addAll(addPerson, vbox2);

        Scene scene = new Scene(vbox, 600, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}