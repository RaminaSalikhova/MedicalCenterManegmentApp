package app.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Address {
    private long id;
    private String name;
    private Integer houseNumber;
    private Integer flatNumber;
    private Long districtId;
    private District districtByDistrictId;
    private Collection<Patient> patientsById;

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

    @Basic
    @Column(name = "houseNumber", nullable = true)
    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Basic
    @Column(name = "flatNumber", nullable = true)
    public Integer getFlatNumber() {
        return flatNumber;
    }

    public void setFlatNumber(Integer flatNumber) {
        this.flatNumber = flatNumber;
    }

    @Basic
    @Column(name = "districtID", nullable = true, insertable = false, updatable = false)
    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return id == address.id && Objects.equals(name, address.name) && Objects.equals(houseNumber, address.houseNumber) && Objects.equals(flatNumber, address.flatNumber) && Objects.equals(districtId, address.districtId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, houseNumber, flatNumber, districtId);
    }

    @ManyToOne
    @JoinColumn(name = "districtID", referencedColumnName = "ID")
    public District getDistrictByDistrictId() {
        return districtByDistrictId;
    }

    public void setDistrictByDistrictId(District districtByDistrictId) {
        this.districtByDistrictId = districtByDistrictId;
    }

    @OneToMany(mappedBy = "addressByAddressId")
    public Collection<Patient> getPatientsById() {
        return patientsById;
    }

    public void setPatientsById(Collection<Patient> patientsById) {
        this.patientsById = patientsById;
    }
}
