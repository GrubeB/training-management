package pl.dawid.app.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "tbl_training")
public class Training extends IdentityModel{

    @Column(name = "training_date", nullable = false)
    private Date trainingDate;

    @Column(name = "training_time")
    private Time trainingTime;

    @Column(name = "travelled_distance")
    private Double travelledDistance;

    @Column(name = "burned_calories")
    private Integer burnedCalories;

    private String comment;
}
