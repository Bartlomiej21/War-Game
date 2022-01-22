package com.epam.prejap.oop.war;

import org.json.JSONArray;
import org.json.JSONException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class JSONParser {

    String json;

    void prepareGame() {
        this.json = loadGameFile();
        if (checkJSONArray(json)) {
            JSONArray ja = new JSONArray(json);
            Players players = new Players((byte)ja.length());
            PlayersCards cards = new PlayersCards(ja);
            if (cards.checkIfNotEmpty(cards.cards)) {
                RunGame runGame = new RunGame(players, cards.cards);
                runGame.playGame();
            }
        }
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

    boolean checkJSONArray(String json) {  //todo check if null is OK - it os ok,checked
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



