package com.epam.prejap.oop.war;

public class Card {

    int cardValue;
    boolean correctValue;
    final byte LOWER_RANGE = 1;    // json strings contain 1's but according to card rules it should be 2. Left as is for now
    final byte UPPER_RANGE = 14;   // I assume Ace will count as the highest card, hence 14 is max

    Card(int cardValue){
        this.cardValue = cardValue;
        this.correctValue = checkValueRange(cardValue);
    }

    boolean checkValueRange(int value) {return (value>=LOWER_RANGE && value <=UPPER_RANGE); }

    int getCardValue(){
        return cardValue;
    }


}
