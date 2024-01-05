package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene1;

    @Override
    public void start(Stage stage) throws IOException {
        scene1 = new Scene(loadFXML("LogInPage"), 1400, 700);
        stage.setResizable(false);
        stage.setScene(scene1);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException { //To change the pages after clicking a button
        scene1.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        Parent root = fxmlLoader.load();
        return root;
    }

    public static void main(String[] args) {
        launch();
    }

}
