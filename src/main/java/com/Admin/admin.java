package com.example;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author fabia
 */
public class admin {
    
private String adminId;
    private String name;
    private String email;
    private String password;
    private String contact_number;
    private String lecturer;

    public admin(String adminId, String name, String email, String password, String contact_number) {
        this.adminId = adminId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact_number = contact_number;
        this.lecturer = lecturer;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
      public String getcontact_number() {
        return email;
    }

    public void setcontact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public void registerStudent(String studentId, String name, String password, String contact_number) {
        
          student newStudent = new student(studentId, name, password);

    try (FileWriter fileWriter = new FileWriter("students.txt", true);
         PrintWriter printWriter = new PrintWriter(fileWriter)) {

        printWriter.println("Student ID: " + newStudent.getStudentId());
        printWriter.println("Name: " + newStudent.getName());
        printWriter.println("Password: " + newStudent.getPassword());
        printWriter.println("Contact Number: " + contact_number);
        printWriter.println(); 

        System.out.println("New student registered and saved to file: " + newStudent.getName());

    } catch (IOException e) {
        System.out.println("Error occurred while writing to file: " + e.getMessage());
    }
         
    }

    public void registerLecturers(String lecturerId, String name, String email, String password, String contact_number) {
        lecturer newLecturer = new lecturer(lecturerId, name, email, password, contact_number);


    try (FileWriter fileWriter = new FileWriter("lecturers.txt", true);
         PrintWriter printWriter = new PrintWriter(fileWriter)) {

        // Append lecturer details to the text file
        printWriter.println("Lecturer ID: " + newLecturer.getLecturerId());
        printWriter.println("Name: " + newLecturer.getName());
        printWriter.println("Email: " + newLecturer.getEmail());
        printWriter.println("Password: " + newLecturer.getPassword());
        printWriter.println("Contact Number: " + newLecturer.getContactNumber());
        printWriter.println(); 

        System.out.println("New lecturer registered and saved to file: " + newLecturer.getName());

    } catch (IOException e) {
        System.out.println("Error occurred while writing to file: " + e.getMessage());
    }
}

    public void amendStudentOrLecturerDetails(String userId, String newName, String newEmail, String newPassword, String newContactNumber, String userType) {
    // Determine whether to update a student or a lecturer based on userType
    if (userType.equalsIgnoreCase("student")) {
        // Update student details
        student studentToUpdate = findStudentById(userId); // Assume a method to find student by ID
        if (studentToUpdate != null) {
            // Update student properties
            studentToUpdate.setName(newName);
            studentToUpdate.setemail(newEmail);
            studentToUpdate.setPassword(newPassword);
            studentToUpdate.setContactNumber(newContactNumber);
 
            System.out.println("Student details updated successfully.");
        } else {
            System.out.println("Student not found with ID: " + userId);
        }
    } else if (userType.equalsIgnoreCase("lecturer")) {
        // Update lecturer details
        Lecturer lecturerToUpdate = findLecturerById(userId); // Assume a method to find lecturer by ID
        if (lecturerToUpdate != null) {
            // Update lecturer properties
            lecturerToUpdate.setName(newName);
            lecturerToUpdate.setEmail(newEmail);
            lecturerToUpdate.setPassword(newPassword);
            lecturerToUpdate.setContactNumber(newContactNumber);
 
            System.out.println("Lecturer details updated successfully.");
        } else {
            System.out.println("Lecturer not found with ID: " + userId);
        }
    } else {
        System.out.println("Invalid user type. Please specify 'student' or 'lecturer'.");
    }
}

    public void allotProjectManagerRole(){
       if (userType.equalsIgnoreCase("student")) {
        // Assign project manager role to the student
        Student studentToAssignRole = findStudentById(userId); // Find student by ID
        if (studentToAssignRole != null) {
            // Set project manager role for the student
            studentToAssignRole.setProjectManager(true);
 
            System.out.println("Project Manager role assigned to student: " + studentToAssignRole.getName());
        } else {
            System.out.println("Student not found with ID: " + userId);
        }
    } else if (userType.equalsIgnoreCase("lecturer")) {
        // Assign project manager role to the lecturer
        Lecturer lecturerToAssignRole = findLecturerById(userId); // Find lecturer by ID
        if (lecturerToAssignRole != null) {
            // Set project manager role for the lecturer
            lecturerToAssignRole.setProjectManager(true);
 
            System.out.println("Project Manager role assigned to lecturer: " + lecturerToAssignRole.getName());
        } else {
            System.out.println("Lecturer not found with ID: " + userId);
        }
    } else {
        System.out.println("Invalid user type. Please specify 'student' or 'lecturer'.");
    }
}
    
    public void editOrRemovePMRole(String userId, String userType, boolean removeRole) {
    // Check user type to determine whether to edit or remove project manager role from a student or lecturer
    if (userType.equalsIgnoreCase("student")) {
        // Find the student by ID
        student studentToUpdate = findStudentById(userId); // Find student by ID
        if (studentToUpdate != null) {
            // Check if removing the role or just editing
            if (removeRole) {
                // Remove project manager role
                studentToUpdate.setProjectManager(false);
                System.out.println("Project Manager role removed from student: " + studentToUpdate.getName());
            } else {
                // No action needed for editing, as this example does not show specific edits
                System.out.println("Project Manager role edited for student: " + studentToUpdate.getName());
            }
        } else {
            System.out.println("Student not found with ID: " + userId);
        }
    } else if (userType.equalsIgnoreCase("lecturer")) {
        // Find the lecturer by ID
        lecturer lecturerToUpdate = findLecturerById(userId); // Find lecturer by ID
        if (lecturerToUpdate != null) {
            // Check if removing the role or just editing
            if (removeRole) {
                // Remove project manager role
                lecturerToUpdate.setProjectManager(false);
                System.out.println("Project Manager role removed from lecturer: " + lecturerToUpdate.getName());
            } else {
                // No action needed for editing, as this example does not show specific edits
                System.out.println("Project Manager role edited for lecturer: " + lecturerToUpdate.getName());
            }
        } else {
            System.out.println("Lecturer not found with ID: " + userId);
        }
    } else {
        System.out.println("Invalid user type. Please specify 'student' or 'lecturer'.");
    }
}
}

    

        
  


