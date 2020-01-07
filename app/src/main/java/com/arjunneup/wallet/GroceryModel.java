package com.arjunneup.wallet;

public class GroceryModel {
    int grocery;
    String month;

    public GroceryModel(int grocery, String month){
        this.grocery = grocery;
        this.month = month;
    }

    public void setGrocery(int grocery){
        this.grocery = grocery;
    }
    public int getGrocery(){
        return grocery;
    }

    public void setMonth(String month){
        this.month = month;
    }
    public String getMonth(){
        return month;
    }

}
