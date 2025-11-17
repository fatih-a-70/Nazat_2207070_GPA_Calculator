package com.example.gpa__calculator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class HelloController {

    @FXML
    private Button startButton;

    @FXML
    private void startGpaCalculator() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("course_entry.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) startButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
