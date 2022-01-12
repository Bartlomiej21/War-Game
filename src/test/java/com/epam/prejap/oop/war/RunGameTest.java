package com.epam.prejap.oop.war;

import org.json.JSONArray;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;


public class RunGameTest {



    String json = "[[9,9,10,14,11,10],[9,9,11,11,13,14],[14,12,12,13,13,13]]";  //from the file

    @BeforeMethod
    void setUp() {
        JSONArray ja = new JSONArray(json);
        Players players = new Players((byte)ja.length());
        new PlayersCards(ja);

    }



    @Test
    public void testGameLimitValue() {
        short gameLimit = (short) (PlayersCards.totalNrOfCards*10);
        assertEquals(gameLimit,180);
    }

    @Test
    public void testCardQuantityChecker() {
        List<Player> players = new ArrayList<>();
        List<List<Card>> cards = new ArrayList<>();
        List<Card> partOfCards = new ArrayList<>();
        cards.add(partOfCards);
        players.add(new Player((byte) 1));

        assertEquals(players.size(),1);
        players.removeIf(i -> cards.get(i.getNumber()-1).size() <1);
        assertEquals(players.size(),0);

    }

    @Test
    public void testAddCardsToWinner() {
        List<Integer> listOfCards = new ArrayList<>();
        listOfCards.add(10);

        List<List<Card>> cards = new ArrayList<>();
        List<Card> partOfCards = new ArrayList<>();
        partOfCards.add(new Card(5));
        cards.add(partOfCards);

        int winner =1;

        for(Integer e: listOfCards){
            cards.get(winner-1).add(new Card(e));
        }
        assertEquals(cards.get(winner-1).get(0).cardValue,5);
        assertEquals(cards.get(winner-1).get(1).cardValue,10);
    }
}