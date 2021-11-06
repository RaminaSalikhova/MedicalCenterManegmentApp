package app.dao;

import app.entity.Appointment;
import app.entity.District;

import java.util.List;

public interface AppointmentDao {
    public Appointment findById(long id);

    public void save(Appointment appointment);

    public void update(Appointment appointment);

    public void delete(Appointment appointment);

    public List<Appointment> findAll();

    public List<Appointment> findAllByDoctorID(long id);

}
