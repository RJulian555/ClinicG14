package dao;

import control.DoctorManager;
import entity.Doctor;
import entity.Consultation;
import control.ConsultationManager;
import entity.Patient;
import control.PatientManager;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;


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
    
    public static void initializeSamplePatients(PatientManager patientManager) {
    try {
        // ================= PATIENTS IN QUEUE (10 patients) =================
        addHardcodedQueuedPatient(patientManager, "Ali Bin Abu", "990101014321", "0123456789", "01/01/1999", "M", "A+", "Peanuts", 70.5, 170.0, 
                                "P001", "Q001", "01/01/2023");
        addHardcodedQueuedPatient(patientManager, "Siti Aminah", "000202023456", "0139876543", "02/02/2000", "F", "O-", "", 55.0, 160.0, 
                                "P002", "Q002", "02/01/2023");
        addHardcodedQueuedPatient(patientManager, "John Tan", "981010104321", "0112233445", "10/10/1998", "M", "B+", "Seafood", 68.0, 175.0, 
                                "P003", "Q003", "03/01/2023");
        addHardcodedQueuedPatient(patientManager, "Nur Izzati", "010303035432", "0199988776", "03/03/2001", "F", "AB-", "", 60.0, 162.0, 
                                "P004", "Q004", "04/01/2023");
        addHardcodedQueuedPatient(patientManager, "Raj Kumar", "970707074321", "0105678901", "07/07/1997", "M", "O+", "Dust", 72.3, 180.0, 
                                "P005", "Q005", "05/01/2023");
        addHardcodedQueuedPatient(patientManager, "Lim Mei Ling", "960606062222", "0143322110", "06/06/1996", "F", "A-", "Lactose", 50.0, 155.0, 
                                "P006", "Q006", "06/01/2023");
        addHardcodedQueuedPatient(patientManager, "Ahmad Fauzi", "950505051234", "0176543210", "05/05/1995", "M", "B-", "Penicillin", 80.0, 178.0, 
                                "P007", "Q007", "07/01/2023");
        addHardcodedQueuedPatient(patientManager, "Sarah Chong", "940404043210", "0167890123", "04/04/1994", "F", "AB+", "", 58.0, 165.0, 
                                "P008", "Q008", "08/01/2023");
        addHardcodedQueuedPatient(patientManager, "Mohd Razak", "930303032345", "0156789012", "03/03/1993", "M", "A+", "Shellfish", 75.0, 172.0, 
                                "P009", "Q009", "09/01/2023");
        addHardcodedQueuedPatient(patientManager, "Tan Wei Ling", "920202021234", "0198765432", "02/02/1992", "F", "O+", "Nuts", 52.0, 158.0, 
                                "P010", "Q010", "10/01/2023");

        // ================= PATIENTS NOT IN QUEUE (40 patients) =================
        addHardcodedNonQueuedPatient(patientManager, "Kumar Selvam", "910101013456", "0134567890", "01/01/1991", "M", "B+", "", 82.0, 182.0, 
                                   "P011", "11/01/2023");
        addHardcodedNonQueuedPatient(patientManager, "Fatimah Binti Abdullah", "891212123456", "0123456780", "12/12/1989", "F", "O-", "Eggs", 65.0, 168.0, 
                                   "P012", "12/01/2023");
        addHardcodedNonQueuedPatient(patientManager, "Lee Chong Wei", "881010102345", "0112345678", "10/10/1988", "M", "A-", "", 70.0, 175.0, 
                                   "P013", "13/01/2023");
        addHardcodedNonQueuedPatient(patientManager, "Nurul Syafiqah", "870707073210", "0190123456", "07/07/1987", "F", "AB-", "Soy", 54.0, 160.0, 
                                   "P014", "14/01/2023");
        addHardcodedNonQueuedPatient(patientManager, "Ramesh Naidu", "860606062345", "0178901234", "06/06/1986", "M", "O+", "", 78.0, 180.0, 
                                   "P015", "15/01/2023");
        addHardcodedNonQueuedPatient(patientManager, "Wong Mei Chen", "850505051234", "0167890123", "05/05/1985", "F", "B+", "Dairy", 56.0, 163.0, 
                                   "P016", "16/01/2023");
        addHardcodedNonQueuedPatient(patientManager, "Ahmad Hakimi", "840404043456", "0156789012", "04/04/1984", "M", "A+", "", 85.0, 185.0, 
                                   "P017", "17/01/2023");
        addHardcodedNonQueuedPatient(patientManager, "Norhayati Binti Osman", "830303032345", "0145678901", "03/03/1983", "F", "O-", "Wheat", 62.0, 170.0, 
                                   "P018", "18/01/2023");
        addHardcodedNonQueuedPatient(patientManager, "Chan Kok Leong", "820202021234", "0134567890", "02/02/1982", "M", "AB+", "", 76.0, 178.0, 
                                   "P019", "19/01/2023");
        addHardcodedNonQueuedPatient(patientManager, "Siti Nurhaliza", "810101013456", "0123456789", "01/01/1981", "F", "B-", "Peanuts", 60.0, 165.0, 
                                   "P020", "20/01/2023");
        
        addHardcodedNonQueuedPatient(patientManager, "Muthu Samy", "791212123456", "0198765432", "12/12/1979", "M", "O+", "", 90.0, 182.0, 
                                   "P021", "21/01/2023");
        addHardcodedNonQueuedPatient(patientManager, "Lim Siew Ling", "781010102345", "0187654321", "10/10/1978", "F", "A+", "Shellfish", 58.0, 162.0, 
                                   "P022", "22/01/2023");
        addHardcodedNonQueuedPatient(patientManager, "Abdul Rahman", "770707073210", "0176543210", "07/07/1977", "M", "B+", "", 82.0, 178.0, 
                                   "P023", "23/01/2023");
        addHardcodedNonQueuedPatient(patientManager, "Tan Bee Lian", "760606062345", "0165432109", "06/06/1976", "F", "AB-", "Latex", 63.0, 168.0, 
                                   "P024", "24/01/2023");
        addHardcodedNonQueuedPatient(patientManager, "Krishnan A/L Muthu", "750505051234", "0154321098", "05/05/1975", "M", "O-", "Eggs", 88.0, 183.0, 
                                   "P025", "25/01/2023");
        addHardcodedNonQueuedPatient(patientManager, "Ong Swee Lin", "740404043456", "0143210987", "04/04/1974", "F", "A-", "", 65.0, 170.0, 
                                   "P026", "26/01/2023");
        addHardcodedNonQueuedPatient(patientManager, "Mohd Faisal", "730303032345", "0132109876", "03/03/1973", "M", "B-", "Penicillin", 92.0, 185.0, 
                                   "P027", "27/01/2023");
        addHardcodedNonQueuedPatient(patientManager, "Yusuf Bin Ismail", "720202021234", "0121098765", "02/02/1972", "M", "AB+", "", 84.0, 180.0, 
                                   "P028", "28/01/2023");
        addHardcodedNonQueuedPatient(patientManager, "Noraini Binti Ali", "710101013456", "0110987654", "01/01/1971", "F", "O+", "Nuts", 70.0, 175.0, 
                                   "P029", "29/01/2023");
        addHardcodedNonQueuedPatient(patientManager, "Robert Chan", "691212123456", "0198765432", "12/12/1969", "M", "A+", "", 95.0, 188.0, 
                                   "P030", "30/01/2023");
        
        addHardcodedNonQueuedPatient(patientManager, "Maimunah Binti Ahmad", "681010102345", "0187654321", "10/10/1968", "F", "B+", "Dairy", 68.0, 172.0, 
                                   "P031", "01/02/2023");
        addHardcodedNonQueuedPatient(patientManager, "Ravi Shankar", "670707073210", "0176543210", "07/07/1967", "M", "O-", "", 87.0, 182.0, 
                                   "P032", "02/02/2023");
        addHardcodedNonQueuedPatient(patientManager, "Lily Wong", "660606062345", "0165432109", "06/06/1966", "F", "AB+", "Wheat", 72.0, 178.0, 
                                   "P033", "03/02/2023");
        addHardcodedNonQueuedPatient(patientManager, "Hassan Bin Omar", "650505051234", "0154321098", "05/05/1965", "M", "A-", "", 90.0, 185.0, 
                                   "P034", "04/02/2023");
        addHardcodedNonQueuedPatient(patientManager, "Susila Devi", "640404043456", "0143210987", "04/04/1964", "F", "B-", "Peanuts", 75.0, 180.0, 
                                   "P035", "05/02/2023");
        addHardcodedNonQueuedPatient(patientManager, "Goh Peng Lim", "630303032345", "0132109876", "03/03/1963", "M", "O+", "Shellfish", 92.0, 188.0, 
                                   "P036", "06/02/2023");
        addHardcodedNonQueuedPatient(patientManager, "Zainab Binti Yusof", "620202021234", "0121098765", "02/02/1962", "F", "AB-", "", 78.0, 175.0, 
                                   "P037", "07/02/2023");
        addHardcodedNonQueuedPatient(patientManager, "Arunachalam Muthu", "610101013456", "0110987654", "01/01/1961", "M", "A+", "Latex", 85.0, 182.0, 
                                   "P038", "08/02/2023");
        addHardcodedNonQueuedPatient(patientManager, "Lim Siew Hong", "591212123456", "0198765432", "12/12/1959", "F", "B+", "Eggs", 80.0, 178.0, 
                                   "P039", "09/02/2023");
        addHardcodedNonQueuedPatient(patientManager, "Ismail Bin Ahmad", "581010102345", "0187654321", "10/10/1958", "M", "O-", "", 95.0, 185.0, 
                                   "P040", "10/02/2023");
        
        addHardcodedNonQueuedPatient(patientManager, "Chong Mei Fong", "570707073210", "0176543210", "07/07/1957", "F", "AB+", "", 82.0, 180.0, 
                                   "P041", "11/02/2023");
        addHardcodedNonQueuedPatient(patientManager, "Muthu Palanisamy", "560606062345", "0165432109", "06/06/1956", "M", "A-", "Penicillin", 98.0, 188.0, 
                                   "P042", "12/02/2023");
        addHardcodedNonQueuedPatient(patientManager, "Aishah Binti Mohd", "550505051234", "0154321098", "05/05/1955", "F", "B-", "Nuts", 85.0, 175.0, 
                                   "P043", "13/02/2023");
        addHardcodedNonQueuedPatient(patientManager, "Tan Kok Wai", "540404043456", "0143210987", "04/04/1954", "M", "O+", "", 100.0, 185.0, 
                                   "P044", "14/02/2023");
        addHardcodedNonQueuedPatient(patientManager, "Norhayati Binti Ali", "530303032345", "0132109876", "03/03/1953", "F", "AB-", "Dairy", 88.0, 178.0, 
                                   "P045", "15/02/2023");
        addHardcodedNonQueuedPatient(patientManager, "Raja Kumar", "520202021234", "0121098765", "02/02/1952", "M", "A+", "", 92.0, 182.0, 
                                   "P046", "16/02/2023");
        addHardcodedNonQueuedPatient(patientManager, "Lim Siew Chin", "510101013456", "0110987654", "01/01/1951", "F", "B+", "Wheat", 78.0, 175.0, 
                                   "P047", "17/02/2023");
        addHardcodedNonQueuedPatient(patientManager, "Ahmad Bin Hassan", "491212123456", "0198765432", "12/12/1949", "M", "O-", "Peanuts", 102.0, 188.0, 
                                   "P048", "18/02/2023");
        addHardcodedNonQueuedPatient(patientManager, "Wong Mei Yee", "481010102345", "0187654321", "10/10/1948", "F", "AB+", "", 85.0, 180.0, 
                                   "P049", "19/02/2023");
        addHardcodedNonQueuedPatient(patientManager, "Muthu Samynathan", "470707073210", "0176543210", "07/07/1947", "M", "A-", "Shellfish", 95.0, 185.0, 
                                   "P050", "20/02/2023");

        // Set counters to continue from where we left off
        patientManager.setPatientCounter(51); // Next ID would be P051
        patientManager.setQueueCounter(11);   // Next queue would be Q011
        
    } catch (Exception e) {
        System.out.println("Error loading sample patients: " + e.getMessage());
    }
}

private static void addHardcodedQueuedPatient(PatientManager manager, String name, String ic, 
        String contact, String dob, String gender, String bloodType, 
        String allergies, double weight, double height,
        String patientId, String queueId, String regDate) {
    
    Patient patient = new Patient(
        patientId, name, ic, contact, dob, gender, 
        bloodType, allergies, weight, height, 
        queueId, regDate
    );
    
    manager.addSamplePatient(patient, true);
}

private static void addHardcodedNonQueuedPatient(PatientManager manager, String name, String ic, 
        String contact, String dob, String gender, String bloodType, 
        String allergies, double weight, double height,
        String patientId, String regDate) {
    
    Patient patient = new Patient(
        patientId, name, ic, contact, dob, gender, 
        bloodType, allergies, weight, height, 
        null, regDate  // null queueId for non-queued patients
    );
    
    manager.addSamplePatient(patient, false);
}



// Main sample consultation initializer
    public static void initializeSampleConsultations(
    ConsultationManager consultationManager,
    DoctorManager doctorManager,
    PatientManager patientManager) {

    try {
        // ===== 1. HARDCODE REQUIRED PATIENTS (P001-P008) =====
        // (If they don't exist, this creates them on the spot)
        addHardcodedQueuedPatient(patientManager, "Ali Bin Abu", "990101014321", "0123456789", 
            "01/01/1999", "M", "A+", "Peanuts", 70.5, 170.0, "P001", "Q001", "01/01/2023");
        addHardcodedQueuedPatient(patientManager, "Siti Aminah", "000202023456", "0139876543", 
            "02/02/2000", "F", "O-", "", 55.0, 160.0, "P002", "Q002", "02/01/2023");
        addHardcodedQueuedPatient(patientManager, "John Tan", "981010104321", "0112233445", 
            "10/10/1998", "M", "B+", "Seafood", 68.0, 175.0, "P003", "Q003", "03/01/2023");
        addHardcodedQueuedPatient(patientManager, "Nur Izzati", "010303035432", "0199988776", 
            "03/03/2001", "F", "AB-", "", 60.0, 162.0, "P004", "Q004", "04/01/2023");
        addHardcodedQueuedPatient(patientManager, "Raj Kumar", "970707074321", "0105678901", 
            "07/07/1997", "M", "O+", "Dust", 72.3, 180.0, "P005", "Q005", "05/01/2023");
        addHardcodedQueuedPatient(patientManager, "Lim Mei Ling", "960606062222", "0143322110", 
            "06/06/1996", "F", "A-", "Lactose", 50.0, 155.0, "P006", "Q006", "06/01/2023");
        addHardcodedQueuedPatient(patientManager, "Ahmad Fauzi", "950505051234", "0176543210", 
            "05/05/1995", "M", "B-", "Penicillin", 80.0, 178.0, "P007", "Q007", "07/01/2023");
        addHardcodedQueuedPatient(patientManager, "Sarah Chong", "940404043210", "0167890123", 
            "04/04/1994", "F", "AB+", "", 58.0, 165.0, "P008", "Q008", "08/01/2023");
        addHardcodedQueuedPatient(patientManager, "Hafiz Rahman", "930303033333", "0121112233",
            "03/03/1993", "M", "O+", "Shellfish", 74.0, 176.0, "P009", "Q009", "09/01/2023");
        addHardcodedQueuedPatient(patientManager, "Chong Wei", "920202022222", "0185556677",
            "02/02/1992", "M", "A+", "", 65.0, 170.0, "P010", "Q010", "10/01/2023");
        addHardcodedQueuedPatient(patientManager, "Amira Yusuf", "910101011111", "0178889990",
            "01/01/1991", "F", "B-", "Gluten", 59.0, 162.0, "P011", "Q011", "11/01/2023");
        addHardcodedQueuedPatient(patientManager, "Michael Lee", "890909099876", "0134445566",
            "09/09/1989", "M", "AB+", "", 82.0, 185.0, "P012", "Q012", "12/01/2023");
        addHardcodedQueuedPatient(patientManager, "Farah Zain", "880808088765", "0192233445",
            "08/08/1988", "F", "O-", "Nuts", 52.0, 158.0, "P013", "Q013", "13/01/2023");
        addHardcodedQueuedPatient(patientManager, "Daniel Wong", "870707077654", "0123344556",
            "07/07/1987", "M", "A-", "", 75.0, 172.0, "P014", "Q014", "14/01/2023");
        addHardcodedQueuedPatient(patientManager, "Aisyah Karim", "860606066543", "0166677788",
            "06/06/1986", "F", "B+", "Eggs", 54.0, 160.0, "P015", "Q015", "15/01/2023");

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
