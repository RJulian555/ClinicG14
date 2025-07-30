package entity;

import java.util.Date;

public class MedicalTreatment {
    private String diagnosisID;
    private String patientID;
    private String doctorID;
    private String medicationID;
    private String diagnosisDescription;
    private String sickType;
    private String sicknessDescription;
    private Date createdDate;

    // Constructor
    public MedicalTreatment(String diagnosisID, String patientID, String doctorID, String medicationID,
                            String diagnosisDescription, String sickType, String sicknessDescription, Date createdDate) {
        this.diagnosisID = diagnosisID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.medicationID = medicationID;
        this.diagnosisDescription = diagnosisDescription;
        this.sickType = sickType;
        this.sicknessDescription = sicknessDescription;
        this.createdDate = createdDate;
    }

    // Getters and Setters
    public String getDiagnosisID() {
        return diagnosisID;
    }

    public void setDiagnosisID(String diagnosisID) {
        this.diagnosisID = diagnosisID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getMedicationID() {
        return medicationID;
    }

    public void setMedicationID(String medicationID) {
        this.medicationID = medicationID;
    }

    public String getDiagnosisDescription() {
        return diagnosisDescription;
    }

    public void setDiagnosisDescription(String diagnosisDescription) {
        this.diagnosisDescription = diagnosisDescription;
    }

    public String getSickType() {
        return sickType;
    }

    public void setSickType(String sickType) {
        this.sickType = sickType;
    }

    public String getSicknessDescription() {
        return sicknessDescription;
    }

    public void setSicknessDescription(String sicknessDescription) {
        this.sicknessDescription = sicknessDescription;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
