package com.epam.prejap.oop.war;

import java.util.*;

public class Clash {

    byte winner;
    List<Integer> playedCards = new ArrayList<>();
    //List<Integer> cardsForWinner = new ArrayList<>();
    List<List<Card>> cards;


    Clash(List<Player> activePlayers, List<List<Card>> cards, List<Integer> cardsForWinner) {
        this.cards = cards;

        ListIterator<Player> iter = activePlayers.listIterator();
        byte playernr;
        // add first card to playedCards and simultaneously remove it from playerCards
        while (iter.hasNext()) {
            playernr = activePlayers.get(iter.nextIndex()).getNumber(); // 1,2,3
            //System.out.println("Player nr"+playernr);
            playedCards.add(cards.get(playernr - 1).remove(0).cardValue);
            iter.next();
        }
        System.out.println("Cards on table: " + playedCards);
        new GameScreen((byte)activePlayers.size(), PlayersCards.totalNrOfCards, playedCards);
        winner = selectWinner(activePlayers, playedCards, cardsForWinner);

    }


        byte selectWinner(List<Player> activePlayers,List<Integer> playedCards, List<Integer> cardsWinner){
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


    void removeInactiveplayers(List<Player> players, List<Integer> playedCards, Integer max){
        for (int i=players.size()-1; i>=0; i-- ){
            if (playedCards.get(i)!=max) players.remove(i);
        }
    }


    public void showCards(){
        for (int i=0; i<cards.size();i++) {
            System.out.println("Cards remaining in player"+(i+1)+" hands: ");
            for (int j = 0; j < cards.get(i).size(); j++) {
                System.out.print(cards.get(i).get(j).getCardValue()+" ");

            }
            System.out.println("");
        }

    }

}
