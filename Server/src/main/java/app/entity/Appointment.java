package app.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Appointment {
    private long id;
    private long doctorId;
    private long patientId;
    private String diagnosis;
    private String recommendation;
    private String report;
    private Timestamp dateTimeOfAppointment;
    private Byte isVisited;
    private Doctor doctorByDoctorId;
    private Patient patientByPatientId;

    @Id
    @Column(name = "ID", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "doctorID", nullable = false, insertable = false, updatable = false)
    public long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
    }

    @Basic
    @Column(name = "patientID", nullable = false, insertable = false, updatable = false)
    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    @Basic
    @Column(name = "diagnosis", nullable = true, length = 2000)
    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    @Basic
    @Column(name = "recommendation", nullable = true, length = 2000)
    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    @Basic
    @Column(name = "report", nullable = true, length = 2000)
    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    @Basic
    @Column(name = "dateTimeOfAppointment", nullable = true)
    public Timestamp getDateTimeOfAppointment() {
        return dateTimeOfAppointment;
    }

    public void setDateTimeOfAppointment(Timestamp dateTimeOfAppointment) {
        this.dateTimeOfAppointment = dateTimeOfAppointment;
    }

    @Basic
    @Column(name = "isVisited", nullable = true)
    public Byte getIsVisited() {
        return isVisited;
    }

    public void setIsVisited(Byte isVisited) {
        this.isVisited = isVisited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return id == that.id && doctorId == that.doctorId && patientId == that.patientId && Objects.equals(diagnosis, that.diagnosis) && Objects.equals(recommendation, that.recommendation) && Objects.equals(report, that.report) && Objects.equals(dateTimeOfAppointment, that.dateTimeOfAppointment) && Objects.equals(isVisited, that.isVisited);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, doctorId, patientId, diagnosis, recommendation, report, dateTimeOfAppointment, isVisited);
    }

    @ManyToOne
    @JoinColumn(name = "doctorID", referencedColumnName = "ID", nullable = false)
    public Doctor getDoctorByDoctorId() {
        return doctorByDoctorId;
    }

    public void setDoctorByDoctorId(Doctor doctorByDoctorId) {
        this.doctorByDoctorId = doctorByDoctorId;
    }

    @ManyToOne
    @JoinColumn(name = "patientID", referencedColumnName = "ID", nullable = false)
    public Patient getPatientByPatientId() {
        return patientByPatientId;
    }

    public void setPatientByPatientId(Patient patientByPatientId) {
        this.patientByPatientId = patientByPatientId;
    }
}
