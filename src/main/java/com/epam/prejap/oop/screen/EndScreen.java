package com.epam.prejap.oop.screen;

import com.epam.prejap.oop.war.Card;
import com.epam.prejap.oop.war.Player;
import com.epam.prejap.oop.war.Players;
import java.util.*;

public class EndScreen implements Screen {

    String message;
    List<List<Card>> newCards;

    public EndScreen(byte winner, short size, short totalNrOfCards){
        message = separator+ String.format("WAR, %d players, ? deck, player %d WON with %d/%d cards",Players.numberOfPlayers,winner,size,totalNrOfCards)+separator;
        System.out.println(message);
    }

    public EndScreen(List<Player> remainingPlayers, List<List<Card>> cards){
        //Integer max = Collections.max(cards);

        System.out.println("End of game");
        chooseWinner(remainingPlayers,cards);
        System.exit(0);
    }

    public void showMessage(){

    }

    byte chooseWinner(List<Player> players, List cards){
        System.out.println(cards);
        return 1;
    }

}


/*
SEPARATOR
WAR, 2 players, ? deck, player1 WON with 2/2 cards
SEPARATOR


"Stopping the game due to X battles without resolution. Winner is the owner of the highest card AND the owner of the greatest amount of cards: player2."

 */
