module org.kamegatze.dbeditor {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires spring.jdbc;
    requires org.postgresql.jdbc;
    requires spring.context;
    requires spring.beans;
    requires spring.core;


    opens org.kamegatze.dbeditor;
    opens org.kamegatze.dbeditor.menubar;
    opens org.kamegatze.dbeditor.menubar.modal;
    opens org.kamegatze.dbeditor.loader.properties;
    opens org.kamegatze.dbeditor.configuration;
    exports org.kamegatze.dbeditor;
}