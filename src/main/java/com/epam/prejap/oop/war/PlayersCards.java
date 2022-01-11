package com.epam.prejap.oop.war;

import org.json.JSONArray;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayersCards {

    List cards;
    static short totalNrOfCards;

    PlayersCards(JSONArray ja) {
        this.cards = createPlayerCardsList(ja);
    }

    private List createPlayerCardsList(JSONArray ja) {

        List<List<Card>> playersCards = new LinkedList<>();

        for (byte i : Players.playersNames) {

            Matcher matcher = Pattern.compile("\\d+").matcher(String.valueOf(ja.get(i - 1)));
            List<Card> childList = new LinkedList<>();

            while (matcher.find()) {
                Card card = new Card(Integer.valueOf(matcher.group()));
                if (card.correctValue) {
                    childList.add(card);
                    totalNrOfCards++;
                }
            }
            playersCards.add(childList);
        }

        return playersCards;
    }

    boolean checkIfNotEmpty(List<List<Card>> cardsToCheck){
        for (List<Card> list: cardsToCheck){
            if (list.isEmpty()) {
                System.out.println("One of the players does not have any cards!");
                System.exit(0);
                //todo for future logs
                break;
            }
        }
        return true;
    }


}
