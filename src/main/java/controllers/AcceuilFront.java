package controllers;

import models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;


import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class AcceuilFront {
    @FXML
    private Button eventButton;
    @FXML
    private Button messagerie;

    private User currentUser;

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }





    @FXML
    private void allerVersBtnMessagerie() {
        changeScene2("/listThreads.fxml");
    }

    private void changeScene2(String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            messagerie.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
