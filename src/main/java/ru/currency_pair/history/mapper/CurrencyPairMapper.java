package ru.currency_pair.history.mapper;

import org.springframework.stereotype.Component;
import ru.currency_pair.history.dto.CurrencyPairDTO;
import ru.currency_pair.history.entity.HistoryEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class CurrencyPairMapper {

    public HistoryEntity dtoToEntity(CurrencyPairDTO dto) throws ParseException {
        Date time = new SimpleDateFormat("HH:mm:ss").parse(dto.getTime());
        Date date = new SimpleDateFormat("yyyy-MM-dd")
                .parse(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
        //Date dateAndTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        //        .parse(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE) + " " + dto.getTime());
        return HistoryEntity.builder()
                .name(dto.getName())
                .value(dto.getValue())
                .time(time)
                .date(date)
                //.fullDate(dateAndTime)
                .build();
    }

}
