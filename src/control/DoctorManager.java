/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import adt.LinkedQueue;
import adt.QueueInterface;
import entity.Doctor;

/**
 *
 * @author user
 */
public class DoctorManager {
    private QueueInterface<Doctor> doctorQueue;
    
    
    
    public DoctorManager() {
         this.doctorQueue = new LinkedQueue<>();;
    }
    
  //-----------------------------------------------------------------------------------------------------------------//  
    public void addDoctor(Doctor doctor) {
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor cannot be null");
        }
        if (doctorExists(doctor.getDoctorID())) {
            throw new IllegalStateException("Doctor with ID " + doctor.getDoctorID() + " already exists");
        }
        doctorQueue.enqueue(doctor);
    }
    //-----------------------------------------------------------------------------------------------------------------//
    public String generateDoctorID() {
    Doctor[] doctors = doctorQueue.toArray(new Doctor[0]);
    int maxID = 0;
    
    // Find the highest existing ID number
    for (Doctor doctor : doctors) {
        String id = doctor.getDoctorID();
        if (id.startsWith("DR") && id.length() > 2) {
            try {
                int num = Integer.parseInt(id.substring(2));
                if (num > maxID) {
                    maxID = num;
                }
            } catch (NumberFormatException e) {
                // Skip if the ID format is invalid
            }
        }
    }
    
    // Return new ID with incremented number
    return String.format("DR%03d", maxID + 1);
}

//-----------------------------------------------------------------------------------------------------------------//  
    public boolean removeDoctor(String doctorID) {
        LinkedQueue<Doctor> tempQueue = new LinkedQueue<>();
        boolean found = false;
        
        while (!doctorQueue.isEmpty()) {
            Doctor current = doctorQueue.dequeue();
            if (!found && current.getDoctorID().equals(doctorID)) {
                found = true;
            } else {
                tempQueue.enqueue(current);
            }
        }
        
        doctorQueue = tempQueue;
        return found;
    }
//-----------------------------------------------------------------------------------------------------------------//  
    public boolean updateDoctor(String doctorID, Doctor newDetails) {
        LinkedQueue<Doctor> tempQueue = new LinkedQueue<>();
        boolean found = false;
        
        while (!doctorQueue.isEmpty()) {
            Doctor current = doctorQueue.dequeue();
            if (current.getDoctorID().equals(doctorID)) {
                // Update all fields except ID
                current.setName(newDetails.getName());
                current.setSpecialization(newDetails.getSpecialization());
                current.setContactNumber(newDetails.getContactNumber());
                current.setYearsOfExperience(newDetails.getYearsOfExperience());
                current.setAvailable(newDetails.isAvailable());
                current.setConsultationFee(newDetails.getConsultationFee());
                current.setOnLeave(newDetails.isOnLeave());
                current.setLeaveDates(newDetails.getLeaveDates());
                current.setWorkingHours(newDetails.getWorkingHours());
                found = true;
            }
            tempQueue.enqueue(current);
        }
        
        doctorQueue = tempQueue;
        return found;
    }
//-----------------------------------------------------------------------------------------------------------------//  
    public Doctor getNextAvailableDoctor() {
        LinkedQueue<Doctor> tempQueue = new LinkedQueue<>();
        Doctor foundDoctor = null;
        
        while (!doctorQueue.isEmpty()) {
            Doctor current = doctorQueue.dequeue();
            if (foundDoctor == null && current.isAvailable() && !current.isOnLeave()) {
                foundDoctor = current;
            }
            tempQueue.enqueue(current);
        }
        
        doctorQueue = tempQueue;
        return foundDoctor;
    }
//-----------------------------------------------------------------------------------------------------------------//  
    // Query Methods
    public Doctor getDoctorByID(String doctorID) {
        for (Doctor doctor : doctorQueue.toArray(new Doctor[0])) {
            if (doctor.getDoctorID().equals(doctorID)) {
                return doctor;
            }
        }
        return null;
    }
//-----------------------------------------------------------------------------------------------------------------//  
    public Doctor[] getAllDoctors() {
        return doctorQueue.toArray(new Doctor[0]);
    }
//-----------------------------------------------------------------------------------------------------------------//  
    public boolean doctorExists(String doctorID) {
        return getDoctorByID(doctorID) != null;

    }
//-----------------------------------------------------------------------------------------------------------------//  
    // Reports
    public String generateSpecializationReport() {
    StringBuilder report = new StringBuilder();
    
    // Header section
    report.append("TUNKU ABDUL RAHMAN UNIVERSITY HOSPITAL\n");
    report.append("DOCTOR MANAGEMENT SYSTEM REPORT\n\n");
    report.append("SUMMARY OF MEDICAL STAFFING\n\n");
    report.append("Generated at: ").append(java.time.LocalDateTime.now()).append("\n\n");
    report.append("***************************************************************************************************************\n\n");
    report.append("TUNKU ABDUL RAHMAN UNIVERSITY HOSPITAL - CONFIDENTIAL MEDICAL STAFF RECORDS\n\n");
    
    // Get all doctors first
    Doctor[] doctors = doctorQueue.toArray(new Doctor[0]);
    
    // Process specializations and fees
    LinkedQueue<String> specializations = new LinkedQueue<>();
    LinkedQueue<Double> minFees = new LinkedQueue<>();
    LinkedQueue<Double> maxFees = new LinkedQueue<>();
    LinkedQueue<Integer> specCounts = new LinkedQueue<>();
    
    // Collect specialization data
    for (Doctor doctor : doctors) {
        String spec = doctor.getSpecialization();
        
        if (!specializations.contains(spec)) {
            specializations.enqueue(spec);
            
            // Initialize min/max fees and count for this specialization
            minFees.enqueue(doctor.getConsultationFee());
            maxFees.enqueue(doctor.getConsultationFee());
            specCounts.enqueue(1);
        } else {
            // Update existing specialization data
            LinkedQueue<String> tempSpecs = new LinkedQueue<>();
            LinkedQueue<Double> tempMin = new LinkedQueue<>();
            LinkedQueue<Double> tempMax = new LinkedQueue<>();
            LinkedQueue<Integer> tempCount = new LinkedQueue<>();
            
            while (!specializations.isEmpty()) {
                String currentSpec = specializations.dequeue();
                double currentMin = minFees.dequeue();
                double currentMax = maxFees.dequeue();
                int currentCount = specCounts.dequeue();
                
                if (currentSpec.equals(spec)) {
                    currentMin = Math.min(currentMin, doctor.getConsultationFee());
                    currentMax = Math.max(currentMax, doctor.getConsultationFee());
                    currentCount++;
                }
                
                tempSpecs.enqueue(currentSpec);
                tempMin.enqueue(currentMin);
                tempMax.enqueue(currentMax);
                tempCount.enqueue(currentCount);
            }
            
            specializations = tempSpecs;
            minFees = tempMin;
            maxFees = tempMax;
            specCounts = tempCount;
        }
    }
    
    // Graphical representation section
    report.append("GRAPHICAL REPRESENTATION OF SPECIALIZATION DISTRIBUTION\n");
    report.append("_________________________________________________________\n\n");

    // Improved specialization distribution visualization
    final int BOX_WIDTH = 35;  // Adjusted to match your example
    final String SPEC_FORMAT = " >>> %s (%d) ";

    // First, build all specialization entries
    LinkedQueue<String> specEntries = new LinkedQueue<>();
    LinkedQueue<String> tempSpecs = new LinkedQueue<>();
    LinkedQueue<Integer> tempCounts = new LinkedQueue<>();

    while (!specializations.isEmpty()) {
        String spec = specializations.dequeue();
        int count = specCounts.dequeue();
        tempSpecs.enqueue(spec);
        tempCounts.enqueue(count);
    
        String entry = String.format(SPEC_FORMAT, spec, count);
        specEntries.enqueue(entry);
    }

    // Reset the queues for later use
    specializations = tempSpecs;
    specCounts = tempCounts;

    // Build the distribution lines
    LinkedQueue<String> distributionLines = new LinkedQueue<>();
    StringBuilder currentLine = new StringBuilder("      |");
    int entriesInLine = 0;

    while (!specEntries.isEmpty()) {
        String entry = specEntries.dequeue();
    
        // Check if adding this entry would exceed line width
        if (currentLine.length() + entry.length() > BOX_WIDTH) {
            // Pad the line to maintain box width
            while (currentLine.length() < BOX_WIDTH) {
                currentLine.append(" ");
            }
            currentLine.append("|");
            distributionLines.enqueue(currentLine.toString());
        
            // Start new line
            currentLine = new StringBuilder("      |");
            entriesInLine = 0;
        }
    
        currentLine.append(entry);
        entriesInLine++;
    }

    // Add the last line if it has content
    if (currentLine.length() > 7) {  // "      |".length() = 7
        while (currentLine.length() < BOX_WIDTH) {
            currentLine.append(" ");
        }
        currentLine.append("|");
        distributionLines.enqueue(currentLine.toString());
    }

    // Add to report
    report.append("               SPECIALIZATION DISTRIBUTION (No. of Doctors)\n");
    report.append("      +").append(new String(new char[BOX_WIDTH-7]).replace('\0', '-')).append("+\n");

    while (!distributionLines.isEmpty()) {
        report.append(distributionLines.dequeue()).append("\n");
    }

    report.append("      +").append(new String(new char[BOX_WIDTH-7]).replace('\0', '-')).append("+\n\n");
    

    // Consultation fee range section
    report.append("               CONSULTATION FEE RANGE BY SPECIALIZATION (RM)\n");
    report.append("      +-----------------------------------------------------+\n");
    
    while (!specializations.isEmpty()) {
        String spec = specializations.dequeue();
        double minFee = minFees.dequeue();
        double maxFee = maxFees.dequeue();
        int specCount = specCounts.dequeue();
        
        // Calculate bar length (simplified visualization)
        int barLength = (int) ((minFee + maxFee) / 2 / 10);
        String bar = new String(new char[barLength]).replace('\0', '=');
        
        report.append(String.format("%-6s | %s %.0f-%.0f\n", 
            spec.substring(0, Math.min(6, spec.length())), 
            bar, minFee, maxFee));
    }
    
    report.append("      +-----------------------------------------------------+\n\n");
    
    // Doctor details table
    report.append("| Doctor ID | Doctor Name       | Specialization       | Yrs Exp | Fee (RM) | Available |\n");
    report.append("|-----------|-------------------|-----------------------|---------|----------|-----------|\n");
    
    // Sort doctors by ID for consistent display
    sortDoctors(doctors);
    
    for (Doctor doctor : doctors) {
        report.append(String.format("| %-9s | %-17s | %-21s | %-7d | %-8.0f | %-9s |\n",
            doctor.getDoctorID(),
            doctor.getName(),
            doctor.getSpecialization(),
            doctor.getYearsOfExperience(),
            doctor.getConsultationFee(),
            doctor.isAvailable() ? "Yes" : "No"));
    }
    
    // Key statistics section
    report.append("\nKEY STATISTICS:\n");
    
    // Calculate statistics
    if (doctors.length > 0) {
        Doctor highestFeeDoctor = doctors[0];
        Doctor lowestFeeDoctor = doctors[0];
        double totalFee = 0;
        double[] specTotals = new double[tempSpecs.size()];
        int[] specCountsArr = new int[tempSpecs.size()];
        
        // Reset temp queues
        tempSpecs = new LinkedQueue<>();
        while (!specializations.isEmpty()) tempSpecs.enqueue(specializations.dequeue());
        
        for (Doctor doctor : doctors) {
            totalFee += doctor.getConsultationFee();
            
            if (doctor.getConsultationFee() > highestFeeDoctor.getConsultationFee()) {
                highestFeeDoctor = doctor;
            }
            if (doctor.getConsultationFee() < lowestFeeDoctor.getConsultationFee()) {
                lowestFeeDoctor = doctor;
            }
        }
        
        double avgFee = totalFee / doctors.length;
        
        report.append(String.format("- Highest Fee: %s (%s) at RM%.0f/session\n",
            highestFeeDoctor.getDoctorID(),
            highestFeeDoctor.getSpecialization(),
            highestFeeDoctor.getConsultationFee()));
        
        report.append(String.format("- Lowest Fee: %s (%s) at RM%.0f/session\n",
            lowestFeeDoctor.getDoctorID(),
            lowestFeeDoctor.getSpecialization(),
            lowestFeeDoctor.getConsultationFee()));
        
        report.append(String.format("- Average Fee: RM%.0f/session\n", avgFee));
        
        // Calculate specialty averages
        if (!tempSpecs.isEmpty()) {
            String mostExpensiveSpec = "";
            double maxSpecAvg = 0;
            String mostAffordableSpec = "";
            double minSpecAvg = Double.MAX_VALUE;
            
            while (!tempSpecs.isEmpty()) {
                String spec = tempSpecs.dequeue();
                double specTotal = 0;
                int specCount = 0;
                
                for (Doctor doctor : doctors) {
                    if (doctor.getSpecialization().equals(spec)) {
                        specTotal += doctor.getConsultationFee();
                        specCount++;
                    }
                }
                
                double specAvg = specTotal / specCount;
                if (specAvg > maxSpecAvg) {
                    maxSpecAvg = specAvg;
                    mostExpensiveSpec = spec;
                }
                if (specAvg < minSpecAvg) {
                    minSpecAvg = specAvg;
                    mostAffordableSpec = spec;
                }
            }
            
            report.append(String.format("- Most Expensive Specialty: %s (Avg RM%.0f)\n",
                mostExpensiveSpec, maxSpecAvg));
            report.append(String.format("- Most Affordable Specialty: %s (Avg RM%.0f)\n",
                mostAffordableSpec, minSpecAvg));
        }
    }
    
    report.append("\n***************************************************************************************************************\n");
    report.append("END OF REPORT");
    
    return report.toString();
}

// Helper method to sort doctors by ID (using bubble sort as required)
private void sortDoctors(Doctor[] doctors) {
    boolean swapped;
    do {
        swapped = false;
        for (int i = 0; i < doctors.length - 1; i++) {
            if (doctors[i].getDoctorID().compareTo(doctors[i+1].getDoctorID()) > 0) {
                Doctor temp = doctors[i];
                doctors[i] = doctors[i+1];
                doctors[i+1] = temp;
                swapped = true;
            }
        }
    } while (swapped);
}
//-----------------------------------------------------------------------------------------------------------------//  
    public String generateSeniorDoctorsReport(int minYears) {
        QueueInterface<Doctor> seniors = doctorQueue.filter(d -> 
            d.getYearsOfExperience() >= minYears);
        
        StringBuilder report = new StringBuilder(
            String.format("Senior Doctors Report (%d+ years experience):\n", minYears));
        
        for (Doctor doctor : seniors.toArray(new Doctor[0])) {
            report.append(String.format("- %s (%s): %d years\n", 
                doctor.getName(), 
                doctor.getSpecialization(), 
                doctor.getYearsOfExperience()));
        }
        
        return report.toString();
    }
}
