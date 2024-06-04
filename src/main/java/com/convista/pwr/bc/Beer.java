package com.convista.pwr.bc;

public class Beer {

    private String brand;

    public Beer(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return brand;
    }
}
