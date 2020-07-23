package com.example.currency_converter;

public class Country {

    String flagName;
    String name;
    String currency;
    boolean inTargetList;

    public Country(String name, String flagName, String currency, boolean inTargetList) {
        this.name = name;
        this.flagName = flagName;
        this.currency = currency;
        this.inTargetList = false;
    }

    public void setFlagName(String flagName) {
        this.flagName = flagName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setInTargetList(boolean inTargetList) {
        this.inTargetList = inTargetList;
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
