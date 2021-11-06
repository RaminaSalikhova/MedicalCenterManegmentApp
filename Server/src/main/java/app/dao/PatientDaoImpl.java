package app.dao;

import app.entity.Address;
import app.entity.Appointment;
import app.entity.Patient;
import app.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PatientDaoImpl implements PatientDao{
    @Override
    public Patient findById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Patient.class, id);
    }

    @Override
    public void save(Patient patient) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(patient);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Patient patient) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(patient);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Patient patient) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(patient);
        tx1.commit();
        session.close();
    }

    @Override
    public List<Patient> findAll() {
        String sql = "From " + Patient.class.getSimpleName();
        List<Patient> patients = (List<Patient>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(sql).list();
        return patients;
    }

    public Patient findPatientByUserID(long userID){
//        String sql = "From " + Appointment.class.getSimpleName();
//        List<Appointment> appointments = (List<Appointment>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(sql).list();
//        return appointments;

        List<Patient> patients = HibernateSessionFactoryUtil.getSessionFactory().openSession().createNativeQuery(
                "select * from patient where userID = :param ",
                Patient.class
        )
                .setParameter("param", userID)
                .getResultList();
        Patient patient;
        if (patients.size()!=0){
            patient=patients.get(0);
        }else {
            patient=null;
        }
        return patient;
    }
}
