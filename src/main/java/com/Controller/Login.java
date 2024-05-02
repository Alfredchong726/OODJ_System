package com.Controller;

import java.io.IOException;

import com.example.App;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login {
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

    public void close() {
        System.exit(0);
    }

    public void login() {
        String role;
        if (ProjectManager.isSelected()) {
            role = "ProjectManager";
        } else if (Lecturer.isSelected()) {
            role = "Lecturer";
        } else if (Student.isSelected()) {
            role = "Student";
        } else if (Admin.isSelected()) {
            role = "Admin";
        } else {
            System.out.println("No role is selected");
        }
    }
}
