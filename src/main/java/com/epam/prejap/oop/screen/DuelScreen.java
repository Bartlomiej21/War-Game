package com.epam.prejap.oop.screen;

import com.epam.prejap.oop.war.Player;
import java.util.List;

public class DuelScreen implements Screen {
    String message;
    short gameRound;
    short duelRound;

    public DuelScreen(List<Player> players, short gameRound, short duelRound){
        this.gameRound = gameRound;
        this.duelRound = duelRound;
        getMessage(players);
        showMessage();
    }

    public void showMessage(){
        System.out.println(message);
    }

    public void getMessage(List<Player> players){
        message = String.format("WAR, 3 players, SMALL deck, turn #%d - DRAW with x/x cards\n",gameRound+1);
        message = message +"""
        ================================================================
                       WE HAVE A WAR LADIES AND GENTLEMEN!
        ================================================================
                """;
        for (Player p: players){
            message = message +"\n"+p.getDuelMessage();
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
