package com.epam.prejap.oop.war;

import com.epam.prejap.oop.screen.DuelScreen;
import java.util.List;

public class Duel {

    byte winner;

    byte resolveDuel(Players allPlayers, List<Player> activePlayers, Cards cardsForWinner){

        //activePlayers.removeIf(i -> cards.get(i.getNumber()-1).getCards().size()<1);  // HERE
        activePlayers.removeIf(i ->  i.getPlayersCards().getCards().size()<1);  // HERE

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
                winner = new Clash((short)5).resolveClash(allPlayers,activePlayers,cardsForWinner);  //todo constructor!
        }
        return winner;
    }
}
