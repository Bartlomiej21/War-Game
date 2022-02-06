package com.epam.prejap.oop.war;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Players implements Iterable<Player> {  //todo access modifier levels

    private List<Player> players;

    Players(List<Player> players) {
        this.players = players;
    }

    Players() { this(new ArrayList<>()); }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }

    @Override
    public void forEach(Consumer<? super Player> action) {
        players.forEach(action);
    }

    @Override
    public Spliterator<Player> spliterator() {
        return players.spliterator();
    }

    public List<Player> getPlayers(){
        return players;
    }




    /*





    public List<Player> players = new ArrayList<>();
    static List<Byte> playersNames = new ArrayList<>();  // f.ex. [1, 2, 3]
    public static byte numberOfPlayers;    //todo try to get rid of static
    final int MIN_PLAYERS = 2;
    final int MAX_PLAYERS = 5;


    Players(byte numberOfPlayers) {   //todo take logic from constructor
        checkPlayersNumber(numberOfPlayers);
        this.numberOfPlayers = numberOfPlayers;
        createPlayersList();

    }

    Players(List<Player> players){
        this.players = players;
    }

    public void createPlayersList() {
        for (byte i=1; i<=numberOfPlayers;i++) {
            players.add(new Player(i));
            playersNames.add(i);
        }
    }

    //get numberofPlayers


    void checkPlayersNumber(byte number) {     //todo should be bool. Also this is not currently implemented
        if (number < MIN_PLAYERS || number > MAX_PLAYERS) {
            new Printer("incorrect number of players.");
        }
    }



     */
}
