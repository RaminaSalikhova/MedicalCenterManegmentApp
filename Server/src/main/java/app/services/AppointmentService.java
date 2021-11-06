package app.services;

import app.dao.AppointmentDao;
import app.dao.AppointmentDaoImpl;
import app.dao.DistrictDao;
import app.dao.DistrictDaoImpl;
import app.entity.Appointment;
import app.entity.District;

import java.util.List;

public class AppointmentService {
    public AppointmentDao appointmentDao = new AppointmentDaoImpl();

    public Appointment findById(long id) {
        return appointmentDao.findById(id);
    }

    public void save(Appointment appointment) {
        appointmentDao.save(appointment);
    }

    public void update(Appointment appointment) {
        appointmentDao.update(appointment);
    }

    public void delete(Appointment appointment) {
        appointmentDao.delete(appointment);
    }

    public List<Appointment> findAll() {
        return appointmentDao.findAll();
    }

    public List<Appointment> findAllByDoctorID(long doctorID){ return  appointmentDao.findAllByDoctorID(doctorID);}
}
