package org.kamegatze.dbeditor.configuration;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.DialogPane;
import org.kamegatze.dbeditor.Main;
import org.kamegatze.dbeditor.loader.properties.Props;
import org.kamegatze.dbeditor.menubar.MenuBarController;
import org.kamegatze.dbeditor.menubar.modal.ConnectDatabaseDialog;
import org.kamegatze.dbeditor.menubar.modal.ConnectDatabaseDialogController;
import org.kamegatze.dbeditor.menubar.modal.ConnectDatabaseDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.util.Objects;

@Configuration
@ComponentScan("org.kamegatze.dbeditor")
@PropertySource("classpath:application.properties")
public class Config {

    private final Props props;

    public Config(Props props) {
        this.props = props;
    }

    @Bean("fxmlLoaderDatabaseDialog")
    public FXMLLoader createFxmlLoaderConnectDatabaseDialog() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(
                ConnectDatabaseDialog.class.getResource(props.getApplicationView())
        ));
        fxmlLoader.load();
        return fxmlLoader;
    }

    @Bean("fxmlLoaderRoot")
    public FXMLLoader createFxmlLoaderRoot() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Objects.requireNonNull(
                Main.class.getResource(props.getApplicationView())
        ));
        fxmlLoader.load();
        return fxmlLoader;
    }

    @Bean
    public ConnectDatabaseDialogController getConnectDatabaseDialogController(
            @Qualifier("fxmlLoaderDatabaseDialog") FXMLLoader fxmlLoaderDatabaseDialog) {
        return fxmlLoaderDatabaseDialog.getController();
    }

    @Bean("dialogPaneForDatabaseDialog")
    public DialogPane getDialogPaneForDatabaseDialog(
            @Qualifier("fxmlLoaderDatabaseDialog") FXMLLoader fxmlLoaderDatabaseDialog) throws IOException {
        return fxmlLoaderDatabaseDialog.getRoot();
    }

    @Bean("root")
    public Parent getRoot(@Qualifier("fxmlLoaderRoot") FXMLLoader fxmlLoaderRoot) throws IOException {
        return fxmlLoaderRoot.getRoot();
    }

    @Bean("MenuBarController")
    public MenuBarController getMenuBarController(
            @Qualifier("fxmlLoaderRoot") FXMLLoader fxmlLoaderDatabaseDialog,
            ConnectDatabaseDialog connectDatabaseDialog) {
        MenuBarController menuBarController = fxmlLoaderDatabaseDialog.getController();
        menuBarController.setProps(props);
        menuBarController.setConnectDatabaseDialog(connectDatabaseDialog);
        return menuBarController;
    }

    @Bean
    public ConnectDatabaseDialog connectDatabaseDialog(@Qualifier("dialogPaneForDatabaseDialog") DialogPane dialogPane,
                                                       ConnectDatabaseDialogController connectDatabaseDialogController) {
        return new ConnectDatabaseDialog(
            new ConnectDatabaseDto("", "", "", "", "", props.getNamePostgresql()),
            dialogPane,
            connectDatabaseDialogController,
            props
        );
    }
}
