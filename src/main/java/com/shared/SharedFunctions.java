package com.shared;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Admin.Admin;
import com.Lecturer.Lecturer;
import com.Presentation.Presentation;
import com.Report.Report;
import com.Student.Student;

public class SharedFunctions {
    public File lecturerFile = new File("src/main/resources/com/textFiles/Lecturer.txt");
    public File adminFile = new File("src/main/resources/com/textFiles/Admin.txt");
    public File studentFile = new File("src/main/resources/com/textFiles/Student.txt");
    public File presentationFile = new File("src/main/resources/com/textFiles/Presentation.txt");
    public File reportFile = new File("src/main/resources/com/textFiles/Report.txt");

    public String[] validatePassword(String username, String password) {
        String[] returnInfo = new String[2];
        String lecturerId = validateLecturer(username, password);
        String adminId = validateAdmin(username, password);
        String studentId = validateStudent(username, password);
        if (lecturerId != null) {
            returnInfo[0] = "Lecturer";
            returnInfo[1] = lecturerId;

            return returnInfo;
        } else if (adminId != null) {
            returnInfo[0] = "Admin";
            returnInfo[1] = adminId;

            return returnInfo;
        } else if (studentId != null) {
            returnInfo[0] = "Student";
            returnInfo[1] = studentId;

            return returnInfo;
        }
        return null;
    }

    private String validateLecturer(String username, String password) {
        List<Lecturer> lecturerData = getLecturerData();
        for (Lecturer lecturer : lecturerData) {
            if (lecturer.name.equals(username) && lecturer.password.equals(password)) {
                return lecturer.lecturerId;
            }
        }
        return null;
    }

    private String validateAdmin(String username, String password) {
        List<Admin> adminData = getAdminData();
        for (Admin admin : adminData) {
            if (admin.username.equals(username) && admin.password.equals(password)) {
                return admin.adminId;
            }
        }
        return null;
    }

    private String validateStudent(String username, String password) {
        List<Student> studentData = getStudentData();
        for (Student student : studentData) {
            if (student.name.equals(username) && student.password.equals(password)) {
                return student.studentId;
            }
        }
        return null;
    }

    public String getProfileImageById(String role, String userId) {
        if (role.equals("Lecturer")) {
            List<Lecturer> lecturerData = getLecturerData();
            for (Lecturer lecturer : lecturerData) {
                if (lecturer.lecturerId.equals(userId)) {
                    return lecturer.imagePath;
                }
            }
        } else if (role.equals("Student")) {
            List<Student> studentData = getStudentData();
            for (Student student : studentData) {
                if (student.studentId.equals(userId)) {
                    return student.imagePath;
                }
            }
        }
        return null;
    }

    public ArrayList<Lecturer> getLecturerData() {
        ArrayList<Lecturer> LecturerData = new ArrayList<Lecturer>();
        try (BufferedReader reader = new BufferedReader(new FileReader(lecturerFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Lecturer lecturer = parseLecturer(line);
                LecturerData.add(lecturer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return LecturerData;
    }

    public ArrayList<Admin> getAdminData() {
        ArrayList<Admin> adminData = new ArrayList<Admin>();
        try (BufferedReader reader = new BufferedReader(new FileReader(adminFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Admin admin = parseAdmin(line);
                adminData.add(admin);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return adminData;
    }

    public ArrayList<Lecturer> getProjectManagerData() {
        ArrayList<Lecturer> LecturerData = new ArrayList<Lecturer>();
        try (BufferedReader reader = new BufferedReader(new FileReader(lecturerFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Lecturer lecturer = parseLecturer(line);
                if (lecturer.isProjectManager) {
                    LecturerData.add(lecturer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return LecturerData;
    }

    public ArrayList<Lecturer> getNonProjectManagerData() {
        ArrayList<Lecturer> LecturerData = new ArrayList<Lecturer>();
        try (BufferedReader reader = new BufferedReader(new FileReader(lecturerFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Lecturer lecturer = parseLecturer(line);
                if (!lecturer.isProjectManager) {
                    LecturerData.add(lecturer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return LecturerData;
    }

    public ArrayList<Student> getStudentData() {
        ArrayList<Student> studentData = new ArrayList<Student>();
        try (BufferedReader reader = new BufferedReader(new FileReader(studentFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Student student = parseStudent(line);
                studentData.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return studentData;
    }

    public ArrayList<Report> getReportData() {
        ArrayList<Report> reportData = new ArrayList<Report>();
        try (BufferedReader reader = new BufferedReader(new FileReader(reportFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Report report = parseReport(line, "all", "all");
                reportData.add(report);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reportData;
    }

    public ArrayList<Presentation> getPresentationById(String lecturerId, String secondMarkerId, String studentId) {
        ArrayList<Presentation> PresentationData = new ArrayList<Presentation>();
        try (BufferedReader reader = new BufferedReader(new FileReader(presentationFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Presentation presentation = parsePresentation(line, lecturerId, secondMarkerId, studentId);
                if (presentation != null) {
                    PresentationData.add(presentation);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return PresentationData;
    }

    public Lecturer getLecturerDataById(String lecturerId) {
        ArrayList<Lecturer> lecturerData = getLecturerData();
        for (Lecturer lecturer: lecturerData) {
            if (lecturer.lecturerId.equals(lecturerId)) {
                return lecturer;
            }
        }
        return null;
    }

    public Student getStudentDataById(String studentId) {
        ArrayList<Student> studentData = getStudentData();
        for (Student student: studentData) {
            if (student.studentId.equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    public ArrayList<Report> getReportById(String lecturerId, String studentId) {
        ArrayList<Report> reportData = new ArrayList<Report>();
        try (BufferedReader reader = new BufferedReader(new FileReader(reportFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Report report = parseReport(line, lecturerId, studentId);
                if (report != null) {
                    reportData.add(report);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reportData;
    }

    public void appendLecturerInfo(Lecturer lecturer) {
        ArrayList<Lecturer> lecturerData = getLecturerData();
        Lecturer lastLecturerInfo = lecturerData.get(lecturerData.size() - 1);
        int newLecturerId = Integer.parseInt(lastLecturerInfo.getLecturerId().substring(1)) + 1;
        String newLecturerIdString = "L" + newLecturerId;
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(lecturerFile, true))) {
            lecturer.lecturerId = newLecturerIdString;
            lecturer.availableSlot = lastLecturerInfo.defaultAvailableSlots;
            writer.write(lecturer.toString().replace("[", "").replace("]", "").replace("{", "").replace("}", "").replace("=", "-").replace(", ", ","));
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public void appendStudentInfo(Student student) {
        ArrayList<Student> studentData = getStudentData();
        Student lastStudentData = studentData.get(studentData.size() - 1);
        int newStudentId = Integer.parseInt(lastStudentData.getStudentId().substring(1)) + 1;
        String newStudentIdString = "S" + newStudentId;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(presentationFile, true))) {
            student.studentId = newStudentIdString;
            writer.write(student.toString());
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public void addReport(Report report) {
        ArrayList<Report> reportData = getReportData();
        Report lastReportData = reportData.get(reportData.size() - 1);
        int newReportId = Integer.parseInt(lastReportData.getReportId().substring(1)) + 1;
        String newReportIdString = "R" + newReportId;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(reportFile, true))) {
            report.reportId = newReportIdString;
            writer.write(report.toString());
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public void addPresentation(Presentation presentation, String studentId) {
        ArrayList<Presentation> presentationData = getPresentationById("", "", studentId);
        Presentation lastPresentation = presentationData.get(presentationData.size() - 1);
        int newPresentationId = Integer.parseInt(lastPresentation.getPresentationId().substring(1)) + 1;
        String newPresentationIdString = "P" + newPresentationId;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(presentationFile, true))) {
            presentation.presentationId = newPresentationIdString;
            writer.write(presentation.toString());
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public void updateReport(Report ReportData) {
        ArrayList<Report> reportData = new ArrayList<Report>();
        try (BufferedReader reader = new BufferedReader(new FileReader(reportFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Report report = parseReport(line, "all", "all");
                if (report.reportId.equals(ReportData.reportId)) {
                    reportData.add(ReportData);
                } else {
                    reportData.add(report);
                }
            }
            writeReportData(reportData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Student StudentData) {
        ArrayList<Student> studentData = new ArrayList<Student>();
        try (BufferedReader reader = new BufferedReader(new FileReader(studentFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Student student = parseStudent(line);
                if (student.studentId.equals(StudentData.studentId)) {
                    studentData.add(StudentData);
                } else {
                    studentData.add(student);
                }
            }
            writeStudentData(studentData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updatePresentation(Presentation PresentationData) {
        ArrayList<Presentation> presentationData = new ArrayList<Presentation>();
        try (BufferedReader reader = new BufferedReader(new FileReader(presentationFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Presentation presentation = parsePresentation(line, "all", "all", "all");
                if (presentation.presentationId.equals(PresentationData.presentationId)) {
                    presentationData.add(PresentationData);
                } else {
                    presentationData.add(presentation);
                }
            }
            writePresentationData(presentationData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeReport(Report ReportData) {
        ArrayList<Report> reportData = new ArrayList<Report>();
        try (BufferedReader reader = new BufferedReader(new FileReader(reportFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Report report = parseReport(line, "all", "all");
                if (!report.reportId.equals(ReportData.reportId)) {
                    reportData.add(ReportData);
                }
            }
            writeReportData(reportData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removePresentation(Presentation PresentationData) {
        ArrayList<Presentation> presentationData = new ArrayList<Presentation>();
        try (BufferedReader reader = new BufferedReader(new FileReader(presentationFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Presentation presentation = parsePresentation(line, "all", "all", "all");
                if (!presentation.presentationId.equals(PresentationData.presentationId)) {
                    presentationData.add(PresentationData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writePresentationData(List<Presentation> presentations) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Presentation presentation : presentations) {
            stringBuilder.append(presentation.toString());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(presentationFile))) {
            writer.write(stringBuilder.toString());
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public void writeReportData(List<Report> reports) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Report report : reports) {
            stringBuilder.append(report.toString());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(reportFile))) {
            writer.write(stringBuilder.toString());
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public void writeStudentData(List<Student> studentData) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Student student : studentData) {
            stringBuilder.append(student.toString());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(studentFile))) {
            writer.write(stringBuilder.toString());
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public void writeLecturerData(List<Lecturer> lecturerData) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Lecturer lecturer : lecturerData) {
            stringBuilder.append(lecturer.toString());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(lecturerFile))) {
            writer.write(stringBuilder.toString().replace("[", "").replace("]", "").replace("{", "").replace("}", "").replace("=", "-").replace(", ", ","));
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public void assignProjectManager(Lecturer lecturer) {
        ArrayList<Lecturer> lecturerData = getLecturerData();
        for (Lecturer lec: lecturerData) {
            if (lec.lecturerId.equals(lecturer.lecturerId)) {
                lec.isProjectManager = true;
            }
        }
        writeLecturerData(lecturerData);
    }

    public void unassignProjectManager(Lecturer lecturer) {
        ArrayList<Lecturer> lecturerData = getLecturerData();
        for (Lecturer lec: lecturerData) {
            if (lec.lecturerId.equals(lecturer.lecturerId)) {
                lec.isProjectManager = false;
            }
        }
        writeLecturerData(lecturerData);
    }

    public void updatePresentationStatus(String presentationId, String status, boolean isSecondMarker) {
        ArrayList<Presentation> PresentationData = new ArrayList<Presentation>();
        try (BufferedReader reader = new BufferedReader(new FileReader(presentationFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Presentation presentation = parsePresentation(line, "all", "all", "all");
                if (presentation.presentationId.equals(presentationId) && isSecondMarker) {
                    presentation.secondMarkerAceeptance = status;
                } else if (presentation.presentationId.equals(presentationId) && !isSecondMarker) {
                    presentation.status = status;
                }
                PresentationData.add(presentation);
            }
            writePresentationData(PresentationData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Lecturer parseLecturer(String line) {
        String[] fields = line.split(";");
        String lecturerId = fields[0];
        String name = fields[1];
        String password = fields[2];
        String birth = fields[3];
        String gender = fields[4];
        String phoneNumber = fields[5];
        String email = fields[6];
        LocalDate appliedDate = LocalDate.parse(fields[7]);
        List<String> supervisees = Arrays.asList(fields[8].split(","));
        Map<String, List<String>> availableSlots = parseAvailableSlots(fields[9]);
        String imagePath = fields[10];
        boolean isProjectManager = Boolean.parseBoolean(fields[11]);

        return new Lecturer(lecturerId, name, password, birth, gender, phoneNumber, email, appliedDate,
             supervisees, availableSlots, imagePath, isProjectManager);
    }

    public static Admin parseAdmin(String line) {
        String[] fields = line.split(";");
        String adminId = fields[0];
        String username = fields[1];
        String password = fields[2];
        String gender = fields[3];
        String gmail = fields[4];

        return new Admin(adminId, username, password, gender, gmail);
    }

    public static Student parseStudent(String line) {
        String[] fields = line.split(";");
        String studentId = fields[0];
        String username = fields[1];
        String password = fields[2];
        String birth = fields[3];
        String gender = fields[4];
        String phoneNumber = fields[5];
        String gmail = fields[6];
        LocalDate appliedDate = LocalDate.parse(fields[7]);
        String assesmentType = fields[8];
        String supervisorId = fields[9];
        String supervisor = fields[10];
        String secondMarkerId = fields[11];
        String secondMarker = fields[12];
        String imagePath = fields[13];

        return new Student(studentId, username, password, birth, gender, phoneNumber, gmail, 
            appliedDate, assesmentType, supervisorId, supervisor, secondMarkerId, secondMarker, 
                imagePath);
    }

    private static Report parseReport(String line, String LecturerId, String StudentId) {
        String[] fields = line.split(";");
        String reportId = fields[0];
        String studentId = fields[1];
        String studentName = fields[2];
        String lecturerId = fields[3];
        String lecturerName = fields[4];
        String assesmentType = fields[5];
        LocalDate submissionDate = LocalDate.parse(fields[6]);
        String submissionLink = fields[7];
        String status = fields[8];

        if (LecturerId != null && LecturerId.equals(fields[2])) {
            return new Report(reportId, studentId, studentName, lecturerId, lecturerName, 
                assesmentType, submissionDate, submissionLink, status);
        } else if (StudentId != null && StudentId.equals(fields[1])) {
            return new Report(reportId, studentId, studentName, lecturerId, lecturerName, 
                assesmentType, submissionDate, submissionLink, status);
        } else if (LecturerId.equals("all") && StudentId.equals("all")) {
            return new Report(reportId, studentId, studentName, lecturerId, lecturerName, 
                assesmentType, submissionDate, submissionLink, status);
        }
        return null;
    }

    private static Map<String, List<String>> parseAvailableSlots(String slotsString) {
        Map<String, List<String>> availableSlots = new HashMap<>();
        String[] daySlots = slotsString.split(":");
        for (String daySlot : daySlots) {
            String[] parts = daySlot.split("-");
            String day = parts[0];
            List<String> slots = Arrays.asList(parts[1].split(","));
            availableSlots.put(day, slots);
        }
        return availableSlots;
    }

    private static Presentation parsePresentation(String line, String LecturerId, String SecondMarkerId, String StudentId) {
        String[] fields = line.split(";");
        String presentationId = fields[0];
        String date = fields[1];
        String supervisorId = fields[2];
        String supervisorName = fields[3];
        String secondMarkerId = fields[4];
        String secondMarkerName = fields[5];
        String secondMarkerAcceptance = fields[6];
        String studentId = fields[7];
        String studentName = fields[8];
        String gender = fields[9];
        String assesmentType = fields[10];
        String slot = fields[11];
        String status = fields[12];

        if (LecturerId != "" && LecturerId.equals(fields[2])) {
            return new Presentation(presentationId, date, supervisorId, supervisorName, 
                secondMarkerId, secondMarkerName, secondMarkerAcceptance, studentId, studentName, 
                    assesmentType, gender, slot, status);
        } else if (SecondMarkerId != "" && SecondMarkerId.equals(fields[4])) {
            return new Presentation(presentationId, date, supervisorId, supervisorName, 
                secondMarkerId, secondMarkerName, secondMarkerAcceptance, studentId, studentName, 
                    assesmentType, gender, slot, status);
        } else if (StudentId != "" && StudentId.equals(fields[7])) {
            return new Presentation(presentationId, date, supervisorId, supervisorName, 
                secondMarkerId, secondMarkerName, secondMarkerAcceptance, studentId, studentName, 
                    assesmentType, gender, slot, status);
        } else if (LecturerId.equals("all") || SecondMarkerId.equals("all") || StudentId.equals("all")) {
            return new Presentation(presentationId, date, supervisorId, supervisorName, 
                secondMarkerId, secondMarkerName, secondMarkerAcceptance, studentId, studentName, 
                    assesmentType, gender, slot, status);
        }
        return null;
        
    }
}
