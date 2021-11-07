package app.dao;

import app.entity.Address;
import app.entity.Patient;

import java.util.List;

public interface PatientDao {
    public Patient findById(long id);

    public void save(Patient patient);

    public void update(Patient patient);

    public void delete(Patient patient);

    public List<Patient> findAll();

    public Patient findPatientByUserID(long userID);

    public void updateWeightAndHeight(double weight, double height, long id);
}
