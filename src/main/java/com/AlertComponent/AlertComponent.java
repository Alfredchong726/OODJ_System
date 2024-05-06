package com.AlertComponent;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertComponent {
    public Alert alert;
    AlertType alertType;
    String title;
    String content;

    public AlertComponent(AlertType alertType, String title, String content) {
        this.alertType = alertType;
        this.title = title;
        this.content = content;
    }

    public Alert showAlert() {
        alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        return alert;
    }
}
