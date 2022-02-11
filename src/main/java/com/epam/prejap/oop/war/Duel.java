package com.epam.prejap.oop.war;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class deals with situation in a game when Clash ends up in a draw. It consists of methods used
 * in a case of a Duel.
 */
public class Duel {

    List<Player> duelPlayers;
    Cards duelCards;
    byte defaultWinner;

    List<Player> createDuelPlayersList(List<Player> activePlayers, Cards playedCards, int max) {
        List<Player> duelList = new ArrayList();
        for (int i = 0; i <= activePlayers.size() - 1; i++) {
            Player player = activePlayers.get(i);
            int cardValue = playedCards.getCards().get(i).cardValue;
            player.getDuelCards().getCards().add(new Card(cardValue));
            if (playedCards.getCards().get(i).cardValue == max) {
                duelList.add(activePlayers.get(i));
                if(player.getDuelMessage() == null){
                    player.setDuelMessage("Player"+player.getNumber()+" played: "+cardValue);
                }
            }
        }
        this.defaultWinner = duelList.get(duelList.size()-1).getNumber();
        return duelList;
    }

    void runOneRoundOfDuel(){
        checkIfPlayerStillHasCards();
        duelCards = Clash.createListOfPlayedCards(duelPlayers);
        addPlayerMessage(duelPlayers, "?");
        moveCardsFromPlayedCardsToPlayerDuelCards(duelPlayers,duelCards);
        checkIfPlayerStillHasCards();
        duelCards = Clash.createListOfPlayedCards(duelPlayers);
        addPlayerMessage(duelPlayers,"");
    }

    void checkIfPlayerStillHasCards(){
        for (Iterator<Player> iterator = duelPlayers.iterator(); iterator.hasNext();){
            Player player = iterator.next();
            if (player.getPlayersCards().getCards().size()<1){
                player.setDuelMessage(player.getDuelMessage()+" EoC");
                iterator.remove();
            }
        }
    }

    void addToDuelMessage(Player player,String concat){ player.setDuelMessage(player.getDuelMessage()+" "+concat); }

    void addPlayerMessage(List<Player> duelPlayers, String concat) {
        int index = 0;
        for (Player p: duelPlayers){
            switch (concat){
                case "?":
                    addToDuelMessage(p,concat);
                    break;
                default:
                    addToDuelMessage(p,String.valueOf(duelCards.getCards().get(index).cardValue));
                    index++;
            }
        }
    }

    void moveCardsFromPlayedCardsToPlayerDuelCards(List<Player> duelPlayers, Cards duelCards){
        for (Player p: duelPlayers){
            p.getDuelCards().getCards().add( duelCards.getCards().remove(0));
        }
    }

    public void prepareCardsForTheWinner(List<Player> activePlayers, Cards cardsForWinner, byte winner) {
        for (Player p: activePlayers){
            cardsForWinner.getCards().addAll(p.getDuelCards().getCards());
            if (p.getNumber()==winner){
                addToDuelMessage(p," -----------> WINNER FOUND!");
            }
        }
    }

    public void deleteDuelMessagesAndCards(List<Player> activePlayers) {
        for (Player p: activePlayers){
            p.getDuelCards().getCards().clear();
            p.setDuelMessage(null);
        }
    }
}
