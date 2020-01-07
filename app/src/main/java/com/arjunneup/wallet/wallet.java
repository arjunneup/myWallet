package com.arjunneup.wallet;

public class wallet {


    public String fullName;
    public String username;



    public String email;
    public String gender;


    public wallet(String fullName, String username, String email, String gender) {
        this.fullName = fullName;
        this.username = username;
        this.email = email;
        this.gender = gender;
    }
    public wallet(){

    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    //getter and setter methods here



}
