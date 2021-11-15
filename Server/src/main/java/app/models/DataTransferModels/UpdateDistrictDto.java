package app.models.DataTransferModels;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UpdateDistrictDto  implements Serializable {
    private Long districtID, addressID;
    private String districtName,
            addressName,
            addressHouse,
            addressFlat;

}