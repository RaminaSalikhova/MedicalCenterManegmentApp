package app.models.DataTransferModels;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AddAddressDto implements Serializable {
    private String districtName;
    private String addressName;
    private String addressHouse;
    private String addressFlat;
}
