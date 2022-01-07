package com.epam.prejap.oop.war;

import org.json.JSONArray;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class JSONParser {

    //List cards;
    String oboze;

    JSONParser(String json){
        JSONArray ja = new JSONArray(json);
        Players players = new Players(ja.length());
        PlayersCards cards = new PlayersCards(ja);
        new RunGame(players,cards.cards);  // new class that will deal with running game. Will be connected to: players, playersCards, Duel,

        // players is a list of players - players = List<Player>;  w ArrayList
        // każdy Player ma name i number

        // cards to List<List<Card>>   of LinkedList
        // np. [[3,4,5],[4,12,3,4,5]]

    }

    JSONParser() {
        ArrayList<String> result = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("war_example.json"));
            while(br.ready()){
                result.add(br.readLine());
                this.oboze = br.lines().collect(Collectors.joining());
                oboze = "["+oboze;   //tak trzeba żyć
                System.out.println("JSON:"+oboze);
            }
        } catch (IOException e) {
            System.out.println("Nooooooo");

        }

        new JSONParser(oboze);
        //System.out.println(result);  // AL: [[, [9,9,10,10,14,14,11,10],, [9,9,11,11,11,12,13,14],, [14,10,12,12,12,13,13,13], ]]

    }

}
