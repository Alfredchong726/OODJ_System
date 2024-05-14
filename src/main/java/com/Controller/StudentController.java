package com.Controller;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;

import com.AlertComponent.AlertComponent;
import com.Lecturer.Lecturer;
import com.Presentation.Presentation;
import com.Report.Report;
import com.Student.Student;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class StudentController implements Initializable {
    @FXML
    private ComboBox<String> assesmentTypeCombobox;

    @FXML
    private Button close;

    @FXML
    private Circle imageCircle;

    @FXML
    private AnchorPane lecturerForm;

    @FXML
    private ComboBox<Lecturer> presentationLecturerCombobox;

    @FXML
    private Button logout;

    @FXML
    private Button minimize;

    @FXML
    private Button presentationAddBtn;

    @FXML
    private Button presentationUpdateBtn;

    @FXML
    private Button presentationRemoveBtn;

    @FXML
    private TableColumn<Presentation, String> presentationAssesmentType;

    @FXML
    private Button presentationClearBtn;

    @FXML
    private TableColumn<Presentation, String> presentationDate;

    @FXML
    private DatePicker presentationDatePicker;

    @FXML
    private TableColumn<Presentation, String> presentationLecturer;

    @FXML
    private AnchorPane presentationRequestForm;

    @FXML
    private TableColumn<Presentation, String> presentationSlot;

    @FXML
    private TableColumn<Presentation, String> presentationStatus;

    @FXML
    private ComboBox<String> presentationTimeSlot;

    @FXML
    private TableColumn<Report, String> reportAssesmentType;

    @FXML
    private Button reportClearBtn;

    @FXML
    private Button reportDeleteBtn;

    @FXML
    private TableColumn<Report, String>reportId;

    @FXML
    private TableColumn<Report, String> reportMark;

    @FXML
    private TableColumn<Report, String> reportStatus;

    @FXML
    private TableColumn<Report, String> reportSubmissionDate;

    @FXML
    private TableColumn<Report, String> reportSubmissionLink;

    @FXML
    private Button reportSubmitBtn;

    @FXML
    private TableColumn<Report, String> reportSupervisor;

    @FXML
    private TableView<Report> reportTableView;

    @FXML
    private Button reportUpdateBtn;

    @FXML
    private TextField searchBar;

    @FXML
    private AnchorPane submissionForm;

    @FXML
    private TextField submissionLink;

    @FXML
    private ComboBox<Lecturer> supervisorCombobox;

    @FXML
    private Label username;

    @FXML
    private Button viewPresentationBtn;

    @FXML
    private AnchorPane viewPresentationForm;

    @FXML
    private TableView<Presentation> viewPresentationTableView;

    @FXML
    private Button viewSubmissionBtn;

    private ObservableList<Presentation> presentationListD = FXCollections.observableArrayList();
    private ObservableList<Lecturer> lecturerListD = FXCollections.observableArrayList();
    private ObservableList<Report> reportListD = FXCollections.observableArrayList();
    private SharedFunctions functions = new SharedFunctions();
    private ArrayList<Presentation> presentationList;
    private ArrayList<Lecturer> lecturerList;
    private ArrayList<Report> reportList;

    private String reportLecturerId;
    private String reportLecturerName;
    private String presentationLecturerId;
    private String presentationLecturerName;
    private String userId;
    private String userName;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private Map<String, List<String>> availableSlot = new HashMap<>();
    
    // ====================== COMMON FUNCTIONS ================================
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        userId = LoginController.getUserId();
        userName = LoginController.getUserName();
        username.setText(LoginController.getUserName());
        lecturerList = functions.getLecturerData();
        for (Lecturer lecturer: lecturerList) {
            lecturerListD.add(lecturer);
        }
        
        supervisorCombobox.setItems(lecturerListD);
        supervisorCombobox.setConverter(new LecturerStringConverter());
        supervisorCombobox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                reportLecturerName = newValue.getName();
                reportLecturerId = newValue.getLecturerId();
            }
        });

        presentationLecturerCombobox.setItems(lecturerListD);
        presentationLecturerCombobox.setConverter(new LecturerStringConverter());
        presentationLecturerCombobox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                presentationLecturerName = newValue.getName();
                presentationLecturerId = newValue.getLecturerId();

                availableSlot = newValue.getAvailableSlot();
                Set<LocalDate> availableDates = new HashSet<>();
                availableSlot.keySet().forEach(day -> {
                    DayOfWeek dayOfWeek = DayOfWeek.valueOf(day.toUpperCase());
                    LocalDate nextDay = LocalDate.now().with(TemporalAdjusters.nextOrSame(dayOfWeek));
                    while (nextDay.isBefore(LocalDate.now().plusYears(1))) {
                        availableDates.add(nextDay);
                        nextDay = nextDay.plusWeeks(1);
                    }
                });
                presentationDatePicker.setDayCellFactory(picker -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        setDisable(empty || !availableDates.contains(date));
                    }
                });
            }
        });

        presentationDatePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                DayOfWeek selectedDay = newValue.getDayOfWeek();
                Map<String, List<String>> availableSlotLowerCase = availableSlot.entrySet().stream()
                    .collect(Collectors.toMap(entry -> entry.getKey().toLowerCase(), Map.Entry::getValue));
                List<String> availableTimeSlots = availableSlotLowerCase.get(selectedDay.toString().toLowerCase());

                for (Presentation presentation : presentationList) {
                    if (presentation.getDateOfPresentation().equals(newValue)) {
                        availableTimeSlots.remove(presentation.getSlot());
                    }
                }
                ObservableList<String> timeSlots = FXCollections.observableArrayList(availableTimeSlots);
                presentationTimeSlot.setItems(timeSlots);
            }
        });

        assesmentTypeCombobox.getItems().addAll("RMCP", "CP1", "CP2", "FYP Project", "Internships", "Investigarion Report");

        viewPresentationBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #1bb0dd, #12d6de)");

        setProfilePicture();
        presentationShowListData();
        reportShowListData();
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
        if (event.getSource() == viewPresentationBtn) {
            presentationRequestForm.setVisible(true);
            submissionForm.setVisible(false);

            viewPresentationBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #1bb0dd, #12d6de)");
            viewSubmissionBtn.setStyle("-fx-background-color: transparent");
        } else if (event.getSource() == viewSubmissionBtn) {
            presentationRequestForm.setVisible(false);
            submissionForm.setVisible(true);

            viewPresentationBtn.setStyle("-fx-background-color: transparent");
            viewSubmissionBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #1bb0dd, #12d6de)");
        }
    }

    // ====================== PRESENTATION REQUESTS ================================
    public void presentationShowListData() {
        presentationListD.clear();
        presentationList = functions.getPresentationById("", "", userId);
        for (Presentation presentation: presentationList) {
            presentationListD.add(presentation);
        }

        presentationLecturer.setCellValueFactory(new PropertyValueFactory<>("supervisorName"));
        presentationAssesmentType.setCellValueFactory(new PropertyValueFactory<>("assesmentType"));
        presentationDate.setCellValueFactory(new PropertyValueFactory<>("dateOfPresentation"));
        presentationSlot.setCellValueFactory(new PropertyValueFactory<>("slot"));
        presentationStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        viewPresentationTableView.setItems(presentationListD);
    }

    public void presentationTableViewSelected() {
        Presentation presentationData = viewPresentationTableView.getSelectionModel().getSelectedItem();
        int num = viewPresentationTableView.getSelectionModel().getFocusedIndex();

        if ((num -1) < -1) {
            return;
        }

        for (Lecturer lecturer: lecturerList) {
            if (lecturer.lecturerId.equals(presentationData.supervisorId)) {
                presentationLecturerCombobox.setValue(lecturer);
            }
        }
        presentationDatePicker.setValue(LocalDate.parse(presentationData.dateOfPresentation));
        presentationTimeSlot.setValue(presentationData.slot);
    }

    public void addPresentation() {
        if (presentationLecturerCombobox.getSelectionModel().getSelectedItem() == null ||
            presentationDatePicker.getValue() == null || 
                presentationTimeSlot.getValue() == null) {
                    Alert alert = new AlertComponent(AlertType.WARNING, "Warning Message", 
                        "Please make sure all the fields are filled").showAlert();
                    alert.showAndWait();
            } else {
                Alert alert = new AlertComponent(AlertType.CONFIRMATION, "Confirmation Message", 
                    "Are you sure you want to request this time slot?").showAlert();

                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    Student studentInfo = functions.getStudentDataById(userId);
                    functions.addPresentation(new Presentation("", 
                        presentationDatePicker.getValue().format(formatter), presentationLecturerId,  presentationLecturerName, 
                            studentInfo.secondMarkerId, studentInfo.secondMarker, "pending", userId, userName, 
                                studentInfo.getAssesmentType(), studentInfo.getGender(), presentationTimeSlot.getValue(), "pending"), userId);
                }
            }
        presentationLecturerCombobox.setValue(null);
        presentationDatePicker.setValue(null);
        presentationTimeSlot.setValue(null);
        presentationShowListData();
    }

    public void clearPresentation() {
        presentationLecturerCombobox.setValue(null);
        presentationDatePicker.setValue(null);
        presentationTimeSlot.setValue(null);
    }

    public void updatePresentation() {
        if (presentationLecturerCombobox.getSelectionModel().getSelectedItem() == null ||
            presentationDatePicker.getValue() == null ||
                presentationTimeSlot.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new AlertComponent(AlertType.WARNING, "Warning Message", 
                "Please make sure all the fields are filled").showAlert();
            alert.showAndWait();
        } else {
            Presentation selectedPresentation = viewPresentationTableView.getSelectionModel().getSelectedItem();
            if (selectedPresentation.status == "approved") {
                Alert alert = new AlertComponent(AlertType.WARNING, "Warning Message", 
                    "This presentation already approved by Supervisor, thus you are not allowed to modify this presentation request anymore").showAlert();
                alert.showAndWait();
                return;
            }
            Alert alert = new AlertComponent(AlertType.CONFIRMATION, "Confirmation Message", 
                "Are you sure you want to update this presentation request?").showAlert();
            Optional<ButtonType> option = alert.showAndWait();

            
            selectedPresentation.supervisorId = presentationLecturerCombobox.getSelectionModel().getSelectedItem().getLecturerId();
            selectedPresentation.supervisorName = presentationLecturerCombobox.getSelectionModel().getSelectedItem().getName();
            selectedPresentation.dateOfPresentation = presentationDatePicker.getValue().format(formatter);
            selectedPresentation.slot = presentationTimeSlot.getValue();

            if (option.get().equals(ButtonType.OK)) {
                functions.updatePresentation(selectedPresentation);
            } else return;
        }
        presentationShowListData();
    }

    public void removePresentation() {
        Presentation selectedPresentation = viewPresentationTableView.getSelectionModel().getSelectedItem();
        if (selectedPresentation.status.equals("approved")) {
            Alert alert = new AlertComponent(AlertType.WARNING, "Warning Message", 
                "This presentation already approved by Supervisor, thus you are not allowed to remove this presentation request anymore").showAlert();
            alert.showAndWait();
        } else {
            Alert alert = new AlertComponent(AlertType.WARNING, "Warning Message", 
                "Are you sure you want to remove this presentation request?").showAlert();
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                functions.removePresentation(selectedPresentation);
            }
        }
        presentationShowListData();
    }

    public void presentationSearch() {
        FilteredList<Presentation> filter = new FilteredList<>(presentationListD, e -> true);
        searchBar.textProperty().addListener((Observable, oldValue, newValue) -> {
            filter.setPredicate(predicatePresentationData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if (predicatePresentationData.getSupervisorName().toString().toLowerCase().contains(searchKey)) {
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
        sortList.comparatorProperty().bind(viewPresentationTableView.comparatorProperty());
        viewPresentationTableView.setItems(sortList);
    }

    // ====================== REPORT SUBMISSION ================================
    public void reportShowListData() {
        reportListD.clear();
        reportList = functions.getReportById("", userId);
        for (Report report: reportList) {
            reportListD.add(report);
        }

        reportId.setCellValueFactory(new PropertyValueFactory<>("reportId"));
        reportSupervisor.setCellValueFactory(new PropertyValueFactory<>("lecturerName"));
        reportAssesmentType.setCellValueFactory(new PropertyValueFactory<>("assesmentType"));
        reportSubmissionDate.setCellValueFactory(new PropertyValueFactory<>("submissionDate"));
        reportSubmissionLink.setCellValueFactory(new PropertyValueFactory<>("submissionLink"));
        reportMark.setCellValueFactory(new PropertyValueFactory<>("marks"));
        reportStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        reportTableView.setItems(reportListD);
    }

    public void reportTableViewSelected() {
        Report reportData = reportTableView.getSelectionModel().getSelectedItem();
        int num = reportTableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        for (Lecturer lecturer: lecturerList) {
            if (lecturer.lecturerId.equals(reportData.lecturerId)) {
                supervisorCombobox.setValue(lecturer);
            }
        }
        assesmentTypeCombobox.setValue(reportData.assesmentType);
        submissionLink.setText(reportData.submissionLink);
    }

    public void submitAssesment() {
        if (supervisorCombobox.getSelectionModel().getSelectedItem() == null ||
            assesmentTypeCombobox.getSelectionModel().getSelectedItem() == null || 
                submissionLink.getText() == null) {
            Alert alert = new AlertComponent(AlertType.WARNING, 
                "Warning Message", "Please make sure all the fields are filled").showAlert();
            alert.showAndWait();
        } else {
            Alert alert = new AlertComponent(AlertType.CONFIRMATION, "Confirmation Message", 
                "Are you sure you want to submit this assesment?").showAlert();
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                functions.addReport(new Report("", userId, userName, reportLecturerId, 
                    reportLecturerName, assesmentTypeCombobox.getSelectionModel().getSelectedItem(),
                    LocalDate.now(), submissionLink.getText(), "", "", "pending"));
            } else return;
        }
        reportShowListData();
    }

    public void updateAssesment() {
        Report selectedReport = reportTableView.getSelectionModel().getSelectedItem();
        if (supervisorCombobox.getSelectionModel().getSelectedItem() == null || 
            assesmentTypeCombobox.getSelectionModel().getSelectedItem() == null || 
                submissionLink.getText() == null) {
            Alert alert = new AlertComponent(AlertType.WARNING, "Warning Message", 
                "Please make sure all the fields are filled").showAlert();
            alert.showAndWait();
        } else if (selectedReport.status.equals("approved")) {
            Alert alert = new AlertComponent(AlertType.WARNING, "Warning Message", 
                "This report already marked by supervisor, thus you are not allow to modify anymore.").showAlert();
            alert.showAndWait();
        } else {
            Alert alert = new AlertComponent(AlertType.CONFIRMATION, "Confirmation Message", 
                "Are you sure you want to update this submission?").showAlert();
            Optional<ButtonType> option = alert.showAndWait();

            selectedReport.lecturerId = supervisorCombobox.getSelectionModel().getSelectedItem().getLecturerId();
            selectedReport.lecturerName = supervisorCombobox.getSelectionModel().getSelectedItem().getName();
            selectedReport.assesmentType = assesmentTypeCombobox.getSelectionModel().getSelectedItem();
            selectedReport.submissionLink = submissionLink.getText();

            if (option.get().equals(ButtonType.OK)) {
                functions.updateReport(selectedReport);
            } else return;
        }
        reportShowListData();
    }

    public void removeAssesment() {
        Report selectedReport = reportTableView.getSelectionModel().getSelectedItem();
        if (selectedReport.status.equals("approved")) {
            Alert alert = new AlertComponent(AlertType.WARNING, "Warning Message", 
                "This report already marked by supervisor, thus you are not allow to modify anymore.").showAlert();
            alert.showAndWait();
        } else {
            Alert alert = new AlertComponent(AlertType.WARNING, "Warning Message", 
                "Are you sure you want to remove this submission?").showAlert();
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                functions.removeReport(selectedReport);
            } else return;
        }
        
    }

    public void clearAssesment() {
        supervisorCombobox.setValue(null);
        assesmentTypeCombobox.setValue(null);
        submissionLink.setText(null);
    }
}
