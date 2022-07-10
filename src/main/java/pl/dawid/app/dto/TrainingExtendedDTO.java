package pl.dawid.app.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
public class TrainingExtendedDTO extends TrainingBaseDTO {
    private Double averageSpeed;
}
