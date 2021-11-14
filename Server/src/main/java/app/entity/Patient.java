package app.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Patient {
    private long id;
    private Long userId;
    private Long addressId;
    private String sex;
    private Double weight;
    private Double height;
    private Collection<Appointment> appointmentsById;
    private User userByUserId;
    private Address addressByAddressId;

    @Id
    @Column(name = "ID", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userID", nullable = false, insertable = false, updatable = false)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "addressID", nullable = true, insertable = false, updatable = false)
    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    @Basic
    @Column(name = "sex", nullable = true, length = 10)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "weight", nullable = true, precision = 0)
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "height", nullable = true, precision = 0)
    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return id == patient.id && userId == patient.userId && addressId == patient.addressId && Objects.equals(sex, patient.sex) && Objects.equals(weight, patient.weight) && Objects.equals(height, patient.height);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, addressId, sex, weight, height);
    }

    @OneToMany(mappedBy = "patientByPatientId")
    public Collection<Appointment> getAppointmentsById() {
        return appointmentsById;
    }

    public void setAppointmentsById(Collection<Appointment> appointmentsById) {
        this.appointmentsById = appointmentsById;
    }

    @ManyToOne
    @JoinColumn(name = "userID", referencedColumnName = "ID", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "addressID", referencedColumnName = "ID", nullable = true)
    public Address getAddressByAddressId() {
        return addressByAddressId;
    }

    public void setAddressByAddressId(Address addressByAddressId) {
        this.addressByAddressId = addressByAddressId;
    }
}
