package app.models.AnswerTransferModels;

import lombok.*;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MakeAnAppointmentDoctorAtm implements Serializable {
    private long id;
    private String first_name;
    private String last_name;
    private String patronymic;
    private String roomNumber;
    private Time startTime;
    private Time endTime;
    private ArrayList<Date> arrangedTime;

}
