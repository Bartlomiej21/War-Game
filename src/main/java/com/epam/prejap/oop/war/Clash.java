package com.epam.prejap.oop.war;

import com.epam.prejap.oop.screen.GameScreen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Clash {

    static byte winner;
    List<Integer> playedCards = new ArrayList<>();

    Clash(List<Player> allPlayers, List<Player> activePlayers, List<List<Card>> cards, List<Integer> cardsForWinner) {

        byte playerNr;
        for (Player p: activePlayers ){
            playerNr = (byte) (p.getNumber()-1);
            playedCards.add(cards.get(playerNr).remove(0).cardValue);
        }

        System.out.println("Cards on table: " + playedCards);
        new GameScreen(activePlayers, PlayersCards.totalNrOfCards, playedCards);
        winner = selectWinner(activePlayers, playedCards, cardsForWinner);
        if (winner==0) new Duel(allPlayers, activePlayers, cards, cardsForWinner);
    }

        byte selectWinner(List<Player> activePlayers,List<Integer> playedCards, List<Integer> cardsWinner){
            System.out.println("Played cards: "+playedCards);
            Integer max = Collections.max(playedCards);
            int occurrences = Collections.frequency(playedCards, max);
            switch (occurrences) {
                case 1:
                    byte winner = activePlayers.get(playedCards.indexOf(max)).getNumber();
                    cardsWinner.addAll(playedCards);
                    playedCards.clear();
                    return winner;

                default:
                    removeInactiveplayers(activePlayers,playedCards,max);
                    cardsWinner.addAll(playedCards);
                    playedCards.clear();
                    return 0;
            }
        }


    void removeInactiveplayers(List<Player> activePlayers, List<Integer> playedCards, Integer max){
        for (int i=activePlayers.size()-1; i>=0; i-- ){
            if (playedCards.get(i)!=max) {
                activePlayers.remove(i);
            }
        }
    }


}
