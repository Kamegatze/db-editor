module org.kamegatze.dbeditor {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires spring.jdbc;
    requires org.postgresql.jdbc;


    opens org.kamegatze.dbeditor to javafx.fxml;
    opens org.kamegatze.dbeditor.menubar to javafx.fxml;
    opens org.kamegatze.dbeditor.menubar.modal to javafx.fxml;
    opens org.kamegatze.dbeditor.loader.properties to javafx.fxml;
    exports org.kamegatze.dbeditor;
}