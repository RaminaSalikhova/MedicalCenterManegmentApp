package app.dao;

import app.entity.Address;
import app.entity.Appointment;
import app.entity.User;
import app.models.DataTransferModels.UpdateAppointmentDto;
import app.models.DataTransferModels.UpdateDistrictDto;
import app.models.DataTransferModels.UpdateUserAddressDto;
import app.models.DataTransferModels.UpdateUserByPatientDto;
import app.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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

    public Address findAddress(UpdateUserAddressDto address) {
        List<Address> addresses = HibernateSessionFactoryUtil.getSessionFactory().openSession().createNativeQuery(
                "select * from address where name = :param AND houseNumber =:paramHouseNumber AND flatNumber =:paramFlatNumber",
                Address.class
        )
                .setParameter("param", address.getAddressName())
                .setParameter("paramHouseNumber", address.getHouse())
                .setParameter("paramFlatNumber", address.getFlat())
                .getResultList();

        Address address1;
        if (addresses.size()!=0){
            address1=addresses.get(0);
        }else {
            address1=null;
        }

        return address1;
    }

    public List<Address> findEqualsHouseAndStreetAddress(UpdateDistrictDto address) {
        List<Address> addresses = HibernateSessionFactoryUtil.getSessionFactory().openSession().createNativeQuery(
                "select * from address where name = :param AND houseNumber =:paramHouseNumber",
                Address.class
        )
                .setParameter("param", address.getAddressName())
                .setParameter("paramHouseNumber", address.getAddressHouse())
                .getResultList();

        return addresses;
    }


    public List<Address> findAllGroupByHouseNumber() {
        List<Address> addresses = HibernateSessionFactoryUtil.getSessionFactory().openSession().createNativeQuery(
                "select * from address " +
                        "Group by houseNumber;",
                Address.class
        )
                .getResultList();

        return addresses;
    }
//    public void updateHouseStreetDistrict(String house, String street, Long districtID){
//        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
//        Transaction tx1 = session.beginTransaction();
//
//        Query query = session.createNativeQuery(
//                "update hospital_db.Address set name = :paramStreet, " +
//                        " paramHouse = :paramRec, " +
//                        " paramDistrictID = :paramReport," +
//                        "where id = :id"
//        )
//                .setParameter("paramStreet", street)
//                .setParameter("paramHouse",updateAppointmentDto.getDiagnosis())
//                .setParameter("paramDistrictID",updateAppointmentDto.getReport())
//                .setParameter("visited",updateAppointmentDto.isVisited())
//                .setParameter("id", updateAppointmentDto.getAppointmentID());
//
//
//        int result = query.executeUpdate();
//        tx1.commit();
//        session.close();
//    }
}
