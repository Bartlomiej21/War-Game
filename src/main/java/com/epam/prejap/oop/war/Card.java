package com.epam.prejap.oop.war;

class Card {

    int cardValue;
    boolean correctValue;

    Card(int cardValue){
        this.cardValue = cardValue;
        this.correctValue = checkValue(cardValue);
    }

    boolean checkValue(int value){
        if (value<2 || value >14) {   // I assume Ace will count as the highest card, hence 14 is max
            System.out.println("Bad card: "+cardValue);
            return false;
        } else {
            return true;
        }
    }

    /*
    @Override
    public String toString(){
        return String.valueOf(cardValue);
    }

    */

}
