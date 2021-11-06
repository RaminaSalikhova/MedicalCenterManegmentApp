package app.dao;

import app.entity.District;
import app.entity.Specialization;
import app.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DistrictDaoImpl implements DistrictDao{
    @Override
    public District findById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(District.class, id);
    }

    @Override
    public void save(District district) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(district);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(District district) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(district);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(District district) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(district);
        tx1.commit();
        session.close();
    }

    @Override
    public List<District> findAll() {
        String sql = "From " + District.class.getSimpleName();
        List<District> districts = (List<District>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(sql).list();
        return districts;
    }
}
