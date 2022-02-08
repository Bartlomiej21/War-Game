package com.epam.prejap.oop.war;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class deals with situation in a game when Clash ends up in a draw.
 */
public class Duel {
    byte winner;
    //Cards duelCards;
    List<Player> duelPlayers;
    byte defaultWinner;

    Duel(){
        //this.duelPlayers = createDuelPlayersList();
    }

    Duel(List<Player> activePlayers){
        //this.duelCards = new Cards();
        this.duelPlayers = activePlayers.stream().collect(Collectors.toList());
    }

    List<Player> createDuelPlayersList(List<Player> activePlayers, Cards playedCards, int max) {
        List<Player> duelList = new ArrayList();
        for (int i = 0; i <= activePlayers.size() - 1; i++) {
            activePlayers.get(i).getDuelCards().getCards().add(new Card(playedCards.getCards().get(i).cardValue)); //this helps with the card order
            if (playedCards.getCards().get(i).cardValue == max) {
                duelList.add(activePlayers.get(i));
                String msg = "Player"+activePlayers.get(i).getNumber()+" played: "+playedCards.getCards().get(i).cardValue;
                activePlayers.get(i).setDuelMessage(msg);
                System.out.println(activePlayers.get(i).getDuelMessage());
            }
        }
        this.defaultWinner = duelList.get(duelList.size()-1).getNumber();
        return duelList;
    }

    /*
    void createDuelMessage(Cards playedCards){
        System.out.println("Beginning of duel message");
        int index=0;
        for (Player p: duelPlayers){
            String duelString = p.toString()+" played: ";
            p.setDuelMessage(duelString);
        }
        System.out.println("End of duel message");
    }

     */

    public void addToDuelMessage(List<Player> duelPlayers, String concat) {
        int index = 0;
        for (Player p: duelPlayers){
            String duelString = p.getDuelMessage();
            duelString = duelString + concat;
            p.setDuelMessage(duelString);
            index++;
            System.out.println(p.getDuelMessage());
        }
    }

    void moveCardsFromPlayedCardsToPlayerDuelCards(List<Player> duelPlayers, Cards playedCards){
        for (Player p: duelPlayers){
            System.out.println("Card removed: "+ playedCards.getCards().get(0).cardValue);
            String message = p.getDuelMessage()+" "+playedCards.getCards().get(0).cardValue;
            p.setDuelMessage(message);
            System.out.println(p.getDuelMessage());
            p.getDuelCards().getCards().add( playedCards.getCards().remove(0));  //since removing, always first card
        }
    }
}
