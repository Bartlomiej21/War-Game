package com.epam.prejap.oop.war;

import java.util.*;
import java.util.Iterator;
import java.util.stream.Collectors;

public class RunGame implements Iterable<Player> {

    List<Player> playersInGame;
    List<Player> activePlayers ;
    List<List<Card>> cards;
    List<Integer> cardsForWinner = new ArrayList<>();


    RunGame(Players playersList, List cards) {
        this.playersInGame = playersList.players;
        this.cards = cards;
        System.out.println("Game started");
        while (playersInGame.size()>1){
            playGame();
        }
        // end screen
    }


    void playGame(){
        // I assume players in game has only players that have cards
        this.activePlayers = playersInGame.stream().collect(Collectors.toList());
        while (activePlayers.size()>1) {
            System.out.println("Active players: "+activePlayers);
            Clash clash = new Clash(activePlayers, cards, cardsForWinner);
            if (clash.winner>0) {
                addCardsToWinner(this.cards, cardsForWinner,clash.winner);

            } else {
                //new Duel(activePlayers,cards);
            }


            System.out.println("Zwycięstwo: "+clash.winner);
            System.out.println("Showcards in rungame");
            showCards();

            // todo check if any chard remains.


        }
        cardsForWinner.clear();

        // tutaj instrukcje po wybraniu zwycięzcy

    }

    List addCardsToWinner(List<List<Card>> cards, List<Integer> listOfCards,byte winner){
        System.out.println("Winnnnnnner"+winner);
        for(Integer e: listOfCards){
            cards.get(winner-1).add(new Card(e));

        }
        System.out.println("Showcards in clash");
        showCards();
        return cards;
    }


    // this does not work cause of iterator
    void removePlayer(byte playerNumber){
        playersInGame.remove(playerNumber);
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

    @Override
    public Iterator<Player> iterator(){
        return playersInGame.iterator();
    }

}


