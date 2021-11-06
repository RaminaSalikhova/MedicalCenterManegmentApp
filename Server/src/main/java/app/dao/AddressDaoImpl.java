package app.dao;

import app.entity.Address;
import app.entity.Appointment;
import app.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AddressDaoImpl implements AddressDao{
    @Override
    public Address findById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Address.class, id);
    }

    @Override
    public void save(Address address) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(address);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Address address) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(address);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Address address) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(address);
        tx1.commit();
        session.close();
    }

    @Override
    public List<Address> findAll() {
        String sql = "From " + Address.class.getSimpleName();
        List<Address> addresses = (List<Address>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(sql).list();
        return addresses;
    }
}
