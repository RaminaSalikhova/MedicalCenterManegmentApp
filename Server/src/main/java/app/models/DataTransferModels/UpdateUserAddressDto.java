package app.models.DataTransferModels;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UpdateUserAddressDto implements Serializable {
    private Long userID;
    private String addressName;
    private String house;
    private String flat;
}
