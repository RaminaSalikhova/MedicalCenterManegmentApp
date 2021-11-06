package app.models.AnswerTransferModels;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GetScheduleListAtm implements Serializable {
    private String first_name;
    private String last_name;
    private String roomNumber;
    private String startTime;
    private String endTime;
}
