package com.epam.prejap.oop.war;

class Card {

    int cardValue;
    boolean correctValue;
    final int LOWER_RANGE = 2;
    final int UPPER_RANGE = 14;   // I assume Ace will count as the highest card, hence 14 is max

    Card(int cardValue){
        this.cardValue = cardValue;
        this.correctValue = checkValueRange(cardValue);
    }

    boolean checkValueRange(int value) {return (value>=LOWER_RANGE && value <=UPPER_RANGE); }


}
