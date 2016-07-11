package com.avigezerit.soccerlookalike;

/**
 * Created by Shaharli on 11/07/2016.
 */
public class Player {

    private String firstName;
    private String lastName;
    private String number;
    private String color;
    private String shirt;

    public Player(String firstName, String lastName, String number, String color, String shirt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.color = color;
        this.shirt = shirt;
    }

    public Player(String number, String color, String shirt) {
        this.number = number;
        this.color = color;
        this.shirt = shirt;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getShirt() {
        return shirt;
    }

    public void setShirt(String shirt) {
        this.shirt = shirt;
    }
}
