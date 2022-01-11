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

    JSONParser() {
        ArrayList<String> result = new ArrayList<>();

        try {
            //BufferedReader br = new BufferedReader(new FileReader("war_example.json"));
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/war.json"));  //todo for Step 3 and beyond
            while(br.ready()){
                result.add(br.readLine());
                this.json = br.lines().collect(Collectors.joining());
                json = "["+json;
            }
        }  catch (FileNotFoundException e)  {
            System.out.println("File not found!");
        }
            catch (IOException e) {
            System.out.println("Input output exception");
            //todo for logs

        }

        try {
            new JSONArray(json);
            } catch (JSONException e){
        }

        JSONArray ja = new JSONArray(json);
        Players players = new Players((byte)ja.length());
        PlayersCards cards = new PlayersCards(ja);
        if (cards.checkIfNotEmpty(cards.cards)) new RunGame(players,cards.cards);

    }


    //todo This will become obsolete after step2
    JSONParser(String json){
        JSONArray ja = new JSONArray(json);
        Players players = new Players((byte)ja.length());
        PlayersCards cards = new PlayersCards(ja);
        if (cards.checkIfNotEmpty(cards.cards)) new RunGame(players,cards.cards);

    }



}

