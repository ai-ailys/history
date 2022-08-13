package ru.currency_pair.history.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "application")
@Data
@NoArgsConstructor
public class GuideEntity {

    List<Info> guide;
    @Data
    @NoArgsConstructor
    @Entity
    @Table(name = "Currency_Pair_Guide")
    public static class Info{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private String name;
        private String basic;
        private String quote;
        private String basicCountry;
        private String quoteCountry;
        private Boolean isMajor;

    }

}
