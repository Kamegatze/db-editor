package org.kamegatze.dbeditor.menubar.modal;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class ConnectDatabaseDialogController {

    public static final ButtonType CONNECT = new ButtonType("Connect", ButtonBar.ButtonData.APPLY);

    @FXML
    private TextField host;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private TextField port;

    @FXML
    private TextField connection;

    public TextField getHost() {
        return host;
    }

    public TextField getUsername() {
        return username;
    }

    public TextField getPassword() {
        return password;
    }

    public TextField getPort() {
        return port;
    }

    public TextField getConnection() {
        return connection;
    }
}
