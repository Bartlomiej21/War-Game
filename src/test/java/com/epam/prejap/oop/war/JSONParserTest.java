package com.epam.prejap.oop.war;

import org.json.JSONArray;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import static org.testng.Assert.*;

@Test(groups = "Game")
public class JSONParserTest {

    String json = "";
    String jsonCorrect = "[[9,9,10,14,11,10],[9,9,11,11,13,14],[14,12,12,13,13,13]]";
    JSONArray ja;
    int totalNrOfCards;

    @DataProvider(name="BooleanCheck")
    public Object[][] provideBoolean(){
        return new Object[][]{
                {true},
                {false}
        };
    }
    @DataProvider(name="DeckSize")
    public Object[][] provideDeck(){
        return new Object[][]{
                {30,"BIG"},
                {25,"BIG"},
                {24,"SMALL"},
                {18,"SMALL"}
        };
    }

    @BeforeMethod
    void setup() {
        ArrayList<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/war.json"))) {
            while (br.ready()) {
                result.add(br.readLine());
                json = br.lines().collect(Collectors.joining());
                json = "[" + json;
                ja = new JSONArray(json);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test (dataProvider = "BooleanCheck")
    private void testCreatePlayers(boolean shuffleDeck) {
        SoftAssert softAssertion = new SoftAssert();
        Players playersList = new Players();
        Matcher matcher;
        Cards playerCards;
        Cards allCards = new Cards();
        JSONArray ja = new JSONArray(json);

        for (int i=0; i<ja.length();i++){
            playersList.getPlayers().add(new Player((byte) (i+1),new Cards()));
        }
        softAssertion.assertEquals(playersList.getPlayers().size(),3);
        switch (shuffleDeck ? 1 : 0){
            case 1:
                matcher = Pattern.compile("\\d+").matcher(String.valueOf(ja));
                while(matcher.find()){
                    Card card = new Card(Integer.parseInt(matcher.group()));
                    if (card.correctValue) {
                        allCards.getCards().add(card);
                        totalNrOfCards++;
                    } else {
                        throw new IllegalArgumentException("Values should be OK");
                    }
                }
                softAssertion.assertEquals(totalNrOfCards,18);
                softAssertion.assertEquals(allCards.getCards().size(),totalNrOfCards);
                List<Card> allCardsCopy = allCards.getCards().stream().collect(Collectors.toList());
                softAssertion.assertEquals(allCards.getCards(),allCardsCopy);
                Collections.shuffle(allCards.getCards());
                softAssertion.assertNotEquals(allCards.getCards(),allCardsCopy);
                int playerNr = 0;
                for (Card card: allCards.getCards()){
                    playersList.getPlayers().get(playerNr).getPlayersCards().getCards().add(card);
                    playerNr++;
                    if(playerNr==ja.length()) playerNr=0;
                }

                int nrCards = 0;
                for (Player p: playersList){
                    nrCards += p.getPlayersCards().getCards().size();
                }
                softAssertion.assertEquals(nrCards,totalNrOfCards);
                break;
            case 0:
                totalNrOfCards = 0;
                for (int i=0; i<ja.length();i++) {
                    matcher = Pattern.compile("\\d+").matcher(String.valueOf(ja.get(i)));
                    playerCards = new Cards();
                    while (matcher.find()) {
                        Card card = new Card(Integer.parseInt(matcher.group()));
                        if (card.correctValue) {
                            playerCards.getCards().add(card);
                            totalNrOfCards++;
                        } else {
                            throw new IllegalArgumentException("Values should be OK");
                        }
                    }
                    playersList.getPlayers().get(i).getPlayersCards().getCards().addAll(playerCards.getCards());
                }
                nrCards = 0;
                for (Player p: playersList){
                    nrCards += p.getPlayersCards().getCards().size();
                }
               softAssertion.assertEquals(nrCards,totalNrOfCards);
        }
        softAssertion.assertAll();
    }

    @Test
    public void testLoadGameFile() throws FileNotFoundException {
        json  = "";
        ArrayList<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/war.json"))) {
            while (br.ready()) {
                result.add(br.readLine());
                json = br.lines().collect(Collectors.joining());
                json = "[" + json;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals(json, jsonCorrect);
    }

    @Test (dataProvider = "DeckSize")
    public void testCheckDeckSize(int deckSize, String expected) {
        String result;
        if (deckSize<=24){
            result = "SMALL";
        } else {
            result = "BIG";
        }
        assertEquals(result,expected);
    }

    @Test
    public void IsJSONArrayAbleToCreateObjectFromGivenString() {
        new JSONArray(jsonCorrect);
        System.out.println("Given String is OK");
    }
}
