package com.epam.prejap.oop.screen;

import com.epam.prejap.oop.war.Cards;
import com.epam.prejap.oop.war.GameInfo;
import com.epam.prejap.oop.war.Player;
import java.util.List;

public class GameScreen implements Screen {

    String message;
    public GameScreen(List<Player> activePlayers, byte nrOfPlayers, short nrOfCards, Cards playedCards){
        message = String.format("WAR, %d players, %s deck, %d cards", nrOfPlayers, GameInfo.INSTANCE.getDeck(),nrOfCards);
        for (int i=0; i<=activePlayers.size()-1; i++){
            message = message.concat("\nPlayer"+activePlayers.get(i).getNumber()+" played: "+playedCards.getCards().get(i).getCardValue());
        }
        message = message + separator;
        showMessage();
    }
    public void showMessage(){
        System.out.println(message);
    }
}

/*
WAR, 4 players, battle #30, SMALL deck, DEFAULT, player4 leads with 6/12 cards
Player1 played: 7
Player2 played: 4
Player4 played: 7
SEPARATOR

 */
