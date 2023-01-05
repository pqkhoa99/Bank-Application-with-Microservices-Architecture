package com.bank.accountsservice.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomerDetails {

    private Accounts accounts;
    private List<Loans> loans;
    private List<Cards> cards;

}