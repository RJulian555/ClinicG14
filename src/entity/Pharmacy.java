package entity;

import java.util.Date;

public class Pharmacy {
    private String medicationID;
    private String medicationName;
    private String medicationDescription;
    private double medicationPrice;
    private int medicationQuantity;
    private Date medicationDate;
    
    public Pharmacy(String medicationID, String medicationName, String medicationDescription, 
                   double medicationPrice, int medicationQuantity) {
        this.medicationID = medicationID;
        this.medicationName = medicationName;
        this.medicationDescription = medicationDescription;
        this.medicationPrice = medicationPrice;
        this.medicationQuantity = medicationQuantity;
        this.medicationDate = new Date();
    }
    
    // Getters and setters
    public String getMedicationID() {
        return medicationID;
    }

    public void setMedicationID(String medicationID) {
        this.medicationID = medicationID;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getMedicationDescription() {
        return medicationDescription;
    }

    public void setMedicationDescription(String medicationDescription) {
        this.medicationDescription = medicationDescription;
    }

    public double getMedicationPrice() {
        return medicationPrice;
    }

    public void setMedicationPrice(double medicationPrice) {
        this.medicationPrice = medicationPrice;
    }

    public int getMedicationQuantity() {
        return medicationQuantity;
    }

    public void setMedicationQuantity(int medicationQuantity) {
        this.medicationQuantity = medicationQuantity;
    }

    public Date getMedicationDate() {
        return medicationDate;
    }

    public void setMedicationDate(Date medicationDate) {
        this.medicationDate = medicationDate;
    }
    
    @Override
    public String toString() {
        return "Medicine ID: " + medicationID + 
               "\nName: " + medicationName + 
               "\nDescription: " + medicationDescription + 
               "\nPrice: RM" + medicationPrice + 
               "\nQuantity in Stock: " + medicationQuantity + 
               "\nDate: " + medicationDate;
    }
}