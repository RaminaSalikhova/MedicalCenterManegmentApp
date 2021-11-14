package app.dao;

import app.entity.Appointment;
import app.entity.Doctor;
import app.entity.User;
import app.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DoctorDaoImpl implements DoctorDao{
    public Doctor findById(long id){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Doctor.class, id);
    }

    public void save(Doctor doctor){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(doctor);
        tx1.commit();
        session.close();
    }

    public void update(Doctor doctor){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(doctor);
        tx1.commit();
        session.close();
    }

    public void delete(Doctor doctor){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(doctor);
        tx1.commit();
        session.close();
    }

    public List<Doctor> findAll(){
        String sql = "From " + Doctor.class.getSimpleName();
        List<Doctor> doctors = (List<Doctor>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(sql).list();
        return doctors;

    }

    public List<Doctor> findAllNotNull(){
        List<Doctor> doctors = HibernateSessionFactoryUtil.getSessionFactory().openSession().createNativeQuery(
                "select * from doctor where specializationID AND scheduleID AND districtID is not NULL ",
                Doctor.class
        )
                .getResultList();

//        String sql = "From " + Doctor.class.getSimpleName();
//        List<Doctor> doctors = (List<Doctor>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(sql).list();
        return doctors;

    }

    public Doctor findAllByUserID(long userID){
        List<Doctor> doctors = HibernateSessionFactoryUtil.getSessionFactory().openSession().createNativeQuery(
                "select * from doctor where userID = :param ",
                Doctor.class
        )
                .setParameter("param", userID)
                .getResultList();

        Doctor doctor;
        if (doctors.size()!=0){
            doctor=doctors.get(0);
        }else {
            doctor=null;
        }

        return doctor;
    }
}
