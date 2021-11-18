package app.models.AnswerTransferModels;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GetPatientListOfVisitedAppointmentAtm implements Serializable {
    private Long visitedAppointmentID;
    private String visitedAppointmentFirst_name;
    private String visitedAppointmentLast_name;
    private String visitedAppointmentPatronymic;
    private String visitedAppointmentSpecialization;
    private String visitedAppointmentDiagnosis;
    private String visitedAppointmentRecommendation;
    private String visitedAppointmentReport;
    private String visitedAppointmentDateTime;

}
