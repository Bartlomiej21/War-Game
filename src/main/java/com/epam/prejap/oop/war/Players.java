package com.epam.prejap.oop.war;

import java.util.*;

class Players  {

    List<Player> players = new ArrayList<>();
    static List<Byte> playersNames = new ArrayList<>();  // f.ex. [1, 2, 3]
    static byte numberOfPlayers;
    final int MIN_PLAYERS = 2;
    final int MAX_PLAYERS = 5;


    Players(byte numberOfPlayers) {
        checkPlayersNumber(numberOfPlayers);
        this.numberOfPlayers = numberOfPlayers;
        createPlayersList();

    }

    public void createPlayersList() {
        for (byte i=1; i<=numberOfPlayers;i++) {
            players.add(new Player(i));
            playersNames.add(i);

        }

    }

    public void iterateThrough(){
        // tryout implementations for iterators

        ListIterator<Player> iter = players.listIterator();

        while (iter.hasNext()) {
            players.get(iter.nextIndex()).getNumber();
            iter.next();
        }

    }


    void checkPlayersNumber(byte number) {
        if (number < MIN_PLAYERS || number > MAX_PLAYERS) {
            System.out.println("Bad player count ");
            try
            {
                Thread.sleep(4000);
            }
            catch(InterruptedException e)
            {
                System.exit(-1);
                //todo log errors
            }
        }
    }

}