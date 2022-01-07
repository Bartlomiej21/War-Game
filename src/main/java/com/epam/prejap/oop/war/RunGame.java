package com.epam.prejap.oop.war;

import java.util.*;
import java.util.Iterator;



public class RunGame implements Iterable<Player> {

    List<Player> playersInGame;
    List<List<Card>> cards;
    List<Integer> playedCards = new LinkedList<>();

    RunGame(Players playersList, List cards) {
        this.playersInGame = playersList.players;
        this.cards = cards;
        //System.out.println(playersList.players);    //[Player1, Player2]
        //System.out.println(playersList.numberOfPlayers);   //2
        playGame();
    }

    void playGame(){
        ListIterator<Player> iter = playersInGame.listIterator();
        int playernr;
        int cardValue;

        showCards();

        System.out.println("Game started");
        while (iter.hasNext()) { //if next element exists

           /*
            //System.out.println(players.get(iter.nextIndex()).getNumber());
            //players.get(iter.nextIndex()).getNumber();
            int a = playersInGame.get(iter.nextIndex()).getNumber();
            int b = cards.get(a-1).size();
            System.out.println("To jest numer tego zawodnika: "+a);
            System.out.println("to jest ilość kart jego: "+b);
            */


            playernr = playersInGame.get(iter.nextIndex()).getNumber();
            /* BACKUP CARDS OPERATIONS
            //cardValue = cards.get(playernr-1).get(0).cardValue;
            //cards.get(playernr-1).remove(0);
            //playedCards.add(cardValue);
            */
            playedCards.add( cards.get(playernr-1).remove(0).cardValue );  //short version
            //System.out.println(cards.get(iter.nextIndex()));
            iter.next();


        }

        /*
        List<String> duelPlayers = new LinkedList<>();
        Integer max = Collections.max(pool);
        int occurrences = Collections.frequency(pool, max);
        System.out.println("Max: "+max+"   Times: "+occurrences);

         */

        // newCardComparator(playedCards);

        // TODO add game screen somewhere around here

        Integer max = Collections.max(playedCards);
        //System.out.println(max);
        int occurrences = Collections.frequency(playedCards, max);
        //System.out.println(occurrences);

        System.out.println("Cards on table: "+playedCards);


        showCards();

        compare(playedCards.get(0),playedCards.get(1));



    }

    public void compare(int one, int two){
        if (one>two) System.out.println("First card is bigger");
        else if(two>one) System.out.println("Second card is bigger");
        else System.out.println("Cards are the same!");
    }

    void removePlayer(byte playerNumber){
        playersInGame.remove(playerNumber);
    }

    // TODO the are 2 methods doing this, so 1 will need to go
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


