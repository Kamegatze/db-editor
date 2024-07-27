package org.kamegatze.dbeditor.menubar.modal;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.util.Callback;
import org.kamegatze.dbeditor.loader.properties.Props;

import java.io.IOException;
import java.util.*;

public class ConnectDatabaseDialog extends Dialog<ConnectDatabaseDto> {

    private final ConnectDatabaseDto connectDatabaseDto;

    private TextField host;
    private TextField path;
    private TextField username;
    private TextField password;

    public ConnectDatabaseDialog(ConnectDatabaseDto connectDatabaseDto) throws IOException {
        super();
        this.setTitle("Create a new connect database");
        this.connectDatabaseDto = connectDatabaseDto;
        buildUI();
        setPropertyBindings();
        setResultConverter();
    }

    private void setResultConverter() {
        Callback<ButtonType, ConnectDatabaseDto> connectDatabaseDtoCallback = (buttonType) -> {
            if (buttonType.equals(ConnectDatabaseDialogController.CONNECT)) {
                return connectDatabaseDto;
            }
            return null;
        };
        setResultConverter(connectDatabaseDtoCallback);
    }

    private void setPropertyBindings() {
        host.textProperty().bindBidirectional(connectDatabaseDto.hostProperty());
        path.textProperty().bindBidirectional(connectDatabaseDto.pathProperty());
        username.textProperty().bindBidirectional(connectDatabaseDto.usernameProperty());
        password.textProperty().bindBidirectional(connectDatabaseDto.passwordProperty());
    }

    private void buildUI() throws IOException {
        Properties properties = Props.getProperties();

        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(
                ConnectDatabaseDialog.class.getResource(properties.getProperty("application.view"))
        ));
        DialogPane pane = createDialog(fxmlLoader);

        ConnectDatabaseDialogController connectDatabaseDialogController = fxmlLoader.getController();

        host = connectDatabaseDialogController.getHost();
        path = connectDatabaseDialogController.getPath();
        username = connectDatabaseDialogController.getUsername();
        password = connectDatabaseDialogController.getPassword();


        setDialogPane(pane);

        Button button = (Button)getDialogPane().lookupButton(ConnectDatabaseDialogController.CONNECT);
        button.addEventFilter(ActionEvent.ACTION, event -> {
            if (!validateDialog()) {
                event.consume();
            }
        });
    }

    private boolean validateDialog() {
        return !(host.getText().isEmpty() || host.getText().isBlank() ||
                path.getText().isEmpty() || path.getText().isBlank() || username.getText().isEmpty() || username.getText().isBlank() ||
                password.getText().isEmpty() || password.getText().isBlank());
    }

    public DialogPane createDialog(FXMLLoader fxmlLoader) throws IOException {
        return fxmlLoader.load();
    }

}