package com.epam.prejap.oop.war;

public class Player {

    private String name;
    private byte number;

    Player(byte playerNumber){
        this.number = playerNumber;
        this.name = "Player"+playerNumber;

    }

    public String toString(){
        return "Player"+number;
    }

    public byte getNumber(){
        return number;
    }

}
