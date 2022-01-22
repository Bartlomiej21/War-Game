package com.epam.prejap.oop.war;

import com.epam.prejap.oop.screen.DuelScreen;
import java.util.List;

public class Duel {

    byte winner;

    byte resolveDuel(List<Player> allPlayers, List<Player> activePlayers, List<List<Card>> cards,Cards cardsForWinner){

        activePlayers.removeIf(i -> cards.get(i.getNumber()-1).size()<1);

        switch (activePlayers.size()) {
            case 1:
                winner = activePlayers.get(0).getNumber();
                break;
            case 0:   // in case it ends in a draw and there are no players with any cards left
                System.out.println("The game has ended in a draw!");
                new DuelScreen(activePlayers);
                //System.exit(0);  //todo this is a situation without end
                break;
            default:
                winner = new Clash().resolveClash(allPlayers,activePlayers,cards,cardsForWinner);
        }
        return winner;
    }
}
