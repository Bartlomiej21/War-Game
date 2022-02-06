package com.epam.prejap.oop.screen;

import com.epam.prejap.oop.war.Cards;
import com.epam.prejap.oop.war.Player;
import java.util.List;

public class GameScreen implements Screen {

    String message;
    public GameScreen(List<Player> activePlayers, short nrOfCards, Cards playedCards){
        message = String.format("WAR, %d players, ? deck, %d cards", 3,nrOfCards);
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
