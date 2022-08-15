package ru.currency_pair.history.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.currency_pair.history.HistoryApplication;
import ru.currency_pair.history.entity.HistoryEntity;
import ru.currency_pair.history.repository.HistoryRepository;
import ru.currency_pair.history.service.HistoryService;

import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/v1/history")
public class HistoryController {

    static final Logger log = LoggerFactory.getLogger(HistoryApplication.class);
    @Autowired
    HistoryService service;

    @Autowired
    HistoryRepository repository;

    @GetMapping(value = "/findAllBy")
    public List<HistoryEntity> findInTimeRange(@RequestParam String name,
                                               @RequestParam String startTime,
                                               @RequestParam String endTime) {
        log.debug("Method 'findInTimeRange' was called with name = " + name + ", startTime = " + startTime + ", endTime = " + endTime);
        try{
            return service.findInTimeRange2(name, startTime, endTime);
        } catch (ParseException exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Uncorrected time format");
        }
    }


}
