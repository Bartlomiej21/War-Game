package com.epam.prejap.oop.war;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.*; //todo change


public class Cards implements Iterable<Card> {

    private List<Card> cards;

    Cards(List<Card> cards) {
        this.cards = cards;
    }

    Cards() { this(new ArrayList<>()); }
    //Cards() { this(new LinkedList<>()); }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }

    @Override
    public void forEach(Consumer<? super Card> action) {
        cards.forEach(action);
    }

    @Override
    public Spliterator<Card> spliterator() {
        return cards.spliterator();
    }

    public List<Card> getCards(){
        return cards;
    }




}
