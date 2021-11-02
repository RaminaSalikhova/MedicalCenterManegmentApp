package app.entity;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity(name = "User")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column (name ="\"ID\"")
    private long id;

    @Column
    private String lastName;

    @Column
    private String firstName;

    @Column
    private String patronymic;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String dateOfBirth;

    @Column
    private String phoneNum;

    @Column
    private String role;

    @Column
    private Boolean status;
}
