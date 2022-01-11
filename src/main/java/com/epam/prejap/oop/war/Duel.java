package com.epam.prejap.oop.war;

import java.util.*;
import java.util.stream.Collectors;

public class Duel {


    List<Integer> cardsForWinner = new ArrayList<>();
    List<Player> duelPlayers;
    List<List<Card>> cardsInGame;
    List<Integer>playedCards;




    Duel(List<Player> allPlayers,List<Player> activePlayers, List<List<Card>> cards,List<Integer> cardsForWinner){
        // check if more than 2 cards
        List<Player> toRemove = new ArrayList<>();
        List<Card> remainingCards = new LinkedList<>();
        for (int i=activePlayers.size()-1;i>=0;i--){
            if (cards.get(i).size()<2){
                remainingCards.addAll( cards.remove(i));
                activePlayers.remove(i);

            }
        }


        /*
        for (Player p: activePlayers){
            if (  cards.get(p.getNumber()-1).size()< 2     ){
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! delete "+p);
                System.out.println(p.getNumber()-1);
                toRemove.add(p);
                //remainingCards.addAll( cards.get(p.getNumber()-1)  );
                System.out.println("To remove: "+toRemove);

                allPlayers.remove(p);

            }// todo zabierz 1 kartę activePlayers i daj do winner
        }
        activePlayers.removeAll(toRemove);
        //cards.removeAll();
        */
    }










}
