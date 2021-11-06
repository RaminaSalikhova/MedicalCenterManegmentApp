package app.models.DataTransferModels;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SetAppointmentDto implements Serializable {
    private long userID;
    private long doctorID;
    private String date;
    private String time;

}