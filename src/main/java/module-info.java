module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.sql;

    opens com.example to javafx.fxml;
    exports com.example;

    opens com.Controller to javafx.fxml;
    exports com.Controller;

    opens com.Presentation to javafx.base;
}
