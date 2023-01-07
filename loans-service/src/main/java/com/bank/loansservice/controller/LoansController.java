package com.bank.loansservice.controller;

import com.bank.loansservice.config.LoansServiceConfig;
import com.bank.loansservice.model.Customer;
import com.bank.loansservice.model.Loans;
import com.bank.loansservice.model.Properties;
import com.bank.loansservice.repository.LoansRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoansController {

    @Autowired
    private LoansRepository loansRepository;

    @Autowired
    private LoansServiceConfig loansConfig;

    @PostMapping("/myLoans")
    public List<Loans> getLoansDetails(@RequestHeader("bank-correlation-id") String correlationid, @RequestBody Customer customer) {
        List<Loans> loans = loansRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
        if (loans != null) {
            return loans;
        } else {
            return null;
        }
    }

    @GetMapping("/loans/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(
                loansConfig.getMsg(),
                loansConfig.getBuildVersion(),
                loansConfig.getMailDetails(),
                loansConfig.getActiveBranches());
        String jsonStr = ow.writeValueAsString(properties);
        return jsonStr;
    }
}
