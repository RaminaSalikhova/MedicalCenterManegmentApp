package app.models.DataTransferModels;

import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GetAvailableTimeListDto implements Serializable {
    private long id;
}
