/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author user
 */
public class Prescription {
    private String treatmentID;
    private String medicationID;
    private int quantity;
    private String patientID; // Good to have for context

    public Prescription(String treatmentID, String patientID, String medicationID, int quantity) {
        this.treatmentID = treatmentID;
        this.patientID = patientID;
        this.medicationID = medicationID;
        this.quantity = quantity;
    }

    // Getters
    public String getTreatmentID() { return treatmentID; }
    public String getPatientID() { return patientID; }
    public String getMedicationID() { return medicationID; }
    public int getQuantity() { return quantity; }
}