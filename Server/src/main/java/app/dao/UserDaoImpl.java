package app.dao;

import app.entity.User;
import app.models.DataTransferModels.UpdateUserByPatientDto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import app.utils.HibernateSessionFactoryUtil;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoImpl implements UserDao {
    public User findById(long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }

    public void save(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void update(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void updateUserStatus(long id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();

        Query query = session.createNativeQuery(
                "update hospital_db.User set status = 0 " +
                        "where id = :id"
        )
                .setParameter("id", id);


        int result = query.executeUpdate();
        tx1.commit();
        session.close();
    }

    public void delete(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    public List<User> findAll() {
        String sql = "From " + User.class.getSimpleName();
        List<User> users = (List<User>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(sql).list();
        return users;
    }

    public User findByLogin(String login) {
        List<User> users = HibernateSessionFactoryUtil.getSessionFactory().openSession().createNativeQuery(
                "select * from user where login = :param",
                User.class
        )
                .setParameter("param", login)
                .getResultList();

        User newUser;
        if (users.size()!=0){
             newUser=users.get(0);
        }else {
            newUser=null;
        }

        return newUser;
    }

    public void updateUsernameAndPhone(UpdateUserByPatientDto updateUserByPatientDto) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();

        Query query = session.createNativeQuery(
                "update hospital_db.User set login = :paramLogin,  phoneNum = :paramPhone \n" +
                        "where id = :id"
        )
                .setParameter("paramLogin", updateUserByPatientDto.getLogin())
                .setParameter("paramPhone", updateUserByPatientDto.getPhoneNum())
                .setParameter("id", updateUserByPatientDto.getId());


        int result = query.executeUpdate();
        tx1.commit();
        session.close();


    }
}
