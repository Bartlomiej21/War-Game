package com.epam.prejap.oop.war;

import com.epam.prejap.oop.screen.GameScreen;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


public class Clash {

    List<Player> activePlayers;
    Cards playedCards;
    byte winner;
    short totalNrOfCards;   //todo could be unnecessary
    Duel duel;
    boolean duelOccurred = false;
    int max;

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
        if (!duelOccurred) {
            // this is a case when there is NO DUEL
            cardsForWinner.getCards().addAll(playedCards.getCards());
            playedCards.getCards().clear();
            return winner;
        } else {
            this.duel = new Duel(activePlayers);
            cardsForWinner.getCards().addAll(takeCardsFromInactivePlayersAndRemoveThem(activePlayers,playedCards,max)); //does not print anything
            duel.createDuelMessage(activePlayers, playedCards);
            duel.moveCardsFromPlayedCardsToPlayerDuelCards(activePlayers, playedCards);
        }
        //todo below should be moved to duel class
        while (winner==-1) {
            //check if any card remaining. Kill player if not.
            activePlayers.removeIf(i -> i.getPlayersCards().getCards().size()<1);  //todo this should get Eoc event
            //take one card.
            this.playedCards = createListOfPlayedCards(activePlayers);  //this removes from playerCards and adds to playedCards
            //Don't compare. Add ? sign
            duel.addToDuelMessage(activePlayers,playedCards, " ?");
            //Add to playersDuelList; Don't compare
            duel.moveCardsFromPlayedCardsToPlayerDuelCards(activePlayers,playedCards);
            ///check in any card remaining. Kill again if yes.
            activePlayers.removeIf(i -> i.getPlayersCards().getCards().size()<1);
            //take cards
            this.playedCards = createListOfPlayedCards(activePlayers);
            // compare
            winner = selectWinner(activePlayers, playedCards, cardsForWinner);
        }
        // todo in case of duel, below gives cards to a winner. Should be in a different place
        for (Player p: duel.duelPlayers){
            System.out.println(p.getDuelMessage());
            cardsForWinner.getCards().addAll(p.getDuelCards().getCards());
            p.getDuelCards().getCards().clear();
        }
        cardsForWinner.getCards().addAll(playedCards.getCards());
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
                    this.max = max;   //todo this needs to disappear in later versions
                    //System.out.println("!!!!!!!!!!!!!!!!DUEL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    duelOccurred = true;
                    return -1;
            }
        }

    List<Card> takeCardsFromInactivePlayersAndRemoveThem(List<Player> activePlayers, Cards playedCards, Integer max){
        List<Card> cardsToTake = new ArrayList<>();
        for (int i=activePlayers.size()-1; i>=0; i-- ){
            if (playedCards.getCards().get(i).cardValue!=max) {
                activePlayers.remove(i);
                cardsToTake.add(playedCards.getCards().remove(i));
            }
        }
        return cardsToTake;
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
