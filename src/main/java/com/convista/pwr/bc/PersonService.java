package com.convista.pwr.bc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public int calculateCurrentAge(Person person) {
        return LocalDate.now().getYear() - person.getBirthDate().getYear();
    }

    public int getInvitedPeopleCount() {
        return personRepository.partyParticipantList().size();
    }

    public int getInvitedVegetariansCount() {
        return (int) personRepository.partyParticipantList().stream().filter(Person::isVegetarian).count();
    }

    public int getNumOfAbstinent(List<Person> listOfPartyParticipants) {
        return (int) listOfPartyParticipants.stream().filter(Person::isAbstinent).count();
    }

    public int getNumOfUnderage(List<Person> listOfPartyParticipants) {
        int legalAge = 18;
        return (int) listOfPartyParticipants.stream().filter(person -> LocalDate.now().getYear() - person.getBirthDate().getYear() < legalAge).count();
    }
}
