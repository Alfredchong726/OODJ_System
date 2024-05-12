package com.Controller;

import java.io.IOException;

import com.AlertComponent.AlertComponent;
import com.example.App;
import com.shared.SharedFunctions;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LoginController {
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
    public static String imagePath;

    public void close() {
        System.exit(0);
    }

    public void login() throws IOException {
        String name = username.getText();
        String pass = password.getText();

        String[] userInfo = functions.validatePassword(name, pass);
        if (userInfo != null) {
            String path = functions.getProfileImageById(userInfo[0], userInfo[1]);
            Alert alert = new AlertComponent(AlertType.INFORMATION, "Information Message", 
                "Successfully Login").showAlert();
            alert.showAndWait();

            setUserId(userInfo[1]);
            setUserName(name);
            setImagePath(path);
            
            App.setRoot(userInfo[0]);
        } else {
            Alert alert = new AlertComponent(AlertType.ERROR, "Error Message", 
                "Please check your username and password").showAlert();
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

    private void setImagePath(String path) {
        imagePath = path;
    }

    public static String getUserId() {
        return userId;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getImagePath() {
        return imagePath;
    }
}
