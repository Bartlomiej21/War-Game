package com.epam.prejap.oop.war;

import com.epam.prejap.oop.screen.GameScreen;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class deals with comparing user cards, a.k.a. clashing. It's supposed to select a winner who will get cards for a given round.
 */
public class Clash {

    List<Player> activePlayers;
    Cards playedCards;
    byte winner;
    short totalNrOfCards;   //todo could be unnecessary
    Duel duel;

    Clash(Players playersInGame, short totalNrOfCards){
        this.activePlayers = playersInGame.getPlayers().stream().collect(Collectors.toList());
        this.totalNrOfCards = totalNrOfCards;
    }

    Cards createListOfPlayedCards(List<Player> activePlayers){
        Cards playedCards = new Cards();
        for (Player p: activePlayers ){
            int removedCard = p.getPlayersCards().getCards().remove(0).getCardValue();
            playedCards.getCards().add(new Card(removedCard));
        }
        return playedCards;
    }

    void printGameScreen(List<Player> activePlayers,short totalNrOfCards, Cards playedCards){
        new GameScreen(activePlayers, totalNrOfCards, playedCards);
    }

    byte resolveClash(Players allPlayers, List<Player> activePlayers, Cards cardsForWinner){

        winner = selectWinner(activePlayers, playedCards, cardsForWinner);
        if (duel==null) {
            cardsForWinner.getCards().addAll(playedCards.getCards());
            playedCards.getCards().clear();
            return winner;
        }
        //todo below should be moved to duel class
        while (winner==-1) {
            duel.duelPlayers.removeIf(i -> i.getPlayersCards().getCards().size()<1);  //todo this should get Eoc event
            this.playedCards = createListOfPlayedCards(duel.duelPlayers);  //this removes from playerCards and adds to playedCards
            duel.addToDuelMessage(duel.duelPlayers, " ?");
            duel.moveCardsFromPlayedCardsToPlayerDuelCards(duel.duelPlayers,playedCards);  //hidden card to playerDuelCards
            duel.duelPlayers.removeIf(i -> i.getPlayersCards().getCards().size()<1);  //also a EoC events
            if (duel.duelPlayers.size()>1){
                this.playedCards = createListOfPlayedCards(duel.duelPlayers);
                winner = selectWinner(duel.duelPlayers, playedCards, cardsForWinner);
            } else if (duel.duelPlayers.size()==1){
                duel.duelPlayers.get(0).setDuelMessage("Winner");
                winner = duel.duelPlayers.get(0).getNumber();
            } else {
                // winner is the last player
                winner = duel.defaultWinner;
            }
        }
        // todo in case of duel, below gives cards to a winner. Should be in a different place
        /*
        for (Player p: duel.duelPlayers){
            System.out.println(p.getDuelMessage());
            cardsForWinner.getCards().addAll(p.getDuelCards().getCards());
            p.getDuelCards().getCards().clear();
        }

         */
        cardsForWinner.getCards().clear();
        for (int i=0; i<=playedCards.getCards().size()-1;i++){
            duel.duelPlayers.get(i).getDuelCards().getCards().add(playedCards.getCards().get(i));
        }

        for (Player p: activePlayers){
            cardsForWinner.getCards().addAll(p.getDuelCards().getCards());
        }


        for (Player p: activePlayers){
            p.getDuelCards().getCards().clear();
            p.setDuelMessage(null);
        }
        //cardsForWinner.getCards().addAll(playedCards.getCards());
        return winner;
    }

        byte selectWinner(List<Player> activePlayers, Cards playedCards, Cards cardsForWinner) {  //todo cards for winner obsolete for now
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
                    return winner;

                default:
                    this.duel = new Duel();
                    duel.duelPlayers = duel.createDuelPlayersList(activePlayers,playedCards,max);
                    //duel.createDuelMessage(playedCards);
                    System.out.println("dueeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeel");
                    return -1;
            }
        }

    void addCardsToWinner(Players players, Cards listOfCards,byte winner){
        // todo check if there is no draw. Actually I might not need to do that - below will not work for winner=0
        for(Player p: players) {
            if (p.getNumber()==winner) {
                for (Card c : listOfCards) {
                    p.getPlayersCards().getCards().add(c);
                }
            }
        }
    }

    public void setWinner(byte number) {
        this.winner = number;
    }   //todo check if obsolete now
}
