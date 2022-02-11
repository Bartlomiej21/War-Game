package com.epam.prejap.oop.screen;

import com.epam.prejap.oop.war.GameInfo;
import com.epam.prejap.oop.war.Player;
import java.util.List;

public class DuelScreen implements Screen {
    String message;
    short gameRound;
    short duelRound;
    byte nrOfPlayers;
    short totalCards;

    public DuelScreen(List<Player> players, byte nrOfPlayers, short gameRound, short duelRound, short totalCards){
        this.gameRound = gameRound;
        this.duelRound = duelRound;
        this.nrOfPlayers = nrOfPlayers;
        this.totalCards = totalCards;
        getMessage(players);
        showMessage();
    }

    public void showMessage(){
        System.out.println(message);
    }

    public void getMessage(List<Player> players){
        message = String.format("""
        WAR, %d players, %s deck, turn #%d - DRAW with %d/%d cards
        ================================================================
                       WE HAVE A WAR LADIES AND GENTLEMEN!
        ================================================================
                """,nrOfPlayers, GameInfo.INSTANCE.getDeck(),gameRound+1,totalCards,totalCards);
        for (Player p: players){
            if (p.getDuelMessage()!=null){
                message = message +"\n"+p.getDuelMessage();
            }
        }
        message = message + String.format("\n\nWar lasted %d rounds",duelRound);
        message = message + separator;
    }
}

/*
WAR, 3 players, SMALL deck, turn #122 - DRAW with 8/24 cards
================================================================
                 WE HAVE A WAR LADIES AND GENTLEMEN!
================================================================
Player1 played: 9 ? 12 ? 10 ? 13 ? EoC
Player2 played: 9 ? 12 ? 10 ? 13 ? EoC
Player3 played: 9 ? 12 ? 10 ? 13 ? -----------> WINNER FOUND!

War lasted 5 rounds.
SEPARATOR

 */
