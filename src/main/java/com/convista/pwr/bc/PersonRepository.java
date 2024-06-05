package com.convista.pwr.bc;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class PersonRepository {

    public Person findPerson() {
        return new Person("Tom", LocalDate.of(2000, 01, 01));
    }

    public List<Person> partyParticipantList() {
        return Collections.emptyList();
    }

}
