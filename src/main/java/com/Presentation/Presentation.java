package com.Presentation;

public class Presentation {
    public String presentationId;
    public String dateOfPresentation;
    public String supervisorId;
    public String supervisorName;
    public String secondMarkerId;
    public String secondMarkerName;
    public String secondMarkerAceeptance;
    public String studentId;
    public String studentName;
    public String assesmentType;
    public String gender;
    public String slot;
    public String status;

    public Presentation(String presentationId, String dateOfPresentation, String supervisorId, String supervisorName, 
        String secondMarkerId, String secondMarkerName, String secondMarkerAccpetance, String studentId, String studentName,
            String assesmentType, String gender, String slot, String status) {
        this.presentationId = presentationId;
        this.dateOfPresentation = dateOfPresentation;
        this.supervisorId = supervisorId;
        this.supervisorName = supervisorName;
        this.secondMarkerId = secondMarkerId;
        this.secondMarkerName = secondMarkerName;
        this.secondMarkerAceeptance = secondMarkerAccpetance;
        this.studentId = studentId;
        this.studentName = studentName;
        this.assesmentType = assesmentType;
        this.gender = gender;
        this.slot = slot;
        this.status = status;
    }

    @Override
    public String toString() {
        return presentationId + ';' +
            dateOfPresentation + ';' +
            supervisorId + ';' +
            supervisorName + ';' +
            secondMarkerId + ';' +
            secondMarkerName + ';' +
            secondMarkerAceeptance + ';' +
            studentId + ';' +
            studentName + ';' +
            assesmentType + ';' +
            gender + ';' +
            slot + ';' +
            status + "\n";
    }

    public String getPresentationId() {
        return this.presentationId;
    }

    public String getDateOfPresentation() {
        return this.dateOfPresentation;
    }

    public String getSupervisorId() {
        return this.supervisorId;
    }

    public String getSupervisorName() {
        return this.supervisorName;
    }
    
    public String getSecondMarkerId() {
        return this.secondMarkerId;
    }

    public String getSecondMarkerName() {
        return this.secondMarkerName;
    }

    public String getSecondMarkerAcceptance() {
        return this.secondMarkerAceeptance;
    }

    public String getStudentId() {
        return this.studentId;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public String getAssesmentType() {
        return this.assesmentType;
    }

    public String getGender() {
        return this.gender;
    }

    public String getSlot() {
        return this.slot;
    }

    public String getStatus() {
        return this.status;
    }
}
