package com.convista.pwr.bc;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PersonRepository {

    public Person findPerson() {
        return new Person("Tom", LocalDate.of(2000, 01, 01), false, false);
    }

    public List<Person> partyParticipantList() {
        Person person_1 = new Person("Tom", LocalDate.of(2000, 01, 01), false, false);
        Person person_2 = new Person("Alice", LocalDate.of(2001, 02, 02), true, false);
        Person person_3 = new Person("Bob", LocalDate.of(2002, 03, 03), false, true);
        Person person_4 = new Person("Charlie", LocalDate.of(2003, 04, 04), true, true);
        Person person_5 = new Person("David", LocalDate.of(2007, 05, 05), false, false);

        return List.of(person_1, person_2, person_3, person_4, person_5);
    }



}
