package com.convista.pwr.bc;

import java.time.LocalDate;

public class Person {

    private String name;
    private LocalDate birthDate;

    public Person(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int calculateCurrentAge() {
        return LocalDate.now().getYear() - birthDate.getYear();
    }
}
