package app.utils;

import app.entity.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                //  URL resource = HibernateSessionFactoryUtil.class.getResource("C:\\Users\\HP PC\\OneDrive\\Рабочий стол\\JAVA\\SlideShow-App-JavaFX\\src\\main\\resources\\hibernate.cfg.xml");
                //Configuration configuration= new Configuration().configure(resource);
                Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
                configuration.addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

//                ServiceRegistry serviceRegistery = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
//
//                SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistery);
//

            }
            catch (HibernateException ex) {
                System.err.println("Initial sessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
            }catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}
