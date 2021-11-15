package app.dao;

import app.entity.District;
import app.entity.Specialization;

import java.util.List;

public interface DistrictDao {
    public District findById(long id);

    public void save(District district);

    public void update(District district);

    public void delete(District district);

    public List<District> findAll();

    public District findByName(String name);
}
