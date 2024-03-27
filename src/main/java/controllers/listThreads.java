package controllers;

import models.Thread;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import services.ThreadService;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.function.Predicate;

public class listThreads {
    @FXML
    private TableView<Thread> table;
    @FXML
    private TextField rechercheField;
    @FXML
    private TableColumn<Thread, String> titreColumn;
    @FXML
    private TableColumn<Thread, String> createurColumn;
    @FXML
    private TableColumn<Thread, Timestamp> dateCreationColumn;

    private ThreadService threadService = new ThreadService();

    public void initialize() {
        try {
            initTableColumns();
            loadThreads();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }
    }

    private void initTableColumns() {
        titreColumn.setCellValueFactory(new PropertyValueFactory<>("titleThread"));
        createurColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getCreatorThread().getNom()));
        dateCreationColumn.setCellValueFactory(new PropertyValueFactory<>("TimeStampCreation"));
    }

    private void loadThreads() throws SQLException {
        ObservableList<Thread> discussions = FXCollections.observableList(threadService.getAll());
        table.setItems(discussions);
    }

    @FXML
    public void onClick() {
        table.setRowFactory(tv -> {
            TableRow<Thread> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {
                    Thread clickedRow = row.getItem();
                    // Handle double-click event
                    // For example: open the discussion in a new window
                }
            });
            return row;
        });
    }

    @FXML
    public void redirectToAjout() {
        changeScene("/addThread.fxml");
    }

    @FXML
    public void retournerVersAcceuil() {
        // Implement the method to go back to the home page or previous page
    }

    @FXML
    public void rechercherParUser() {
        String nomRecherche = rechercheField.getText().trim().toLowerCase();

        if (!nomRecherche.isEmpty()) {
            Predicate<Thread> nomPredicate = thread ->
                    thread.getCreatorThread().getNom().toLowerCase().contains(nomRecherche);
            FilteredList<Thread> filteredList = new FilteredList<>(table.getItems(), nomPredicate);
            table.setItems(filteredList);
        } else {
            try {
                loadThreads();
            } catch (SQLException e) {
                e.printStackTrace(); // Handle or log the exception appropriately
            }
        }
    }

    private void changeScene(String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            table.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }
    }
}
