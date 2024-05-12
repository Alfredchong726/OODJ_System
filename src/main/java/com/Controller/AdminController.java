package com.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import com.AlertComponent.AlertComponent;
import com.Lecturer.Lecturer;
import com.Student.Student;
import com.example.App;
import com.shared.SharedFunctions;
import com.shared.getData;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class AdminController implements Initializable {
    @FXML
    private AnchorPane adminForm;

    @FXML
    private Button assignRoleBtn;

    @FXML
    private AnchorPane assignRoleForm;

    @FXML
    private Button close;

    @FXML
    private Button homeBtn;

    @FXML
    private AnchorPane informationContainer;

    @FXML
    private Button lecturerAddBtn;

    @FXML
    private DatePicker lecturerBirth;

    @FXML
    private Button lecturerClearBtn;

    @FXML
    private PasswordField lecturerConfirmPassword;

    @FXML
    private AnchorPane lecturerContainer;

    @FXML
    private TextField lecturerEmail;

    @FXML
    private ComboBox<String> lecturerGender;

    @FXML
    private ImageView lecturerImage;

    @FXML
    private Button lecturerInsertBtn;

    @FXML
    private ListView<Lecturer> lecturerListView;

    @FXML
    private TextField lecturerName;

    @FXML
    private PasswordField lecturerPassword;

    @FXML
    private TextField lecturerPhoneNumber;

    @FXML
    private Button leftBtn;

    @FXML
    private Button logout;

    @FXML
    private Button minimize;

    @FXML
    private ListView<Lecturer> projectManagerListView;

    @FXML
    private Button registerBtn;

    @FXML
    private AnchorPane registerForm;

    @FXML
    private ComboBox<String> registerOption;

    @FXML
    private Button rightBtn;

    @FXML
    private Button saveChangesBtn;

    @FXML
    private Button studentAdd;

    @FXML
    private DatePicker studentBirth;

    @FXML
    private Button studentClear;

    @FXML
    private PasswordField studentConfirmPassword;

    @FXML
    private AnchorPane studentContainer;

    @FXML
    private TextField studentEmail;

    @FXML
    private ComboBox<String> studentGender;

    @FXML
    private ImageView studentImage;

    @FXML
    private Button studentInsertBtn;

    @FXML
    private TextField studentName;

    @FXML
    private PasswordField studentPassword;

    @FXML
    private TextField studentPhoneNumber;

    @FXML
    private Label totalLecturer;

    @FXML
    private AreaChart<String, Integer> totalLecturerCharts;

    @FXML
    private Label totalProjectManager;

    @FXML
    private LineChart<String, Integer> totalProjectManagerChart;

    @FXML
    private Label totalStudent;

    @FXML
    private BarChart<String, Integer> totalStudentChart;

    @FXML
    private Label username;

    private SharedFunctions functions = new SharedFunctions();
    private ArrayList<Lecturer> lecturerInfo = functions.getLecturerData();
    private ArrayList<Lecturer> projectManagerInfo = functions.getProjectManagerData();
    private ArrayList<Lecturer> nonProjectManagerInfo = functions.getNonProjectManagerData();
    private ObservableList<Lecturer> nonProjectManagerList = FXCollections.observableArrayList();
    private ObservableList<Lecturer> projectManagerList = FXCollections.observableArrayList();
    private ArrayList<Student> studentInfo = functions.getStudentData();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private Image stuImage;
    private Image lecImage;

    
    // ======================= COMMON FUNCTIONS =========================
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        adminDashboard();
        totalEnrolledLecturerChart();
        totalAssignedProjectManagerChart();
        totalEnrolledStudentChart();
        showListView();
        username.setText(LoginController.getUserName());

        homeBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #1bb0dd, #12d6de)");
        registerOption.getItems().addAll("Lecturer", "Student");
        studentGender.getItems().addAll("Male", "Female");
        lecturerGender.getItems().addAll("Male", "Female");
    }

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) adminForm.getScene().getWindow();
        stage.setIconified(true);
    }
    
    public void logout() throws IOException {
        Alert alert =  new AlertComponent(AlertType.CONFIRMATION, "Confirmation Message", 
            "Are you sure you want to Logout ?").showAlert();
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get().equals(ButtonType.OK)) {
            App.setRoot("Login");
        } else return;
    }

    public void switchScene(ActionEvent event) {
        if (event.getSource() == homeBtn) {
            adminForm.setVisible(true);
            registerForm.setVisible(false);
            assignRoleForm.setVisible(false);

            homeBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #1bb0dd, #12d6de)");
            registerBtn.setStyle("-fx-background-color: transparent");
            assignRoleBtn.setStyle("-fx-background-color: transparent");
        } else if (event.getSource() == registerBtn) {
            adminForm.setVisible(false);
            registerForm.setVisible(true);
            assignRoleForm.setVisible(false);

            homeBtn.setStyle("-fx-background-color: transparent");
            registerBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #1bb0dd, #12d6de)");
            assignRoleBtn.setStyle("-fx-background-color: transparent");
        } else if (event.getSource() == assignRoleBtn) {
            adminForm.setVisible(false);
            registerForm.setVisible(false);
            assignRoleForm.setVisible(true);

            homeBtn.setStyle("-fx-background-color: transparent");
            registerBtn.setStyle("-fx-background-color: transparent");
            assignRoleBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #1bb0dd, #12d6de)");
        }
    }

    public void showListView() {
        projectManagerListView.getItems().clear();
        lecturerListView.getItems().clear();

        for (Lecturer lecturer: nonProjectManagerInfo) {
            nonProjectManagerList.add(lecturer);
        }
        
        for (Lecturer projectManager: projectManagerInfo) {
            projectManagerList.add(projectManager);
        }

        projectManagerListView.setItems(projectManagerList);
        projectManagerListView.setCellFactory(param -> new LecturerListCell());

        lecturerListView.setItems(nonProjectManagerList);
        lecturerListView.setCellFactory(param -> new LecturerListCell());
    }

    // ======================= HOME FUNCTIONS =========================
    public void adminDashboard() {
        int totalEnrolledLecturer = functions.getLecturerData().size();
        int totalEnrolledProjectManager = functions.getProjectManagerData().size();
        int totalEnrolledStudent = functions.getStudentData().size();

        totalLecturer.setText(String.valueOf(totalEnrolledLecturer));
        totalStudent.setText(String.valueOf(totalEnrolledStudent));
        totalProjectManager.setText(String.valueOf(totalEnrolledProjectManager));
    }

    public void totalEnrolledLecturerChart() {
        totalLecturerCharts.getData().clear();
        
        XYChart.Series<String, Integer> chart = new XYChart.Series<>();

        for (Lecturer lecturer: lecturerInfo) {
            chart.getData().add(new XYChart.Data(lecturer.getAppliedDate().format(formatter), lecturerInfo.size()));
        }
        totalLecturerCharts.getData().add(chart);
    }

    public void totalAssignedProjectManagerChart() {
        totalProjectManagerChart.getData().clear();
        
        XYChart.Series<String, Integer> chart = new XYChart.Series<>();

        for (Lecturer projectManager: projectManagerInfo) {
            chart.getData().add(new XYChart.Data(projectManager.getAppliedDate().format(formatter), projectManagerInfo.size()));
        }
        totalProjectManagerChart.getData().add(chart);
    }

    public void totalEnrolledStudentChart() {
        totalStudentChart.getData().clear();

        XYChart.Series<String, Integer> chart = new XYChart.Series<>();

        for (Student student: studentInfo) {
            chart.getData().add(new XYChart.Data(student.getAppliedDate().format(formatter), studentInfo.size()));
        }
        totalStudentChart.getData().add(chart);
    }
    // ======================= REGISTER FUNCTIONS =========================
    public void showInfoContainer() {
        String option = registerOption.getValue();
        informationContainer.setVisible(true);
        if (option.equals("Lecturer")) {
            studentContainer.setVisible(false);
            lecturerContainer.setVisible(true);
        } else if (option.equals("Student")) {
            studentContainer.setVisible(true);
            lecturerContainer.setVisible(false);
        }
    }

    public void insertBtnClicked() {
        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new ExtensionFilter("Image File", "*jpg", "*png"));

        File file = open.showOpenDialog(registerForm.getScene().getWindow());

        URI temp1 = new File(System.getProperty("user.dir")).toURI();
        URI temp2 = new File(file.getAbsolutePath()).toURI();

        if (file != null && registerOption.getValue().equals("Student")) {
            stuImage = new Image(file.toURI().toString(), 160, 180, false, true);
            studentImage.setImage(stuImage);

            getData.path = temp1.relativize(temp2).toString();
        } else if (file != null && registerOption.getValue().equals("Lecturer")) {
            lecImage = new Image(file.toURI().toString(), 160, 180, false, true);
            lecturerImage.setImage(lecImage);

            getData.path = temp1.relativize(temp2).toString();
        }
    }

    public void addLecturerOrStudent() {
        if (registerOption.getValue().equals("Student")) {

            if (studentName.getText().isEmpty() || 
                    studentPassword.getText().isEmpty() ||
                    studentConfirmPassword.getText().isEmpty() ||
                    studentEmail.getText().isEmpty() ||
                    studentPhoneNumber.getText().isEmpty() ||
                    studentBirth.getValue() == null ||
                    studentGender.getValue() == null) {

                Alert alert = new AlertComponent(AlertType.WARNING, "Warning Message", 
                    "Please fill all the fields").showAlert();
                alert.showAndWait();

            } else if (!studentPassword.getText().equals(studentConfirmPassword.getText())) {

                Alert alert = new AlertComponent(AlertType.WARNING, "Warning Message", 
                    "Passwords do not match").showAlert();
                alert.showAndWait();
                
            } else {
                LocalDate currentDate = LocalDate.now();
                Student studentInfo = new Student(null, studentName.getText(), studentPassword.getText(),
                    studentBirth.getValue().toString(), studentGender.getValue().toString(), studentPhoneNumber.getText(),
                        studentEmail.getText(), currentDate, null, null, null, null, null, getData.path);

                Alert alert = new AlertComponent(AlertType.CONFIRMATION, "Confirmation Message", 
                "Are you sure you want to add Student called " + studentInfo.name).showAlert();
    
                Optional<ButtonType> option = alert.showAndWait();
                if (option.get().equals(ButtonType.OK)) {
                    functions.appendStudentInfo(studentInfo);
                } else return;
            }
        } else if (registerOption.getValue().equals("Lecturer")) {

            if (lecturerName.getText().isEmpty() || 
                    lecturerPassword.getText().isEmpty() ||
                    lecturerConfirmPassword.getText().isEmpty() ||
                    lecturerEmail.getText().isEmpty() ||
                    lecturerPhoneNumber.getText().isEmpty() ||
                    lecturerBirth.getValue() == null ||
                    lecturerGender.getValue() == null) {
                Alert alert = new AlertComponent(AlertType.WARNING, "Warning Message", 
                    "Please fill all the fields").showAlert();
                alert.showAndWait();

            } else if (!lecturerPassword.getText().equals(lecturerConfirmPassword.getText())) {

                Alert alert = new AlertComponent(AlertType.WARNING, "Warning Message", 
                    "Passwords do not match").showAlert();
                alert.showAndWait();

            } else {
                LocalDate currentDate = LocalDate.now();

                Lecturer lecturerInfo = new Lecturer(null, lecturerName.getText(), lecturerPassword.getText(),
                     lecturerBirth.getValue().toString(), lecturerGender.getValue().toString(), lecturerPhoneNumber.getText(),
                      lecturerEmail.getText(), currentDate , null, null, getData.path, false);

                Alert alert = new AlertComponent(AlertType.CONFIRMATION, "Confirmation Message", 
                    "Are you sure you want to add Lecturer called " + lecturerInfo.name).showAlert();

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    functions.appendLecturerInfo(lecturerInfo);

                    lecturerName.clear();;
                    lecturerPassword.clear();
                    lecturerConfirmPassword.clear();
                    lecturerEmail.clear();
                    lecturerPhoneNumber.clear();
                    lecturerBirth.setValue(null);
                    lecturerGender.setValue(null);
                } else return;

                
            }
        }
    }
    // ======================= ASSIGN ROLES FUNCTIONS =========================
    public void assignProjectManager() {
        Lecturer selectedLecturer = lecturerListView.getSelectionModel().getSelectedItem();
        nonProjectManagerInfo.remove(selectedLecturer);
        projectManagerInfo.add(selectedLecturer);

        showListView();
    }

    public void unassignProjectManager() {
        Lecturer selectedProjectManager = projectManagerListView.getSelectionModel().getSelectedItem();
        projectManagerInfo.remove(selectedProjectManager);
        nonProjectManagerInfo.add(selectedProjectManager);

        showListView();
    }

    public void saveChanges() {
        Alert alert = new AlertComponent(AlertType.CONFIRMATION, "Confirmation Message", "Are you sure you want to save this changes?").showAlert();
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get().equals(ButtonType.OK)) {
            for (Lecturer lecturer: projectManagerInfo) {
                functions.assignProjectManager(lecturer);
            }
            
            for (Lecturer lecturer: nonProjectManagerInfo) {
                functions.unassignProjectManager(lecturer);
            }
        }
    }
}

class LecturerListCell extends ListCell<Lecturer> {
    
    @Override
    protected void updateItem(Lecturer lecturer, boolean empty) {
        super.updateItem(lecturer, empty);
        
        if (empty || lecturer == null) {
            setText(null);
        } else {
            setText(lecturer.getName());
        }
    }
}
