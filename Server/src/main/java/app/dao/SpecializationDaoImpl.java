package app.dao;

import app.entity.Doctor;
import app.entity.Specialization;
import app.entity.User;
import app.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SpecializationDaoImpl implements SpecializationDao{
    public Specialization findById(long id){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Specialization.class, id);
    }

    public void save(Specialization specialization){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(specialization);
        tx1.commit();
        session.close();
    }

    public void update(Specialization specialization){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(specialization);
        tx1.commit();
        session.close();
    }

    public void delete(Specialization specialization){
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(specialization);
        tx1.commit();
        session.close();
    }

    public List<Specialization> findAll(){
        String sql = "From " + Specialization.class.getSimpleName();
        List<Specialization> specializations = (List<Specialization>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(sql).list();
        return specializations;
    }

    public Specialization findByName(String name) {
        List<Specialization> specializations = HibernateSessionFactoryUtil.getSessionFactory().openSession().createNativeQuery(
                "select * from hospital_db.specialization where name = :param",
                Specialization.class
        )
                .setParameter("param", name)
                .getResultList();

        Specialization specialization;
        if (specializations.size()!=0){
            specialization=specializations.get(0);
        }else {
            specialization=null;
        }

        return specialization;
    }
}
