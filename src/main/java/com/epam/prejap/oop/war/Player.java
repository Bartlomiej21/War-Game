package com.epam.prejap.oop.war;

import java.util.List;

public class Player {

    private String name;
    private byte number;
    private List<Card> playersCards;

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

    public List<Card> getPlayersCards(){
        return playersCards;
    }

}
