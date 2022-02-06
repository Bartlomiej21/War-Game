package com.epam.prejap.oop.war;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class deals with situation in a game when Clash ends up in a draw.
 */
public class Duel {
    byte winner;
    Cards duelCards;
    List<Player> duelPlayers;

    Duel(List<Player> activePlayers){
        this.duelCards = new Cards();
        this.duelPlayers = activePlayers.stream().collect(Collectors.toList());
    }

    void createDuelMessage(List<Player> activePlayers, Cards playedCards){
        int index = 0;
        for (Player p: activePlayers){
            String duelString = p.toString()+" played: ";
            p.setDuelMessage(duelString);
            index++;
        }
    }

    public void addToDuelMessage(List<Player> activePlayers, Cards playedCards, String concat) {
        int index = 0;
        for (Player p: activePlayers){
            String duelString = p.getDuelMessage();
            duelString = duelString + concat;
            p.setDuelMessage(duelString);
            index++;
            System.out.println(p.getDuelMessage());
        }
    }

    void moveCardsFromPlayedCardsToPlayerDuelCards(List<Player> activePlayers, Cards playedCards){
        for (Player p: activePlayers){
            System.out.println("Card removed: "+ playedCards.getCards().get(0).cardValue);
            String message = p.getDuelMessage()+" "+playedCards.getCards().get(0).cardValue;
            p.setDuelMessage(message);
            p.getDuelCards().getCards().add( playedCards.getCards().remove(0));  //since removing, always first card
        }
    }
}
