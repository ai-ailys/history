package ru.currency_pair.history.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import ru.currency_pair.history.HistoryApplication;
import ru.currency_pair.history.dto.CurrencyPairDTO;
import ru.currency_pair.history.entity.HistoryEntity;
import ru.currency_pair.history.mapper.CurrencyPairMapper;
import ru.currency_pair.history.repository.HistoryRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class HistoryService {

    static final Logger log = LoggerFactory.getLogger(HistoryApplication.class);

    @Autowired
    GuideService guideService;

    @Autowired
    CurrencyPairMapper mapper;

    @Autowired
    HistoryRepository repository;

    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);
    WebClient client = WebClient.create("http://localhost:8081/api/v1");

    @Scheduled(fixedDelayString = "PT030S")
    public void test() throws ParseException {

        HistoryEntity entity;
        try {
            for (String name: guideService.findAllName()){


                List<CurrencyPairDTO> pairs = client
                        .get()
                        .uri("/currency_pairs?name=" + name)
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<List<CurrencyPairDTO>>() {
                        })
                        .block(REQUEST_TIMEOUT);

                assert pairs != null;
                for (CurrencyPairDTO dto : pairs) {
                    entity = mapper.dtoToEntity(dto);
                    if (!(repository.existsByNameAndTime(entity.getName(), entity.getTime()))) {
                        repository.save(entity);
                    }
                }
            }
            log.debug("DB 'HISTORY' was updated");
        }
        catch (WebClientRequestException exception) {
            log.warn("Generator service is not running");
        }

    }


    @GetMapping
    public List<HistoryEntity> findInTimeRange2(@RequestParam String name,
                                                @RequestParam String startTime,
                                                @RequestParam String endTime) throws ParseException {

        String date_ = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

        Date start = new SimpleDateFormat("HH:mm:ss").parse(startTime);
        Date end = new SimpleDateFormat("HH:mm:ss").parse(endTime);
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(date_);

        return repository.findAllByTimeBetweenAndDateAndName(start, end, date, name);
    }




}
