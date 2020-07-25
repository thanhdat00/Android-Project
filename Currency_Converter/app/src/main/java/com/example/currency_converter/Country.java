package com.example.currency_converter;

import java.io.Serializable;

public class Country implements Serializable {

    String flagName;
    String name;
    String currency;
    int amount;
    boolean inTargetList;


    public Country(String name, String flagName, String currency, int amount, boolean inTargetList) {
        this.name = name;
        this.flagName = flagName;
        this.currency = currency;
        this.inTargetList = false;
        this.amount = amount;
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

    public int getAmount() {
        return amount;
    }

    public boolean isInTargetList() {
        return inTargetList;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


}
