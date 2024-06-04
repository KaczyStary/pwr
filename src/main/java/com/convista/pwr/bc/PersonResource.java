package com.convista.pwr.bc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

@RestController
public class PersonResource {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private StoreService storeService;

    private static final String PERSON_AGE_BY_FUNCTION_MESSAGE_FORMAT = "Person is {0} years old - calculated by function";

    @GetMapping(value = "/person-age-function")
    public String getPersonAgeByFunction() {
        Person person = personRepository.findPerson();
        int age = personService.calculateCurrentAge(person);

        return MessageFormat.format(PERSON_AGE_BY_FUNCTION_MESSAGE_FORMAT, age);
    }

    @GetMapping(value = "/person/buy-beer")
    @ResponseBody
    public String buyBeer() {
        Person person = personRepository.findPerson();
        Beer beer = storeService.buyBeerForClient(person);

        return beer.toString();
    }

}
