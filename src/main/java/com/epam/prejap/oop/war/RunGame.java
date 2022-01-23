package com.epam.prejap.oop.war;

import com.epam.prejap.oop.screen.EndScreen;

import java.util.List;
import java.util.Iterator;
import java.util.stream.Collectors;

public class RunGame implements Iterable<Player> {

    List<Player> playersInGame;
    List<Player> activePlayers ;
    List<Cards> cards;
    Cards cardsForWinner = new Cards();
    short totalNrOfCards;
    byte nrOfPlayers;

    RunGame(Players playersList, List cards, short totalNrOfCards) {
        this.playersInGame = playersList.players;
        this.cards = cards;
        this.totalNrOfCards = totalNrOfCards;
        this.nrOfPlayers = (byte) playersInGame.size();
        new Printer(nrOfPlayers,totalNrOfCards,(byte)0,(byte)0);
        System.out.println("Welcome to the game of WAR!");
    }

    void playGame() {
        short round = 1;
        short gameLimit = (short) (totalNrOfCards*10);   //todo this is final version
        //short gameLimit = 10;  // for short tests

        while (playersInGame.size()>1 && round<gameLimit){
            this.activePlayers = playersInGame.stream().collect(Collectors.toList());
            showCards();    //SHOWS player cards
            byte winner = new Clash(totalNrOfCards).resolveClash(playersInGame,activePlayers, cards, cardsForWinner);

            addCardsToWinner(this.cards, cardsForWinner, winner);  //HERE
            cardQuantityChecker(playersInGame, cards);              //HERE
            this.activePlayers = playersInGame.stream().collect(Collectors.toList());

            cardsForWinner.getCards().clear();
            round++;
            System.out.println("End of round "+round+"\n");
            if (round==gameLimit) {
                LongGameResolution lgs = new LongGameResolution(playersInGame, cards);  //here
                new Printer(nrOfPlayers,totalNrOfCards,lgs.winner,round);
                new EndScreen(round, lgs.winner);
                }
            }
        byte winner = playersInGame.get(0).getNumber();
        short size = (short) this.cards.get(winner-1).getCards().size();
        new Printer(winner,round);
        new EndScreen(winner, size, totalNrOfCards);
    }

    void cardQuantityChecker(List<Player> playersInGame, List<Cards> cards){
        playersInGame.removeIf(i -> cards.get(i.getNumber()-1).getCards().size() <1);
    }

    List addCardsToWinner(List<Cards> cards, Cards listOfCards,byte winner){
        for(Card c: listOfCards){
            cards.get(winner-1).getCards().add(c);
        }
        return cards;
    }


    public void showCards(){
        for (int i=0; i<cards.size();i++) {
            System.out.println("Cards remaining in player"+(i+1)+" hands: ");
            for (int j = 0; j < cards.get(i).getCards().size(); j++) {          //here
                System.out.print(cards.get(i).getCards().get(j).getCardValue()+" ");  //here

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


