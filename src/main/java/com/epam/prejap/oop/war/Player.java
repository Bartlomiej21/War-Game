package com.epam.prejap.oop.war;

public class Player {

    private String name;
    private String number;


    Player(String playerNumber){
        this.number = playerNumber;
        this.name = "P"+playerNumber;

    }

    public String toString(){
        return "Player"+number;
    }

    public String getNumber(){
        return "Number" + number;
    }
}
