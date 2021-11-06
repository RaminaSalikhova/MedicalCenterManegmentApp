package app.dao;

import app.entity.Schedule;
import app.entity.User;
import app.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ScheduleDaoImpl implements ScheduleDao{
    public Schedule findById(long id){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Schedule.class, id);
    }

    public void save(Schedule schedule){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(schedule);
        tx1.commit();
        session.close();
    }

    public void update(Schedule schedule){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(schedule);
        tx1.commit();
        session.close();
    }

    public void delete(Schedule schedule){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(schedule);
        tx1.commit();
        session.close();
    }

    public List<Schedule> findAll(){
        String sql = "From " + Schedule.class.getSimpleName();
        List<Schedule> schedules = (List<Schedule>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(sql).list();
        return schedules;
    }
}
