package com.bank.cardsservice.controller;

import java.util.List;

import com.bank.cardsservice.config.CardsServiceConfig;
import com.bank.cardsservice.model.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bank.cardsservice.model.Cards;
import com.bank.cardsservice.model.Customer;
import com.bank.cardsservice.repository.CardsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RestController
public class CardsController {

    @Autowired
    private CardsRepository cardsRepository;

    @Autowired
    private CardsServiceConfig cardsConfig;

    @PostMapping("/myCards")
    public List<Cards> getCardDetails(@RequestHeader("bank-correlation-id") String correlationid, @RequestBody Customer customer) {
        List<Cards> cards = cardsRepository.findByCustomerId(customer.getCustomerId());
        if (cards != null) {
            return cards;
        } else {
            return null;
        }
    }

    @GetMapping("/cards/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(cardsConfig.getMsg(), cardsConfig.getBuildVersion(),
                cardsConfig.getMailDetails(), cardsConfig.getActiveBranches());
        String jsonStr = ow.writeValueAsString(properties);
        return jsonStr;
    }

}