package app.models.DataTransferModels;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UpdateUserByPatientDto implements Serializable {
    private long id;
    private String lastName;
    private String firstName;
    private String patronymic;
    private String login;
    private String password;
    private String dateOfBirth;
    private String phoneNum;
    private String role;
}