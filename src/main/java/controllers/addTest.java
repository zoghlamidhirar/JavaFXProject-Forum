package controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.Test;
import services.TestService;

import java.sql.SQLException;

public class addTest {

    private final TestService us = new TestService();
    @FXML
    private TextField ageTF;

    @FXML
    private TextField nameTF;

    @FXML
    void addTest(ActionEvent event) throws SQLException {

        if(nameTF.getText().isEmpty())
        {
            System.out.println("name is empty");
        }
        else {
            us.add(new Test(Integer.parseInt(ageTF.getText()), nameTF.getText()));
        }
    }
}
