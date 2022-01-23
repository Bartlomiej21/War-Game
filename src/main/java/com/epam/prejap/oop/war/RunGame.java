package com.epam.prejap.oop.war;

import com.epam.prejap.oop.screen.EndScreen;

import java.util.List;
import java.util.Iterator;
import java.util.stream.Collectors;

public class RunGame implements Iterable<Player> {

    Players playersInGame;
    List<Player> activePlayers ;
    Cards cardsForWinner = new Cards();
    short totalNrOfCards;
    byte nrOfPlayers;

    RunGame(Players playersList) {
        this.playersInGame = playersList;
        //this.cards = cards;  //this was from a constructor
        this.totalNrOfCards = 18;
        this.nrOfPlayers = (byte) playersInGame.getPlayers().size();
        new Printer(nrOfPlayers,totalNrOfCards,(byte)0,(byte)0);
        System.out.println("Welcome to the game of WAR!");
    }

    void playGame() {
        short round = 1;
        short gameLimit = (short) (totalNrOfCards*10);   //todo this is final version
        //short gameLimit = 10;  // for short tests

        while (playersInGame.getPlayers().size()>1 && round<gameLimit){
            this.activePlayers = playersInGame.getPlayers().stream().collect(Collectors.toList());
            //this.activePlayers = new Players();
            //activePlayers.getPlayers().addAll(playersInGame.getPlayers().stream().collect(Collectors.toList()));  // this is not a copy


            /*
            this.activePlayers = new Players();
            for (Player p: playersInGame.getPlayers()) {
                activePlayers.getPlayers().add(p);
            }

             */



            showCards();    //SHOWS player cards
            byte winner = new Clash(totalNrOfCards).resolveClash(playersInGame,activePlayers, cardsForWinner);  //todo poprawić short w konstruktorze

            System.out.println("winner: "+winner);
            System.out.println(cardsForWinner.getCards().size());
            addCardsToWinner(playersInGame, cardsForWinner, winner);  //HERE
            cardQuantityChecker(playersInGame);              //HERE

            /*
            this.activePlayers = new Players();
            for (Player p: playersInGame.getPlayers()) {
                activePlayers.getPlayers().add(p);
            }
            */


            this.activePlayers = playersInGame.getPlayers().stream().collect(Collectors.toList());


            cardsForWinner.getCards().clear();
            round++;
            System.out.println("End of round "+round+"\n");
            if (round==gameLimit) {
                LongGameResolution lgs = new LongGameResolution(playersInGame);  //here
                new Printer(nrOfPlayers,totalNrOfCards,lgs.winner,round);
                new EndScreen(round, lgs.winner);
                }
            }
        byte winner = playersInGame.getPlayers().get(0).getNumber();
        //short size = (short) this.cards.get(winner-1).getCards().size();
        short size = 18;
        new Printer(winner,round);
        new EndScreen(winner, size, totalNrOfCards);
    }

    void cardQuantityChecker(Players playersInGame){
        playersInGame.getPlayers().removeIf(i -> i.getPlayersCards().getCards().size()<1);
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


    public void showCards(){
        for (Player p: playersInGame) {
            System.out.println("Cards remaining in player"+(p.getNumber())+" hands: ");
            for (Card c: p.getPlayersCards().getCards()) {
                System.out.print(c.cardValue+" ");

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


