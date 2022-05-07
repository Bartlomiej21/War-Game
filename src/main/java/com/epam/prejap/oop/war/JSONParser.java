package com.epam.prejap.oop.war;

import com.epam.prejap.oop.screen.DealingScreen;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.Collections;

/**
 * This class parses JSON and uses data from it to create Players and Cards necessary for the game.
 * If no scenario is given, program will use default war.json file to run the game, but every time cards
 * will be shuffled.
 */
public class JSONParser {

    String json;
    short totalNrOfCards;
    boolean shuffleDeck;

    JSONParser(boolean shuffleDeck){
        this.shuffleDeck = shuffleDeck;
    }

    void prepareGame(String filePath) {
        this.json = loadGameFile(filePath);
        if (checkJSONArray(json)) {
            JSONArray ja = new JSONArray(json);
            Players players = createPlayers(ja);
            GameInfo.INSTANCE.setDeck(checkDeckSize());
            RunGame runGame = new RunGame(players,totalNrOfCards);
            runGame.playGame();
        }
    }

    Players createPlayers(JSONArray ja) {
        Players playersList = new Players();
        Matcher matcher;
        Cards playerCards;
        Cards allCards = new Cards();

        for (int i=0; i<ja.length();i++){
            playersList.getPlayers().add(new Player((byte) (i+1),new Cards()));
        }
        switch (shuffleDeck ? 1 : 0){
            case 1:
                matcher = Pattern.compile("\\d+").matcher(String.valueOf(ja));
                while(matcher.find()){
                    Card card = new Card(Integer.parseInt(matcher.group()));
                    if (card.correctValue) {
                        allCards.getCards().add(card);
                        totalNrOfCards++;
                    }
                }
                Collections.shuffle(allCards.getCards());
                int playerNr = 0;
                for (Card card: allCards.getCards()){
                    playersList.getPlayers().get(playerNr).getPlayersCards().getCards().add(card);
                    playerNr++;
                    if(playerNr==ja.length()) playerNr=0;
                }
                new DealingScreen(playersList,totalNrOfCards).showMessage();
                return playersList;
            case 0:
                for (int i=0; i<ja.length();i++) {
                    matcher = Pattern.compile("\\d+").matcher(String.valueOf(ja.get(i)));
                    playerCards = new Cards();
                    while (matcher.find()) {
                        Card card = new Card(Integer.parseInt(matcher.group()));
                        if (card.correctValue) {
                            playerCards.getCards().add(card);
                            totalNrOfCards++;
                        }
                    }
                    playersList.getPlayers().get(i).getPlayersCards().getCards().addAll(playerCards.getCards());
                }
                return playersList;
        }
        return null;
    }

    String checkDeckSize(){
        if (totalNrOfCards<=24) return "SMALL";
        return "BIG";
    }

    String loadGameFile(String filePath) {
        ArrayList<String> result = new ArrayList<>();
        System.out.println(filePath);
        String jsonFromFile;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while(br.ready()){
                result.add(br.readLine());
                jsonFromFile = br.lines().collect(Collectors.joining());
                jsonFromFile = "["+jsonFromFile;
                return jsonFromFile;
            }
        }  catch (FileNotFoundException e)  {
            System.out.println("File not found!");
            new Printer(e);
        }
        catch (IOException e) {
            new Printer(e);
        }
        return null;
    }

    boolean checkJSONArray(String json) {
        try {
            new JSONArray(json);
        }
        catch (JSONException e){
            new Printer(e);
            return false;
        }
        return true;
    }
}
