package com.convista.pwr.bc;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PersonRepository {

    public Person findPerson() {
        return new Person("Tom", LocalDate.of(2000, 01, 01));
    }

}
