package com.epam.prejap.oop.war;

import com.epam.prejap.oop.screen.GameScreen;

import java.util.Iterator;
import java.util.List;

public class Clash {

    byte winner;    //todo winner type Player;
    //Player winner1 = new Player((byte) 0);
    Cards playedCards = new Cards();
    short totalNrOfCards;

    Clash(short nrOfCards){
        this.totalNrOfCards = nrOfCards;
    }


    byte resolveClash(List<Player> allPlayers, List<Player> activePlayers, List<Cards> cards, Cards cardsForWinner){
        byte playerNr;
        for (Player p: activePlayers ){
            playerNr = (byte) (p.getNumber()-1);
            int removedCard = cards.get(playerNr).getCards().remove(0).getCardValue();   //HERE
            playedCards.getCards().add(new Card(removedCard));
        }

        //System.out.println("Cards on table: " + playedCards);
        new GameScreen(activePlayers, totalNrOfCards, playedCards);
        winner = selectWinner(activePlayers, playedCards, cardsForWinner);
        while (winner==0) {
            //winner = new Duel(allPlayers, activePlayers, cards, cardsForWinner);
            winner = new Duel().resolveDuel(allPlayers, activePlayers, cards, cardsForWinner);
        }
        return winner;
    }

        byte selectWinner(List<Player> activePlayers,Cards playedCards, Cards cardsWinner) {
            int max = 0;
            short occurrences = 1;
            int index = 0;
            int indexOfWinner = 0;
            Iterator<Card> itr = playedCards.iterator();
            while (itr.hasNext()) {
                int maxTemp = itr.next().getCardValue();
                if (maxTemp > max) {
                    max = maxTemp;
                    occurrences = 1;
                    indexOfWinner = index;
                } else if (maxTemp == max) {
                    occurrences++;
                }
                index++;
            }

            switch (occurrences) {
                case 1:
                    byte winner = activePlayers.get(indexOfWinner).getNumber();
                    cardsWinner.getCards().addAll(playedCards.getCards());
                    playedCards.getCards().clear();
                    return winner;

                default:
                    removeInactiveplayers(activePlayers,playedCards,max);
                    cardsWinner.getCards().addAll(playedCards.getCards());
                    playedCards.getCards().clear();
                    return 0;
            }
        }

    void removeInactiveplayers(List<Player> activePlayers, Cards playedCards, Integer max){
        for (int i=activePlayers.size()-1; i>=0; i-- ){
            if (playedCards.getCards().get(i).cardValue!=max) {
                activePlayers.remove(i);
            }
        }
    }

    public void setWinner(byte number) {
        this.winner = number;
    }   //todo check if obsolete now
}
