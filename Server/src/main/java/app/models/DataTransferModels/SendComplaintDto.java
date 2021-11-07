package app.models.DataTransferModels;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SendComplaintDto implements Serializable {
    private String message;
}
