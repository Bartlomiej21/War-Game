package com.epam.prejap.oop.war;

import java.util.List;


public class GameScreen implements Screen {


    GameScreen(byte nrOfPlayers,short nrOfCards, List playedCards){

        String message;
        message = String.format("WAR, %d players, ? deck, %d cards",nrOfPlayers,nrOfCards);
        for (Byte b: Players.playersNames){
            message = message.concat("\nPlayer"+b+" played: "+playedCards.get(b-1));

        }

        System.out.println(message);
    }



    /*
    WAR, 2 players, ? deck, 2 cards
    Player1 played: 3
    Player2 played: 2
    SEPARATOR
    */

}
