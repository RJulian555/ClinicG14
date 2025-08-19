package dao;

import adt.*;
import control.*;
import entity.*;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalTime;
/**
 *
 * @author user
 */
public class ClinicInitializer {
    
    
     public static void initializeSampleDoctors(DoctorManager manager) {
        try {
            // Cardiology  
            addSampleDoctor(manager, "D101", "Dr. Ahmad", "Cardiology", "+60123456789", 12, 150.00, new String[]{"2023-12-25", "2024-01-01"}, "9am-5pm");
            addSampleDoctor(manager, "D102", "Dr. Siti", "Cardiology", "+60198765432", 8, 120.00, new String[]{"2023-12-31"}, "10am-6pm");
            addSampleDoctor(manager, "D103", "Dr. Lim", "Cardiology", "+60111223344", 15, 180.00, new String[]{}, "8am-4pm");
            addSampleDoctor(manager, "D104", "Dr. Fatimah", "Cardiology", "+60122334455", 10, 160.00, new String[]{"2024-02-14"}, "11am-7pm");

            // Pediatrics  
            addSampleDoctor(manager, "D201", "Dr. Mei Ling", "Pediatrics", "+60345678901", 15, 100.00, new String[]{}, "8am-4pm");
            addSampleDoctor(manager, "D202", "Dr. Kumar", "Pediatrics", "+60456789012", 5, 80.00, new String[]{"2024-02-01"}, "2pm-10pm");
            addSampleDoctor(manager, "D203", "Dr. Ali", "Pediatrics", "+60333444555", 7, 90.00, new String[]{"2024-01-15"}, "9am-5pm");
            addSampleDoctor(manager, "D204", "Dr. Aina", "Pediatrics", "+60444555666", 12, 110.00, new String[]{}, "10am-6pm");

            // Orthopedics  
            addSampleDoctor(manager, "D301", "Dr. Rajesh", "Orthopedics", "+60567890123", 20, 200.00, new String[]{"2023-12-20", "2023-12-21"}, "9am-5pm");
            addSampleDoctor(manager, "D302", "Dr. Fatimah", "Orthopedics", "+60678901234", 7, 130.00, new String[]{}, "11am-7pm");
            addSampleDoctor(manager, "D303", "Dr. Wong", "Orthopedics", "+60555666777", 18, 220.00, new String[]{"2024-01-10"}, "8am-4pm");
            addSampleDoctor(manager, "D304", "Dr. Sofia", "Orthopedics", "+60666777888", 9, 170.00, new String[]{}, "2pm-10pm");

            // Neurology  
            addSampleDoctor(manager, "D401", "Dr. Lim", "Neurology", "+60789012345", 18, 250.00, new String[]{"2024-01-15"}, "8am-4pm");
            addSampleDoctor(manager, "D402", "Dr. Ali", "Neurology", "+60890123456", 10, 180.00, new String[]{}, "10am-6pm");
            addSampleDoctor(manager, "D403", "Dr. Chen", "Neurology", "+60777888999", 22, 280.00, new String[]{"2024-02-05"}, "9am-5pm");
            addSampleDoctor(manager, "D404", "Dr. Priya", "Neurology", "+60888999000", 14, 210.00, new String[]{}, "11am-7pm");

            // Oncology 
            addSampleDoctor(manager, "D501", "Dr. Chen", "Oncology", "+60901234567", 22, 300.00, new String[]{"2023-12-28"}, "9am-5pm");
            addSampleDoctor(manager, "D502", "Dr. Devi", "Oncology", "+60111234567", 6, 160.00, new String[]{}, "2pm-10pm");
            addSampleDoctor(manager, "D503", "Dr. Ahmad", "Oncology", "+60999887766", 25, 320.00, new String[]{"2024-01-20"}, "8am-4pm");
            addSampleDoctor(manager, "D504", "Dr. Anis", "Oncology", "+60188776655", 8, 190.00, new String[]{}, "10am-6pm");

            // General Surgery 
            addSampleDoctor(manager, "D601", "Dr. Wong", "General Surgery", "+60122345678", 14, 170.00, new String[]{"2024-01-10"}, "8am-4pm");
            addSampleDoctor(manager, "D602", "Dr. Anwar", "General Surgery", "+60133456789", 9, 140.00, new String[]{}, "10am-6pm");
            addSampleDoctor(manager, "D603", "Dr. Kamal", "General Surgery", "+60144556677", 19, 200.00, new String[]{"2024-02-15"}, "9am-5pm");
            addSampleDoctor(manager, "D604", "Dr. Nina", "General Surgery", "+60155667788", 11, 160.00, new String[]{}, "11am-7pm");

            // Dermatology 
            addSampleDoctor(manager, "D701", "Dr. Tan", "Dermatology", "+60144567890", 11, 110.00, new String[]{"2023-12-15"}, "9am-5pm");
            addSampleDoctor(manager, "D702", "Dr. Kamal", "Dermatology", "+60155678901", 4, 90.00, new String[]{}, "11am-7pm");
            addSampleDoctor(manager, "D703", "Dr. Lee", "Dermatology", "+60166778899", 16, 130.00, new String[]{"2024-01-25"}, "8am-4pm");
            addSampleDoctor(manager, "D704", "Dr. Sara", "Dermatology", "+60177889900", 7, 100.00, new String[]{}, "10am-6pm");

            // Ophthalmology 
            addSampleDoctor(manager, "D801", "Dr. Lee", "Ophthalmology", "+60166789012", 17, 190.00, new String[]{"2024-01-05"}, "8am-4pm");
            addSampleDoctor(manager, "D802", "Dr. Saras", "Ophthalmology", "+60177890123", 8, 125.00, new String[]{}, "10am-6pm");
            addSampleDoctor(manager, "D803", "Dr. Raj", "Ophthalmology", "+60188992233", 20, 210.00, new String[]{"2024-02-20"}, "9am-5pm");
            addSampleDoctor(manager, "D804", "Dr. Maya", "Ophthalmology", "+60199003344", 12, 170.00, new String[]{}, "11am-7pm");

            // Emergency Medicine 
            addSampleDoctor(manager, "D901", "Dr. John", "Emergency Medicine", "+60188901234", 13, 160.00, new String[]{}, "24-hour shifts");
            addSampleDoctor(manager, "D902", "Dr. Aisha", "Emergency Medicine", "+60199012345", 5, 110.00, new String[]{"2023-12-24"}, "24-hour shifts");
            addSampleDoctor(manager, "D903", "Dr. Sam", "Emergency Medicine", "+60100112233", 18, 190.00, new String[]{"2024-01-31"}, "24-hour shifts");
            addSampleDoctor(manager, "D904", "Dr. Lina", "Emergency Medicine", "+60111223344", 9, 140.00, new String[]{}, "24-hour shifts");

            // Psychiatry 
            addSampleDoctor(manager, "DC01", "Dr. Richard", "Psychiatry", "+60109123456", 19, 220.00, new String[]{"2024-01-20"}, "9am-5pm");
            addSampleDoctor(manager, "DC02", "Dr. Nor", "Psychiatry", "+60119234567", 7, 150.00, new String[]{}, "2pm-10pm");
            addSampleDoctor(manager, "DC03", "Dr. David", "Psychiatry", "+60109334455", 22, 240.00, new String[]{"2024-02-10"}, "8am-4pm");
            addSampleDoctor(manager, "DC04", "Dr. Zara", "Psychiatry", "+60119445566", 10, 180.00, new String[]{}, "10am-6pm");    
            
            
        } catch (Exception e) {
            System.out.println("Error loading sample data: " + e.getMessage());
        }
    }

  
     
    private static void addSampleDoctor(DoctorManager manager, String id, String name, 
            String specialization, String contact, int experience, double fee, 
            String[] leaveDates, String hours) {
        
        Doctor doctor = new Doctor(id, name, specialization);
        doctor.setContactNumber(contact);
        doctor.setYearsOfExperience(experience);
        doctor.setConsultationFee(fee);
        doctor.setLeaveDates(leaveDates);
        doctor.setWorkingHours(hours);
        doctor.setAvailable(leaveDates.length == 0);
        doctor.setOnLeave(leaveDates.length > 0);
        
        manager.addDoctor(doctor);
    }
    
    
    public static void initializeSamplePharmacyStock(PharmacyControl pharmacyControl) {
        try {
            addSampleMedication(pharmacyControl, "M001", "Paracetamol", "Pain reliever", 10.50, 100, "Tablet");
            addSampleMedication(pharmacyControl, "M002", "Ibuprofen", "Anti-inflammatory", 15.00, 50, "Tablet");
            addSampleMedication(pharmacyControl, "M003", "Cough Syrup", "Soothes cough", 25.00, 30, "Liquid");
            addSampleMedication(pharmacyControl, "M004", "Antacid", "Relieves heartburn", 12.75, 80, "Liquid");
            addSampleMedication(pharmacyControl, "M005", "Aspirin", "Blood thinner", 8.00, 20, "Tablet");
            addSampleMedication(pharmacyControl, "M006", "Amoxicillin", "Antibiotic", 35.20, 60, "Capsule");
            addSampleMedication(pharmacyControl, "M007", "Vitamin C", "Supplement", 18.00, 150, "Tablet");
        } catch (Exception e) {
            System.out.println("Error loading sample pharmacy data: " + e.getMessage());
        }
    }
    
    private static void addSampleMedication(PharmacyControl pharmacyControl, String id, String name, 
            String description, double price, int quantity, String type) {

        // Here we create the entity object within the DAO layer
        Pharmacy medication = new Pharmacy(id, name, description, price, quantity, type, new Date());
        
        // And pass the complete object to the control layer's new method
        pharmacyControl.addMedication(medication);
    }
    
    public static void initializeSampleDiagnoses(MedicalTreatmentControl treatmentControl) {
        try {
            treatmentControl.addDiagnosisTemplate("DIAG01", "Common Flu", "Viral infection of the upper respiratory tract. Recommend rest and hydration.");
            treatmentControl.addDiagnosisTemplate("DIAG02", "Strep Throat", "Bacterial infection causing a severe sore throat. Requires antibiotics.");
            treatmentControl.addDiagnosisTemplate("DIAG03", "Minor Sprain", "Stretching or tearing of ligaments. Apply RICE method (Rest, Ice, Compression, Elevation).");
            treatmentControl.addDiagnosisTemplate("DIAG04", "Tension Headache", "Mild to moderate pain in the head, often described as a tight band. Suggest pain relievers.");
            treatmentControl.addDiagnosisTemplate("DIAG05", "Migraine", "Severe, recurring headache, often accompanied by nausea and light sensitivity.");
            treatmentControl.addDiagnosisTemplate("DIAG06", "Indigestion", "Discomfort in the upper abdomen, often after eating. Recommend antacids.");
        } catch (Exception e) {
            System.out.println("Error loading sample diagnoses: " + e.getMessage());
        }
    }
    
    public static void initializeSamplePatients(PatientManager patientManager) {
        try {
            // Patients in queue
            addSamplePatient(patientManager, "Ali Bin Abu", "990101014321", "0123456789", "01/01/1999", 
                "M", "A+", "Peanuts", 70.5, 170.0, true, "15/03/2024");
            addSamplePatient(patientManager, "Siti Aminah", "000202023456", "0139876543", "02/02/2000", 
                "F", "O-", "", 55.0, 160.0, true, "18/03/2024");
            addSamplePatient(patientManager, "John Tan", "981010104321", "0112233445", "10/10/1998", 
                "M", "B+", "Seafood", 68.0, 175.0, true, "20/03/2024");
            addSamplePatient(patientManager, "Nur Izzati", "010303035432", "0199988776", "03/03/2001", 
                "F", "AB-", "", 60.0, 162.0, true, "22/03/2024");
            addSamplePatient(patientManager, "Raj Kumar", "970707074321", "0105678901", "07/07/1997", 
                "M", "O+", "Dust", 72.3, 180.0, true, "25/03/2024");
            
            // Patients not in queue
            addSamplePatient(patientManager, "Lim Mei Ling", "960606062222", "0143322110", "06/06/1996", 
                "F", "A-", "Lactose", 50.0, 155.0, false, "05/01/2024");
            addSamplePatient(patientManager, "Ahmad Fauzi", "950505051234", "0176543210", "05/05/1995", 
                "M", "B-", "Penicillin", 80.0, 178.0, false, "10/01/2024");
            addSamplePatient(patientManager, "Sarah Chong", "940404043210", "0167890123", "04/04/1994", 
                "F", "AB+", "", 58.0, 165.0, false, "15/01/2024");
            addSamplePatient(patientManager, "Mohd Razak", "930303032345", "0156789012", "03/03/1993", 
                "M", "A+", "Shellfish", 75.0, 172.0, false, "20/01/2024");
            addSamplePatient(patientManager, "Tan Wei Ling", "920202021234", "0198765432", "02/02/1992", 
                "F", "O+", "Nuts", 52.0, 158.0, false, "25/01/2024");
            
            // More patients
            addSamplePatient(patientManager, "Kumar Selvam", "910101013456", "0134567890", "01/01/1991", 
                "M", "B+", "", 82.0, 182.0, false, "01/02/2024");
            addSamplePatient(patientManager, "Fatimah Binti Abdullah", "891212123456", "0123456780", "12/12/1989", 
                "F", "O-", "Eggs", 65.0, 168.0, false, "05/02/2024");
            addSamplePatient(patientManager, "Lee Chong Wei", "881010102345", "0112345678", "10/10/1988", 
                "M", "A-", "", 70.0, 175.0, true, "10/02/2024");
            addSamplePatient(patientManager, "Nurul Syafiqah", "870707073210", "0190123456", "07/07/1987", 
                "F", "AB-", "Soy", 54.0, 160.0, false, "15/02/2024");
            addSamplePatient(patientManager, "Ramesh Naidu", "860606062345", "0178901234", "06/06/1986", 
                "M", "O+", "", 78.0, 180.0, true, "20/02/2024");
            
            // Additional patients
            addSamplePatient(patientManager, "Wong Mei Chen", "850505051234", "0167890123", "05/05/1985", 
                "F", "B+", "Dairy", 56.0, 163.0, false, "01/03/2024");
            addSamplePatient(patientManager, "Ahmad Hakimi", "840404043456", "0156789012", "04/04/1984", 
                "M", "A+", "", 85.0, 185.0, false, "05/03/2024");
            addSamplePatient(patientManager, "Norhayati Binti Osman", "830303032345", "0145678901", "03/03/1983", 
                "F", "O-", "Wheat", 62.0, 170.0, true, "10/03/2024");
            addSamplePatient(patientManager, "Chan Kok Leong", "820202021234", "0134567890", "02/02/1982", 
                "M", "AB+", "", 76.0, 178.0, false, "15/03/2024");
            addSamplePatient(patientManager, "Siti Nurhaliza", "810101013456", "0123456789", "01/01/1981", 
                "F", "B-", "Peanuts", 60.0, 165.0, false, "20/03/2024");
            
            // More diverse patients
            addSamplePatient(patientManager, "Muthu Samy", "791212123456", "0198765432", "12/12/1979", 
                "M", "O+", "", 90.0, 182.0, true, "01/04/2024");
            addSamplePatient(patientManager, "Lim Siew Ling", "781010102345", "0187654321", "10/10/1978", 
                "F", "A+", "Shellfish", 58.0, 162.0, false, "05/04/2024");
            addSamplePatient(patientManager, "Abdul Rahman", "770707073210", "0176543210", "07/07/1977", 
                "M", "B+", "", 82.0, 178.0, false, "10/04/2024");
            addSamplePatient(patientManager, "Tan Bee Lian", "760606062345", "0165432109", "06/06/1976", 
                "F", "AB-", "Latex", 63.0, 168.0, true, "15/04/2024");
            addSamplePatient(patientManager, "Krishnan A/L Muthu", "750505051234", "0154321098", "05/05/1975", 
                "M", "O-", "Eggs", 88.0, 183.0, false, "20/04/2024");
            
            // Older patients
            addSamplePatient(patientManager, "Ong Swee Lin", "740404043456", "0143210987", "04/04/1974", 
                "F", "A-", "", 65.0, 170.0, false, "01/05/2024");
            addSamplePatient(patientManager, "Mohd Faisal", "730303032345", "0132109876", "03/03/1973", 
                "M", "B-", "Penicillin", 92.0, 185.0, true, "05/05/2024");
            addSamplePatient(patientManager, "Yusuf Bin Ismail", "720202021234", "0121098765", "02/02/1972", 
                "M", "AB+", "", 84.0, 180.0, false, "10/05/2024");
            addSamplePatient(patientManager, "Noraini Binti Ali", "710101013456", "0110987654", "01/01/1971", 
                "F", "O+", "Nuts", 70.0, 175.0, false, "15/05/2024");
            addSamplePatient(patientManager, "Robert Chan", "691212123456", "0198765432", "12/12/1969", 
                "M", "A+", "", 95.0, 188.0, true, "20/05/2024");
            
            // Senior patients
            addSamplePatient(patientManager, "Maimunah Binti Ahmad", "681010102345", "0187654321", "10/10/1968", 
                "F", "B+", "Dairy", 68.0, 172.0, false, "01/06/2024");
            addSamplePatient(patientManager, "Ravi Shankar", "670707073210", "0176543210", "07/07/1967", 
                "M", "O-", "", 87.0, 182.0, false, "05/06/2024");
            addSamplePatient(patientManager, "Lily Wong", "660606062345", "0165432109", "06/06/1966", 
                "F", "AB+", "Wheat", 72.0, 178.0, true, "10/06/2024");
            addSamplePatient(patientManager, "Hassan Bin Omar", "650505051234", "0154321098", "05/05/1965", 
                "M", "A-", "", 90.0, 185.0, false, "15/06/2024");
            addSamplePatient(patientManager, "Susila Devi", "640404043456", "0143210987", "04/04/1964", 
                "F", "B-", "Peanuts", 75.0, 180.0, false, "20/06/2024");
            
            // More senior patients
            addSamplePatient(patientManager, "Goh Peng Lim", "630303032345", "0132109876", "03/03/1963", 
                "M", "O+", "Shellfish", 92.0, 188.0, true, "01/07/2024");
            addSamplePatient(patientManager, "Zainab Binti Yusof", "620202021234", "0121098765", "02/02/1962", 
                "F", "AB-", "", 78.0, 175.0, false, "05/07/2024");
            addSamplePatient(patientManager, "Arunachalam Muthu", "610101013456", "0110987654", "01/01/1961", 
                "M", "A+", "Latex", 85.0, 182.0, false, "10/07/2024");
            addSamplePatient(patientManager, "Lim Siew Hong", "591212123456", "0198765432", "12/12/1959", 
                "F", "B+", "Eggs", 80.0, 178.0, true, "15/07/2024");
            addSamplePatient(patientManager, "Ismail Bin Ahmad", "581010102345", "0187654321", "10/10/1958", 
                "M", "O-", "", 95.0, 185.0, false, "20/07/2024");
            
            // Elderly patients
            addSamplePatient(patientManager, "Chong Mei Fong", "570707073210", "0176543210", "07/07/1957", 
                "F", "AB+", "", 82.0, 180.0, false, "01/08/2024");
            addSamplePatient(patientManager, "Muthu Palanisamy", "560606062345", "0165432109", "06/06/1956", 
                "M", "A-", "Penicillin", 98.0, 188.0, true, "05/08/2024");
            addSamplePatient(patientManager, "Aishah Binti Mohd", "550505051234", "0154321098", "05/05/1955", 
                "F", "B-", "Nuts", 85.0, 175.0, false, "10/08/2024");
            addSamplePatient(patientManager, "Tan Kok Wai", "540404043456", "0143210987", "04/04/1954", 
                "M", "O+", "", 100.0, 185.0, false, "15/08/2024");
            addSamplePatient(patientManager, "Norhayati Binti Ali", "530303032345", "0132109876", "03/03/1953", 
                "F", "AB-", "Dairy", 88.0, 178.0, true, "20/08/2024");
            
            // Final set of patients
            addSamplePatient(patientManager, "Raja Kumar", "520202021234", "0121098765", "02/02/1952", 
                "M", "A+", "", 92.0, 182.0, false, "01/09/2024");
            addSamplePatient(patientManager, "Lim Siew Chin", "510101013456", "0110987654", "01/01/1951", 
                "F", "B+", "Wheat", 78.0, 175.0, false, "05/09/2024");
            addSamplePatient(patientManager, "Ahmad Bin Hassan", "491212123456", "0198765432", "12/12/1949", 
                "M", "O-", "Peanuts", 102.0, 188.0, true, "10/09/2024");
            addSamplePatient(patientManager, "Wong Mei Yee", "481010102345", "0187654321", "10/10/1948", 
                "F", "AB+", "", 85.0, 180.0, false, "15/09/2024");
            addSamplePatient(patientManager, "Muthu Samynathan", "470707073210", "0176543210", "07/07/1947", 
                "M", "A-", "Shellfish", 95.0, 185.0, false, "20/09/2024");
            
            // Additional patients - Group 1
            addSamplePatient(patientManager, "Chen Wei Liang", "961111115678", "0144556677", "11/11/1996", 
            "M", "B+", "Pollen", 72.0, 176.0, true, "22/03/2024");
            addSamplePatient(patientManager, "Nur Syakirah", "970909094321", "0133445566", "09/09/1997", 
            "F", "A-", "Shellfish", 58.5, 163.0, false, "12/02/2024");
            addSamplePatient(patientManager, "David Ng", "950808083456", "0122334455", "08/08/1995", 
            "M", "O+", "", 81.0, 179.0, true, "18/04/2024");
            addSamplePatient(patientManager, "Aisyah Binti Mohd", "980707072345", "0199887766", "07/07/1998", 
            "F", "AB+", "Dust mites", 62.0, 167.0, false, "05/03/2024");
            addSamplePatient(patientManager, "Karthik Murugan", "940606061234", "0188776655", "06/06/1994", 
            "M", "B-", "Penicillin", 76.0, 177.0, true, "28/04/2024");

// Additional patients - Group 2
            addSamplePatient(patientManager, "Lim Jia Hui", "990505054321", "0177665544", "05/05/1999", 
            "F", "O-", "Nuts", 54.0, 161.0, false, "15/01/2024");
            addSamplePatient(patientManager, "Amirul Hafiz", "930404043210", "0166554433", "04/04/1993", 
            "M", "A+", "", 83.5, 181.0, true, "02/05/2024");
            addSamplePatient(patientManager, "Priya Devi", "910303032345", "0155443322", "03/03/1991", 
            "F", "B+", "Latex", 59.0, 164.0, false, "22/02/2024");
            addSamplePatient(patientManager, "Tan Kok Ming", "900202021456", "0144332211", "02/02/1990", 
            "M", "AB-", "Eggs", 77.0, 178.0, true, "08/05/2024");
            addSamplePatient(patientManager, "Siti Aishah", "880101013456", "0133221100", "01/01/1988", 
            "F", "O+", "Soy", 63.5, 169.0, false, "18/01/2024");

// Additional patients - Group 3
            addSamplePatient(patientManager, "Rahman Bin Ali", "871212123210", "0122110099", "12/12/1987", 
            "M", "A-", "", 85.0, 182.0, true, "12/05/2024");
            addSamplePatient(patientManager, "Chong Mei Ling", "861010102345", "0111009988", "10/10/1986", 
            "F", "B-", "Dairy", 60.0, 165.0, false, "25/02/2024");
            addSamplePatient(patientManager, "Manoj Kumar", "850909093456", "0199887766", "09/09/1985", 
            "M", "AB+", "Peanuts", 79.0, 180.0, true, "15/05/2024");
            addSamplePatient(patientManager, "Nor Azlina", "840808082345", "0188776655", "08/08/1984", 
            "F", "O-", "", 64.0, 168.0, false, "08/03/2024");
            addSamplePatient(patientManager, "Goh Seng Choon", "830707071234", "0177665544", "07/07/1983", 
            "M", "A+", "Wheat", 87.0, 183.0, true, "18/05/2024");

// Additional patients - Group 4
            addSamplePatient(patientManager, "Yap Mei Fong", "820606062345", "0166554433", "06/06/1982", 
            "F", "B+", "Shellfish", 61.5, 166.0, false, "15/03/2024");
            addSamplePatient(patientManager, "Hafiz Bin Osman", "810505051456", "0155443322", "05/05/1981", 
            "M", "AB-", "", 90.0, 184.0, true, "22/05/2024");
            addSamplePatient(patientManager, "Lai Yee Ling", "800404043210", "0144332211", "04/04/1980", 
            "F", "O+", "Pollen", 66.0, 170.0, false, "22/03/2024");
            addSamplePatient(patientManager, "Rajendran A/L Muthu", "790303032345", "0133221100", "03/03/1979", 
            "M", "A-", "Penicillin", 92.5, 185.0, true, "25/05/2024");
            addSamplePatient(patientManager, "Wong Siew Peng", "780202021234", "0122110099", "02/02/1978", 
            "F", "B-", "", 68.0, 172.0, false, "28/03/2024");

// Additional patients - Group 5
            addSamplePatient(patientManager, "Ahmad Firdaus", "771111113456", "0111009988", "11/11/1977", 
            "M", "AB+", "Dust", 94.0, 186.0, true, "28/05/2024");
            addSamplePatient(patientManager, "Norlela Binti Samad", "761010102345", "0199887766", "10/10/1976", 
            "F", "O-", "Latex", 70.5, 173.0, false, "05/04/2024");
            addSamplePatient(patientManager, "Lim Chee Beng", "750909091234", "0188776655", "09/09/1975", 
            "M", "A+", "", 96.0, 187.0, true, "01/06/2024");
            addSamplePatient(patientManager, "Saraswathy Devi", "740808082345", "0177665544", "08/08/1974", 
            "F", "B+", "Eggs", 72.0, 174.0, false, "12/04/2024");
            addSamplePatient(patientManager, "Omar Bin Hussain", "730707073210", "0166554433", "07/07/1973", 
            "M", "AB-", "Soy", 98.0, 188.0, true, "05/06/2024");

// Additional patients - Group 6
            addSamplePatient(patientManager, "Tan Bee Choo", "720606061234", "0155443322", "06/06/1972", 
            "F", "O+", "", 74.0, 175.0, false, "18/04/2024");
            addSamplePatient(patientManager, "Krishnan A/L Palani", "710505053456", "0144332211", "05/05/1971", 
            "M", "A-", "Nuts", 100.0, 189.0, true, "08/06/2024");
           addSamplePatient(patientManager, "Chan Mei Yee", "700404042345", "0133221100", "04/04/1970", 
           "F", "B-", "Dairy", 76.0, 176.0, false, "25/04/2024");
           addSamplePatient(patientManager, "Ismail Bin Ibrahim", "691212123210", "0122110099", "12/12/1969", 
           "M", "AB+", "", 102.0, 190.0, true, "12/06/2024");
           addSamplePatient(patientManager, "Lim Siew Mei", "681010101234", "0111009988", "10/10/1968", 
           "F", "O-", "Wheat", 78.0, 177.0, false, "02/05/2024");

        } catch (Exception e) {
            System.out.println("Error loading sample patients: " + e.getMessage());
        }
    }
    
    private static void addSamplePatient(PatientManager patientManager, String name, String ic, 
    String contact, String dob, String gender, String bloodType, String allergies, 
    double weight, double height, boolean inQueue, String registrationDateStr) {
    
    try {
        String patientID = patientManager.generatePatientID();
        String queueID = inQueue ? patientManager.generateQueueID() : null;
        
        Patient patient = new Patient(
            patientID,
            name, 
            ic, 
            contact, 
            dob, 
            gender, 
            bloodType, 
            allergies, 
            weight, 
            height, 
            queueID,
            registrationDateStr
        );
        
        patientManager.addSamplePatient(patient, inQueue);
    } catch (IllegalArgumentException e) {
        System.err.println("Failed to add patient " + name + ": " + e.getMessage());
        // Don't increment counters for failed additions
        patientManager.rollbackCounters();
    }
}
    // Main sample consultation initializer
    public static void initializeSampleConsultations(
    ConsultationManager consultationManager,
    DoctorManager doctorManager,
    PatientManager patientManager) {

    try {
        // ===== 1. HARDCODE REQUIRED PATIENTS (P001-P008) =====
        // (If they don't exist, this creates them on the spot)
        addSamplePatient(patientManager, "Ali Bin Abu", "990101014321", "0123456789",
            "01/01/1999", "M", "A+", "Peanuts", 70.5, 170.0, true, "01/01/2023");

        addSamplePatient(patientManager, "Siti Aminah", "000202023456", "0139876543",
            "02/02/2000", "F", "O-", "", 55.0, 160.0, true, "02/01/2023");

        addSamplePatient(patientManager, "John Tan", "981010104321", "0112233445",
            "10/10/1998", "M", "B+", "Seafood", 68.0, 175.0, true, "03/01/2023");

        addSamplePatient(patientManager, "Nur Izzati", "010303035432", "0199988776",
            "03/03/2001", "F", "AB-", "", 60.0, 162.0, true, "04/01/2023");

        addSamplePatient(patientManager, "Raj Kumar", "970707074321", "0105678901",
            "07/07/1997", "M", "O+", "Dust", 72.3, 180.0, true, "05/01/2023");

        addSamplePatient(patientManager, "Lim Mei Ling", "960606062222", "0143322110",
            "06/06/1996", "F", "A-", "Lactose", 50.0, 155.0, true, "06/01/2023");

        addSamplePatient(patientManager, "Ahmad Fauzi", "950505051234", "0176543210",
            "05/05/1995", "M", "B-", "Penicillin", 80.0, 178.0, true, "07/01/2023");

        addSamplePatient(patientManager, "Sarah Chong", "940404043210", "0167890123",
            "04/04/1994", "F", "AB+", "", 58.0, 165.0, true, "08/01/2023");

        addSamplePatient(patientManager, "Hafiz Rahman", "930303033333", "0121112233",
            "03/03/1993", "M", "O+", "Shellfish", 74.0, 176.0, true, "09/01/2023");

        addSamplePatient(patientManager, "Chong Wei", "920202022222", "0185556677",
            "02/02/1992", "M", "A+", "", 65.0, 170.0, true, "10/01/2023");

        addSamplePatient(patientManager, "Amira Yusuf", "910101011111", "0178889990",
            "01/01/1991", "F", "B-", "Gluten", 59.0, 162.0, true, "11/01/2023");

        addSamplePatient(patientManager, "Michael Lee", "890909099876", "0134445566",
            "09/09/1989", "M", "AB+", "", 82.0, 185.0, true, "12/01/2023");

        addSamplePatient(patientManager, "Farah Zain", "880808088765", "0192233445",
            "08/08/1988", "F", "O-", "Nuts", 52.0, 158.0, true, "13/01/2023");

        addSamplePatient(patientManager, "Daniel Wong", "870707077654", "0123344556",
            "07/07/1987", "M", "A-", "", 75.0, 172.0, true, "14/01/2023");

        addSamplePatient(patientManager, "Aisyah Karim", "860606066543", "0166677788",
            "06/06/1986", "F", "B+", "Eggs", 54.0, 160.0, true, "15/01/2023");

        // ===== 2. ADD CONSULTATIONS (FORCE ALL 8) =====
        // Cardiology
        consultationManager.addConsultation(new Consultation(
            "C001", "D101", "P001", LocalDate.of(2025, 3, 15), LocalTime.of(10, 0),
            "Scheduled", "General", false, ""
        ));
        consultationManager.addConsultation(new Consultation(
            "C002", "D102", "P002", LocalDate.of(2025, 3, 18), LocalTime.of(14, 0),
            "Scheduled", "General", false, ""
        ));
        consultationManager.addConsultation(new Consultation(
            "C003", "D103", "P003", LocalDate.of(2025, 3, 20), LocalTime.of(11, 30),
            "Scheduled", "General", false, ""
        ));
        consultationManager.addConsultation(new Consultation(
            "C004", "D104", "P004", LocalDate.of(2025, 3, 22), LocalTime.of(15, 0),
            "Scheduled", "General", false, ""
        ));

        // Pediatrics
        consultationManager.addConsultation(new Consultation(
            "C005", "D201", "P005", LocalDate.of(2025, 3, 25), LocalTime.of(9, 30),
            "Scheduled", "General", false, ""
        ));
        consultationManager.addConsultation(new Consultation(
            "C006", "D202", "P006", LocalDate.of(2025, 3, 26), LocalTime.of(13, 0),
            "Scheduled", "General", false, ""
        ));
        consultationManager.addConsultation(new Consultation(
            "C007", "D203", "P007", LocalDate.of(2025, 3, 27), LocalTime.of(10, 30),
            "Scheduled", "General", false, ""
        ));
        consultationManager.addConsultation(new Consultation(
            "C008", "D204", "P008", LocalDate.of(2025, 3, 28), LocalTime.of(14, 0),
            "Scheduled", "General", false, ""
        ));
                
        consultationManager.addConsultation(new Consultation(
                "C009", "D301", "P009", LocalDate.of(2025, 3, 29), LocalTime.of(11, 0),
                "Scheduled", "Skin Check", false, ""
        ));
        consultationManager.addConsultation(new Consultation(
                "C010", "D302", "P010", LocalDate.of(2025, 3, 30), LocalTime.of(15, 30),
                "Scheduled", "Skin Check", false, ""
        ));

        consultationManager.addConsultation(new Consultation(
                "C011", "D401", "P011", LocalDate.of(2025, 4, 1), LocalTime.of(9, 0),
                "Scheduled", "Bone/Joint", false, ""
        ));
        consultationManager.addConsultation(new Consultation(
                "C012", "D402", "P012", LocalDate.of(2025, 4, 2), LocalTime.of(14, 30),
                "Scheduled", "Bone/Joint", false, ""
        ));

        consultationManager.addConsultation(new Consultation(
                "C013", "D501", "P013", LocalDate.of(2025, 4, 3), LocalTime.of(10, 15),
                "Scheduled", "Neuro", false, ""
        ));
        consultationManager.addConsultation(new Consultation(
                "C014", "D502", "P014", LocalDate.of(2025, 4, 4), LocalTime.of(13, 45),
                "Scheduled", "Neuro", false, ""
        ));

        consultationManager.addConsultation(new Consultation(
                "C015", "D103", "P015", LocalDate.of(2025, 4, 5), LocalTime.of(11, 30),
                "Scheduled", "General Follow-up", true, "Follow-up for routine check" // General follow-up
        ));

       
    } catch (Exception e) {
        System.out.println("CRITICAL ERROR: " + e.getMessage());
     }
    }
}