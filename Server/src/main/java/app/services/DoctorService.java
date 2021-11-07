package app.services;

import app.dao.DoctorDao;
import app.dao.DoctorDaoImpl;
import app.dao.UserDao;
import app.dao.UserDaoImpl;
import app.entity.Doctor;
import app.entity.User;

import java.util.List;

public class DoctorService {
    public DoctorDao doctorDao = new DoctorDaoImpl();

    public Doctor findById(long id) {
        return doctorDao.findById(id);
    }

    public void save(Doctor doctor) {
        doctorDao.save(doctor);
    }

    public void update(Doctor doctor) {
        doctorDao.update(doctor);
    }

    public void delete(Doctor doctor) {
        doctorDao.delete(doctor);
    }

    public List<Doctor> findAll() {
        return doctorDao.findAll();
    }

    public Doctor findAllByUserID(long userID){return  doctorDao.findAllByUserID(userID);}
}
