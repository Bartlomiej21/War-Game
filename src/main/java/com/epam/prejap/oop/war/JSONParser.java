package com.epam.prejap.oop.war;

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
 * This class parsesJSON and uses data from it to create Players and Cards necessary for the game.
 * If no scenario is given, program will use default war.json file to run the game, but every time cards will be shuffled.
 */
public class JSONParser {

    String json;
    short totalNrOfCards;
    boolean shuffleDeck;

    JSONParser(boolean shuffleDeck){
        this.shuffleDeck = shuffleDeck;
    }

    void prepareGame(String jsonFilePath) {
        this.json = loadGameFile(jsonFilePath);
        if (checkJSONArray(json)) {
            JSONArray ja = new JSONArray(json);
            Players players = createPlayers(ja);
            //if (cards.checkIfNotEmpty(cards.cards)) {  //todo
            if (true){
                RunGame runGame = new RunGame(players,totalNrOfCards); //todo
                runGame.playGame();
            }
        }
    }

    private Players createPlayers(JSONArray ja) {
        Players playersList = new Players();
        Matcher matcher;
        Cards playerCards;
        Cards allCards = new Cards();

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
                //todo move to before switch after checking
                for (int i=0; i<ja.length();i++){
                    playersList.getPlayers().add(new Player((byte) (i+1),new Cards()));
                }
                int player = 0;
                for (Card card: allCards.getCards()){
                    playersList.getPlayers().get(player).getPlayersCards().getCards().add(card);
                    player++;
                    if(player==ja.length()) player=0;
                }
                //System.out.println(playersList.getPlayers().get(2).getPlayersCards().getCards().get(5).cardValue);
                //playerCards = new Cards();
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
                    playersList.getPlayers().add(new Player((byte)(i+1),playerCards));
                }
                return playersList;
        }
        return null;
    }

    String loadGameFile(String filePath) {
        ArrayList<String> result = new ArrayList<>();
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
