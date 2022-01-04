package com.epam.prejap.oop.war;

import java.util.Iterator;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Players implements Iterable<Player> {

    List<Player> players = new ArrayList<>();
    int numberOfPlayers;   // NUMBER_OF_PLAYERS
    final int MIN_PLAYERS = 2;
    final int MAX_PLAYERS = 4;



    Players(int numberOfPlayers) {
        checkPlayersNumber(numberOfPlayers);
        this.numberOfPlayers = numberOfPlayers;
        createPlayersList();

    }

    Players(){ }


    public List<Integer> createPlayersIntList(int nr){
        List<Integer> playersInt = IntStream.rangeClosed(1, nr).boxed().collect(Collectors.toList());
        return playersInt;

    }

    public void createPlayersList() {

        List<String> playersString = new ConvertIntToStringList(createPlayersIntList(numberOfPlayers)).stringList;
        //System.out.println("String of players: " + playersString);
        for (String s : playersString) players.add(new Player(s));

    }

    public void iterateThrough(){
        // tryout implementations for iterators
        ListIterator<Player> iter = players.listIterator();

        while (iter.hasNext()) { //if next element exists
            System.out.println(players.get(iter.nextIndex()).getNumber());

            //System.out.println( "Bum lyo" +  players.get(j.nextIndex())  );  //Bum lyoPlayer1
            //System.out.println("c"+players.get(j.nextIndex()));  // cPlayer1

            iter.next(); // advance the pointer
        }


    }


    void checkPlayersNumber(int number) {
        if (number < MIN_PLAYERS || number > MAX_PLAYERS) {
            System.out.println("Bad player count ");
            try
            {
                Thread.sleep(4000);
            }
            catch(InterruptedException e)
            {
                System.exit(-1);
            }

        }

    }



    @Override
    public Iterator<Player> iterator(){
        return players.iterator();
    }

}
