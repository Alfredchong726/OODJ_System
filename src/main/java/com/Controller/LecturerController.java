package com.Controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import com.AlertComponent.AlertComponent;
import com.Presentation.Presentation;
import com.Student.Student;
import com.Lecturer.Lecturer;
import com.Report.Report;
import com.example.App;
import com.shared.SharedFunctions;
import com.shared.LecturerStringConverter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class LecturerController implements Initializable {
    @FXML
    private Button close;

    @FXML
    private Button homeBtn;

    @FXML
    private AnchorPane homeForm;

    @FXML
    private AnchorPane lecturerForm;

    @FXML
    private AnchorPane secondMarkerForm;

    @FXML
    private AnchorPane studentListForm;

    @FXML
    private AnchorPane reportForm;

    @FXML
    private Button logout;

    @FXML
    private Button minimize;

    @FXML
    private Button presentationBtn;

    @FXML
    private Button secondMarkerBtn;

    @FXML
    private Button clearFilterBtn;

    @FXML
    private Button approveBtn;

    @FXML
    private Button rejectBtn;

    @FXML
    private AnchorPane presentationForm;

    @FXML
    private Button clearFilterSecondMarker;

    @FXML
    private Button studentListBtn;
    
    @FXML
    private Button reportBtn;

    @FXML
    private Button saveChangesBtn;

    @FXML
    private TextField studentNameTextField;

    @FXML
    private DatePicker birthDatePicker;

    @FXML
    private ComboBox<String> genderCombobox;

    @FXML
    private DatePicker appliedDatePicker;

    @FXML
    private ComboBox<String> assesmentTypeCombobox;

    @FXML
    private ComboBox<Lecturer> supervisorCombobox;

    @FXML
    private ComboBox<Lecturer> secondMarkerCombobox;

    @FXML
    private TextField reportSearchBar;

    @FXML
    private TableView<Report> reportTableView;

    @FXML
    private TableColumn<Report, String> reportIdColumn;

    @FXML
    private TableColumn<Report, String> reportStudentName;

    @FXML
    private TableColumn<Report, String> reportSupervisoName;

    @FXML
    private TableColumn<Report, String> reportAssesmentType;

    @FXML
    private TableColumn<Report, LocalDate> reportSubmissionDate;

    @FXML
    private TableColumn<Report, String> reportStatus;

    @FXML
    private Button approveSecondMarker;

    @FXML
    private Button rejectSecondMarker;

    @FXML
    private Label totalEnrolled;

    @FXML
    private Label totalFemale;

    @FXML
    private Label totalMale;

    @FXML
    private BarChart<String, Integer> totalEnrolledChart;

    @FXML
    private AreaChart<String, Integer> totalFemaleChart;

    @FXML
    private LineChart<String, Integer> totalMaleChart;

    @FXML
    private TableColumn<Presentation, String> presentationAssesmentType;

    @FXML
    private TableColumn<Presentation, String> presentationDate;

    @FXML
    private TableColumn<Presentation, String> presentationGender;

    @FXML
    private TableColumn<Presentation, String> presentationSlot;

    @FXML
    private TableColumn<Presentation, String> presentationStatus;

    @FXML
    private TableColumn<Presentation, String> presentationStudentId;

    @FXML
    private TableColumn<Presentation, String> presentationStudentName;

    @FXML
    private TableView<Presentation> presentationTableView;
    
    @FXML
    private TableColumn<Presentation, String> secondMarkerAssesmentType;

    @FXML
    private TableColumn<Presentation, String> secondMarkerDate;

    @FXML
    private TableColumn<Presentation, String> secondMarkerGender;

    @FXML
    private TableColumn<Presentation, String> secondMarkerSlot;

    @FXML
    private TableColumn<Presentation, String> secondMarkerStatus;

    @FXML
    private TableColumn<Presentation, String> secondMarkerStudentId;

    @FXML
    private TableColumn<Presentation, String> secondMarkerStudentName;

    @FXML
    private TableView<Presentation> secondMarkerTableView;

    @FXML
    private TableView<Student> studentListTableView;

    @FXML
    private TableColumn<Student, String> studentNameColumn;

    @FXML
    private TableColumn<Student, String>birthColumn;

    @FXML
    private TableColumn<Student, String> genderColumn;

    @FXML
    private TableColumn<Student, LocalDate> appliedDateColumn;

    @FXML
    private TableColumn<Student, String> assesmentTypeColumn;

    @FXML
    private TableColumn<Student, String> supervisorColumn;

    @FXML
    private TableColumn<Student, String> secondMarkerColumn;

    @FXML
    private Label username;

    @FXML
    private TextField presentationSearch;

    @FXML
    private TextField searchSecondMarker;

    @FXML
    private Circle imageCircle;

    private ObservableList<Presentation> presentationListD = FXCollections.observableArrayList();
    private ObservableList<Presentation> secondMarkerListD = FXCollections.observableArrayList();
    private ObservableList<Lecturer> lecturerListD = FXCollections.observableArrayList();
    private ObservableList<Student> studentListD = FXCollections.observableArrayList();
    private ObservableList<Report> reportListD = FXCollections.observableArrayList();
    private SharedFunctions functions = new SharedFunctions();
    private ArrayList<Presentation> presentationList;
    private ArrayList<Lecturer> lecturerList;
    private ArrayList<Student> studentList;
    private ArrayList<Report> reportList;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private String userId;

    // ===================== COMMON FUNCTIONS =================
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        userId = LoginController.getUserId();
        username.setText(LoginController.getUserName());
        lecturerList = functions.getLecturerData();
        Lecturer lecInfo = functions.getLecturerDataById(userId);
        if (!lecInfo.isProjectManager) {
            studentListBtn.setVisible(false);
            reportBtn.setVisible(false);
        }

        for (Lecturer lecturer: lecturerList) {
            lecturerListD.add(lecturer);
        }

        supervisorCombobox.setItems(lecturerListD);
        supervisorCombobox.setConverter(new LecturerStringConverter());

        secondMarkerCombobox.setItems(lecturerListD);
        secondMarkerCombobox.setConverter(new LecturerStringConverter());

        genderCombobox.getItems().addAll("Male", "Female");
        assesmentTypeCombobox.getItems().addAll("RMCP", "CP1", "CP2", "FYP Project", 
            "Internships", "Investigarion Report");

        lecturerShowListData();
        setProfilePicture();
        lecturerDashboard();
        lecturerCharts();
        studentShowListData();
        reportShowListData();

        homeBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #1bb0dd, #12d6de)");
        presentationBtn.setStyle("-fx-background-color: transparent");
    }

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) lecturerForm.getScene().getWindow();
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

    public void setProfilePicture() {
        String uri = "file:" + System.getProperty("user.dir") + "/" + LoginController.getImagePath();
        Image image = new Image(uri, 35, 35, false, true);
        imageCircle.setFill(new ImagePattern(image));
    }

    public void switchScene(ActionEvent event) {
        if (event.getSource() == homeBtn) {
            homeForm.setVisible(true);
            presentationForm.setVisible(false);
            secondMarkerForm.setVisible(false);
            studentListForm.setVisible(false);
            reportForm.setVisible(false);

            homeBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #1bb0dd, #12d6de)");
            presentationBtn.setStyle("-fx-background-color: transparent");
            secondMarkerBtn.setStyle("-fx-background-color: transparent");
            studentListBtn.setStyle("-fx-background-color: transparent");
            reportBtn.setStyle("-fx-background-color: transparent");

            lecturerDashboard();
            lecturerCharts();
        } else if (event.getSource() == presentationBtn) {
            homeForm.setVisible(false);
            presentationForm.setVisible(true);
            secondMarkerForm.setVisible(false);
            studentListForm.setVisible(false);
            reportForm.setVisible(false);

            homeBtn.setStyle("-fx-background-color: transparent");
            presentationBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #1bb0dd, #12d6de)");
            secondMarkerBtn.setStyle("-fx-background-color: transparent");
            studentListBtn.setStyle("-fx-background-color: transparent");
            reportBtn.setStyle("-fx-background-color: transparent");

            lecturerShowListData();
        } else if (event.getSource() == secondMarkerBtn) {
            homeForm.setVisible(false);
            presentationForm.setVisible(false);
            secondMarkerForm.setVisible(true);
            studentListForm.setVisible(false);
            reportForm.setVisible(false);

            homeBtn.setStyle("-fx-background-color: transparent");
            presentationBtn.setStyle("-fx-background-color: transparent");
            secondMarkerBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #1bb0dd, #12d6de)");
            studentListBtn.setStyle("-fx-background-color: transparent");
            reportBtn.setStyle("-fx-background-color: transparent");
            
            secondMarkerShowList();
        } else if (event.getSource() == studentListBtn) {
            homeForm.setVisible(false);
            presentationForm.setVisible(false);
            secondMarkerForm.setVisible(false);
            studentListForm.setVisible(true);
            reportForm.setVisible(false);

            homeBtn.setStyle("-fx-background-color: transparent");
            presentationBtn.setStyle("-fx-background-color: transparent");
            secondMarkerBtn.setStyle("-fx-background-color: transparent");
            studentListBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #1bb0dd, #12d6de)");
            reportBtn.setStyle("-fx-background-color: transparent");

            studentShowListData();
        } else if (event.getSource() == reportBtn) {
            homeForm.setVisible(false);
            presentationForm.setVisible(false);
            secondMarkerForm.setVisible(false);
            studentListForm.setVisible(false);
            reportForm.setVisible(true);

            homeBtn.setStyle("-fx-background-color: transparent");
            presentationBtn.setStyle("-fx-background-color: transparent");
            secondMarkerBtn.setStyle("-fx-background-color: transparent");
            studentListBtn.setStyle("-fx-background-color: transparent");
            reportBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #1bb0dd, #12d6de)");
        }
    }

    // ===================== DASHBOARD FUNCTIONS =================
    public void lecturerDashboard() {
        int totalEnrolled = 0;
        int totalMale = 0;
        int totalFemale = 0;

        studentList = functions.getStudentData();

        for (Student student : studentList) {
            totalEnrolled++;
            if (student.gender.equals("Male")) {
                totalMale++;
            } else if (student.gender.equals("Female")) {
                totalFemale++;
            }
        }
        
        this.totalEnrolled.setText(String.valueOf(totalEnrolled));
        this.totalMale.setText(String.valueOf(totalMale));
        this.totalFemale.setText(String.valueOf(totalFemale));
    }

    public void lecturerCharts() {
        int totalEnrolled = 0;
        int totalMale = 0;
        int totalFemale = 0;

        totalEnrolledChart.getData().clear();
        totalMaleChart.getData().clear();
        totalFemaleChart.getData().clear();

        XYChart.Series<String, Integer> chart = new XYChart.Series<>();
        XYChart.Series<String, Integer> maleChart = new XYChart.Series<>();
        XYChart.Series<String, Integer> femaleChart = new XYChart.Series<>();

        for (Student student : studentList) {
            chart.getData().add(new XYChart.Data<>(student.appliedDate.format(formatter), totalEnrolled));
            totalEnrolled ++;
            if (student.gender.equals("Male")) {
                maleChart.getData().add(new XYChart.Data<>(student.appliedDate.format(formatter), totalMale));
                totalMale ++;
            } else if (student.gender.equals("Female")) {
                femaleChart.getData().add(new XYChart.Data<>(student.appliedDate.format(formatter), totalFemale));
                totalFemale++;
            }
        }

        totalEnrolledChart.getData().add(chart);
        totalFemaleChart.getData().add(femaleChart);
        totalMaleChart.getData().add(maleChart);
    }

    // ===================== VIEW PRESENTATION FUNCTIONS =================
    public void lecturerShowListData() {
        presentationList = functions.getPresentationById(userId, "", "");

        presentationListD.clear();

        for (Presentation presentation : presentationList) {
            presentationListD.add(presentation);
        }
        
        presentationStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        presentationStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        presentationGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        presentationAssesmentType.setCellValueFactory(new PropertyValueFactory<>("assesmentType"));
        presentationDate.setCellValueFactory(new PropertyValueFactory<>("dateOfPresentation"));
        presentationSlot.setCellValueFactory(new PropertyValueFactory<>("slot"));
        presentationStatus.setCellValueFactory(new PropertyValueFactory<>("status"));


        presentationTableView.setItems(presentationListD);

        presentationStatus.setCellFactory(column -> {
            return new TableCell<Presentation, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    if (empty || item == null) {
                        setText(null);
                        setEditable(false);
                    } else {
                        Presentation presentation = getTableView().getItems().get(getIndex());

                        if (presentation.getSecondMarkerAcceptance().equals("approved")) {
                            setDisable(true);
                        } else {
                            setDisable(false);
                        }

                        setText(item);
                    }
                }
            };
        });
    }

    public void clearFilter() {
        lecturerShowListData();
        presentationSearch.clear();
    }

    public void approvePresentationSlot() {
        Presentation selectedPresentation = presentationTableView.getSelectionModel().getSelectedItem();

        if (selectedPresentation.status == "rejected") {
            AlertComponent alert = new AlertComponent(AlertType.ERROR, "Error Message", 
                "This presentation slot already rejected, need to wait student to request again");
            alert.showAlert();
        } 
        Alert alert =  new AlertComponent(AlertType.CONFIRMATION, "Confirmation Message", 
            "Are you sure you want to approve this presentation slot ? You will not allow to change the status anymore").showAlert();
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get().equals(ButtonType.OK)) {
            functions.updatePresentationStatus(selectedPresentation.presentationId, "approved", false);
        } else return;
    }

    public void rejectPresentationSlot() {
        Presentation selectedPresentation = presentationTableView.getSelectionModel().getSelectedItem();

        if (selectedPresentation.status == "approved") {
            AlertComponent alert = new AlertComponent(AlertType.ERROR, "Error Message", 
                "This presentation slot already approved, cannot change the status anymore");
            alert.showAlert();
        } 
        Alert alert =  new AlertComponent(AlertType.CONFIRMATION, "Confirmation Message", 
            "Are you sure you want to reject this presentation slot ?").showAlert();
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get().equals(ButtonType.OK)) {
            functions.updatePresentationStatus(selectedPresentation.presentationId, "rejected", false);
        } else return;
    }

    public void presentationSearch() {
        FilteredList<Presentation> filter = new FilteredList<>(presentationListD, e -> true);
        presentationSearch.textProperty().addListener((Observable, oldValue, newValue) -> {
            filter.setPredicate(predicatePresentationData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();
                
                if (predicatePresentationData.getStudentId().toString().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicatePresentationData.getStudentName().toString().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicatePresentationData.getGender().toString().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicatePresentationData.getAssesmentType().toString().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicatePresentationData.getDateOfPresentation().toString().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicatePresentationData.getSlot().toString().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicatePresentationData.getStatus().toString().toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<Presentation> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(presentationTableView.comparatorProperty());
        presentationTableView.setItems(sortList);
    }

    // ======================= Second Marker =======================
    public void secondMarkerShowList() {
        userId = LoginController.getUserId();
        presentationList = functions.getPresentationById("", userId, "");

        secondMarkerListD.clear();

        for (Presentation presentation : presentationList) {
            secondMarkerListD.add(presentation);
        }
        
        secondMarkerStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        secondMarkerStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        secondMarkerGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        secondMarkerAssesmentType.setCellValueFactory(new PropertyValueFactory<>("assesmentType"));
        secondMarkerDate.setCellValueFactory(new PropertyValueFactory<>("dateOfPresentation"));
        secondMarkerSlot.setCellValueFactory(new PropertyValueFactory<>("slot"));
        secondMarkerStatus.setCellValueFactory(new PropertyValueFactory<>("status"));


        secondMarkerTableView.setItems(secondMarkerListD);

        secondMarkerStatus.setCellFactory(column -> {
            return new TableCell<Presentation, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    if (empty || item == null) {
                        setText(null);
                        setEditable(false);
                    } else {
                        Presentation presentation = getTableView().getItems().get(getIndex());

                        if (presentation.getStatus().equals("approved")) {
                            setDisable(true);
                        } else {
                            setDisable(false);
                        }

                        setText(item);
                    }
                }
            };
        });
    }

    public void clearFilter2() {
        secondMarkerShowList();
        searchSecondMarker.clear();
    }

    public void approvePresentationSlotAsSecondMarker() {
        Presentation selectedPresentation = secondMarkerTableView.getSelectionModel().getSelectedItem();

        if (selectedPresentation.status == "rejected") {
            AlertComponent alert = new AlertComponent(AlertType.ERROR, "Error Message", 
                "This presentation slot already rejected, need to wait student to request again");
            alert.showAlert();
        } 
        Alert alert =  new AlertComponent(AlertType.CONFIRMATION, "Confirmation Message", 
            "Are you sure you want to approve this presentation slot ? You will not allow to change the status anymore").showAlert();
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get().equals(ButtonType.OK)) {
            functions.updatePresentationStatus(selectedPresentation.presentationId, "approved", true);
        } else return;
    }

    public void rejectPresentationSlotAsSecondMarker() {
        Presentation selectedPresentation = presentationTableView.getSelectionModel().getSelectedItem();

        if (selectedPresentation.status == "approved") {
            AlertComponent alert = new AlertComponent(AlertType.ERROR, "Error Message", 
                "This presentation slot already approved, cannot change the status anymore");
            alert.showAlert();
        } 
        Alert alert =  new AlertComponent(AlertType.CONFIRMATION, "Confirmation Message", 
            "Are you sure you want to reject this presentation slot ?").showAlert();
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get().equals(ButtonType.OK)) {
            functions.updatePresentationStatus(selectedPresentation.presentationId, "rejected", false);
        } else return;
    }

    public void presentationSearchAsSecondMarker() {
        FilteredList<Presentation> filter = new FilteredList<>(secondMarkerListD, e -> true);
        searchSecondMarker.textProperty().addListener((Observable, oldValue, newValue) -> {
            filter.setPredicate(predicatePresentationData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();
                
                if (predicatePresentationData.getStudentId().toString().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicatePresentationData.getStudentName().toString().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicatePresentationData.getGender().toString().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicatePresentationData.getAssesmentType().toString().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicatePresentationData.getDateOfPresentation().toString().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicatePresentationData.getSlot().toString().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicatePresentationData.getStatus().toString().toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });

        SortedList<Presentation> sortList = new SortedList<>(filter);
        sortList.comparatorProperty().bind(secondMarkerTableView.comparatorProperty());
        secondMarkerTableView.setItems(sortList);
    }

    // ===================== STUDENT LIST FUNCTIONS =================
    public void studentShowListData() {
        studentListD.clear();

        for (Student student : studentList) {
            studentListD.add(student);
        }
        
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        birthColumn.setCellValueFactory(new PropertyValueFactory<>("birth"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        appliedDateColumn.setCellValueFactory(new PropertyValueFactory<>("appliedDate"));
        assesmentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("assesmentType"));
        supervisorColumn.setCellValueFactory(new PropertyValueFactory<>("supervisor"));
        secondMarkerColumn.setCellValueFactory(new PropertyValueFactory<>("secondMarker"));


        studentListTableView.setItems(studentListD);
    }

    public void studentListTableViewSelected() {
        Student selectedStudent = studentListTableView.getSelectionModel().getSelectedItem();

        if (selectedStudent.supervisorId != null) {
            for (Lecturer lecturer: lecturerList) {
                if (selectedStudent.supervisorId.equals(lecturer.lecturerId)) {
                    supervisorCombobox.setValue(lecturer);
                }
            }
        }
        if (selectedStudent.secondMarkerId != null) {
            for (Lecturer lecturer: lecturerList) {
                if (selectedStudent.supervisorId.equals(lecturer.lecturerId)) {
                    secondMarkerCombobox.setValue(lecturer);
                }
            }
        }

        studentNameTextField.setText(selectedStudent.name);
        birthDatePicker.setValue(LocalDate.parse(selectedStudent.birth));
        genderCombobox.setValue(selectedStudent.gender);
        appliedDatePicker.setValue(selectedStudent.appliedDate);
        assesmentTypeCombobox.setValue(selectedStudent.assesmentType);
    }

    public void saveChanges() {
        if (assesmentTypeCombobox.getValue() == null || supervisorCombobox.getValue() == null || secondMarkerCombobox.getValue() == null) {
            Alert alert = new AlertComponent(AlertType.WARNING, "Warning Message", 
                "Please make sure all fields are filled").showAlert();
            alert.showAndWait();
        } else {
            Alert alert = new AlertComponent(AlertType.CONFIRMATION, "Confirmation Message", 
                "Are you sure you want to save this changes?").showAlert();
            
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                Student selectedStudent = studentListTableView.getSelectionModel().getSelectedItem();
                selectedStudent.assesmentType = assesmentTypeCombobox.getValue();
                selectedStudent.supervisorId = supervisorCombobox.getValue().lecturerId;
                selectedStudent.supervisor = supervisorCombobox.getValue().name;
                selectedStudent.secondMarkerId = secondMarkerCombobox.getValue().lecturerId;
                selectedStudent.secondMarker = secondMarkerCombobox.getValue().name;
                functions.updateStudent(selectedStudent);
            }
        }
    }

    // ===================== VIEW REPORT FUNCTIONS =================
    public void reportShowListData() {
        reportListD.clear();
        reportList = functions.getReportData();
        for (Report report : reportList) {
            reportListD.add(report);
        }

        reportIdColumn.setCellValueFactory(new PropertyValueFactory<>("reportId"));
        reportStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        reportSupervisoName.setCellValueFactory(new PropertyValueFactory<>("lecturerName"));
        reportAssesmentType.setCellValueFactory(new PropertyValueFactory<>("assesmentType"));
        reportSubmissionDate.setCellValueFactory(new PropertyValueFactory<>("submissionDate"));
        reportStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        reportTableView.setItems(reportListD);
    }

    public void reportSearch() {
        FilteredList<Report> filter = new FilteredList<>(reportListD, e -> true);
        reportSearchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate(predicateReportData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicateReportData.getReportId().toString().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateReportData.getStudentName().toString().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateReportData.getLecturerName().toString().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateReportData.getAssesmentType().toString().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateReportData.getSubmissionDate().toString().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateReportData.getStatus().toString().toLowerCase().contains(searchKey)) {
                    return true;
                } else {
                    return false;
                }
            });
        });
    }
}
