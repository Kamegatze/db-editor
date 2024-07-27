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
    private TextField path;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    public TextField getHost() {
        return host;
    }

    public TextField getPath() {
        return path;
    }

    public TextField getUsername() {
        return username;
    }

    public TextField getPassword() {
        return password;
    }
}
