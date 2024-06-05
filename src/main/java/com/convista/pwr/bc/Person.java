package com.convista.pwr.bc;

import java.time.LocalDate;

public class Person {

    private String name;
    private LocalDate birthDate;
    private boolean vegetarian;
    private boolean abstinent;

    public Person(String name, LocalDate birthDate, boolean vegetarian, boolean abstinent) {
        this.name = name;
        this.birthDate = birthDate;
        this.vegetarian = vegetarian;
        this.abstinent = abstinent;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
    public String getName() {
        return name;
    }
    public boolean isVegetarian() {
        return vegetarian;
    }
    public boolean isAbstinent() {
        return abstinent;
    }
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", vegetarian=" + vegetarian +
                ", abstinent=" + abstinent +
                '}';
    }
}
