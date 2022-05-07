package com.epam.prejap.oop.war;

import com.epam.prejap.oop.screen.DuelScreen;
import com.epam.prejap.oop.screen.GameScreen;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class deals with comparing user cards, a.k.a. clashing. It's supposed to select a winner
 * who will get cards for a given round.
 */
public class Clash {

    List<Player> activePlayers;
    Cards playedCards;
    byte winner;
    short totalNrOfCards;
    byte nrOfPlayers;
    short gameRound;
    short duelRound = 1;
    Duel duel;

    Clash(Players playersInGame, short totalNrOfCards, short round, byte nrOfPlayers){
        this.activePlayers = playersInGame.getPlayers().stream().collect(Collectors.toList());
        this.totalNrOfCards = totalNrOfCards;
        this.nrOfPlayers = nrOfPlayers;
        this.gameRound = round;
    }

    static Cards createListOfPlayedCards(List<Player> players){
        Cards playedCards = new Cards();
        for (Player p: players ){
            int removedCard = p.getPlayersCards().getCards().remove(0).getCardValue();
            playedCards.getCards().add(new Card(removedCard));
        }
        return playedCards;
    }

    void printGameScreen(List<Player> activePlayers,short totalNrOfCards, Cards playedCards){
        new GameScreen(activePlayers, nrOfPlayers, totalNrOfCards, playedCards);
    }

    byte resolveClash(List<Player> activePlayers, Cards cardsForWinner){
        winner = selectWinner(activePlayers, playedCards, cardsForWinner);
        if (duel==null) {
            cardsForWinner.getCards().addAll(playedCards.getCards());
            return winner;
        }
        while (winner==-1) {
            duelRound++;
            duel.runOneRoundOfDuel();
            playedCards = duel.duelCards;
            if (duel.duelPlayers.size()>1){
                winner = selectWinner(duel.duelPlayers, playedCards, cardsForWinner);
            } else if (duel.duelPlayers.size()==1){
                winner = duel.duelPlayers.get(0).getNumber();
            } else {
                winner = duel.defaultWinner;
            }
        }
        duel.moveCardsFromPlayedCardsToPlayerDuelCards(duel.duelPlayers,playedCards);
        duel.prepareCardsForTheWinner(activePlayers,cardsForWinner,winner);
        new DuelScreen(activePlayers, nrOfPlayers, gameRound, duelRound, totalNrOfCards);
        duel.deleteDuelMessagesAndCards(activePlayers);
        return winner;
    }

    byte selectWinner(List<Player> activePlayers, Cards playedCards, Cards cardsForWinner) {
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
                    return -1;
            }
        }

    void addCardsToWinner(Players players, Cards listOfCards,byte winner){
        for(Player p: players) {
            if (p.getNumber()==winner) {
                for (Card c : listOfCards) {
                    p.getPlayersCards().getCards().add(c);
                }
            }
        }
    }
}
