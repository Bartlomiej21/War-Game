package com.epam.prejap.oop.war;

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
    final int NR_OF_PLAYERS ;
    public static int cardNumber = 2;    //just for game screen, function will probably change with new tasks

    JSONParser(String json){
        JSONArray ja = new JSONArray(json);
        new PlayersCards(ja);  //createPlayerCardsList(ja);
        this.NR_OF_PLAYERS = ja.length();   //TODO should be different class with it's own requirements (2-4 players)

    }


}
