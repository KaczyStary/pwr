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
    private static final int TEMPERATURE_FOR_BBQ = 20;
    private static final int TEMPERATURE_FOR_COLD_TEA = 22;
    private static final int BEERS_PER_PERSON = 8;
    private static final int TEAS_PER_PERSON = 2;
    private static final int NUM_OF_BIG_PARTY_PEOPLE = 5;
    private static final String BUY_MESSAGE = "Buy: ";

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

    public void shoppingList(List<String> partyActions) {
        int temperatureOutside = weatherService.readCurrentTemperature();

        PartyFood partyFoodType = (temperatureOutside < TEMPERATURE_FOR_BBQ ? new PartyFood("Pizza", true) : new PartyFood("BBQ", false));

        int numOfVegetarians = personService.getInvitedVegetariansCount();
        int numOfPeople = personService.getInvitedPeopleCount();

        partyActions.add("temperature outside: " + temperatureOutside + " degrees");

        if (numOfVegetarians > 0){
            String partyFood = partyFoodType.getName().equals("Pizza") ? "vegetarian pizzas" : "vegetarian BBQ";
            partyActions.add(BUY_MESSAGE + numOfVegetarians + " " + partyFood);
        }

        if ((numOfPeople-numOfVegetarians) > 0){
            String partyFood = partyFoodType.getName().equals("Pizza") ? "regular/meat pizzas" : "regular/meat BBQ";
            partyActions.add(BUY_MESSAGE + (numOfPeople - numOfVegetarians) + " " + partyFood);
        }

        int numOfAbstinent = personService.getNumOfAbstinent(personRepository.partyParticipantList());
        int numOfUnderage = personService.getNumOfUnderage(personRepository.partyParticipantList());
        int numOfDrinkingBeerPeople = numOfPeople - numOfAbstinent - numOfUnderage;

        if (numOfDrinkingBeerPeople>0){
            int beersToBuy = numOfDrinkingBeerPeople * BEERS_PER_PERSON;
            partyActions.add(BUY_MESSAGE + beersToBuy + " " + BEER_BRAND + " beers");
        }

        int numOfNotDrinkingBeerPeople = numOfPeople - numOfDrinkingBeerPeople;

        if (numOfNotDrinkingBeerPeople > 0){
            int nonAlcoholicBeersToBuy = numOfNotDrinkingBeerPeople * BEERS_PER_PERSON;
            partyActions.add(BUY_MESSAGE + nonAlcoholicBeersToBuy + " non-alcoholic beers");
        }

        int numOfTeaToBuy = personService.getInvitedPeopleCount() * TEAS_PER_PERSON;

        if(temperatureOutside < TEMPERATURE_FOR_COLD_TEA ? partyActions.add(BUY_MESSAGE + numOfTeaToBuy + " " + new Tea(TeaType.HOT) + " teas") : partyActions.add(BUY_MESSAGE + numOfTeaToBuy + " " + new Tea(TeaType.ICE) + " teas"));

        if(personService.getInvitedPeopleCount() < NUM_OF_BIG_PARTY_PEOPLE ? partyActions.add("create chill playlist") : partyActions.add("create fire playlist"));
    }
}
