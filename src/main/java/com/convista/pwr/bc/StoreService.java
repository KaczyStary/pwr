package com.convista.pwr.bc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    // No magic numbers and strings !
    private static final int ALCOHOL_LEGAL_AGE = 18;
    private static final String ILLEGAL_MESSAGE = "It is illegal";
    private static final String BLIK_PAYMENT_ERROR_MESSAGE = "Blik payment error";
    private static final String BEER_BRAND = "Harnaś";

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CashService cashService;

    @Autowired
    private WeatherService weatherService;

    public Beer buyBeerForClient(Person client) {

        if (personService.calculateCurrentAge(client) >= ALCOHOL_LEGAL_AGE) {
            if (cashService.payByBlik(123456)) {
                return new Beer("Harnaś");
            }
            throw new RuntimeException("Blik payment error");
        }
        throw new RuntimeException("It is illegal");
    }

    /**
     * Jeden poziom 'wcięcia' brak zagnieżdżania bardziej czytelne warunki
     *
     */
    public Beer buyBeerForClientAndTea(Person client, List<String> someList) {

        if (personService.calculateCurrentAge(client) < ALCOHOL_LEGAL_AGE) {
            throw new RuntimeException(ILLEGAL_MESSAGE);
        }
        if (!cashService.payByBlik(123456)) {
            throw new RuntimeException(BLIK_PAYMENT_ERROR_MESSAGE);
        }

        someList.add("do not forget about tea: " + buyTea(TeaType.ICE).toString());
        return new Beer(BEER_BRAND);
    }

    public Tea buyTea(TeaType teaType) {
        return new Tea(teaType);
    }

    public void buyPizza(List<String> partyActions) {
        if (weatherService.readCurrentTemperature() > 20) {
            partyActions.add("temperature checked - fine");
            // TODO: prepare BBQ
        } else {
            partyActions.add("temperature checked - not so fine ");
            // TODO: order pizza
        }
        partyActions.add("plan to buy beer before 22: " + buyBeerForClientAndTea(personRepository.findPerson(), partyActions).toString());
        if (personService.invitedPeopleCount() < 5) {
            partyActions.add("create chill playlist");
        } else {
            partyActions.add("create fire playlist");
        }
    }
}
