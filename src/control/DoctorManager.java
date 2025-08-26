package control;

import adt.LinkedQueue;
import adt.QueueInterface;
import entity.Consultation;
import entity.Doctor;
import entity.Patient;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Comparator;
/**
 *
 * @author user
 */
public class DoctorManager {
    /* ----------  DATA  ---------- */
    private QueueInterface<Doctor>       doctorQueue   = new LinkedQueue<>();
    private       QueueInterface<Consultation> consultationQueue;  // injected
    private       QueueInterface<Patient>      patientQueue;       // injected

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
    public QueueInterface<Doctor> filterDoctorsByPatientWorkload(int minConsultations) {
        QueueInterface<Doctor> result = new LinkedQueue<>();
        if (consultationQueue == null) return result;

        for (Doctor d : doctorQueue.toArray(new Doctor[0])) {
            int count = 0;
            for (Consultation c : consultationQueue.toArray(new Consultation[0]))
                if (c.getDoctorId().equals(d.getDoctorID())) count++;
            if (count >= minConsultations) result.enqueue(d);
        }
        return result;
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
            return "Shared queues not injected";

        StringBuilder rpt = new StringBuilder("DOCTOR WORKLOAD SUMMARY\n");
        for (Doctor d : doctorQueue.toArray(new Doctor[0])) {
            int cons = 0;
            QueueInterface<Patient> uniques = new LinkedQueue<>();

            for (Consultation c : consultationQueue.toArray(new Consultation[0])) {
                if (!c.getDoctorId().equals(d.getDoctorID())) continue;
                cons++;
                for (Patient p : patientQueue.toArray(new Patient[0]))
                    if (p.getPatientID().equals(c.getPatientId()) && !uniques.contains(p))
                        uniques.enqueue(p);
            }
            rpt.append(String.format("%s (%s) – %d consultations, %d unique patients\n",
                    d.getName(), d.getSpecialization(), cons, uniques.size()));
        }
        return rpt.toString();
    }

    public String generateSpecializationReportWithPatientCount() {
        // Doctor + Consultation + Patient dependency
        if (consultationQueue == null || patientQueue == null)
            return "Shared queues not injected";

        StringBuilder rpt = new StringBuilder("SPECIALIZATION SUMMARY (with patient counts)\n");
        for (Doctor d : doctorQueue.toArray(new Doctor[0])) {
            int cons = 0;
            QueueInterface<Patient> uniques = new LinkedQueue<>();
            for (Consultation c : consultationQueue.toArray(new Consultation[0])) {
                if (!c.getDoctorId().equals(d.getDoctorID())) continue;
                cons++;
                for (Patient p : patientQueue.toArray(new Patient[0]))
                    if (p.getPatientID().equals(c.getPatientId()) && !uniques.contains(p))
                        uniques.enqueue(p);
            }
            rpt.append(String.format("%-20s | %3d cons | %3d patients\n",
                    d.getSpecialization(), cons, uniques.size()));
        }
        return rpt.toString();
    }

    
    
     public String generateSeniorDoctorsReport(int minYears) {
        if (consultationQueue == null)
            return "Shared queues not injected";

        StringBuilder rpt = new StringBuilder("SENIOR DOCTORS REPORT\n");
        for (Doctor d : doctorQueue.toArray(new Doctor[0])) {
            if (d.getYearsOfExperience() >= minYears) {
                int cons = 0;
                for (Consultation c : consultationQueue.toArray(new Consultation[0]))
                    if (c.getDoctorId().equals(d.getDoctorID())) cons++;
                rpt.append(String.format("%s (%s) – %d years, %d consultations\n",
                        d.getName(), d.getSpecialization(), d.getYearsOfExperience(), cons));
            }
        }
        return rpt.toString();
    }
     
      /* ----------  LEAVE MANAGEMENT  ---------- */
    public String viewAllLeaves() {
        StringBuilder report = new StringBuilder("DOCTOR LEAVE SCHEDULES\n");
        for (Doctor d : doctorQueue.toArray(new Doctor[0])) {
            report.append("ID: ").append(d.getDoctorID())
                  .append(", Name: ").append(d.getName())
                  .append(", Leave Dates: ").append(d.getFormattedLeaveDates())
                  .append("\n");
        }
        return report.toString();
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

    public void processLeaveStatusUpdates() {
        LocalDate today = LocalDate.now();
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
        }
    }
        /* ----------  Duty-Schedule & Availability (Doctor + Consultation + Patient) ---------- */

            public boolean addDutyShift(String doctorID, String shiftPattern) {
                Doctor d = getDoctorByID(doctorID);
                if (d == null) return false;
                d.setWorkingHours(shiftPattern);   // reuse existing field
                return true;
            }

            public boolean isDoctorAvailable(String doctorID, LocalDate date, LocalTime slot) {
                Doctor d = getDoctorByID(doctorID);
                if (d == null || d.isOnLeave()) return false;

                /* 1. Check leave  */
                for (String leave : d.getLeaveDates()) {
                    if (LocalDate.parse(leave, DateTimeFormatter.ISO_LOCAL_DATE).isEqual(date))
                        return false;
                }

                /* 2. Check duty window (simple HH:MM-HH:MM pattern) */
                String pattern = d.getWorkingHours();
                if (pattern == null || pattern.isEmpty()) return false;

                String[] parts = pattern.split("-");
                if (parts.length != 2) return false;
                LocalTime start = LocalTime.parse(parts[0].trim());
                LocalTime end   = LocalTime.parse(parts[1].trim());
                if (slot.isBefore(start) || slot.isAfter(end)) return false;

                /* 3. Check existing consultations */
                for (Consultation c : consultationQueue.toArray(new Consultation[0])) {
                    if (c.getDoctorId().equals(doctorID) &&
                        c.getConsultationDate().equals(date) &&
                        c.getConsultationTime().equals(slot))
                        return false;
                }
                return true;
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