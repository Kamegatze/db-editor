package org.kamegatze.dbeditor.menubar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;
import org.kamegatze.dbeditor.menubar.modal.ConnectDatabaseDto;
import org.kamegatze.dbeditor.menubar.modal.ConnectDatabaseDialog;

import java.io.IOException;
import java.util.Optional;

public class MenuBarController {

    @FXML
    private MenuItem menuItemConnectCreate;

    @FXML
    private void onConnectCreate(ActionEvent event) throws IOException {
        Dialog<ConnectDatabaseDto> connectDatabaseDtoDialog = new ConnectDatabaseDialog(
                new ConnectDatabaseDto("", "", "", "")
        );
        Optional<ConnectDatabaseDto> connectDatabaseDto = connectDatabaseDtoDialog.showAndWait();
        connectDatabaseDto.ifPresent(connect -> {
            System.out.println(connect);
        });
    }

}
