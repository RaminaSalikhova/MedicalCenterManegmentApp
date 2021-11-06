package app.dao;

import app.entity.Address;
import app.entity.Appointment;

import java.util.List;

public interface AddressDao {
    public Address findById(long id);

    public void save(Address address);

    public void update(Address address);

    public void delete(Address address);

    public List<Address> findAll();
}
