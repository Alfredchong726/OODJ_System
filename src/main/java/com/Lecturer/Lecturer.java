package com.Lecturer;

import java.util.List;
import java.util.Map;

import com.Presentation.Presentation;
import com.shared.SharedFunctions;

public class Lecturer {
    public String lecturerId;
    public String name;
    private String password;
    public String birth;
    public String gender;
    public String phoneNumber;
    public String email;
    public String role;
    public String presentationRequests;
    public List<String> assignedSupervisee;
    public Map<String, List<String>> availableSlot;

    public SharedFunctions function = new SharedFunctions();

    public Lecturer(String id, String name, String password, String birth, 
        String gender, String phoneNumber, String email, String role, 
        List<String> assignedSupervisee, Map<String, List<String>> availableSlot) {

        this.lecturerId = id;
        this.name = name;
        this.password = password;
        this.birth = birth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.role = role;
        this.assignedSupervisee = assignedSupervisee;
        this.availableSlot = availableSlot;
    }

    public List<String> viewAssignedSupervisees() {
        return this.assignedSupervisee;
    }

    public List<Presentation> viewPresentationRequests() {
        List<Presentation> info = function.getPresentationById("L01", "");
        return info;
    }

    public void viewSecondMarkerAcceptance() {

    }

    public void evaluateReportFeedback() {

    }

    public void viewSuperviseList() {

    }

    public void viewDashboardDetails() {

    }

    public void confirmDatePresentationOrSlot(String presentationId) {
        function.updatePresentationStatus(presentationId, "apprived");
    }
    @Override
        public String toString() {
            return lecturerId + ";" +
            name + ";" +
            password + ";" +
            birth + ";" +
            gender + ";" +
            phoneNumber + ";" +
            email + ";" +
            role + ";" +
            assignedSupervisee + ";" +
            availableSlot;
        }
}
