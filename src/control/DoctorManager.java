/**
 *
 * @author Made BY RYAN JULIAN RAJESH 25WMR10036(Doctor Module)
 */
package control;

import adt.LinkedQueue;
import adt.QueueInterface;
import entity.Consultation;
import entity.Doctor;
import entity.Patient;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DoctorManager {
    /* ----------  DATA  ---------- */
    private QueueInterface<Doctor>       doctorQueue   = new LinkedQueue<>();
    private QueueInterface<Consultation> consultationQueue;  // injected
    private QueueInterface<Patient>      patientQueue;       // injected

    /* ----------  INJECTION  ---------- */
    public void setSharedQueues(QueueInterface<Consultation> cq,
                                QueueInterface<Patient> pq) {
        this.consultationQueue = cq;
        this.patientQueue      = pq;
    }
    
    
  /* ----------  CRUD  ---------- */
    public void addDoctor(Doctor d) {
        if (d == null) throw new IllegalArgumentException("Doctor is null");
        if (doctorExists(d.getDoctorID()))
            throw new IllegalStateException("Duplicate ID");
        doctorQueue.enqueue(d);
    }

    public boolean removeDoctor(String doctorID) {
        // 1. remove doctor
        boolean removed = false;
        QueueInterface<Doctor> tmp = new LinkedQueue<>();
        while (!doctorQueue.isEmpty()) {
            Doctor d = doctorQueue.dequeue();
            if (d.getDoctorID().equals(doctorID)) removed = true;
            else tmp.enqueue(d);
        }
        doctorQueue = tmp;

        // 2. cascade: remove all consultations linked to this doctor
        if (removed && consultationQueue != null) {
            QueueInterface<Consultation> tmpCon = new LinkedQueue<>();
            while (!consultationQueue.isEmpty()) {
                Consultation c = consultationQueue.dequeue();
                if (!c.getDoctorId().equals(doctorID)) tmpCon.enqueue(c);
            }
            consultationQueue = tmpCon;
        }
        return removed;
    }
    

    public boolean updateDoctor(String doctorID, Doctor newData) {
    if (newData == null || !doctorExists(doctorID)) return false;

    QueueInterface<Doctor> tmp = new LinkedQueue<>();
    boolean updated = false;

    while (!doctorQueue.isEmpty()) {
        Doctor d = doctorQueue.dequeue();
        if (d.getDoctorID().equals(doctorID)) {
            // Update fields if new values are provided
            if (newData.getName() != null) d.setName(newData.getName());
            if (newData.getSpecialization() != null) d.setSpecialization(newData.getSpecialization());
            if (newData.getContactNumber() != null) d.setContactNumber(newData.getContactNumber());
            if (newData.getYearsOfExperience() >= 0) d.setYearsOfExperience(newData.getYearsOfExperience());
            if (newData.getConsultationFee() >= 0) d.setConsultationFee(newData.getConsultationFee());
            d.setAvailable(newData.isAvailable());
            d.setOnLeave(newData.isOnLeave());
            if (newData.getLeaveDates() != null) d.setLeaveDates(newData.getLeaveDates());
            if (newData.getWorkingHours() != null) d.setWorkingHours(newData.getWorkingHours());
            updated = true;
        }
        tmp.enqueue(d);
    }

    doctorQueue = tmp;
    return updated;
}
/* ----------  SEARCH / FILTER  ---------- */
public QueueInterface<String> getDoctorsWithWorkload(int minConsultations) {
    QueueInterface<String> report = new LinkedQueue<>();

    // 1.  build an array of doctors once
    Doctor[] docs = doctorQueue.toArray(new Doctor[0]);

    // 2.  count consultations for each doctor
    int[] counts = new int[docs.length];
    for (int i = 0; i < docs.length; i++) {
        counts[i] = 0;
    }
    for (Consultation c : consultationQueue.toArray(new Consultation[0])) {
        for (int i = 0; i < docs.length; i++) {
            if (c.getDoctorId().equals(docs[i].getDoctorID())) {
                counts[i]++;
                break;
            }
        }
    }

    // 3.  enqueue formatted row strings for those who meet the threshold
    for (int i = 0; i < docs.length; i++) {
        if (counts[i] >= minConsultations) {
            Doctor d = docs[i];
            String row = String.format("%-8s|%-22s|%-20s|%-14s|%5d yrs| RM%6.2f |%-11s|  %12d",
                    d.getDoctorID(),
                    d.getName(),
                    d.getSpecialization(),
                    d.getContactNumber(),
                    d.getYearsOfExperience(),
                    d.getConsultationFee(),
                    d.isOnLeave() ? "OnLeave" : (d.isAvailable() ? "Available" : "Occupied"),
                    counts[i]);
            report.enqueue(row);
        }
    }
    return report;
}

    private Doctor getDoctorBySelectionNumber(int selection, String specialization) {
        LinkedQueue<Doctor> tempQueue = new LinkedQueue<>();
        Doctor selectedDoctor = null;
        int currentIndex = 0;
        
        while (!doctorQueue.isEmpty()) {
            Doctor current = doctorQueue.dequeue();
            boolean matches = current.isAvailable() && !current.isOnLeave() && 
                            (specialization == null || 
                             current.getSpecialization().equalsIgnoreCase(specialization));
        
            if (matches) {
                currentIndex++;
                if (currentIndex == selection) {
                    selectedDoctor = current;
                }
            }
            tempQueue.enqueue(current);
        }
    
        doctorQueue = tempQueue;
        return selectedDoctor;
    }

/* ----------  REPORTS  ---------- */
    public String generateDoctorWorkloadReport() {
    if (consultationQueue == null || patientQueue == null)
        return "Shared queues not injected\n";

    /* ---------- 1.  collect counts ---------- */
    final int MAX_DOCS = doctorQueue.size();
    Doctor[] docs = doctorQueue.toArray(new Doctor[0]);

    int[] cons = new int[MAX_DOCS];
    int[] uniq = new int[MAX_DOCS];

    for (int i = 0; i < MAX_DOCS; i++) {
        Doctor d = docs[i];

        /* consultations */
        int c = 0;
        for (Consultation con : consultationQueue.toArray(new Consultation[0])) {
            if (con.getDoctorId().equals(d.getDoctorID())) c++;
        }
        cons[i] = c;

        /* unique patients */
        QueueInterface<Patient> seen = new LinkedQueue<>();
        for (Consultation con : consultationQueue.toArray(new Consultation[0])) {
            if (!con.getDoctorId().equals(d.getDoctorID())) continue;
            Patient p = null;
            for (Patient cand : patientQueue.toArray(new Patient[0])) {
                if (cand.getPatientID().equals(con.getPatientId())) {
                    p = cand;
                    break;
                }
            }
            if (p != null && !seen.contains(p)) seen.enqueue(p);
        }
        uniq[i] = seen.size();
    }

    /* ---------- 2.  sort indices (top 10) ---------- */
    int[] idxByCons = new int[MAX_DOCS];
    int[] idxByUniq = new int[MAX_DOCS];
    for (int i = 0; i < MAX_DOCS; i++) {
        idxByCons[i] = i;
        idxByUniq[i] = i;
    }

    // simple descending bubble sort over indices
    for (int i = 0; i < MAX_DOCS - 1; i++) {
        for (int j = i + 1; j < MAX_DOCS; j++) {
            if (cons[idxByCons[j]] > cons[idxByCons[i]]) {
                int tmp = idxByCons[i]; idxByCons[i] = idxByCons[j]; idxByCons[j] = tmp;
            }
            if (uniq[idxByUniq[j]] > uniq[idxByUniq[i]]) {
                int tmp = idxByUniq[i]; idxByUniq[i] = idxByUniq[j]; idxByUniq[j] = tmp;
            }
        }
    }

    /* ---------- 3.  build output ---------- */
    StringBuilder sb = new StringBuilder();
        sb.append(" TUNKU ADBUL RAHMAN UNIVERSITY MANAGEMENT and TECHNOLOGY\n");
        sb.append(" TARG14 CLINIC OF HEALTH AND WELLNESS\n");
        sb.append(" BLOCK K ROOM 104A\n");
        // Centered title
        sb.append("________________________________________________________________________________________________________________________________________________________________\n");
        sb.append("| ").append("DOCTOR WORKLOAD REPORT").append(" |\n");
        sb.append("________________________________________________________________________________________________________________________________________________________________\n");

    sb.append("+--------+----------------------+--------------------+--------------+------------------+\n");
    sb.append("| ID     | Name                 | Specialization     | Consultations| Unique Patients  |\n");
    sb.append("+--------+----------------------+--------------------+--------------+------------------+\n");

    for (int i = 0; i < MAX_DOCS; i++) {
        Doctor d = docs[i];
        sb.append(String.format("| %-6s | %-20s | %-18s | %12d | %16d |\n",
                d.getDoctorID(),
                d.getName(),
                d.getSpecialization(),
                cons[i],
                uniq[i]));
    }
    sb.append("+--------+----------------------+--------------------+--------------+------------------+\n\n");

            /* ---------- 4.  vertical histogram with values & labels ---------- */
            int top = Math.min(10, MAX_DOCS);
            int maxC = (top > 0) ? cons[idxByCons[0]] : 0;
            int maxU = (top > 0) ? uniq[idxByUniq[0]] : 0;

            /* ---- CONSULTATIONS ---- */
            sb.append("________________________________________________________________________________________________________________________________________________________________\n");
            sb.append("");
            sb.append("Top 10 - Consultations (Histogram)\n");
            for (int line = maxC; line >= 0; line--) {
                // left label
                sb.append(String.format("%4d|", line));
                // bars + value on top
                for (int r = 0; r < top; r++) {
                    int val = cons[idxByCons[r]];
                    if (line == maxC) {                       // top line → value
                        sb.append(String.format("  %-8s", val));
                    } else {                                  // bar or space
                        sb.append(val >= line ? "    #     " : "    ");
                    }
                }
                sb.append("\n");
            }
            // bottom names
            sb.append("   ");
            for (int r = 0; r < top; r++) {
                String name = docs[idxByCons[r]].getName();
                sb.append(String.format("%-10s", name.substring(0, Math.min(8, name.length()))));
            }
            sb.append("\n\n");

            /* ---- UNIQUE PATIENTS ---- */
            sb.append("________________________________________________________________________________________________________________________________________________________________\n");
            sb.append("");
            sb.append("Top 10 - Unique Patients (Histogram)\n");
            for (int line = maxU; line >= 0; line--) {
                sb.append(String.format("%2d|", line));
                for (int r = 0; r < top; r++) {
                    int val = uniq[idxByUniq[r]];
                    if (line == maxU) {
                        sb.append(String.format("  %-8s", val));
                    } else {
                        sb.append(val >= line ? "    #     " : "    ");
                    }
                }
                sb.append("\n");
            }
            sb.append("   ");
            for (int r = 0; r < top; r++) {
                String name = docs[idxByUniq[r]].getName();
                sb.append(String.format("%-10s", name.substring(0, Math.min(8, name.length()))));
            }
            sb.append("\n");

                return sb.toString();
            }

    public String generateSpecializationReportWithPatientCount() {
    if (consultationQueue == null || patientQueue == null)
        return "Shared queues not injected";

    final String[] SPEC = {
        "Cardiology", "Pediatrics", "Orthopedics", "Neurology", "Oncology",
        "General Surgery", "Dermatology", "Ophthalmology", "Emergency Medicine", "Psychiatry"
    };

    int[] docs   = new int[SPEC.length];
    int[] cons   = new int[SPEC.length];
    int[] expSum = new int[SPEC.length];

    /* ---- count doctors ---- */
    for (Doctor d : doctorQueue.toArray(new Doctor[0])) {
        for (int i = 0; i < SPEC.length; i++)
            if (SPEC[i].equalsIgnoreCase(d.getSpecialization())) {
                docs[i]++;
                expSum[i] += d.getYearsOfExperience();
                break;
            }
    }

    /* ---- count consultations ---- */
    for (Consultation c : consultationQueue.toArray(new Consultation[0])) {
        Doctor d = getDoctorByID(c.getDoctorId());
        if (d == null) continue;
        for (int i = 0; i < SPEC.length; i++)
            if (SPEC[i].equalsIgnoreCase(d.getSpecialization())) {
                cons[i]++;
                break;
            }
    }
        

    StringBuilder sb = new StringBuilder("\n");
sb.append(" TUNKU ADBUL RAHMAN UNIVERSITY MANAGEMENT and TECHNOLOGY\n");
sb.append(" TARG14 CLINIC OF HEALTH AND WELLNESS\n");
sb.append(" BLOCK K ROOM 104A\n");
        // Centered title
sb.append("________________________________________________________________________________________________________________________________________________________________\n");
        sb.append("| ").append("SPECIFICATION REPORT").append(" |\n");
sb.append("________________________________________________________________________________________________________________________________________________________________\n");
    /* ---- wider, prettier table ---- */
    sb.append("SPECIALITY LIST TABLE");

        sb.append("\n+----------------------------------------+----------+-------------+-------------+\n");
        sb.append("| Specialty                              | Doctors  | Consults    | Avg Exp(yrs)|\n");
        sb.append("+----------------------------------------+----------+-------------+-------------+\n");

        for (int i = 0; i < SPEC.length; i++) {
            if (docs[i] == 0) continue;
            double avg = (double) expSum[i] / docs[i];
            sb.append(String.format("| %-38s |    %-4d  |    %-5d    |  %9.1f  |\n",
                    SPEC[i], docs[i], cons[i], avg));
        }
        sb.append("+----------------------------------------+----------+-------------+-------------+\n\n");
        
        
sb.append("________________________________________________________________________________________________________________________________________________________________\n");
   sb.append("CONSULTATIONS & AVERAGE EXPERIENCE IN GRAPH FORM");
/* ---- side-by-side filled boxes with square-like characters ---- */
        sb.append("\nFOR EACH SPECIALIZATION\n");
        sb.append("+--------------------------------------------------+------------------------------+\n");
        sb.append("| Specialty          | Consultations   (#)         | Average Experience (yrs)     |\n");
        sb.append("+--------------------------------------------------+------------------------------+\n");

        for (int i = 0; i < SPEC.length; i++) {
            if (docs[i] == 0) continue;

            int cBox = cons[i];
            int eBox = (int) Math.round((double) expSum[i] / docs[i]);
            double avg = (double) expSum[i] / docs[i];

            // Create a visual representation of the bar graph for consultations
            String consBar = "";
            for (int j = 0; j < 20; j++) { // Assuming a maximum scale of 20 for consultations
                if (j < cBox) consBar += "#";
                else consBar += " ";
            }

            // Create a visual representation of the bar graph for average experience
            String expBar = "";
            for (int j = 0; j < 20; j++) { // Assuming a maximum scale of 20 for average experience
                if (j < eBox) expBar += "#";
                else expBar += " ";
            }

            sb.append(String.format("| %-18s | %-27s | %-28s |\n",
                    SPEC[i],
                    consBar + " (" + cBox + ")",
                    expBar + " (" + String.format("%.1f", avg) + ")"));
        }
        sb.append("+--------------------------------------------------+------------------------------+\n");
        sb.append("________________________________________________________________________________________________________________________________________________________________\n");
   sb.append("BAR CHART - AMMOUNT OF DOCTORS AGAINST SPECIALIZATION");
    /* ---- vertical column for doctors ---- */
    sb.append("DOCTORS (vertical column)\n\n");
    int maxH = 10;   // fixed scale height for demo
    for (int h = maxH; h >= 0; h--) {
        sb.append(String.format("%2d|", h));
        for (int i = 0; i < SPEC.length; i++) {
            if (docs[i] == 0) continue;
            sb.append(docs[i] >= h ? "   #   " : "   ");
        }
        sb.append('\n');
    }
    sb.append("  +");
    for (int i = 0; i < SPEC.length; i++)
        if (docs[i] != 0) sb.append("-------");
    sb.append('\n');
    sb.append("   ");
    for (int i = 0; i < SPEC.length; i++)
        if (docs[i] != 0) sb.append(String.format("%-7s", SPEC[i].substring(0, 3)));
    sb.append('\n');
    sb.append("________________________________________________________________________________________________________________________________________________________________\n");

    return sb.toString();
}
    
    
     public String generateSeniorDoctorsReport(int minYears) {
    if (consultationQueue == null)
        return "Shared queues not injected";

    StringBuilder rpt = new StringBuilder();
    rpt.append("-------------------------------------------------------------------\n");
    rpt.append(String.format("%-3d+ YEARS OF EXPERIENCE\n", minYears));
    rpt.append("-------------------------------------------------------------------\n");
    rpt.append("| ID |     NAME     | SPECIALIZATION       | YEARS | CONSULTATION |\n");
    rpt.append("|----|--------------|----------------------|-------|--------------|\n");

    boolean any = false;
    for (Doctor d : doctorQueue.toArray(new Doctor[0])) {
        if (d.getYearsOfExperience() >= minYears) {
            any = true;
            int cons = 0;
            for (Consultation c : consultationQueue.toArray(new Consultation[0]))
                if (c.getDoctorId().equals(d.getDoctorID())) cons++;

            rpt.append(String.format("|%-4s|%-14s|%-22s|%-7d|%-14d|\n",
                    d.getDoctorID(), d.getName(), d.getSpecialization(),
                    d.getYearsOfExperience(), cons));
        }
    }

    if (!any) {
        rpt.append("|                 No doctors                 |\n");
    }
    rpt.append("-------------------------------------------------------------------\n");
    return rpt.toString();
}
      /* ----------  LEAVE MANAGEMENT  ---------- */
            public String viewAllLeaves() {
            if (doctorQueue.isEmpty())
                return "No doctors on record.\n";

            StringBuilder sb = new StringBuilder();
            sb.append("DOCTOR LEAVE SCHEDULES\n");
            sb.append("+----------+----------------------+-------------------------------+\n");
            sb.append("| ID       | Name                 | Leave Dates                   |\n");
            sb.append("+----------+----------------------+-------------------------------+\n");

            for (Doctor d : doctorQueue.toArray(new Doctor[0])) {
                String leaves = d.getFormattedLeaveDates();
                if ("No leave scheduled".equals(leaves)) leaves = "-";
                sb.append(String.format("| %-8s | %-20s | %-29s |\n",
                        d.getDoctorID(),
                        d.getName(),
                        leaves));
            }
            sb.append("+----------+----------------------+-------------------------------+\n");
            return sb.toString();
        }

    public boolean registerLeave(String doctorID, String leaveDate) {
        Doctor d = getDoctorByID(doctorID);
        if (d == null) return false;
        d.addLeaveDate(leaveDate);
        return true;
    }

    public boolean endLeave(String doctorID, String leaveDate) {
        Doctor d = getDoctorByID(doctorID);
        if (d == null) return false;
        d.removeLeaveDate(leaveDate);
        return true;
    }

    public String processLeaveStatusUpdates() {
    LocalDate today = LocalDate.now();
    StringBuilder report = new StringBuilder("STATUS UPDATE\n");

    for (Doctor d : doctorQueue.toArray(new Doctor[0])) {
        boolean onLeave = false;
        for (String date : d.getLeaveDates()) {
            if (LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE).isEqual(today)) {
                onLeave = true;
                break;
            }
        }

        d.setOnLeave(onLeave);
        d.setAvailable(!onLeave);

        // only list doctors who are on leave today
        if (onLeave) {
            report.append("Is on leave today  : ").append(d.getDoctorID()).append("\n");
        }
    }

    if (report.length() == 14) { // length of "STATUS UPDATE\n"
        return "STATUS UPDATE\nNo doctors on leave today.\n";
    }
    return report.toString();
}

    
        /* ----------  Duty-Schedule & Availability (Doctor + Consultation + Patient) ---------- */

            public boolean addDutyShift(String doctorID, String shiftPattern) {
                Doctor d = getDoctorByID(doctorID);
                if (d == null) return false;
                d.setWorkingHours(shiftPattern);   // reuse existing field
                return true;
            }

            public String checkDoctorAvailability(String doctorID, LocalDate date, LocalTime slot) {
                Doctor d = getDoctorByID(doctorID);
                if (d == null) return "Doctor not found";

                StringBuilder report = new StringBuilder("Availability Report for Doctor: " + d.getName() + "\n");

                if (d.isOnLeave()) {
                    report.append("NOT available: Doctor is on leave.\n");
                    return report.toString();
                }

                for (String leave : d.getLeaveDates()) {
                    if (LocalDate.parse(leave, DateTimeFormatter.ISO_LOCAL_DATE).isEqual(date)) {
                        report.append("NOT available: Doctor has a scheduled leave on this date.\n");
                        return report.toString();
                    }
                }

                String pattern = d.getWorkingHours();
                if (pattern == null || pattern.isEmpty()) {
                    report.append("NOT available: Doctor's working hours are not set.\n");
                    return report.toString();
                }

                String[] parts = pattern.split("-");
                if (parts.length != 2) {
                    report.append("NOT available: Invalid working hours format.\n");
                    return report.toString();
                }

                LocalTime start = LocalTime.parse(parts[0].trim());
                LocalTime end = LocalTime.parse(parts[1].trim());
                if (slot.isBefore(start) || slot.isAfter(end)) {
                    report.append("NOT available: Slot is outside working hours.\n");
                    return report.toString();
                }

                for (Consultation c : consultationQueue.toArray(new Consultation[0])) {
                    if (c.getDoctorId().equals(doctorID) &&
                        c.getConsultationDate().equals(date) &&
                        c.getConsultationTime().equals(slot)) {
                        report.append("NOT available: Slot is already booked.\n");
                        return report.toString();
                    }
                }

                report.append("Available\n");
                return report.toString();
            }

            public String generateDutyAvailabilityReport(String doctorID) {
                Doctor d = getDoctorByID(doctorID);
                if (d == null) return "Doctor not found";

                StringBuilder sb = new StringBuilder("DUTY & AVAILABILITY\n");
                sb.append("Doctor: ").append(d.getName()).append("\n");
                sb.append("Shift: ").append(d.getWorkingHours()).append("\n");
                sb.append("Leave: ").append(d.getFormattedLeaveDates()).append("\n");

                int booked = 0;
                for (Consultation c : consultationQueue.toArray(new Consultation[0]))
                    if (c.getDoctorId().equals(doctorID)) booked++;
                sb.append("Booked Slots: ").append(booked).append("\n");

                // Add more detailed information about booked slots
                sb.append("Booked Slots Details:\n");
                for (Consultation c : consultationQueue.toArray(new Consultation[0])) {
                    if (c.getDoctorId().equals(doctorID)) {
                        sb.append("  Date: ").append(c.getConsultationDate())
                          .append(", Time: ").append(c.getConsultationTime())
                          .append(", Patient ID: ").append(c.getPatientId())
                          .append("\n");
                    }
                }

                return sb.toString();
            }

            
    public String suggestAlternativeSlots(String doctorID, LocalDate date, LocalTime slot) {
    Doctor d = getDoctorByID(doctorID);
    if (d == null) return "Doctor not found";

    StringBuilder report = new StringBuilder("Alternative Slots for Doctor: " + d.getName() + "\n");
    report.append("Requested Date: ").append(date).append(", Time: ").append(slot).append("\n");

    String availabilityReport = checkDoctorAvailability(doctorID, date, slot);

    if (availabilityReport.contains("Available")) {
        report.append("Requested slot is available.\n");
    } else {
        report.append("Requested slot is NOT available.\n");
        report.append("Suggested alternative slots:\n");

        boolean foundAlternative = false;
        for (LocalTime time = LocalTime.of(9, 0); time.isBefore(LocalTime.of(17, 0)); time = time.plusMinutes(30)) {
            String alternativeReport = checkDoctorAvailability(doctorID, date, time);
            if (alternativeReport.contains("Available")) {
                report.append("  ").append(time).append("\n");
                foundAlternative = true;
            }
        }

        if (!foundAlternative) {
            report.append("  No alternative slots available for this day.\n");
        }
    }

    return report.toString();
}

            
     
    
/* ----------  UTILITIES  ---------- */
    public String generateDoctorID() {
        int max = 0;
        for (Doctor d : doctorQueue.toArray(new Doctor[0]))
            if (d.getDoctorID().startsWith("DR"))
                max = Math.max(max, Integer.parseInt(d.getDoctorID().substring(2)));
        return String.format("DR%03d", max + 1);
    }

    public Doctor getDoctorByID(String id) {
        for (Doctor d : doctorQueue.toArray(new Doctor[0]))
            if (d.getDoctorID().equals(id)) return d;
        return null;
    }

    public Doctor[] getAllDoctors() {
        return doctorQueue.toArray(new Doctor[0]);
    }

    public boolean doctorExists(String id) {
        return getDoctorByID(id) != null;
    }
    

    
}