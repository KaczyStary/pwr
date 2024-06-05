package com.convista.pwr.bc;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PersonService {

    public int calculateCurrentAge(Person person) {
        return LocalDate.now().getYear() - person.getBirthDate().getYear();
    }

    public int invitedPeopleCount() {
        return 5;
    }
}
