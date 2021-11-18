package app.models.AnswerTransferModels;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GetPatientListAppointmentAtm implements Serializable {
    private Long appointmentID;
    private String first_name;
    private String last_name;
    private String patronymic;
    private String specialization;
    private String dateTime;
}
