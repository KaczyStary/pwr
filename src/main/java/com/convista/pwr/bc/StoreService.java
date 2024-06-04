package com.convista.pwr.bc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    @Autowired
    private PersonService personService;

    @Autowired
    private CashService cashService;

    public Beer buyBeerForClient(Person client) {
        // TODO: implement age restriction and use also dummy cashService to pay by blik
        // TODO: if person is not allowed to: age < 18 or Blikpayment returns false throw Runtime exception with detailed message
        return new Beer("Harnaś");
    }
}
