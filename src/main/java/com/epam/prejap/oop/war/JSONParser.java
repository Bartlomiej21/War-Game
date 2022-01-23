package com.epam.prejap.oop.war;

import org.json.JSONArray;
import org.json.JSONException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class JSONParser {

    String json;
    short totalNrOfCards;

    void prepareGame() {
        this.json = loadGameFile();
        if (checkJSONArray(json)) {
            JSONArray ja = new JSONArray(json);
            Players players = createPlayers(ja);
            // i want for the game to run with just players and total nr of cards
            //Players players = new Players((byte)ja.length());

            //DeckCreator cards = new DeckCreator(ja);
            //if (cards.checkIfNotEmpty(cards.cards)) {
            if (true){
                RunGame runGame = new RunGame(players); //todo
                runGame.playGame();
            }
        }
    }

    private Players createPlayers(JSONArray ja) {

        Players playersList = new Players();
        //Players players = new Players(playersList);
        // todo switch with option of shuffled cards

        for (int i=0; i<ja.length();i++) {

            Matcher matcher = Pattern.compile("\\d+").matcher(String.valueOf(ja.get(i)));
            Cards playerCards = new Cards();

            while (matcher.find()) {
                Card card = new Card(Integer.parseInt(matcher.group()));
                if (card.correctValue) {
                    playerCards.getCards().add(card);
                    totalNrOfCards++;
                }
            }
            //playersCards.add(childList);
            playersList.getPlayers().add(new Player((byte)(i+1),playerCards));
        }
        //System.out.println("PLAYERS" + playersList.getPlayers().get(1).getPlayersCards().getCards().get(3).cardValue);
        return playersList;
    }




    String loadGameFile() {
        ArrayList<String> result = new ArrayList<>();
        String jsonFromFile;
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/war.json"));
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

// todo think if a new class for checking stuff is needed
// check ja.length before passing to players



