package com.epam.prejap.oop.war;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class deals with situation in a game when Clash ends up in a draw.
 */
public class Duel {

    List<Player> duelPlayers;
    Cards duelCards;
    byte defaultWinner;

    Duel(){
        //this.duelPlayers = createDuelPlayersList();
    }

    List<Player> createDuelPlayersList(List<Player> activePlayers, Cards playedCards, int max) {
        List<Player> duelList = new ArrayList();
        for (int i = 0; i <= activePlayers.size() - 1; i++) {
            Player player = activePlayers.get(i);
            int cardValue = playedCards.getCards().get(i).cardValue;
            player.getDuelCards().getCards().add(new Card(cardValue));
            if (player.getDuelMessage() == null){
                player.setDuelMessage("Player"+player.getNumber()+" played: "+cardValue);
            }
            if (playedCards.getCards().get(i).cardValue == max) {
                duelList.add(activePlayers.get(i));
            }
        }
        this.defaultWinner = duelList.get(duelList.size()-1).getNumber();
        return duelList;
    }

    void runOneRoundOfDuel(){
        checkIfPlayerStillHasCards();
        duelCards = Clash.createListOfPlayedCards(duelPlayers);
        addHiddenCardMessage(duelPlayers, "?");
        moveCardsFromPlayedCardsToPlayerDuelCards(duelPlayers,duelCards);
        checkIfPlayerStillHasCards();
        duelCards = Clash.createListOfPlayedCards(duelPlayers);
        int index = 0;
        for (Player p: duelPlayers){
            addToDuelMessage(p,String.valueOf(duelCards.getCards().get(index).cardValue));
            index++;
            //System.out.println("duel message: "+p.getDuelMessage());
        }
    }

    void checkIfPlayerStillHasCards(){
        for (Iterator<Player> iterator = duelPlayers.iterator(); iterator.hasNext();){
            Player player = iterator.next();
            if (player.getPlayersCards().getCards().size()<1){
                player.setDuelMessage(player.getDuelMessage()+" EoC");  //todo method to add to get message (Player,String)
                iterator.remove();
            }
        }
    }

    void addToDuelMessage(Player player,String concat){ player.setDuelMessage(player.getDuelMessage()+" "+concat); }

    void addHiddenCardMessage(List<Player> duelPlayers, String concat) {
        for (Player p: duelPlayers){
            addToDuelMessage(p,concat);
        }
    }

    void moveCardsFromPlayedCardsToPlayerDuelCards(List<Player> duelPlayers, Cards duelCards){
        for (Player p: duelPlayers){
            p.getDuelCards().getCards().add( duelCards.getCards().remove(0));  //since removing, always first card
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
