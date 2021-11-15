package app.models.DataTransferModels;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UpdateDoctorDto implements Serializable {
    private Long userID, doctorID;
    private String firstName,lastName, patronymic, phoneNum,
            fromWorkTime,
            tillWorkTime,
            roomNumber,
            specialization,
            districtName,salary, experience;
}