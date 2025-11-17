module com.example.gpa__calculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gpa__calculator to javafx.fxml;
    exports com.example.gpa__calculator;
}