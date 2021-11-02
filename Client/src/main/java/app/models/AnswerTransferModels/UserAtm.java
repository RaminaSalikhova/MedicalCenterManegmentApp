package app.models.AnswerTransferModels;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserAtm implements Serializable {
    private long id;
    private String lastName;
    private String firstName;
    private String patronymic;
    private String login;
    private String password;
    private String dateOfBirth;
    private String phoneNum;
    private String role;
    private String pascode;

}