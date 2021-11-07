package app.services;

import app.dao.ComplaintDao;
import app.dao.ComplaintDaoImpl;
import app.dao.DistrictDao;
import app.dao.DistrictDaoImpl;
import app.entity.Complaint;
import app.entity.District;

import java.util.List;

public class ComplaintService {
    public ComplaintDao complaintDao = new ComplaintDaoImpl();

    public Complaint findById(long id) {
        return complaintDao.findById(id);
    }

    public void save(Complaint complaint) {
        complaintDao.save(complaint);
    }

    public void update(Complaint complaint) {
        complaintDao.update(complaint);
    }

    public void delete(Complaint complaint) {
        complaintDao.delete(complaint);
    }

    public List<Complaint> findAll() {
        return complaintDao.findAll();
    }
}
