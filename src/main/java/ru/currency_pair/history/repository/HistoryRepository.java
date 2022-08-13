package ru.currency_pair.history.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.currency_pair.history.entity.HistoryEntity;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {

    Boolean existsByNameAndTime(String name, Date time);

    HistoryEntity findByDateAndTime(String date, Date time);

    List<HistoryEntity> findAllByTimeBetweenAndDateAndName(Date start, Date end, Date date, String name);

}
