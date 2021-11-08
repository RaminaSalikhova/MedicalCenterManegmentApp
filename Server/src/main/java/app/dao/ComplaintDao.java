package app.dao;

import app.entity.Address;
import app.entity.Complaint;

import java.util.List;

public interface ComplaintDao {
    public Complaint findById(long id);

    public void save(Complaint complaint);

    public void update(Complaint complaint);

    public void delete(Complaint complaint);

    public void deleteByID(long id);

    public List<Complaint> findAll();
}
