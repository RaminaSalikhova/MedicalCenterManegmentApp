package app.models.AnswerTransferModels;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GetUserAddressAtm implements Serializable {
    private String districtName;
    private String addressName;
    private String addressHouse;
    private String addressFlat;
}
