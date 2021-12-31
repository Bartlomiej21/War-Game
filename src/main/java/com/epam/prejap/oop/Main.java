package com.epam.prejap.oop;

import org.json.JSONArray;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Main {

    public static void main(String[] args) {
        String json = """
                [
                [3],
                [2],
                ]""";

        JSONParser jparse = new JSONParser(json);  // MY constructor
        System.out.println("Deck in a form of Linked List:  "+jparse.cards);

        //System.out.println(jparse.nrOfPlayers);
        System.out.println("p1: "+jparse.cards.get(0)+"\np2: "+jparse.cards.get(1));


        //p1: 3
        //p2: 2
    }

}
