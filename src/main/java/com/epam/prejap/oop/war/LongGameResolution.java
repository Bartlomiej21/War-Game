package com.epam.prejap.oop.war;

import java.util.*;

public class LongGameResolution {

    byte winner;

    LongGameResolution(List<Player> players, List<List<Card>> cards){

        this.winner = findWinner(cards,players);
        //System.out.println("Winner "+winner);



    }


    byte findWinner(List<List<Card>> cards, List<Player> players) {
        List<Byte> winnerCondition1 = new ArrayList<>();
        List<Byte> winnerCondition2 = new ArrayList<>();
        List<List<Integer>> cardsValues = new ArrayList<>();
        int max = 0;
        short size = 0;

        for (Player p : players) {
            List<Integer> childList = new ArrayList<>();
            int playerIndex = p.getNumber()-1;
            for (int i = 0; i < cards.get(playerIndex).size(); i++) {
                childList.add(cards.get(playerIndex).get(i).cardValue);
            }

            cardsValues.add(childList);

            Integer maxTemp = Collections.max(childList);
               if (maxTemp > max) {
                   winnerCondition2.clear();
                   winnerCondition2.add((byte) (playerIndex+1));
                   max = maxTemp;
               } else if (maxTemp.equals(max)) winnerCondition2.add((byte) (playerIndex+1));

               short sizeTemp = (short) cards.get(playerIndex).size();

                if (sizeTemp>size){
                    winnerCondition1.clear();
                    winnerCondition1.add((byte) (playerIndex+1));
                    size=sizeTemp;
                }

            }

        //System.out.println(winnerCondition1);
        //System.out.println(winnerCondition2);
        winnerCondition1.retainAll(winnerCondition2);
        System.out.println("Remaining cards: "+cardsValues);
        if (winnerCondition1.size()==1) return winnerCondition1.get(0);

            return 0;

        }

}