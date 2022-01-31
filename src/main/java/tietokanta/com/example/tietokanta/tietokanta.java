package tietokanta.com.example.tietokanta;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.text.DateFormat;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class tietokanta extends Application {
    @Override
    public void start(Stage stage) throws IOException {

      final ObservableList<person>data = FXCollections.observableArrayList();
        List<List<String>> excelContent = new ArrayList<>();

       TableView <person> tableView = new TableView();
       boolean backupFound = false;
      XSSFWorkbook workbook = new XSSFWorkbook();
      XSSFSheet sheet = workbook.createSheet("data");

        TextField nimiKenttä = new TextField();
        TextField riviKenttä = new TextField();
        TextField hautaustapaKenttä = new TextField();
        TextField paikkaKenttä = new TextField();
        TextField tuhkausPaikkaKenttä = new TextField();
        TextField hautausPvmKenttä = new TextField();

            DataFormatter formatter = new DataFormatter();

            File directory = new File(new File(".").getAbsolutePath());
            String[] list = directory.list();
        TableColumn nimiCol = new TableColumn("Nimi");
        nimiCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        TableColumn tapaCol = new TableColumn("Hautaustapa");
        tapaCol.setCellValueFactory(new PropertyValueFactory<>("Hautaustapa"));
        TableColumn paikkaCol = new TableColumn("Paikka");
        paikkaCol.setCellValueFactory(new PropertyValueFactory<>("Paikka"));
        TableColumn riviCol = new TableColumn("Rivi");
       riviCol.setCellValueFactory(new PropertyValueFactory<>("Rivi"));
        TableColumn tuhkausCol = new TableColumn("Tuhkaus Paikka");
        tuhkausCol.setCellValueFactory(new PropertyValueFactory<>("TuhkausPaikka"));
        TableColumn pvmCol = new TableColumn("Hautauspäivämäärä");
        pvmCol.setCellValueFactory(new PropertyValueFactory<>("HautausPvm"));

        tableView.getColumns().addAll(nimiCol,tapaCol,paikkaCol,riviCol,tuhkausCol,pvmCol);

            for (int i = 0; i < list.length; i++) {
                String fileName = list[i];
                if (fileName.equals("Hautakirja_Backup.xlsx")) {
                    System.out.print("löyty");
                    backupFound = true;
                } else {
                 /*   if(workbook.getSheet("data")== null) {
                        workbook.createSheet("data");
                    }
                    FileOutputStream out = new FileOutputStream("Hautakirja_Backup.xlsx");

                        workbook.write(out);
                        out.close();*/
                }
            }
            if(backupFound) {
                try {
                    FileInputStream fis = new FileInputStream("Hautakirja_Backup.xlsx");
                    int columnCounter = 0;
                    int rowCounter = 0;

                    Workbook wb = WorkbookFactory.create(fis);
                    Sheet sheet1 = wb.getSheetAt(0);

                    for (Row row : sheet1) {
                        List<String> tempList = new ArrayList();

                        for (Cell cell : row) {

                                String text = formatter.formatCellValue(cell);
                                System.out.print(++columnCounter + ": " + text + " - ");
                                   System.out.println(tempList);

                                if(row.getRowNum() != 0) {
                                    tempList.add(text.length() == 0 ? "" : text);
                                }

                        }
                        columnCounter = 0;
                        System.out.print("derpad");
                        if(!tempList.isEmpty()) {
                            excelContent.add(tempList);
                        }
                        ++rowCounter;
                        if (rowCounter == 5) {
                            break;
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                //tässä populoidaan solut
                    if(!excelContent.isEmpty()) {
                        for (int i = 0; i < excelContent.size(); i++) {
                            int curcol = 0;
                            data.add(new person(excelContent.get(i).get(curcol),
                                    excelContent.get(i).get(curcol + 1),
                                    excelContent.get(i).get(curcol + 2),
                                    excelContent.get(i).get(curcol + 3),
                                    excelContent.get(i).get(curcol + 4),
                                    excelContent.get(i).get(curcol + 5)));
                        }

                        tableView.setItems(data);
                    }
            }

        Button addPerson = new Button("lisää");

        Button saveToExcel = new Button("tallenna exceliin");
        saveToExcel.setMaxSize(200, 200);
        saveToExcel.setMinHeight(100);

        addPerson.setOnAction(new EventHandler<ActionEvent>(){

            public void handle(ActionEvent event){

                Button sendButton = new Button("lisää");
                Button saveButton = new Button("tallenna");
                Label nimi = new Label("nimi");
                Label hautausTapa = new Label("hautaustapa");
                Label paikka = new Label("paikka");
                Label rivi = new Label("rivi");
                Label tuhkausPaikka = new Label("tuhkauspaikka");
                Label hautauspvm = new Label("hautauspäivämäärä");
                VBox layout = new VBox();
                layout.getChildren().addAll(nimi,nimiKenttä,hautausTapa, hautaustapaKenttä,paikka, paikkaKenttä,rivi, riviKenttä, tuhkausPaikka, tuhkausPaikkaKenttä,hautauspvm,hautausPvmKenttä, saveButton);

                saveButton.setOnAction(actionEvent -> {

                   data.add(new person(nimiKenttä.getText(),
                                       hautaustapaKenttä.getText(),
                                       paikkaKenttä.getText(),
                                       riviKenttä.getText(),
                                        tuhkausPaikkaKenttä.getText(),
                                        hautausPvmKenttä.getText()));

                   nimiKenttä.setText("");
                   hautaustapaKenttä.setText("");
                   paikkaKenttä.setText("");
                   riviKenttä.setText("");
                   tuhkausPaikkaKenttä.setText("");
                   hautausPvmKenttä.setText("");

                });
               tableView.setItems(data);


                Scene newScene = new Scene(layout, 600,600);

                Stage newWindow = new Stage();
                newWindow.setTitle("lisää hautakirjaan");
                newWindow.setScene(newScene);
                newWindow.initModality(Modality.WINDOW_MODAL);
                newWindow.initOwner(stage);

                newWindow.setX(stage.getX()+200);
                newWindow.setY(stage.getY()+100);
                newWindow.show();

            }});

        saveToExcel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
           public void handle(ActionEvent actionEvent) {

                writeToFile(workbook,tableView,"hautakirjaOte");
                showAlertNoHeader("Hautakirjaotteen tallennus", "Hautakirjaote tallennettu.");
            }
        });

        stage.setOnCloseRequest(windowEvent ->


        writeToFile(workbook,tableView, "backUp"));

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
    public String getDateTime(){
        Locale locale = new Locale("fi","FI");
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.SHORT, locale);

        return dateFormat.format(new Date());
    }
    private void writeToFile(XSSFWorkbook wantedFile, TableView<person> tableView, String whichFunction){
        FileOutputStream out = null;
        XSSFSheet persons;
        if(wantedFile.getSheet("data") == null){
            wantedFile.createSheet("data");
        }
        persons = wantedFile.getSheet("data");

        Row row = persons.createRow(0);


        for(int i = 0; i < tableView.getColumns().size(); i++){
            row.createCell(i).setCellValue(tableView.getColumns().get(i).getText());

        }
        for(int i = 0; i <tableView.getItems().size(); i++) {
            row = persons.createRow(i+1);
            for(int j = 0; j <tableView.getColumns().size();j++)
            {
                if(tableView.getColumns().get(j).getCellData(i) != null){
                    row.createCell(j).setCellValue(tableView.getColumns().get(j).getCellData(i).toString());
                }else{
                    row.createCell(j).setCellValue("");
                }
            }
        }
        try {
            switch(whichFunction){
                case "hautakirjaOte": {

                    out = new FileOutputStream("HautakirjaOte_" + getDateTime() + ".xlsx");
                    break;
                }
                case "backUp":
                {
                    out = new FileOutputStream("Hautakirja_Backup.xlsx");
                    break;
                }
            }
           // FileOutputStream out = new FileOutputStream("HautakirjaOte_"+getDateTime()+".xlsx");

           wantedFile.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void showAlertNoHeader(String title,String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static void main(String[] args) {
        launch();
    }
}