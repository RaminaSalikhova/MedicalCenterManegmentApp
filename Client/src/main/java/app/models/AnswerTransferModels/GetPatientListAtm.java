package app.models.AnswerTransferModels;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GetPatientListAtm implements Serializable {
    private long appointmentID;
    private long patientID;
    private String first_name;
    private String last_name;
    private String patronymic;
    private String address;
    private String sex;
    private String time;
}
