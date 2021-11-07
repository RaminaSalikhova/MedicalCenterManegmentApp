package app.models.DataTransferModels;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DoctorAppointmentPatientDto implements Serializable {
    private long patientID;
    private long appointmentID;
}
