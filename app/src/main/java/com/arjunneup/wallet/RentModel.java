package com.arjunneup.wallet;

public class RentModel {

    int rent;
    String month;

    public RentModel(int rent, String month){
        this.rent = rent;
        this.month = month;
    }

    public void setRent(int salary){
        this.rent = rent;
    }
    public int getRent(){
        return rent;
    }

    public void setMonth(String month){
        this.month = month;
    }
    public String getMonth(){
        return month;
    }

}
