package app.models.AnswerTransferModels;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DoctorAppointmentPatientAtm implements Serializable {
    private long patientID;
    private String diagnose;
    private String recommendation;
    private String report;
    private boolean visited;
    private double weight;
    private double height;
    private String sex;
}
