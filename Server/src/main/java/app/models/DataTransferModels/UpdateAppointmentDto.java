package app.models.DataTransferModels;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UpdateAppointmentDto implements Serializable {
    private long appointmentID;
    private long patientID;
    private double weight;
    private double height;
    private String report;
    private String recommendation;
    private String diagnosis;
    private boolean visited;
    private String sex;
}
