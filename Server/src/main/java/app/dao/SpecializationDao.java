package app.dao;

import app.entity.Doctor;
import app.entity.Specialization;

import java.util.List;

public interface SpecializationDao {
    public Specialization findById(long id);

    public void save(Specialization specialization);

    public void update(Specialization specialization);

    public void delete(Specialization specialization);

    public List<Specialization> findAll();

    public Specialization findByName(String name);
}
