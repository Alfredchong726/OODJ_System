package com.Presentation;

public class Presentation {
    public String presentationId;
    public String reportId;
    public String dateOfPresentation;
    public String supervisorId;
    public String secondMarkerId;
    public String secondMarkerAceeptance;
    public String studentId;
    public String studentName;
    public String assesmentType;
    public String gender;
    public String slot;
    public String status;

    public Presentation(String presentationId, String reportId, String dateOfPresentation, String supervisorId, String secondMarkerId, String secondMarkerAccpetance, String studentId, String studentName, String assesmentType, String gender, String slot, String status) {
        this.presentationId = presentationId;
        this.reportId = reportId;
        this.dateOfPresentation = dateOfPresentation;
        this.supervisorId = supervisorId;
        this.secondMarkerId = secondMarkerId;
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
            reportId + ';' +
            dateOfPresentation + ';' +
            supervisorId + ';' +
            secondMarkerId + ';' +
            secondMarkerAceeptance + ';' +
            studentId + ';' +
            studentName + ';' +
            assesmentType + ';' +
            gender + ';' +
            slot + ';' +
            status;
    }

    public String getPresentationId() {
        return this.presentationId;
    }

    public String getReportId() {
        return this.reportId;
    }

    public String getDateOfPresentation() {
        return this.dateOfPresentation;
    }

    public String getSupervisorId() {
        return this.supervisorId;
    }
    
    public String getSecondMarkerId() {
        return this.secondMarkerId;
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
