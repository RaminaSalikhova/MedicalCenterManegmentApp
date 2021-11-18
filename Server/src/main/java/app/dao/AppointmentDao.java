package app.dao;

import app.entity.Appointment;
import app.entity.District;
import app.models.DataTransferModels.UpdateAppointmentDto;

import java.util.List;

public interface AppointmentDao {
    public Appointment findById(long id);

    public void save(Appointment appointment);

    public void update(Appointment appointment);

    public void delete(Appointment appointment);

    public List<Appointment> findAll();

    public List<Appointment> findAllByDoctorID(long id);

    public List<Appointment> findAllByPatientID(long patientID);

    public List<Appointment> findAllByPatientIDAndDoctorID(long patientID, long doctorID);

    public void updateVisit(UpdateAppointmentDto updateAppointmentDto);

    public List<Appointment> findAllByPatientIDAndVisited(long patientID);
}
