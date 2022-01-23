package com.epam.prejap.oop.war;

import org.json.JSONArray;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayersCards {


    List<Cards> cards;
    //static short totalNrOfCards;
    short totalNrOfCards;

    PlayersCards(JSONArray ja) {
        this.cards = createPlayerCardsList(ja);
    }

    private List createPlayerCardsList(JSONArray ja) {  //not depend on JSONa todo

        List<Cards> playersCards = new LinkedList<>();

        for (byte i : Players.playersNames) {

            Matcher matcher = Pattern.compile("\\d+").matcher(String.valueOf(ja.get(i - 1)));
            Cards childList = new Cards();

            while (matcher.find()) {
                Card card = new Card(Integer.parseInt(matcher.group()));
                if (card.correctValue) {
                    childList.getCards().add(card);
                    totalNrOfCards++;
                }
            }
            playersCards.add(childList);
        }
        return playersCards;
    }

    boolean checkIfNotEmpty(List<Cards> cardsToCheck){
        for (Cards list: cardsToCheck){
            if (list.getCards().isEmpty()) {
                //System.out.println("One of the players does not have any cards!");
                new Printer("one or more players did not have any cards!");
                System.exit(0);
                break;
            }
        }
        return true;
    }


}

/*
public class PlayersCards {

    //List<List<Card>> cards;  //List<Hand>
    List<Cards> cards;
    static short totalNrOfCards;

    PlayersCards(JSONArray ja) {
        this.cards = createPlayerCardsList(ja);
    }

    private List createPlayerCardsList(JSONArray ja) {  //not depend on JSONa todo

        List<List<Card>> playersCards = new LinkedList<>();

        for (byte i : Players.playersNames) {

            Matcher matcher = Pattern.compile("\\d+").matcher(String.valueOf(ja.get(i - 1)));
            List<Card> childList = new LinkedList<>();

            while (matcher.find()) {
                Card card = new Card(Integer.parseInt(matcher.group()));
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
                //System.out.println("One of the players does not have any cards!");
                new Printer("one or more players did not have any cards!");
                System.exit(0);
                break;
            }
        }
        return true;
    }


}


 */