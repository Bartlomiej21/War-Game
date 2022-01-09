package com.epam.prejap.oop.war;

import org.json.JSONArray;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class JSONParser {

    String json;

    JSONParser() {
        ArrayList<String> result = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("war_example.json"));
            while(br.ready()){
                result.add(br.readLine());
                this.json = br.lines().collect(Collectors.joining());
                json = "["+json;
            }
        } catch (IOException e) {
            System.out.println("Nooooooo");
            //todo for logs

        }

        JSONArray ja = new JSONArray(json);
        Players players = new Players((byte)ja.length());
        PlayersCards cards = new PlayersCards(ja);
        new RunGame(players,cards.cards);

    }


    //todo This will become obsolete after step2
    JSONParser(String json){
        JSONArray ja = new JSONArray(json);
        Players players = new Players((byte)ja.length());
        PlayersCards cards = new PlayersCards(ja);
        new RunGame(players,cards.cards);

    }


}

