package com.example.currency_converter;

public class Country {

    String flagName;
    String name;
    String currency;

    public Country(String name, String flagName, String currency) {
        this.name = name;
        this.flagName = flagName;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public String getFlagName() {
        return flagName;
    }

    public String getCurrency() {
        return currency;
    }

}
