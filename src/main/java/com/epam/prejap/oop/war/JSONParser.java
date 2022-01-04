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
    public static int cardNumber = 2;    //just for game screen, function will probably change with new tasks

    JSONParser(String json){
        JSONArray ja = new JSONArray(json);
        Players players = new Players(ja.length());
        PlayersCards cards = new PlayersCards(ja);
        //new Game();  // new class that will deal with running game. Will be connected to: players, playersCards, Duel,

    }


}
