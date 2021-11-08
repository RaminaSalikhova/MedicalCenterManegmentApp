package app.dao;

import app.entity.User;
import app.models.DataTransferModels.UpdateUserByPatientDto;

import java.util.List;

public interface UserDao {
    public User findById(long id);

    public void save(User user);

    public void update(User user);

    public void updateUserStatus(long id);

    public void delete(User user);

    public List<User> findAll();

    public User findByLogin(String value);

    public void updateUsernameAndPhone(UpdateUserByPatientDto updateUserByPatientDto);
}
