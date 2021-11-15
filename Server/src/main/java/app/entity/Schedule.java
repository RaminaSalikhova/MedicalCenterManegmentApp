package app.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Schedule {
    private Long id;
    private Date date;
    private Time startTime;
    private Time endTime;
    private String roomNumber;
    private Byte isWeekend;
    private Collection<Doctor> doctorsById;

    @Id
    @Column(name = "ID", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "startTime", nullable = true)
    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    @Basic
    @Column(name = "endTime", nullable = true)
    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    @Basic
    @Column(name = "roomNumber", nullable = true, length = 30)
    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Basic
    @Column(name = "isWeekend", nullable = true)
    public Byte getIsWeekend() {
        return isWeekend;
    }

    public void setIsWeekend(Byte isWeekend) {
        this.isWeekend = isWeekend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return id == schedule.id && Objects.equals(date, schedule.date) && Objects.equals(startTime, schedule.startTime) && Objects.equals(endTime, schedule.endTime) && Objects.equals(roomNumber, schedule.roomNumber) && Objects.equals(isWeekend, schedule.isWeekend);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, startTime, endTime, roomNumber, isWeekend);
    }

    @OneToMany(mappedBy = "scheduleByScheduleId")
    public Collection<Doctor> getDoctorsById() {
        return doctorsById;
    }

    public void setDoctorsById(Collection<Doctor> doctorsById) {
        this.doctorsById = doctorsById;
    }
}
