package entity;

import java.util.Date;

public class MedicalTreatment {
    private String treatmentID;
    private String diagnosisID;
    private String patientID;
    private String doctorID;
    private String medicationID;
    private int dispensedQuantity;
    private String patientSicknessDescription; // Renamed for clarity
    private String sickType;
    private String diagnosisDescription;
    private Date createdDate;

    public MedicalTreatment(String treatmentID, String diagnosisID, String patientID, String doctorID, String medicationID,
                            String patientSicknessDescription, String sickType, String diagnosisDescription, Date createdDate) {
        this.treatmentID = treatmentID;
        this.diagnosisID = diagnosisID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.medicationID = medicationID;
        this.patientSicknessDescription = patientSicknessDescription;
        this.sickType = sickType;
        this.diagnosisDescription = diagnosisDescription;
        this.createdDate = createdDate;
    }

    // --- Getters and Setters ---

    // **FIX 1: ADD THE MISSING GETTER FOR treatmentID**
    public String getTreatmentID() {
        return treatmentID;
    }
    public void setTreatmentID(String treatmentID) {
        this.treatmentID = treatmentID;
    }
    
    // This is the correct getter for the patient's specific notes
    public String getPatientSicknessDescription() {
        return patientSicknessDescription;
    }
    // This is the correct setter, which will be used by the Update feature
    public void setPatientSicknessDescription(String description) {
        this.patientSicknessDescription = description;
    }
    
    // **FIX 2: The old `getSicknessDescription()` is no longer needed.**
    // We will keep the setter for now as it may be used in the update logic.
    public void setSicknessDescription(String sicknessDescription) {
        this.patientSicknessDescription = sicknessDescription;
    }
    
    // The rest of the getters and setters are correct
    public String getDiagnosisID() { return diagnosisID; }
    public String getPatientID() { return patientID; }
    public String getDoctorID() { return doctorID; }
    public String getMedicationID() { return medicationID; }
    public int getDispensedQuantity() { return dispensedQuantity; }
    public void setDispensedQuantity(int dispensedQuantity) { this.dispensedQuantity = dispensedQuantity; }
    public String getSickType() { return sickType; }
    public String getDiagnosisDescription() { return diagnosisDescription; }
    public void setDiagnosisDescription(String diagnosisDescription) { this.diagnosisDescription = diagnosisDescription; }
    public Date getCreatedDate() { return createdDate; }
}