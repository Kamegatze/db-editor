package org.kamegatze.dbeditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kamegatze.dbeditor.loader.properties.Props;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Properties properties = Props.PROPERTIES;
        Parent root = FXMLLoader.load(
            Objects.requireNonNull(
                Main.class.getResource(
                    properties.getProperty("application.view")
                )
            )
        );


        Scene scene = new Scene(root, 800, 600);
        stage.setTitle(properties.getProperty("application.title"));

        stage.setScene(scene);
        stage.show();
        stage.setMaximized(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}