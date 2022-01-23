package com.epam.prejap.oop.war;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.*; //todo change


public class Cards implements Iterable<Card>, Collection<Card> {

    private List<Card> cards;

    Cards(List<Card> cards) {
        this.cards = cards;
    }

    Cards() { this(new LinkedList<>()); }
    //Cards() { this(new LinkedList<>()); }


    @Override
    public int size() {
        return cards.size();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Card card) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends Card> c) {
        return false;
    }


    /*
    @Override
    public List<Card> addAll(Cards c) {
        cards.addAll(c);
        return cards;
    }

     */

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

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
