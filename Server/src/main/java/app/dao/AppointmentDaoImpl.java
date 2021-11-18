package app.dao;

import app.entity.Appointment;
import app.entity.District;
import app.entity.User;
import app.models.DataTransferModels.UpdateAppointmentDto;
import app.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AppointmentDaoImpl implements AppointmentDao{
    public Appointment findById(long id){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Appointment.class, id);
    }

    public void save(Appointment appointment){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(appointment);
        tx1.commit();
        session.close();
    }

    public void update(Appointment appointment){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(appointment);
        tx1.commit();
        session.close();
    }

    public void updateVisit(UpdateAppointmentDto updateAppointmentDto){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();

        Query query = session.createNativeQuery(
                "update hospital_db.Appointment set diagnosis = :paramDiagnosis, " +
                        " recommendation = :paramRec, " +
                        " report = :paramReport," +
                        "isVisited = :visited " +
                        "where id = :id"
        )
                .setParameter("paramRec", updateAppointmentDto.getRecommendation())
                .setParameter("paramDiagnosis",updateAppointmentDto.getDiagnosis())
                .setParameter("paramReport",updateAppointmentDto.getReport())
                .setParameter("visited",updateAppointmentDto.isVisited())
                .setParameter("id", updateAppointmentDto.getAppointmentID());


        int result = query.executeUpdate();
        tx1.commit();
        session.close();
    }

    public void delete(Appointment appointment){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(appointment);
        tx1.commit();
        session.close();
    }

    public List<Appointment> findAll(){
        String sql = "From " + Appointment.class.getSimpleName();
        List<Appointment> appointments = (List<Appointment>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(sql).list();
        return appointments;
    }

    public List<Appointment> findAllByDoctorID(long doctorID){
//        String sql = "From " + Appointment.class.getSimpleName();
//        List<Appointment> appointments = (List<Appointment>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(sql).list();
//        return appointments;

        List<Appointment> appointments = HibernateSessionFactoryUtil.getSessionFactory().openSession().createNativeQuery(
                "select * from appointment where doctorID = :param and isVisited=0",
                Appointment.class
        )
                .setParameter("param", doctorID)
                .getResultList();

        return appointments;
    }

    public List<Appointment> findAllByPatientID(long patientID){

        List<Appointment> appointments = HibernateSessionFactoryUtil.getSessionFactory().openSession().createNativeQuery(
                "select * from appointment where patientID = :param and isVisited=0",
                Appointment.class
        )
                .setParameter("param", patientID)
                .getResultList();

        return appointments;
    }

    public List<Appointment> findAllByPatientIDAndVisited(long patientID){

        List<Appointment> appointments = HibernateSessionFactoryUtil.getSessionFactory().openSession().createNativeQuery(
                "select * from appointment where patientID = :param",
                Appointment.class
        )
                .setParameter("param", patientID)
                .getResultList();

        return appointments;
    }

    public List<Appointment> findAllByPatientIDAndDoctorID(long patientID, long doctorID){

        List<Appointment> appointments = HibernateSessionFactoryUtil.getSessionFactory().openSession().createNativeQuery(
                "select * from appointment where patientID = :param and doctorID =:param2 and isVisited=0",
                Appointment.class
        )
                .setParameter("param", patientID)
                .setParameter("param2", doctorID)
                .getResultList();

        return appointments;
    }

}
