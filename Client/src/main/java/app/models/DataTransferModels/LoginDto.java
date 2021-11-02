package app.models.DataTransferModels;


import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LoginDto implements Serializable {

    private String login;
    private String password;
//    private String role;
}
