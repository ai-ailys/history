package ru.currency_pair.history.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyPairDTO {

    private String name;
    private Double value;
    private String time;

}
