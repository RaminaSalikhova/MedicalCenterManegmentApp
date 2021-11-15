package app.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Doctor {
    private Long id;
    private Long userId;
    private Long scheduleId;
    private Long specializationId;
    private Long districtId;
    private Double salary;
    private Double experience;
    private Collection<Appointment> appointmentsById;
    private User userByUserId;
    private Schedule scheduleByScheduleId;
    private Specialization specializationBySpecializationId;
    private District districtByDistrictId;

    @Id
    @Column(name = "ID", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userID", nullable = false, insertable = false, updatable = false)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "scheduleID", nullable = true, insertable = false, updatable = false)
    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    @Basic
    @Column(name = "specializationID", nullable = true, insertable = false, updatable = false)
    public Long getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(Long specializationId) {
        this.specializationId = specializationId;
    }

    @Basic
    @Column(name = "districtID", nullable = true, insertable = false, updatable = false)
    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    @Basic
    @Column(name = "salary", nullable = true, precision = 0)
    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Basic
    @Column(name = "experience", nullable = true, precision = 0)
    public Double getExperience() {
        return experience;
    }

    public void setExperience(Double experience) {
        this.experience = experience;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return id == doctor.id && userId == doctor.userId && scheduleId == doctor.scheduleId && specializationId == doctor.specializationId && districtId == doctor.districtId && Objects.equals(salary, doctor.salary) && Objects.equals(experience, doctor.experience);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, scheduleId, specializationId, districtId, salary, experience);
    }

    @OneToMany(mappedBy = "doctorByDoctorId")
    public Collection<Appointment> getAppointmentsById() {
        return appointmentsById;
    }

    public void setAppointmentsById(Collection<Appointment> appointmentsById) {
        this.appointmentsById = appointmentsById;
    }

    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "ID", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "scheduleID", referencedColumnName = "ID", nullable = true)
    public Schedule getScheduleByScheduleId() {
        return scheduleByScheduleId;
    }

    public void setScheduleByScheduleId(Schedule scheduleByScheduleId) {
        this.scheduleByScheduleId = scheduleByScheduleId;
    }

    @ManyToOne
    @JoinColumn(name = "specializationID", referencedColumnName = "ID", nullable = true)
    public Specialization getSpecializationBySpecializationId() {
        return specializationBySpecializationId;
    }

    public void setSpecializationBySpecializationId(Specialization specializationBySpecializationId) {
        this.specializationBySpecializationId = specializationBySpecializationId;
    }

    @ManyToOne
    @JoinColumn(name = "districtID", referencedColumnName = "ID", nullable = true)
    public District getDistrictByDistrictId() {
        return districtByDistrictId;
    }

    public void setDistrictByDistrictId(District districtByDistrictId) {
        this.districtByDistrictId = districtByDistrictId;
    }
}
