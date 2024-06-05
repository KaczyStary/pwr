package com.convista.pwr.bc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PartyPlanner {

    private static final int TEMPERATURE_FOR_POOL_PARTY = 30;
    private static final int TEMPERATURE_FOR_BBQ = 20;

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private WeatherService weatherService;

    public List<String> planParty() {
        List<String> partyPlan = new ArrayList<>();

        int temperatureOutside = weatherService.readCurrentTemperature();
        partyPlan.add("Weather outside: " + temperatureOutside + " degrees");

        PartyType partyType = (temperatureOutside < TEMPERATURE_FOR_POOL_PARTY ? PartyType.HOUSE_PARTY : PartyType.POOL_PARTY);
        partyPlan.add("Party type: " + partyType);

        if (partyType == PartyType.POOL_PARTY) {
            partyPlan.add("12:00 - " + "meet && greet");
            partyPlan.add("13:30 - " + "presentation about butterflies");
            partyPlan.add("14:00 - " + "BBQ");
            partyPlan.add("17:00 - " + "beerpong tournament in swimming pool");
            partyPlan.add("18:30 - " + "shrek 2 projection");
            partyPlan.add("21:00 - " + "shrek 2 commentary");
            partyPlan.add("21:30 - " + "ElClassico REAL VS BARCA Champions League final " + LocalDate.now().getYear());
            partyPlan.add("23:00 - " + "post match discussion");
            partyPlan.add("00:00 - " + "pool rave");
            partyPlan.add("06:00 - " + "afterparty");
            partyPlan.add("08:00 - " + "goodbye breakfast");
            partyPlan.add("10:00 - " + "end of party");
        } else {
            partyPlan.add("12:00 - " + "meet && greet");
            partyPlan.add("13:30 - " + "presentation about butterflies");
            if(temperatureOutside < TEMPERATURE_FOR_BBQ ? partyPlan.add("14:00 - " + "BBQ") : partyPlan.add("14:00 - " + "Pizza"));
            partyPlan.add("17:00 - " + "beerpong tournament");
            partyPlan.add("18:30 - " + "shrek 3 projection");
            partyPlan.add("21:00 - " + "shrek 3 commentary");
            partyPlan.add("21:30 - " + "ElClassico REAL VS BARCA Champions League final " + LocalDate.now().getYear());
            partyPlan.add("23:00 - " + "post match discussion");
            partyPlan.add("00:00 - " + "rave");
            partyPlan.add("06:00 - " + "afterparty");
            partyPlan.add("08:00 - " + "goodbye breakfast");
            partyPlan.add("10:00 - " + "end of party");
        }

        return partyPlan;
    }
}
