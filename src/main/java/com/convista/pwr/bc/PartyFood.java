package com.convista.pwr.bc;

public class PartyFood {
    private String name;
    private boolean vegetarian;

    public PartyFood(String name, boolean vegetarian) {
        this.name = name;
        this.vegetarian = vegetarian;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }
}
