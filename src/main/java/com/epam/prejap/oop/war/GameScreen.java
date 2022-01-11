package com.epam.prejap.oop.war;

import java.util.List;


public class GameScreen implements Screen {

    String message;

    GameScreen(List<Player> activePlayers,short nrOfCards, List playedCards){
        message = String.format("WAR, %d players, ? deck, %d cards",Players.numberOfPlayers,nrOfCards);
        for (int i=0; i<=activePlayers.size()-1; i++){
            message = message.concat("\nPlayer"+activePlayers.get(i).getNumber()+" played: "+playedCards.get(i));
        }
        message = message +"\n==========\n\n";
        showMessage();

    }

    public void showMessage(){
        System.out.println(message);
    }



    /*
    WAR, 2 players, ? deck, 2 cards
    Player1 played: 3
    Player2 played: 2
    SEPARATOR

     */

}
