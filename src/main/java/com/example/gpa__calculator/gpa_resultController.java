package com.example.gpa__calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class gpa_resultController {

    @FXML private TableView<Course> courseTable;
    @FXML private TableColumn<Course, String> colName;
    @FXML private TableColumn<Course, Integer> colCredit;
    @FXML private TableColumn<Course, String> colGrade;

    @FXML private Label gpaLabel;

    public void setData(List<Course> courses) {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCredit.setCellValueFactory(new PropertyValueFactory<>("credit"));
        colGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));

        courseTable.getItems().addAll(courses);

        double totalPoints = 0;
        int totalCredits = 0;

        for (Course c : courses) {
            totalPoints += gradeToPoints(c.getGrade()) * c.getCredit();
            totalCredits += c.getCredit();
        }

        double gpa = totalPoints / totalCredits;
        gpaLabel.setText(String.format("%.2f", gpa));
    }

    private double gradeToPoints(String g) {
        return switch (g) {
            case "A+" -> 4.00;
            case "A"  -> 3.75;
            case "A-" -> 3.5;
            case "B+" -> 3.25;
            case "B"  -> 3.0;
            case "B-" -> 2.75;
            case "C+" -> 2.5;
            case "C"  -> 2.25;
            case "D"  -> 2.0;
            default -> 0.0;
        };
    }
}
