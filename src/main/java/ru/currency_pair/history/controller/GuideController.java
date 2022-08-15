package ru.currency_pair.history.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.currency_pair.history.HistoryApplication;
import ru.currency_pair.history.entity.GuideEntity;
import ru.currency_pair.history.service.GuideService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/v1/guide")
public class GuideController {

    static final Logger log = LoggerFactory.getLogger(HistoryApplication.class);

    @Autowired
    GuideService service;

    @GetMapping
    public List<GuideEntity.Info> guideInfo() {
        log.debug("Method 'guideInfo' was called");
        return service.guideInfo();
    }

}
