package controllers;

import models.Thread;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import services.ThreadService;

import java.io.IOException;
import java.sql.SQLException;

public class modifyTitle {
    @FXML
    private TextField titreMod;
    @FXML
    private TextField descriptionMod;
    @FXML
    private Label error;
    @FXML
    private ColorPicker color;
    @FXML
    private Label error2;

    private boolean isTitreValid = true;
    private boolean isDescriptionValid = true;

    private ThreadService threadService = new ThreadService();

    public void initialize() {
        try {
            Thread thread = threadService.getById(9); //do it manually until i figure out MessageController
            titreMod.setText(thread.getTitleThread());
            descriptionMod.setText(thread.getDescriptionThread());
            color.setValue(Color.web(thread.getColorThread()));
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }
    }

    @FXML
    void modifierTitre(ActionEvent event) {
        String titre = titreMod.getText();
        String description = descriptionMod.getText();
        int id = 9; //MessageController.discuId;
        Color c = color.getValue();
        String colorAsRgb = String.format("#%02X%02X%02X",
                (int)(c.getRed() * 255),
                (int)(c.getGreen() * 255),
                (int)(c.getBlue() * 255));

        if (titre.isEmpty()) {
            error.setText("Le champ titre est vide !");
            isTitreValid = false;
        } else if (titre.length() > 10) {
            error.setText("Le titre ne doit pas dépasser 10 caractères !");
            isTitreValid = false;
        } else {
            error.setText("");
            isTitreValid = true;
        }

        if (description.isEmpty()) {
            error2.setText("Le champ description est vide !");
            isDescriptionValid = false;
        } else {
            error2.setText("");
            isDescriptionValid = true;
        }


        if (isTitreValid && isDescriptionValid) {
            Thread thread = new Thread(id, titre, description, colorAsRgb);
            try {
                threadService.update(thread);
                showAlert(Alert.AlertType.INFORMATION, "Success", "Thread has been successfully modified !");
                changeScene("/listThreads.fxml");
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
                changeScene("/listThreads.fxml");
            }
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void changeScene(String s) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(s));
            titreMod.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void retour() {
        // Implement the method to go back to the previous screen
    }
}
