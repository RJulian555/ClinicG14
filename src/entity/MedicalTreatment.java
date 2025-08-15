package entity;

import java.util.Date;

public class MedicalTreatment {
    private String diagnosisID;
    private String patientID;
    private String doctorID;
    private String medicationID;
    private int dispensedQuantity;
    private String sicknessDescription;
    private String sickType;
    private String diagnosisDescription;
    private Date createdDate;

    // Constructor
    public MedicalTreatment(String diagnosisID, String patientID, String doctorID, String medicationID,
                            String sicknessDescription, String sickType, String diagnosisDescription, Date createdDate) {
        this.diagnosisID = diagnosisID;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.medicationID = medicationID;
        this.sicknessDescription = sicknessDescription;
        this.sickType = sickType;
        this.diagnosisDescription = diagnosisDescription;
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
    
    public int getDispensedQuantity() {
    return dispensedQuantity;
}

public void setDispensedQuantity(int dispensedQuantity) {
    this.dispensedQuantity = dispensedQuantity;
}

    public String getSicknessDescription() {
        return sicknessDescription;
    }

    public void setSicknessDescription(String sicknessDescription) {
        this.sicknessDescription = sicknessDescription;
    }

    public String getSickType() {
        return sickType;
    }

    public void setSickType(String sickType) {
        this.sickType = sickType;
    }
    
    public String getDiagnosisDescription() {
        return diagnosisDescription;
    }

    public void setDiagnosisDescription(String diagnosisDescription) {
        this.diagnosisDescription = diagnosisDescription;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
     @Override
public String toString() {
    return String.format(
        "DiagnosisID: %s | PatientID: %s | DoctorID: %s | MedicationID: %s%n" +
        "Sickness Description: %s | Sick Type: %s | Diagnosis: %s%n" +
        "Created: %s",
        diagnosisID, patientID, doctorID, medicationID,
        sicknessDescription, sickType, diagnosisDescription,
        createdDate
    );
}
}
