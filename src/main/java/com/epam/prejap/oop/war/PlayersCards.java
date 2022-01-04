package com.epam.prejap.oop.war;

import org.json.JSONArray;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PlayersCards {

    List cards;

    PlayersCards(JSONArray ja) {
        this.cards = createPlayerCardsList(ja);
    }

    private List createPlayerCardsList(JSONArray ja) {

        List<Integer> playersList = new Players().createPlayersIntList(ja.length());
        List<List<Card>> playersCards = new LinkedList<>();

        for (int i : playersList) {

            Matcher matcher = Pattern.compile("\\d+").matcher(String.valueOf(ja.get(i - 1)));
            List<Card> childList = new LinkedList<>();

            while (matcher.find()) {
                Card card = new Card(Integer.valueOf(matcher.group()));
                if (card.correctValue) childList.add(card);
            }

            playersCards.add(childList);


        }

        showCards(playersCards);
        return playersCards;
    }

    void showCards(List<List<Card>> cards) {
        System.out.println("#################");
        for (List<Card> l : cards) {
            for (Card ca : l) {
                System.out.println("Value of card: " + ca.cardValue);
            }
        }
    }




}
