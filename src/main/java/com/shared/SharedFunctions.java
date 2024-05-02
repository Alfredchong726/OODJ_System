package com.shared;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Lecturer.Lecturer;
import com.Presentation.Presentation;

public class SharedFunctions {
    public File lecturerFile = new File("src/main/resources/com/textFiles/Lecturer.txt");
    public File presentationFile = new File("src/main/resources/com/textFiles/Presentation.txt");


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

    public ArrayList<Presentation> getPresentationById(String lecturerId, String studentId) {
        ArrayList<Presentation> PresentationData = new ArrayList<Presentation>();
        try (BufferedReader reader = new BufferedReader(new FileReader(presentationFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Presentation presentation = parsePresentation(line, lecturerId, studentId);
                if (presentation != null) {
                    PresentationData.add(presentation);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return PresentationData;
    }

    private void writePresentationData(List<Presentation> presentations) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Presentation presentation : presentations) {
            stringBuilder.append(presentation.toString());
            stringBuilder.append("\n");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(presentationFile))) {
            writer.write(stringBuilder.toString());
        } catch (Exception e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public void updatePresentationStatus(String presentationId, String status) {
        ArrayList<Presentation> PresentationData = new ArrayList<Presentation>();
        try (BufferedReader reader = new BufferedReader(new FileReader(presentationFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Presentation presentation = parsePresentation(line, "all", "all");
                if (presentation.presentationId.equals(presentationId)) {
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
        String role = fields[7];
        List<String> supervisees = Arrays.asList(fields[8].split(","));
        Map<String, List<String>> availableSlots = parseAvailableSlots(fields[9]);

        return new Lecturer(lecturerId, name, password, birth, gender, phoneNumber, email, role, supervisees, availableSlots);
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

    private static Presentation parsePresentation(String line, String LecturerId, String StudentId) {
        String[] fields = line.split(";");
        String presentationId = "";
        String reportId = "";
        String date = "";
        String lecturerId = "";
        String studentId = "";
        String slot = "";
        String status = "";

        if (LecturerId != "" && LecturerId.equals(fields[3])) {
            presentationId = fields[0];
            reportId = fields[1];
            date = fields[2];
            lecturerId = fields[3];
            studentId = fields[4];
            slot = fields[5];
            status = fields[6];
        } else if (StudentId != "" & StudentId == fields[4]) {
            presentationId = fields[0];
            reportId = fields[1];
            date = fields[2];
            lecturerId = fields[3];
            studentId = fields[4];
            slot = fields[5];
            status = fields[6];
        } else if (LecturerId.equals("all") && StudentId.equals("all")) {
            presentationId = fields[0];
            reportId = fields[1];
            date = fields[2];
            lecturerId = fields[3];
            studentId = fields[4];
            slot = fields[5];
            status = fields[6];
        }else {
            return null;
        }
        return new Presentation(presentationId, reportId, date, lecturerId, studentId, slot, status);
    }
}
