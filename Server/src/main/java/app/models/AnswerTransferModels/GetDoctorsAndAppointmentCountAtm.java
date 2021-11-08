package app.models.AnswerTransferModels;

import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GetDoctorsAndAppointmentCountAtm implements Serializable {
    private String doctorName;
    private int appointmentsCount;
}