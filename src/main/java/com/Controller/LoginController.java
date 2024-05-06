package com.Controller;

import java.io.IOException;

import com.AlertComponent.AlertComponent;
import com.example.App;
import com.shared.SharedFunctions;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LoginController {
    @FXML
    private CheckBox Admin;

    @FXML
    private CheckBox Lecturer;

    @FXML
    private CheckBox ProjectManager;

    @FXML
    private CheckBox Student;

    @FXML
    private Button close;

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private ToggleButton toogleBtn;

    private SharedFunctions functions = new SharedFunctions();
    public static String userId;
    public static String userName;

    public void close() {
        System.exit(0);
    }

    public void login() throws IOException {
        String role = "";
        String name = username.getText();
        String pass = password.getText();
        if (ProjectManager.isSelected()) {
            role = "ProjectManager";
        } else if (Lecturer.isSelected()) {
            role = "Lecturer";
        } else if (Student.isSelected()) {
            role = "Student";
        } else if (Admin.isSelected()) {
            role = "Admin";
        } else {
            Alert alert = new AlertComponent(AlertType.ERROR, "Error Message", "Please choose what role you want to login").showAlert();
            alert.showAndWait();
            return;
        }

        String userId = functions.validatePassword(role, name, pass);
        if (userId != null) {
            Alert alert = new AlertComponent(AlertType.INFORMATION, "Information Message", "Successfully Login").showAlert();
            alert.showAndWait();

            setUserId(userId);
            setUserName(name);
            
            App.setRoot("Lecturer");
        } else {
            Alert alert = new AlertComponent(AlertType.ERROR, "Error Message", "Please check your username and password").showAlert();
            alert.showAndWait();
        }
    }

    public void toggleShowPassword() {
        if (toogleBtn.isSelected()) {
            password.setPromptText(password.getText());
            password.setText("");
        } else {
            password.setText(password.getPromptText());
            password.setPromptText("");
        }
    }

    private void setUserId(String id) {
        userId = id;
    }

    private void setUserName(String username) {
        userName = username;
    }

    public static String getUserId() {
        return userId;
    }

    public static String getUserName() {
        return userName;
    }
}
