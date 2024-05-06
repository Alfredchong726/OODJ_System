package com.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import com.AlertComponent.AlertComponent;
import com.Presentation.Presentation;
import com.example.App;
import com.shared.SharedFunctions;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
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
    private BarChart<?, ?> totalEnrolledChart;

    @FXML
    private AreaChart<?, ?> totalFemaleChart;

    @FXML
    private LineChart<?, ?> totalMaleChart;

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
    private Label username;

    @FXML
    private TextField presentationSearch;

    @FXML
    private TextField searchSecondMarker;

    private ObservableList<Presentation> presentationListD = FXCollections.observableArrayList();
    private ObservableList<Presentation> secondMarkerListD = FXCollections.observableArrayList();
    private SharedFunctions functions = new SharedFunctions();
    private ArrayList<Presentation> presentationList;
    private String userId;

    // ===================== COMMON FUNCTIONS =================
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lecturerShowListData();
        username.setText(LoginController.getUserName());

        lecturerDashboard();
        // lecturerCharts();
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
        Alert alert =  new AlertComponent(AlertType.CONFIRMATION, "Confirmation Message", "Are you sure you want to Logout ?").showAlert();
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get().equals(ButtonType.OK)) {
            App.setRoot("Login");
        } else return;
    }

    public void switchScene(ActionEvent event) {
        if (event.getSource() == homeBtn) {
            homeForm.setVisible(true);
            presentationForm.setVisible(false);
            secondMarkerForm.setVisible(false);

            homeBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #1bb0dd, #12d6de)");
            presentationBtn.setStyle("-fx-background-color: transparent");
            secondMarkerBtn.setStyle("-fx-background-color: transparent");

            lecturerDashboard();
            // lecturerCharts();
        } else if (event.getSource() == presentationBtn) {
            homeForm.setVisible(false);
            presentationForm.setVisible(true);
            secondMarkerForm.setVisible(false);

            homeBtn.setStyle("-fx-background-color: transparent");
            presentationBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #1bb0dd, #12d6de)");
            secondMarkerBtn.setStyle("-fx-background-color: transparent");

            lecturerShowListData();
        } else if (event.getSource() == secondMarkerBtn) {
            homeForm.setVisible(false);
            presentationForm.setVisible(false);
            secondMarkerForm.setVisible(true);

            homeBtn.setStyle("-fx-background-color: transparent");
            presentationBtn.setStyle("-fx-background-color: transparent");
            secondMarkerBtn.setStyle("-fx-background-color: linear-gradient(to bottom right, #1bb0dd, #12d6de)");
            
            secondMarkerShowList();
        }
    }

// ===================== DASHBOARD FUNCTIONS =================
    // TODO: Need to improve after student text file is done
    // This function should be used student info and not presentation data
    public void lecturerDashboard() {
        int totalEnrolled = 0;
        int totalMale = 0;
        int totalFemale = 0;

        for (Presentation presentation : presentationList) {
            totalEnrolled++;
            if (presentation.gender.equals("Male")) {
                totalMale++;
            } else if (presentation.gender.equals("Female")) {
                totalFemale++;
            }
        }
        
        this.totalEnrolled.setText(String.valueOf(totalEnrolled));
        this.totalMale.setText(String.valueOf(totalMale));
        this.totalFemale.setText(String.valueOf(totalFemale));
    }

    public void lecturerCharts() {
        // totalEnrolledChart.getData().clear();
        // totalMaleChart.getData().clear();
        // totalFemaleChart.getData().clear();

        // XYChart.Series chart = new XYChart.Series();
        // XYChart.Series maleChart = new XYChart.Series();
        // XYChart.Series femaleChart = new XYChart.Series();

        // for (Presentation presentation : presentationList) {
        //     chart.getData().add(new XYChart.Data(presentation.dateOfPresentation, totalEnrolled));
        //     if (presentation.gender.equals("Male")) {
        //         chart.getData().add(new XYChart.Data(presentation.dateOfPresentation, ));
        //     } else if (presentation.gender.equals("Female")) {
        //         chart.getData().add(new XYChart.Data(presentation.dateOfPresentation, totalEnrolled));
        //     }
        // }

        // chart.getData().add(new XYChart.Data(dateList, totalEnrolled));
        // maleChart.getData().add(new XYChart.Data(maleChart, totalEnrolled));
        // femaleChart.getData().add(new XYChart.Data(femaleChart, totalEnrolled));

        // totalEnrolledChart.getData().add(chart);
        // totalFemaleChart.getData().add(femaleChart);
        // totalMaleChart.getData().add(maleChart);
    }

// ===================== VIEW PRESENTATION FUNCTIONS =================
    public void lecturerShowListData() {
        userId = LoginController.getUserId();
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
            AlertComponent alert = new AlertComponent(AlertType.ERROR, "Error Message", "This presentation slot already rejected, need to wait student to request again");
            alert.showAlert();
        } 
        Alert alert =  new AlertComponent(AlertType.CONFIRMATION, "Confirmation Message", "Are you sure you want to approve this presentation slot ? You will not allow to change the status anymore").showAlert();
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get().equals(ButtonType.OK)) {
            functions.updatePresentationStatus(selectedPresentation.presentationId, "approved", false);
        } else return;
    }

    public void rejectPresentationSlot() {
        Presentation selectedPresentation = presentationTableView.getSelectionModel().getSelectedItem();

        if (selectedPresentation.status == "approved") {
            AlertComponent alert = new AlertComponent(AlertType.ERROR, "Error Message", "This presentation slot already approved, cannot change the status anymore");
            alert.showAlert();
        } 
        Alert alert =  new AlertComponent(AlertType.CONFIRMATION, "Confirmation Message", "Are you sure you want to reject this presentation slot ?").showAlert();
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
                
                if (predicatePresentationData.getStudentId().toString().contains(searchKey)) {
                    return true;
                } else if (predicatePresentationData.getStudentName().toString().contains(searchKey)) {
                    return true;
                } else if (predicatePresentationData.getGender().toString().contains(searchKey)) {
                    return true;
                } else if (predicatePresentationData.getAssesmentType().toString().contains(searchKey)) {
                    return true;
                } else if (predicatePresentationData.getDateOfPresentation().toString().contains(searchKey)) {
                    return true;
                } else if (predicatePresentationData.getSlot().toString().contains(searchKey)) {
                    return true;
                } else if (predicatePresentationData.getStatus().toString().contains(searchKey)) {
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
            AlertComponent alert = new AlertComponent(AlertType.ERROR, "Error Message", "This presentation slot already rejected, need to wait student to request again");
            alert.showAlert();
        } 
        Alert alert =  new AlertComponent(AlertType.CONFIRMATION, "Confirmation Message", "Are you sure you want to approve this presentation slot ? You will not allow to change the status anymore").showAlert();
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get().equals(ButtonType.OK)) {
            functions.updatePresentationStatus(selectedPresentation.presentationId, "approved", true);
        } else return;
    }

    public void rejectPresentationSlotAsSecondMarker() {
        Presentation selectedPresentation = presentationTableView.getSelectionModel().getSelectedItem();

        if (selectedPresentation.status == "approved") {
            AlertComponent alert = new AlertComponent(AlertType.ERROR, "Error Message", "This presentation slot already approved, cannot change the status anymore");
            alert.showAlert();
        } 
        Alert alert =  new AlertComponent(AlertType.CONFIRMATION, "Confirmation Message", "Are you sure you want to reject this presentation slot ?").showAlert();
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get().equals(ButtonType.OK)) {
            functions.updatePresentationStatus(selectedPresentation.presentationId, "rejected", false);
        } else return;
    }

    public void presentationSearchAsSecondMarker() {
        FilteredList<Presentation> filter = new FilteredList<>(secondMarkerListD, e -> true);
        presentationSearch.textProperty().addListener((Observable, oldValue, newValue) -> {
            filter.setPredicate(predicatePresentationData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String searchKey = newValue.toLowerCase();
                
                if (predicatePresentationData.getStudentId().toString().contains(searchKey)) {
                    return true;
                } else if (predicatePresentationData.getStudentName().toString().contains(searchKey)) {
                    return true;
                } else if (predicatePresentationData.getGender().toString().contains(searchKey)) {
                    return true;
                } else if (predicatePresentationData.getAssesmentType().toString().contains(searchKey)) {
                    return true;
                } else if (predicatePresentationData.getDateOfPresentation().toString().contains(searchKey)) {
                    return true;
                } else if (predicatePresentationData.getSlot().toString().contains(searchKey)) {
                    return true;
                } else if (predicatePresentationData.getStatus().toString().contains(searchKey)) {
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
}
