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
        playGame();
        /*
        while (playersInGame.size()>1){
            playGame();
        }

         */
        System.out.println("End of game");
        System.out.println("The winner is " + playersInGame.get(0).getNumber()+". Congratulations!!!");
        // end screen
    }


    void playGame() {
        int counter = 0;
        while (playersInGame.size() > 1) {
            this.activePlayers = playersInGame.stream().collect(Collectors.toList());
            while (activePlayers.size() > 1) {
                showCards();
                System.out.println("All players: " + playersInGame);
                System.out.println("Active players: " + activePlayers);
                Clash clash = new Clash(activePlayers, cards, cardsForWinner);
                if (clash.winner > 0) {
                    System.out.println("Cards for winner"+cardsForWinner);
                    addCardsToWinner(this.cards, cardsForWinner, clash.winner);

                } else {
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!DUEL");
                    new Duel(playersInGame, activePlayers, cards, cardsForWinner);
                    for (Player p : activePlayers) {
                        System.out.println("Active pla"+activePlayers);
                        System.out.println("players in game"+playersInGame);
                        showCards();
                        cardsForWinner.add(cards.get(p.getNumber() - 1).remove(0).cardValue);
                    }
                }


                System.out.println("Zwycięstwo: " + clash.winner);

                cardQuantityChecker(playersInGame, activePlayers, cards);
                // todo check if any card remains.

                cardsForWinner.clear();
            }
            counter++;
            System.out.println("Koniec "+counter+" rundy");

            // reszta cards For winner dla gracza jeżeil jeszcze ich nie ma (w przypadku zabraniu kart w Duel)
            //cardsForWinner.clear();

            // tutaj instrukcje po wybraniu zwycięzcy

        }

    }

    void cardQuantityChecker(List<Player> players, List<Player> activePlayers, List<List<Card>> cards){
        for (int i=players.size()-1;i>=0;i--){
            /// usuwa zły element, bo wcześniej ten element jest usunięty przez klasę duel
            if (cards.get(i).size()<1){
                cards.remove(i);
                players.remove(i);
                activePlayers.remove(i);
                System.out.println("usuwam: "+i);
            }
        }

    }

    List addCardsToWinner(List<List<Card>> cards, List<Integer> listOfCards,byte winner){
        System.out.println("Winner"+winner);
        for(Integer e: listOfCards){
            cards.get(winner-1).add(new Card(e));

        }
        showCards();
        return cards;
    }



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


