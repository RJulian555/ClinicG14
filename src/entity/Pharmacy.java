package entity;

import java.util.Date;
import java.util.Objects;

public class Pharmacy {

    private String medicationID;
    private String medicationName;
    private String medicationDescription;
    private double medicationPrice;
    private int medicationQuantity;
    private String medicationType;
    private Date medicationDate; // e.g., expiry date or stock-in date
    
      // ADD THIS NO-ARGUMENT CONSTRUCTOR
    public Pharmacy() {
    }

    public Pharmacy(String medicationID, String medicationName, String medicationDescription, double medicationPrice, int medicationQuantity, String medicationType, Date medicationDate) {
        this.medicationID = medicationID;
        this.medicationName = medicationName;
        this.medicationDescription = medicationDescription;
        this.medicationPrice = medicationPrice;
        this.medicationQuantity = medicationQuantity;
        this.medicationType = medicationType;
        this.medicationDate = medicationDate;
    }

    // Getters
    public String getMedicationID() {
        return medicationID;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public String getMedicationDescription() {
        return medicationDescription;
    }

    public double getMedicationPrice() {
        return medicationPrice;
    }

    public int getMedicationQuantity() {
        return medicationQuantity;
    }

    public String getMedicationType() {
        return medicationType;
    }

    public Date getMedicationDate() {
        return medicationDate;
    }

    // Setters
    public void setMedicationID(String medicationID) {
        this.medicationID = medicationID;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public void setMedicationDescription(String medicationDescription) {
        this.medicationDescription = medicationDescription;
    }

    public void setMedicationPrice(double medicationPrice) {
        this.medicationPrice = medicationPrice;
    }

    public void setMedicationQuantity(int medicationQuantity) {
        this.medicationQuantity = medicationQuantity;
    }

    public void setMedicationType(String medicationType) {
        this.medicationType = medicationType;
    }

    public void setMedicationDate(Date medicationDate) {
        this.medicationDate = medicationDate;
    }

    @Override
    public String toString() {
        return String.format("ID: %-5s | Name: %-20s | Desc: %-25s | Price: RM%.2f | Qty: %-5d | Type: %-15s | Date: %s",
                medicationID, medicationName, medicationDescription, medicationPrice, medicationQuantity, medicationType, medicationDate.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pharmacy that = (Pharmacy) obj;
        return Objects.equals(medicationID, that.medicationID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicationID);
    }
}