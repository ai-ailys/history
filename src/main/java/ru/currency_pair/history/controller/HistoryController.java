package ru.currency_pair.history.controller;

import liquibase.pro.packaged.S;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.currency_pair.history.dto.RequestDTO;
import ru.currency_pair.history.entity.HistoryEntity;
import ru.currency_pair.history.repository.HistoryRepository;
import ru.currency_pair.history.service.HistoryService;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/history")
public class HistoryController {

    @Autowired
    HistoryService service;

    @Autowired
    HistoryRepository repository;

    @GetMapping
    public List<HistoryEntity> findInTimeRange(@RequestBody RequestDTO requestDTO) throws ParseException {
        return service.findInTimeRange(requestDTO);
    }

    @GetMapping(value = "/findAllBy")
    public List<HistoryEntity> findInTimeRange(@RequestParam String name, @RequestParam String startTime, @RequestParam String endTime) throws ParseException {
        return service.findInTimeRange2(name, startTime, endTime);
    }


}
