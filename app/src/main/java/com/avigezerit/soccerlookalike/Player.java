package com.avigezerit.soccerlookalike;

import android.graphics.Color;

/**
 * Created by Shaharli on 11/07/2016.
 */
public class Player {

    private String firstName;
    private String lastName;
    private int number;
    private Color skinTone;
    private String color;
    private int shirt;

    public Player(String firstName, String lastName, int number, Color skinTone, String color, int shirt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.color = color;
        this.skinTone = skinTone;
        this.shirt = shirt;
    }


    public int getNumber() {
        return number;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getShirt() {
        return shirt;
    }

    public Color getSkinTone() {
        return skinTone;
    }
}
