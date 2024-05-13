package com.Report;

import java.time.LocalDate;

public class Report {
    public String reportId;
    public String studentId;
    public String studentName;
    public String lecturerId;
    public String lecturerName;
    public String assesmentType;
    public LocalDate submissionDate;
    public String submissionLink;
    public String feedback;
    public String marks;
    public String status;

    public Report (String reportId, String studentId, String studentName, String lecturerId, 
        String lecturerName, String assesmentType, LocalDate submissionDate, String submissionLink, 
            String feedback, String marks, String status) {
        this.reportId = reportId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.lecturerId = lecturerId;
        this.lecturerName = lecturerName;
        this.assesmentType = assesmentType;
        this.submissionDate = submissionDate;
        this.submissionLink = submissionLink;
        this.feedback = feedback;
        this.marks = marks;
        this.status = status;
    }

    // Getter
    public String getReportId() {
        return this.reportId;
    }
    
    public String getStudentId() {
        return this.studentId;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public String getLecturerId() {
        return this.lecturerId;
    }

    public String getLecturerName() {
        return this.lecturerName;
    }

    public String getAssesmentType() {
        return this.assesmentType;
    }

    public LocalDate getSubmissionDate() {
        return this.submissionDate;
    }

    public String getSubmissionLink() {
        return this.submissionLink;
    }

    public String getFeedback() {
        return this.feedback;
    }

    public String getMarks() {
        return this.marks;
    }

    public String getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        return reportId + ";" +
            studentId + ";" +
            studentName + ";" +
            lecturerId + ";" +
            lecturerName + ";" +
            assesmentType + ";" +
            submissionDate + ";" +
            submissionLink + ";" +
            feedback + ";" +
            marks + ";" +
            status + "\n";
    }
}
