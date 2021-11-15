package app.services;

import app.dao.AddressDao;
import app.dao.AddressDaoImpl;
import app.dao.ScheduleDao;
import app.dao.ScheduleDaoImpl;
import app.entity.Address;
import app.entity.Schedule;
import app.models.DataTransferModels.UpdateUserAddressDto;

import java.util.List;

public class AddressService {
    public AddressDao addressDao = new AddressDaoImpl();

    public Address findById(long id) {
        return addressDao.findById(id);
    }

    public void save(Address address) {
        addressDao.save(address);
    }

    public void update(Address address) {
        addressDao.update(address);
    }

    public void delete(Address address) {
        addressDao.delete(address);
    }

    public List<Address> findAll() {
        return addressDao.findAll();
    }
    public Address findAddress(UpdateUserAddressDto address) {return addressDao.findAddress(address);}

}
