package app.services;

import app.dao.DoctorDao;
import app.dao.DoctorDaoImpl;
import app.dao.PatientDao;
import app.dao.PatientDaoImpl;
import app.entity.Doctor;
import app.entity.Patient;

import java.util.List;

public class PatientService {
    public PatientDao patientDao = new PatientDaoImpl();

    public Patient findById(long id) {
        return patientDao.findById(id);
    }

    public void save(Patient patient) {
        patientDao.save(patient);
    }

    public void update(Patient patient) {
        patientDao.update(patient);
    }

    public void delete(Patient patient) {
        patientDao.delete(patient);
    }

    public List<Patient> findAll() {
        return patientDao.findAll();
    }
    public Patient findPatientByUserID(long userID){return  patientDao.findPatientByUserID(userID);};
}
