package app.services;

import app.dao.DoctorDao;
import app.dao.DoctorDaoImpl;
import app.dao.ScheduleDao;
import app.dao.ScheduleDaoImpl;
import app.entity.Doctor;
import app.entity.Schedule;

import java.util.List;

public class ScheduleService {
    public ScheduleDao scheduleDao = new ScheduleDaoImpl();

    public Schedule findById(long id) {
        return scheduleDao.findById(id);
    }

    public void save(Schedule schedule) {
        scheduleDao.save(schedule);
    }

    public void update(Schedule schedule) {
        scheduleDao.update(schedule);
    }

    public void delete(Schedule schedule) {
        scheduleDao.delete(schedule);
    }

    public List<Schedule> findAll() {
        return scheduleDao.findAll();
    }

}
