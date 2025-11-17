package com.example.gpa__calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.ArrayList;

public class gpa_resultController {

    @FXML private TableView<Course> courseTable;

    @FXML private TableColumn<Course, String> colName;
    @FXML private TableColumn<Course, String> colCode;
    @FXML private TableColumn<Course, String> colTeacher1;
    @FXML private TableColumn<Course, String> colTeacher2;
    @FXML private TableColumn<Course, Integer> colCredit;
    @FXML private TableColumn<Course, String> colGrade;

    @FXML private TableColumn<Course, Double> colObtained; // NEW COLUMN

    @FXML private Label gpaLabel;

    public void setData(ArrayList<Course> courseList) {

        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colTeacher1.setCellValueFactory(new PropertyValueFactory<>("teacher1"));
        colTeacher2.setCellValueFactory(new PropertyValueFactory<>("teacher2"));
        colCredit.setCellValueFactory(new PropertyValueFactory<>("credit"));
        colGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));

        // Obtained credit = gradePoint * credit
        colObtained.setCellValueFactory(data -> {
            Course c = data.getValue();
            double obtained = gradeToPoint(c.getGrade()) * c.getCredit();
            return new SimpleDoubleProperty(obtained).asObject();
        });

        courseTable.getItems().addAll(courseList);

        double totalPoints = 0;
        int totalCredits = 0;

        for (Course c : courseList) {
            totalPoints += gradeToPoint(c.getGrade()) * c.getCredit();
            totalCredits += c.getCredit();
        }

        double gpa = totalCredits > 0 ? totalPoints / totalCredits : 0;
        gpaLabel.setText(String.format("%.2f", gpa));
    }

    private double gradeToPoint(String g) {
        switch (g.toUpperCase()) {
            case "A+": return 4.0;
            case "A": return 4.0;
            case "A-": return 3.7;
            case "B+": return 3.3;
            case "B": return 3.0;
            case "B-": return 2.7;
            case "C+": return 2.3;
            case "C": return 2.0;
            case "C-": return 1.7;
            case "D": return 1.0;
            default: return 0.0;
        }
    }
}
