package app.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class District {
    private long id;
    private String name;
    private Collection<Address> addressesById;
    private Collection<Doctor> doctorsById;

    @Id
    @Column(name = "ID", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        District district = (District) o;
        return id == district.id && Objects.equals(name, district.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "districtByDistrictId")
    public Collection<Address> getAddressesById() {
        return addressesById;
    }

    public void setAddressesById(Collection<Address> addressesById) {
        this.addressesById = addressesById;
    }

    @OneToMany(mappedBy = "districtByDistrictId")
    public Collection<Doctor> getDoctorsById() {
        return doctorsById;
    }

    public void setDoctorsById(Collection<Doctor> doctorsById) {
        this.doctorsById = doctorsById;
    }
}
