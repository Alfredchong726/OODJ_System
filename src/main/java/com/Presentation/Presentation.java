package com.Presentation;

public class Presentation {
    public String presentationId;
    public String reportId;
    public String dateOfPresentation;
    public String lecturerId;
    public String studentId;
    public String slot;
    public String status;

    public Presentation(String presentationId, String reportId, String dateOfPresentation, String lecturerId, String studentId, String slot, String status) {
        this.presentationId = presentationId;
        this.reportId = reportId;
        this.dateOfPresentation = dateOfPresentation;
        this.lecturerId = lecturerId;
        this.studentId = studentId;
        this.slot = slot;
        this.status = status;
    }

    @Override
    public String toString() {
        return presentationId + ';' +
            reportId + ';' +
            dateOfPresentation + ';' +
            lecturerId + ';' +
            studentId + ';' +
            slot + ';' +
            status;
    }
}
