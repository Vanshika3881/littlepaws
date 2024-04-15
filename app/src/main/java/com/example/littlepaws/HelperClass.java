package com.example.littlepaws;

public class HelperClass {
    String name;
    String pin;
    String state;

    public HelperClass(String name, String pin, String state, String city, String mail, String phone_number) {
        this.name = name;
        this.pin = pin;
        this.state = state;
        this.city = city;
        this.mail = mail;
        this.phone_number = phone_number;
    }

    String city;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }



    String mail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    String phone_number;











    public HelperClass() {
    }
}
