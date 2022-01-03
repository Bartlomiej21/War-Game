package com.epam.prejap.oop.war;

import org.json.JSONArray;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PlayersCards {

    List cards;

    PlayersCards(JSONArray ja){
        this.cards = createPlayerCardsList(ja);
    }

    private List createPlayerCardsList(JSONArray ja){

        List<Integer> playersList = IntStream.rangeClosed(1, ja.length())
                .boxed().collect(Collectors.toList());
        List<List<Card>> playersCards = new LinkedList<>();

        for (int i : playersList) {
            Matcher matcher = Pattern.compile("\\d+").matcher(String.valueOf(ja.get(i-1)));
            List<Card> childList = new LinkedList<>();

            while (matcher.find()) {
                Card card = new Card( Integer.valueOf(matcher.group()) );
                if (card.correctValue) childList.add(card);
            }


/*
            for (Card c: childList){
                System.out.println("Cardvalue: " + c.cardValue);
            }
*/

            playersCards.add(childList);


        }


        System.out.println("#################");
        for (List<Card> l: playersCards){
            for (Card ca: l){
                System.out.println("oto wartość karty: " + ca.cardValue);
            }
        }


        return playersCards;
    }


}
