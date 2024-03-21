package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Charger le fichier FXML
        Parent root = FXMLLoader.load(getClass().getResource("/hello-view.fxml"));

        // Créer une nouvelle scène avec le contenu du fichier FXML
        Scene scene = new Scene(root);
        //scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        // Définir la scène sur la scène principale
        primaryStage.setScene(scene);

        // Définir le titre de la fenêtre
        primaryStage.setTitle("Forum");

        // Afficher la fenêtre
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
