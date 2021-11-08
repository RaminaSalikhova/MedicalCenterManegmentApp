package app.dao;

import app.entity.Complaint;
import app.entity.Patient;
import app.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ComplaintDaoImpl implements ComplaintDao{
    @Override
    public Complaint findById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Complaint.class, id);
    }

    @Override
    public void save(Complaint complaint) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(complaint);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Complaint complaint) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(complaint);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Complaint complaint) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(complaint);
        tx1.commit();
        session.close();
    }


    @Override
    public void deleteByID(long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();

        Query query = session.createNativeQuery(
                "delete from hospital_db.complaint " +
                        "where id = :id"
        )
                .setParameter("id", id);


        int result = query.executeUpdate();
        tx1.commit();
        session.close();
    }

    @Override
    public List<Complaint> findAll() {
        String sql = "From " + Complaint.class.getSimpleName();
        List<Complaint> complaints = (List<Complaint>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(sql).list();
        return complaints;
    }
}
