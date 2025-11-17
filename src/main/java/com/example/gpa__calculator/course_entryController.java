package com.example.gpa__calculator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.util.ArrayList;

public class course_entryController {

    @FXML private TextField totalCreditField;
    @FXML private TextField courseNameField;
    @FXML private TextField courseCodeField;
    @FXML private TextField creditField;
    @FXML private TextField teacher1Field;
    @FXML private TextField teacher2Field;
    @FXML private ComboBox<String> gradeComboBox;
    @FXML private Label creditLabel;
    @FXML private Button calcButton;

    private int requiredCredits = 0;
    private int currentCredits = 0;

    private ArrayList<Course> courses = new ArrayList<>();

    @FXML
    public void initialize() {
        gradeComboBox.getItems().addAll("A+","A","A-","B+","B","B-","C+","C","D","F");
        calcButton.setDisable(true);
    }

    @FXML
    private void addCourse() {
        try {
            if (requiredCredits == 0) {
                requiredCredits = Integer.parseInt(totalCreditField.getText());
            }

            String name = courseNameField.getText();
            String code = courseCodeField.getText();
            int credit = Integer.parseInt(creditField.getText());
            String t1 = teacher1Field.getText();
            String t2 = teacher2Field.getText();
            String grade = gradeComboBox.getValue();

            if (name.isEmpty() || code.isEmpty() || grade == null) {
                show("Please fill all required fields!", Alert.AlertType.ERROR);
                return;
            }

            courses.add(new Course(name, code, credit, t1, t2, grade));
            currentCredits += credit;

            creditLabel.setText("Credits Added: " + currentCredits + " / " + requiredCredits);

            if (currentCredits >= requiredCredits) {
                calcButton.setDisable(false);
            }

            // clear input fields
            courseNameField.clear();
            courseCodeField.clear();
            creditField.clear();
            teacher1Field.clear();
            teacher2Field.clear();
            gradeComboBox.setValue(null);

            show("Course Added!", Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            show("Invalid input!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void calculateGPA() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gpa_result.fxml"));
            Parent root = loader.load();

            gpa_resultController controller = loader.getController();
            controller.setData(courses);

            Stage stage = (Stage) courseNameField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void show(String msg, Alert.AlertType type) {
        Alert a = new Alert(type, msg);
        a.show();
    }
}
