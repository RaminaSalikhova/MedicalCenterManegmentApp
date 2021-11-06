package app.dao;

import app.entity.Doctor;
import app.entity.Schedule;

import java.util.List;

public interface ScheduleDao {
    public Schedule findById(long id);

    public void save(Schedule schedule);

    public void update(Schedule schedule);

    public void delete(Schedule schedule);

    public List<Schedule> findAll();

}
