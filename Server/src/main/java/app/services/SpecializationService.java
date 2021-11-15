package app.services;

import app.dao.ScheduleDao;
import app.dao.ScheduleDaoImpl;
import app.dao.SpecializationDao;
import app.dao.SpecializationDaoImpl;
import app.entity.Doctor;
import app.entity.Schedule;
import app.entity.Specialization;

import java.util.List;

public class SpecializationService {
    public SpecializationDao specializationDao = new SpecializationDaoImpl();

    public Specialization findById(long id) {
        return specializationDao.findById(id);
    }

    public void save(Specialization specialization) {
        specializationDao.save(specialization);
    }

    public void update(Specialization specialization) {
        specializationDao.update(specialization);
    }

    public void delete(Specialization specialization) {
        specializationDao.delete(specialization);
    }

    public List<Specialization> findAll() {
        return specializationDao.findAll();
    }

    public Specialization findByName(String name) {return specializationDao.findByName(name);}
    }
