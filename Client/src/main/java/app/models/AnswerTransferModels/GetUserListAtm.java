package app.models.AnswerTransferModels;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GetUserListAtm implements Serializable {
    private long userID;
    private String first_name;
    private String last_name;
    private String patronymic;
    private String login;
    private String phoneNum;
    private String role;
    private boolean status;
}
