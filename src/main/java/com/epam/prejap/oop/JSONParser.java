package com.epam.prejap.oop;

import org.json.JSONArray;

import java.io.StringBufferInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JSONParser {

    List cards;
    int nrOfPlayers;

    JSONParser(String json){
        JSONArray ja = new JSONArray(json);
        this.cards = createPlayersCardsList(ja);
        this.nrOfPlayers = ja.length();

    }


    private List createPlayersCardsList(JSONArray ja){

        List<Integer> playersList = IntStream.rangeClosed(1, ja.length())
                .boxed().collect(Collectors.toList());
        List<List<Integer>> playersCards = new LinkedList<>();


        for (int i : playersList) {
            Matcher matcher = Pattern.compile("\\d+").matcher(String.valueOf(ja.get(i-1)));

            LinkedList<Integer> partDeck = new LinkedList<>();

            while (matcher.find()) {
                partDeck.add(Integer.valueOf(matcher.group()));
            }
            playersCards.add(partDeck);


        }

        return playersCards;
    }

}
