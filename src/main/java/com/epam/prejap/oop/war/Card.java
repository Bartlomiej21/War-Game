package com.epam.prejap.oop.war;

/**
 * This class is a representation of a single playing card.
 */
public class Card {

    int cardValue;
    boolean correctValue;
    final byte LOWER_RANGE = 1;
    final byte UPPER_RANGE = 14;

    Card(int cardValue){
        this.cardValue = cardValue;
        this.correctValue = checkValueRange(cardValue);
    }

    boolean checkValueRange(int value) {return (value>=LOWER_RANGE && value <=UPPER_RANGE); }

    public int getCardValue(){
        return cardValue;
    }

    public void setCardValue(int i){cardValue=i;}  //for tests

}
