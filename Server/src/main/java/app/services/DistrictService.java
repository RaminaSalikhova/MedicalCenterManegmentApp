package app.services;

import app.dao.DistrictDao;
import app.dao.DistrictDaoImpl;
import app.dao.ScheduleDao;
import app.dao.ScheduleDaoImpl;
import app.entity.District;
import app.entity.Doctor;
import app.entity.Schedule;

import java.util.List;

public class DistrictService {
    public DistrictDao districtDao = new DistrictDaoImpl();

    public District findById(long id) {
        return districtDao.findById(id);
    }

    public void save(District district) {
        districtDao.save(district);
    }

    public void update(District district) {
        districtDao.update(district);
    }

    public void delete(District district) {
        districtDao.delete(district);
    }

    public List<District> findAll() {
        return districtDao.findAll();
    }
}
