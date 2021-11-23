module tietokanta.com.example.tietokanta {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.poi.poi;


    opens tietokanta.com.example.tietokanta to javafx.fxml;
    exports tietokanta.com.example.tietokanta;
}