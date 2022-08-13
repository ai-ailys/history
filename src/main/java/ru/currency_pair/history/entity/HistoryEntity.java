package ru.currency_pair.history.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@AllArgsConstructor
@Builder
@Entity
@Data
@NoArgsConstructor
@Table(name = "History")
public class HistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(name = "value_field")
    private Double value;

    @Temporal(TemporalType.TIME)
    private Date time;
    @Temporal(TemporalType.DATE)
    private Date date;

    //@Temporal(TemporalType.TIMESTAMP)
    //private Date fullDate;


}
