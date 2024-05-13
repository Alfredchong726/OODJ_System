package com.Controller;

import java.io.IOException;

import com.Report.Report;
import com.shared.SharedFunctions;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DialogBoxController {
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
    private Report reportData;

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) studentName.getScene().getWindow();
        stage.setIconified(true);
    }

    public void showDialogBox(Report report) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/DialogBox.fxml"));
            Parent root = fxmlLoader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Dialog Box");
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            dialogStage.show();

            studentName.setText(report.getStudentName());
            assesmentTypeBtn.setText(report.getAssesmentType());
            submissionLink.setText(report.getSubmissionLink());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void okBtnClicked() {
        reportData.feedback = feedback.getText();
        reportData.marks = marks.getText();

        functions.updateReport(reportData);
    }
}
