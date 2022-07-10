package pl.dawid.app.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Date;
import java.sql.Time;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
public class TrainingUpdateDTO extends IdentityDTO {
    private Date trainingDate;
    private Time trainingTime;
    private Double travelledDistance;
    private Integer burnedCalories;
    private String comment;
}
