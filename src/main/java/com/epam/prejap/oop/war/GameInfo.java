package com.epam.prejap.oop.war;

public enum GameInfo {

    INSTANCE();
    private String deck;

    private GameInfo () { }

    public GameInfo getInstance() {
        return INSTANCE;
    }

    void setDeck(String deckValue){
        this.deck = deckValue;
    }

    public String getDeck(){
        return this.deck;
    }
}
