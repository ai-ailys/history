package ru.currency_pair.history.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.currency_pair.history.entity.GuideEntity;
import ru.currency_pair.history.repository.GuideRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuideService {

    @Autowired
    GuideEntity config;

    @Autowired
    GuideRepository repository;

    @EventListener(ApplicationReadyEvent.class)
    public void configToDatabase(){
        for (GuideEntity.Info info : config.getGuide()){
            if (!(repository.existsByName(info.getName()))) {
                repository.save(info);
            }
        }
    }

    public List<String> findAllName(){
        return repository.
                findAll().
                stream().
                map(guide -> guide.getName()).
                collect(Collectors.toList());
    }




}
