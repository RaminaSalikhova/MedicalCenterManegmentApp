package app.models.AnswerTransferModels;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GetDistrictsAndAddressesForAdminAtm implements Serializable {
    private Long districtID, addressID;
    private String districtName,
            addressName,
            addressHouse,
            addressFlat;

}
