package tietokanta.com.example.tietokanta;

import javafx.application.Application;
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

        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
        List<List<String>> excelContent = new ArrayList<>();
       TableView<ObservableList<String>> tableView = new TableView();

        TextField nimiKenttä = new TextField();
        TextField riviKenttä = new TextField();
        TextField hautaustapaKenttä = new TextField();
        TextField paikkaKenttä = new TextField();
        TextField tuhkausPaikkaKenttä = new TextField();
        TextField hautausPvmKenttä = new TextField();

        try {
            DataFormatter formatter = new DataFormatter();

            int rowCounter = 0;
            FileInputStream fis = new FileInputStream("testi.xlsx");
            XSSFWorkbook wb = new XSSFWorkbook(fis);

            XSSFSheet sheet = wb.getSheetAt(0);

            for(Row row: sheet){
                List<String> tempList = new ArrayList<>();
                for (Cell cell:row){
                   // CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
                        String text = formatter.formatCellValue(cell);
                        tempList.add(text.length() == 0 ? "":text);

                }

                excelContent.add(tempList);

                ++rowCounter;
                if(rowCounter == 5){
                    break;
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }


            for(int i = 0; i <excelContent.get(0).size(); i++){
                int curCol = i;
                final TableColumn<ObservableList<String>, String> column = new TableColumn<>(excelContent.get(0).get(i));
                column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(curCol)));
                tableView.getColumns().add(column);


            }
        for(int i = 0; i <excelContent.size(); i++){
            if(excelContent.get(i).contains("nimi") ||
                    excelContent.get(i).contains("hautaustapa")||
                    excelContent.get(i).contains("paikka")||
                    excelContent.get(i).contains("rivi")||
                    excelContent.get(i).contains("tuhkauspaikka")||
                    excelContent.get(i).contains("hautauspäivämäärä")){

            }else{
                data.add(FXCollections.observableArrayList(excelContent.get(i)));
            }


        }
        tableView.setItems(data);

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

                saveButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        ObservableList<ObservableList<String>> data1 = FXCollections.observableArrayList();
                        List<List<String>> personData = new ArrayList<>();
                        List<String> tempList = new ArrayList<>();

                        tempList.add(nimiKenttä.getText());
                        tempList.add(hautaustapaKenttä.getText());
                        tempList.add(paikkaKenttä.getText());
                                tempList.add(riviKenttä.getText());
                        tempList.add(tuhkausPaikkaKenttä.getText());
                        tempList.add(hautausPvmKenttä.getText());
                        personData.add(tempList);
                        for(int i = 0; i <personData.size();i++){
                            data.add(FXCollections.observableArrayList(personData.get(i)));
                        }
                      tableView.setItems(data);
                       nimiKenttä.setText("");
                       hautaustapaKenttä.setText("");
                       paikkaKenttä.setText("");
                       riviKenttä.setText("");
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

        saveToExcel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
           public void handle(ActionEvent actionEvent) {
                System.out.print("joo ");
                DataFormatter formatter = new DataFormatter();
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet persons = workbook.createSheet("data");
               // XSSFRow row;

        Row row = persons.createRow(0);
              //  Map<String, Object[]> dataToBeSaved = new TreeMap<String, Object[]>();

                for(int i = 0; i < tableView.getColumns().size(); i++){
                    row.createCell(i).setCellValue(tableView.getColumns().get(i).getText());


                 /*   int curCol = 0;

                    dataToBeSaved.put("1", new Object[]{tableView.getColumns().get(curCol).getText(),
                            (curCol++),
                            tableView.getColumns().get(curCol).getText(),
                            (curCol++),
                                    tableView.getColumns().get(curCol).getText(),
                            (curCol++),
                                          tableView.getColumns().get(curCol).getText(),
                            (curCol++),
                                                   tableView.getColumns().get(curCol).getText(),
                            (curCol++),
                                                          tableView.getColumns().get(curCol).getText()});*/



                }

                    for(int i = 0; i<tableView.getItems().size(); i++) {

                        row = persons.createRow(i+1);
                        for(int j = 0; j <tableView.getColumns().size();j++)
                        {
                            if(tableView.getColumns().get(j).getCellData(i) != null){
                                row.createCell(j).setCellValue(tableView.getColumns().get(j).getCellData(i).toString());
                            }else{
                                row.createCell(j).setCellValue("");
                            }
                        }
                    /*    dataToBeSaved.put("2", new Object[]{
                                tableView.getItems().get(0).get(i)});
                      //  System.out.print(tableView.getColumns().get(curCol).getCellData(i));
                      //  System.out.print("curcol "+ curCol);
                      //  System.out.print(tableView.getItems().get(0).size());
                        System.out.print(tableView.getItems().get(0).get(i));*/

                    }



         /*   Set<String> keyId = dataToBeSaved.keySet();

            int rowId = 0;
                for (String key: keyId)
            {

                row = persons.createRow(rowId++);
                Object[] objectArr = dataToBeSaved.get(key);

                int cellId = 0;
                for(Object obj:objectArr){
                    Cell cell = row.createCell(cellId++);

                    cell.setCellValue(obj.toString());
                }

            }*/
                try {
                    FileChooser chooser = new FileChooser();
                    chooser.setTitle("open");
                    chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("text","*txt"));
                    File selectedFile = chooser.showSaveDialog(stage);
                    if(selectedFile != null) {

                    }
                    FileOutputStream out = new FileOutputStream(new File(String.valueOf(selectedFile)));
                    System.out.print("filessä");
                    workbook.write(out);
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });

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