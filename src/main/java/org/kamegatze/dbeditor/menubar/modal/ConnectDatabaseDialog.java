package org.kamegatze.dbeditor.menubar.modal;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.util.Callback;
import org.kamegatze.dbeditor.loader.properties.Props;
import org.kamegatze.dbeditor.loader.properties.TypeDatabase;

import java.io.IOException;

public class ConnectDatabaseDialog extends Dialog<ConnectDatabaseDto> {

    private final ConnectDatabaseDto connectDatabaseDto;

    private TextField connection;
    private TextField host;
    private TextField port;
    private TextField username;
    private TextField password;
    private ChoiceBox<TypeDatabase> typeDatabase;

    private final DialogPane dialogPane;
    private final ConnectDatabaseDialogController connectDatabaseDialogController;
    private final Props props;

    public ConnectDatabaseDialog(ConnectDatabaseDto connectDatabaseDto,
                                 DialogPane dialogPane,
                                 ConnectDatabaseDialogController connectDatabaseDialogController,
                                 Props props) {
        super();
        this.setTitle("Create a new connect database");
        this.connectDatabaseDto = connectDatabaseDto;
        this.dialogPane = dialogPane;
        this.connectDatabaseDialogController = connectDatabaseDialogController;
        this.props = props;
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
        username.textProperty().bindBidirectional(connectDatabaseDto.usernameProperty());
        password.textProperty().bindBidirectional(connectDatabaseDto.passwordProperty());
        port.textProperty().bindBidirectional(connectDatabaseDto.portProperty());
        connection.textProperty().bindBidirectional(connectDatabaseDto.nameConnectProperty());
        typeDatabase.valueProperty().bindBidirectional(connectDatabaseDto.getTypeDatabase());
    }

    private void buildUI() {

        connection = connectDatabaseDialogController.getConnection();
        host = connectDatabaseDialogController.getHost();
        port = connectDatabaseDialogController.getPort();
        username = connectDatabaseDialogController.getUsername();
        password = connectDatabaseDialogController.getPassword();
        typeDatabase = connectDatabaseDialogController.getTypeDatabase();

        setDialogPane(dialogPane);

        Button button = (Button)getDialogPane().lookupButton(ConnectDatabaseDialogController.CONNECT);
        button.addEventFilter(ActionEvent.ACTION, event -> {
            if (!validateDialog()) {
                event.consume();
            }
        });
    }

    private boolean validateDialog() {
        return !(host.getText().isEmpty() || host.getText().isBlank() ||
                username.getText().isEmpty() || username.getText().isBlank() ||
                port.getText().isEmpty() || port.getText().isBlank() ||
                password.getText().isEmpty() || password.getText().isBlank());
    }

    public DialogPane createDialog(FXMLLoader fxmlLoader) throws IOException {
        return fxmlLoader.load();
    }

    public ConnectDatabaseDialog init() {
        connectDatabaseDto.setNameConnect("");
        connectDatabaseDto.setHost("");
        connectDatabaseDto.setPort("");
        connectDatabaseDto.setUsername("");
        connectDatabaseDto.setPassword("");
        connectDatabaseDto.setTypeDatabase(props.getNamePostgresql());
        return this;
    }

}