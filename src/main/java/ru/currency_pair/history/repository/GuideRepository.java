package ru.currency_pair.history.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.currency_pair.history.entity.GuideEntity;

public interface GuideRepository extends JpaRepository<GuideEntity.Info, Integer> {

    GuideEntity.Info findByName(String name);
    Boolean existsByName(String name);


}
