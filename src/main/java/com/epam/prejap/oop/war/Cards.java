package com.epam.prejap.oop.war;

import java.util.*;
import java.util.function.Consumer;


public class Cards implements Iterable<Card> {

    private List<Card> cards;

    Cards(List<Card> cards) {
        this.cards = cards;
    }

    Cards() {
        this(new ArrayList<>());
    }

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
