package com.epam.prejap.oop.war;

import org.json.JSONArray;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import static org.testng.Assert.*;

public class RunGameTest {

    String json = "[[9,9,10,14,11,10],[9,9,11,11,13,14],[14,12,12,13,13,13]]";
    byte totalNrOfCards = 18;
    Players players;
    RunGame testGame;

    @BeforeMethod
    void setUp() {
        JSONArray ja = new JSONArray(json);
        JSONParser jpars = new JSONParser(false);
        this.players = jpars.createPlayers(ja);
        this.testGame = new RunGame(players,totalNrOfCards);
    }

    @Test
    public void testGameLimitValue() {
        short gameLimit = (short) (totalNrOfCards*10);
        assertEquals(gameLimit,180);
    }

    @Test
    public void testNumberOfPlayers(){
        assertEquals(players.getPlayers().size(),3);
    }

    @Test
    public void testCardQuantityChecker() {
        int totalCardsQuantity = 0;
        for (Player p: players){
            totalCardsQuantity += p.getPlayersCards().getCards().size();
        }
        assertEquals(totalCardsQuantity,18);
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
        assertEquals(cards.get(winner-1).get(0).getCardValue(),5);
        assertEquals(cards.get(winner-1).get(1).getCardValue(),10);
    }
}
