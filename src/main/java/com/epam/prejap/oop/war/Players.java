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
}
