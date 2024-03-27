package controllers;

import models.Thread;
import models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import services.ThreadService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class addThread {

    @FXML
    private TextField titre ;
    @FXML
    private ColorPicker color;

    @FXML
    private TextField description;
    @FXML
    private Label errorMessage;

    @FXML
    private Label errorMessage2;


    User user1 = new User(2,"dhirar");


    boolean isTitleValid = true;
    boolean isDescriptionValid = true;
    @FXML
    void addEvent() throws SQLException {
        String title = titre.getText();
        String desc = description.getText();
        Timestamp currentTimestamp = new Timestamp( System.currentTimeMillis());
        Color c = color.getValue();
        String colorAsRgb = String.format("#%02X%02X%02X",
                (int)(c.getRed() * 255),
                (int)(c.getGreen() * 255),
                (int)(c.getBlue() * 255));
        Thread thread = new Thread(title,currentTimestamp,user1,desc,colorAsRgb);
        if (title.isEmpty()) {
            errorMessage.setText("Le champ titre est vide !");
            isTitleValid = false;
        } else if (title.length() > 10) {
            errorMessage.setText("Le titre ne doit pas dépasser 10 caractères !");
            isTitleValid = false;
        }else{
            errorMessage.setText("");
            isTitleValid = true;
        }
        if (desc.isEmpty()) {
            errorMessage2.setText("Le champ description est vide !");
            isDescriptionValid = false;
        }else{
            errorMessage2.setText("");
            isDescriptionValid = true;
        }
        if(titreExist(title)){
            errorMessage.setText("Le titre exist !");

        }
        if(!titreExist(title) && isTitleValid && isDescriptionValid){
            try {

                ThreadService ts = new ThreadService();
                ts.add(thread);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Thread added successfully!");

                alert.showAndWait();
                changeScene();


            }catch(Exception e){
                System.out.println(e.getMessage());
            }

        }
    }

    public void changeScene() {
        try {
            // Chargez le fichier FXML pour la nouvelle scène
            Parent root = FXMLLoader.load(getClass().getResource("/listThreads.fxml"));
            titre.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean titreExist(String titre) throws SQLException {
        ThreadService ts = new ThreadService();
        List<Thread> threads = ts.getAll();
        for(Thread thread:threads){
            if(thread.getTitleThread().equalsIgnoreCase(titre))

                return true ;
        }
        return false;
    }
}
