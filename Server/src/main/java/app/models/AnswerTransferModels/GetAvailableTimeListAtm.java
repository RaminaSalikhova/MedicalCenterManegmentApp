package app.models.AnswerTransferModels;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GetAvailableTimeListAtm implements Serializable {
//    private Date date;
    private String date;
    private String time;
}
