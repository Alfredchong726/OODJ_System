package com.Student;

import java.time.LocalDate;

public class Student {
    public String studentId;
    public String name;
    public String password;
    public String birth;
    public String gender;
    public String phoneNumber;
    public String email;
    public LocalDate appliedDate;
    public String assesmentType;
    public String supervisorId;
    public String supervisor;
    public String secondMarkerId;
    public String secondMarker;
    public String imagePath;

    public Student(String studentId, String name, String password, String birth, String gender, String phoneNumber, 
        String email, LocalDate appliedDate, String assesmentType, String supervisorId, String supervisor, String secondMarkerId, 
            String secondMarker, String imagePath) {
        this.studentId = studentId;
        this.name = name;
        this.password =  password;
        this.birth = birth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.appliedDate = appliedDate;
        this.assesmentType = assesmentType;
        this.supervisorId = supervisorId;
        this.supervisor = supervisor;
        this.secondMarkerId = secondMarkerId;
        this.secondMarker = secondMarker;
        this.imagePath = imagePath;
    }

    // Getter
    public String getStudentId() {
        return this.studentId;
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

    public String getAssesmentType() {
        return this.assesmentType;
    }

    public String getSupervisorId() {
        return this.supervisorId;
    }

    public String getSupervisor() {
        return this.supervisor;
    }

    public String getSecondMarkerId() {
        return this.secondMarkerId;
    }

    public String getSecondMarker() {
        return this.secondMarker;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    @Override
    public String toString() {
        return studentId + ";" +
            name + ";" +
            password + ";" +
            birth + ";" +
            gender + ";" +
            phoneNumber + ";" +
            email + ";" +
            appliedDate + ";" +
            assesmentType + ";" +
            supervisorId + ";" +
            supervisor + ";" +
            secondMarkerId + ";" +
            secondMarker + ";" +
            imagePath + "\n";
    }
}
