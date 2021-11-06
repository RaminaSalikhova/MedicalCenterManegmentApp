package app.dao;

import app.entity.Doctor;
import app.entity.User;

import java.util.List;

public interface DoctorDao {
    public Doctor findById(long id);

    public void save(Doctor doctor);

    public void update(Doctor doctor);

    public void delete(Doctor doctor);

    public List<Doctor> findAll();

}
