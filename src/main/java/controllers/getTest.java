package controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import services.TestService;

import java.sql.SQLException;


public class getTest {

    private final TestService us = new TestService();

    @FXML
    private Label test;


    @FXML
    void afficher(ActionEvent event) throws SQLException {
        //code
        String tests = us.getAll().toString();
        test.setText(tests);

    }





}
