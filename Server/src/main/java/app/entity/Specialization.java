package app.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Specialization {
    private long id;
    private String name;
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
        Specialization that = (Specialization) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @OneToMany(mappedBy = "specializationBySpecializationId")
    public Collection<Doctor> getDoctorsById() {
        return doctorsById;
    }

    public void setDoctorsById(Collection<Doctor> doctorsById) {
        this.doctorsById = doctorsById;
    }
}
