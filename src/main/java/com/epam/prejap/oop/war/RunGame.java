package com.epam.prejap.oop.war;

import com.epam.prejap.oop.screen.EndScreen;

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
        System.out.println("Welcome to the game of WAR!");

        //new LongGameResolution(playersInGame,cards);   //todo delete after  further testing
        playGame();
        byte winner = playersInGame.get(0).getNumber();
        short size = (short) this.cards.get(winner-1).size();
        new EndScreen(winner, size, PlayersCards.totalNrOfCards);


    }

    void playGame() {
        short round = 1;
        short gameLimit = (short) (PlayersCards.totalNrOfCards*10);   //todo this is final version
        //short gameLimit = 10;  // to keep it short for now

        while (playersInGame.size()>1 && round<gameLimit){
            this.activePlayers = playersInGame.stream().collect(Collectors.toList());
                //showCards();    //SHOWS player cards
                //System.out.println("All players: " + playersInGame);
                //System.out.println("Active players: " + activePlayers);
                Clash clash = new Clash(playersInGame,activePlayers, cards, cardsForWinner);
                //System.out.println("Winner of the round "+round+": "+clash.winner);

            //System.out.println("All players: " + playersInGame);
            //System.out.println("Active players: " + activePlayers);

            addCardsToWinner(this.cards, cardsForWinner, clash.winner);
            cardQuantityChecker(playersInGame, cards);
            this.activePlayers = playersInGame.stream().collect(Collectors.toList());

            cardsForWinner.clear();
            round++;
            System.out.println("End of round "+round+"\n");
            if (round==gameLimit) {
                LongGameResolution lgs = new LongGameResolution(playersInGame, cards);
                new EndScreen(round, lgs.winner);
                }
            }

        }


    void cardQuantityChecker(List<Player> playersInGame, List<List<Card>> cards){
        playersInGame.removeIf(i -> cards.get(i.getNumber()-1).size() <1);
    }

    List addCardsToWinner(List<List<Card>> cards, List<Integer> listOfCards,byte winner){
       // System.out.println("Adding cards "+listOfCards+" to player "+winner);
        for(Integer e: listOfCards){
            cards.get(winner-1).add(new Card(e));
        }

        return cards;
    }


    public void showCards(){
        for (int i=0; i<cards.size();i++) {
            System.out.println("Cards remaining in player"+(i+1)+" hands: ");
            for (int j = 0; j < cards.get(i).size(); j++) {
                System.out.print(cards.get(i).get(j).getCardValue()+" ");

            }
            System.out.println("");
        }
        System.out.println("");
    }

    @Override
    public Iterator<Player> iterator(){
        return playersInGame.iterator();
    }

}


