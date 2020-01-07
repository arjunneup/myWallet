package com.arjunneup.wallet;

public class SalaryModel {
    int salary;
    String month;

    public SalaryModel(int salary, String month){
        this.salary = salary;
        this.month = month;
    }

    public void setSalary(int salary){
        this.salary = salary;
    }
    public int getSalary(){
        return salary;
    }

    public void setMonth(String month){
        this.month = month;
    }
    public String getMonth(){
        return month;
    }

}
