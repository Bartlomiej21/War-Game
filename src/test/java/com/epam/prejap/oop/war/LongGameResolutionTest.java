package com.epam.prejap.oop.war;

import org.json.JSONArray;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.*;

import static org.testng.Assert.*;

public class LongGameResolutionTest {


    List<List<Card>> deck;
    List<Player> players;

    String jsonAfterGame = "[[9,9,10],[9,9,11,11,13,14],[14,12,13,13]]";  //can be changed to test results
    @BeforeMethod
    void setUp() {
        JSONArray ja = new JSONArray(jsonAfterGame);
        Players p = new Players((byte)ja.length());
        this.deck = new PlayersCards(ja).cards;
        this.players = p.players;

    }

    @Test
    public void testFindWinner() {
        LongGameResolution lg = new LongGameResolution(players,deck);
        assertEquals(lg.winner,2);

    }
}