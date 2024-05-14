package com.Controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.AlertComponent.AlertComponent;
import com.Report.Report;
import com.shared.SharedFunctions;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DialogBoxController implements Initializable {
     @FXML
    private AnchorPane adminForm;

    @FXML
    private TextField assesmentTypeBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private Button close;

    @FXML
    private TextArea feedback;

    @FXML
    private TextField marks;

    @FXML
    private Button minimize;

    @FXML
    private Button okBtn;

    @FXML
    private TextField studentName;

    @FXML
    private TextField submissionLink;

    private SharedFunctions functions = new SharedFunctions();
    private String reportId;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }

    public void close() {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    public void minimize() {
        Stage stage = (Stage) studentName.getScene().getWindow();
        stage.setIconified(true);
    }

    public void setReport(Report report) {
        reportId = report.reportId;
        studentName.setText(report.studentName);
        assesmentTypeBtn.setText(report.assesmentType);
        submissionLink.setText(report.submissionLink);
    }

    public void okBtnClicked() {
        Alert alert = new AlertComponent(AlertType.CONFIRMATION, "Confirmation Message", 
            "Are you sure you want to set student " + studentName.getText() + "'s feedback and marks to :" + "\n" +
            "Feedback :" + feedback.getText() + "\n" + "Marks :" + marks.getText()).showAlert();

        Optional<ButtonType> option = alert.showAndWait();

        if (option.get().equals(ButtonType.OK)) {
            functions.addFeedbackNMarkForReport(reportId, feedback.getText(), marks.getText());
            close();
        }

        
    }
}
