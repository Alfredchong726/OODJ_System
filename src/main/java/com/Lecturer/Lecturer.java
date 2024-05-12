package com.Lecturer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shared.SharedFunctions;

public class Lecturer {
    public String lecturerId;
    public String name;
    public String password;
    public String birth;
    public String gender;
    public String phoneNumber;
    public String email;
    public LocalDate appliedDate;
    public String presentationRequests;
    public List<String> assignedSupervisee;
    public Map<String, List<String>> availableSlot;
    public String imagePath;
    public boolean isProjectManager;

    public Map<String, List<String>> defaultAvailableSlots = new HashMap<>();

    public SharedFunctions function = new SharedFunctions();

    // Setter
    public Lecturer(String id, String name, String password, String birth, 
        String gender, String phoneNumber, String email, LocalDate appliedDate, List<String> assignedSupervisee, 
        Map<String, List<String>> availableSlot, String imagePath, boolean isProjectManager) {

        this.lecturerId = id;
        this.name = name;
        this.password = password;
        this.birth = birth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.appliedDate = appliedDate;
        this.assignedSupervisee = assignedSupervisee;
        this.availableSlot = availableSlot;
        this.imagePath = imagePath;
        this.isProjectManager = isProjectManager;

        defaultAvailableSlots.put("Monday", new ArrayList<>(Arrays.asList("8am", "10am", "11am", "12pm", "1pm", "2pm", "3pm", "4pm", "5pm")));
        defaultAvailableSlots.put("Tuesday", new ArrayList<>(Arrays.asList("8am", "10am", "11am", "12pm", "1pm", "2pm", "3pm", "4pm", "5pm")));
        defaultAvailableSlots.put("Wednesday", new ArrayList<>(Arrays.asList("8am", "10am", "11am", "12pm", "1pm", "2pm", "3pm", "4pm", "5pm")));
        defaultAvailableSlots.put("Thursday", new ArrayList<>(Arrays.asList("8am", "10am", "11am", "12pm", "1pm", "2pm", "3pm", "4pm", "5pm")));
        defaultAvailableSlots.put("Friday", new ArrayList<>(Arrays.asList("8am", "10am", "11am", "12pm", "1pm", "2pm", "3pm", "4pm", "5pm")));
    }

    // Getter
    public String getLecturerId() {
        return this.lecturerId;
    }

    public String getName() {
        return this.name;
    }

    public String getBirth() {
        return this.birth;
    }

    public String getGender() {
        return this.gender;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public LocalDate getAppliedDate() {
        return this.appliedDate;
    }

    public List<String> getAssignedSupervisee() {
        return this.assignedSupervisee;
    }

    public Map<String, List<String>> getAvailableSlot() {
        return this.availableSlot;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public boolean getIsProjectManager() {
        return this.isProjectManager;
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
        appliedDate + ";" +
        assignedSupervisee + ";" +
        availableSlot + ";" +
        imagePath + ";" +
        isProjectManager + "\n";
    }
}
