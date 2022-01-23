package com.epam.prejap.oop.war;

import java.util.List;
import java.util.ArrayList;

public class Players  {  //todo access modifier levels

    List<Player> players = new ArrayList<>();
    static List<Byte> playersNames = new ArrayList<>();  // f.ex. [1, 2, 3]
    public static byte numberOfPlayers;    //todo try to get rid of static
    final int MIN_PLAYERS = 2;
    final int MAX_PLAYERS = 5;


    Players(byte numberOfPlayers) {   //todo take logic from constructor
        checkPlayersNumber(numberOfPlayers);
        this.numberOfPlayers = numberOfPlayers;
        createPlayersList();

    }

    public void createPlayersList() {     //not actually creating a list todo
        for (byte i=1; i<=numberOfPlayers;i++) {
            players.add(new Player(i));
            playersNames.add(i);
        }
    }

    //get numberofPlayers


    void checkPlayersNumber(byte number) {     //todo should be bool
        if (number < MIN_PLAYERS || number > MAX_PLAYERS) {
            new Printer("incorrect number of players.");
        }
    }
}
