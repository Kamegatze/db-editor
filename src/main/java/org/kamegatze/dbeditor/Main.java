package org.kamegatze.dbeditor;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kamegatze.dbeditor.configuration.Config;
import org.kamegatze.dbeditor.loader.properties.Props;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        Parent root = applicationContext.getBean("root", Parent.class);
        Props props = applicationContext.getBean(Props.class);

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle(props.getApplicationTitle());

        stage.setScene(scene);
        stage.show();
        stage.setMaximized(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}