package org.kamegatze.dbeditor.menubar;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Dialog;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.kamegatze.dbeditor.backend.info.metadata.ConnectionDatabase;
import org.kamegatze.dbeditor.backend.info.metadata.database.schema.Schema;
import org.kamegatze.dbeditor.backend.info.metadata.database.schema.table.Table;
import org.kamegatze.dbeditor.backend.info.metadata.database.schema.view.View;
import org.kamegatze.dbeditor.backend.info.metadata.impl.ConnectionPostgresQLImpl;
import org.kamegatze.dbeditor.backend.info.metadata.repositories.database.postgresql.impl.DatabasePGDatabaseRepositoryImpl;
import org.kamegatze.dbeditor.backend.info.metadata.database.Database;
import org.kamegatze.dbeditor.loader.properties.Props;
import org.kamegatze.dbeditor.menubar.modal.ConnectDatabaseDto;
import org.kamegatze.dbeditor.menubar.modal.ConnectDatabaseDialog;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import java.io.IOException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class MenuBarController {


    @FXML
    private TreeView<String> treeView;

    @FXML
    private void onConnectCreate(ActionEvent event) throws IOException {
        Dialog<ConnectDatabaseDto> connectDatabaseDtoDialog = new ConnectDatabaseDialog(
                new ConnectDatabaseDto("", "", "", "", "",
                        Props.PROPERTIES.getProperty("connection.databases.name.postgresql"))
        );


        Optional<ConnectDatabaseDto> connectDatabaseDto = connectDatabaseDtoDialog.showAndWait();
        connectDatabaseDto.ifPresent(connect -> {
            String url = String.format(Props.PROPERTIES.getProperty("connection.url.template"), connect.getTypeDatabaseValue().getProperty(),
                    connect.getHost(), connect.getPort()
            );

            SimpleDriverDataSource simpleDriverDataSource;
            try {
                Driver driver = DriverManager.getDriver(url);
                simpleDriverDataSource = new SimpleDriverDataSource(driver, url,
                        connect.getUsername(), connect.getPassword());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            JdbcTemplate jdbcTemplate = new JdbcTemplate(simpleDriverDataSource);
            treeView.getRoot().getChildren()
                    .add(createTreeMenu(new ConnectionPostgresQLImpl(connect.getNameConnect(), simpleDriverDataSource, new DatabasePGDatabaseRepositoryImpl(jdbcTemplate))));

        });
    }

    private TreeItem<String> createTreeMenu(ConnectionDatabase connectionDatabase) {
        TreeItem<String> connection = new TreeItem<>(connectionDatabase.getName());
        TreeItem<String> databases = new TreeItem<>(Props.PROPERTIES.getProperty("connection.databases.name"));
        for(Database database : connectionDatabase.getDatabase()) {
            TreeItem<String> schemas = new TreeItem<>(Props.PROPERTIES.getProperty("connection.databases.schemas.name"));
            TreeItem<String> databaseTreeItem = new TreeItem<>(database.getName());
            for(Schema schema : database.getSchemas()) {
                TreeItem<String> schemaTreeItem = new TreeItem<>(schema.getName());
                TreeItem<String> tables = new TreeItem<>(Props.PROPERTIES.getProperty("connection.databases.schemas.tables.name"));
                for(Table table : schema.getTables()) {
                    TreeItem<String> tableTreeItem = new TreeItem<>(table.getName());
                    tables.getChildren().add(tableTreeItem);
                }
                TreeItem<String> views = new TreeItem<>(Props.PROPERTIES.getProperty("connection.databases.schemas.views.name"));
                for (View view : schema.getViews()) {
                    TreeItem<String> viewTreeItem = new TreeItem<>(view.getName());
                    views.getChildren().add(viewTreeItem);
                }
                schemaTreeItem.getChildren().addAll(tables, views);
                schemas.getChildren().add(schemaTreeItem);
            }
            databaseTreeItem.getChildren().add(schemas);
            databases.getChildren().add(databaseTreeItem);
        }
        final String loginAndGroupRolesString = String.format(Props.PROPERTIES.getProperty("connection.login-and-group-roles"), 16);
        TreeItem<String> loginAndGroupRoles = new TreeItem<>(loginAndGroupRolesString);
        connection.getChildren().add(databases);
        connection.getChildren().add(loginAndGroupRoles);
        return connection;
    }

}
