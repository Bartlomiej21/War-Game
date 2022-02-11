package com.epam.prejap.oop.war;

public enum GameInfo {

    INSTANCE();
    private String deck;

    private GameInfo () {
    }

    public GameInfo getInstance() {
        return INSTANCE;
    }

    // getters and setters
    void setDeck(String deckValue){
        this.deck = deckValue;
    }
    public String getDeck(){
        return this.deck;
    }

}
