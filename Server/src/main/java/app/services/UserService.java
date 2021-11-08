package app.services;

import app.dao.UserDao;
import app.dao.UserDaoImpl;
import app.entity.User;
import app.models.DataTransferModels.UpdateUserByPatientDto;

import java.util.List;

public class UserService {
    public UserDao userDao = new UserDaoImpl();

    public User findById(long id) {
        return userDao.findById(id);
    }

    public void save(User user) {
        userDao.save(user);
    }

    public void update(User user) {
        userDao.update(user);
    }

    public void updateUserStatus(long id){userDao.updateUserStatus(id);}

    public void delete(User user) {
        userDao.delete(user);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findByLogin(String value) {
        return userDao.findByLogin(value);
    }

    public void updateUsernameAndPhone(UpdateUserByPatientDto updateUserByPatientDto){
        userDao.updateUsernameAndPhone(updateUserByPatientDto);
    }
}
