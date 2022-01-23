package com.epam.prejap.oop.war;

import java.util.List;

public class Player {

    private String name;
    private byte number;
    //private List<Card> playersCards;
    private Cards playersCards;

    /*
    Player(byte playerNumber, Cards playerCards){
        this.number = playerNumber;
        this.name = "Player"+playerNumber;

    }

     */

    public Player(byte playerNumber, Cards playersCards){
        this.number = playerNumber;
        this.name = "Player"+playerNumber;
        this.playersCards = playersCards;
    }


    public String toString(){
        return "Player"+number;
    }

    public byte getNumber(){
        return number;
    }

    public Cards getPlayersCards(){
        return playersCards;
    }

}
