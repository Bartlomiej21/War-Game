package com.epam.prejap.oop.war;

import java.util.List;

public class Player {

    private String name;
    private byte number;
    private Cards playersCards;
    private Cards duelCards = new Cards();
    private String duelMessage;


    public Player(byte playerNumber, Cards playersCards){
        this.number = playerNumber;
        this.name = "Player"+playerNumber;
        this.playersCards = playersCards;
        //this.duelCards = new Cards();
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

    public void setDuelMessage(String msg){ this.duelMessage=msg; }

    public String getDuelMessage(){
        return this.duelMessage;
    }


    public Cards getDuelCards() {
        return duelCards;
    }

    public void setDuelCards(Cards duelCards) {  //todo probly obsolete
        this.duelCards = duelCards;
    }
}
