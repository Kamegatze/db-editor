module org.kamegatze.dbeditor {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.kamegatze.dbeditor to javafx.fxml;
    opens org.kamegatze.dbeditor.menubar to javafx.fxml;
    opens org.kamegatze.dbeditor.menubar.modal to javafx.fxml;
    exports org.kamegatze.dbeditor;
}