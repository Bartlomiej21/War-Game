package com.epam.prejap.oop.war;


import java.util.List;

public class Duel {


    Duel(List<Player> allPlayers, List<Player> activePlayers, List<List<Card>> cards,List<Integer> cardsForWinner){

        activePlayers.removeIf(i -> cards.get(i.getNumber()-1).size()<1);

        switch (activePlayers.size()) {
            case 1:
                System.out.println("Case 1");
                Clash.winner = activePlayers.get(0).getNumber();
                break;
            case 0:   // in case it ends in a draw todo solve cases when draw and no more cards
                System.out.println("The game has ended in a draw!!!");
                System.exit(0);
                break;
            default:
                new Clash(allPlayers,activePlayers,cards,cardsForWinner);

        }

    }

    byte resolveDrawWithNoCardsLeft(){

        return 0;
    }


}
