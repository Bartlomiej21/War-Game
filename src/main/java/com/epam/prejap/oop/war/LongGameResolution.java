package com.epam.prejap.oop.war;

import java.util.*;

public class LongGameResolution {

    byte winner;

    LongGameResolution(Players players){
        this.winner = findWinner(players);
    }

    byte findWinner(Players players) {
        List<Byte> winnerCondition1 = new ArrayList<>();
        List<Byte> winnerCondition2 = new ArrayList<>();
        List<List<Integer>> cardsValues = new ArrayList<>();
        int max = 0;
        short size = 0;

        for (Player p : players) {
            List<Integer> childList = new ArrayList<>();
            int playerIndex = p.getNumber()-1;
            for (int i = 0; i < p.getPlayersCards().getCards().size(); i++) {    //ch
                childList.add(p.getPlayersCards().getCards().get(i).cardValue);  //ch
            }

            cardsValues.add(childList);

            Integer maxTemp = Collections.max(childList);
               if (maxTemp > max) {
                   winnerCondition2.clear();
                   winnerCondition2.add((byte) (playerIndex+1));
                   max = maxTemp;
               } else if (maxTemp.equals(max)) winnerCondition2.add((byte) (playerIndex+1));

               short sizeTemp = (short) p.getPlayersCards().getCards().size();  // ch

                if (sizeTemp>size){
                    winnerCondition1.clear();
                    winnerCondition1.add((byte) (playerIndex+1));
                    size=sizeTemp;
                } else if (sizeTemp == size) winnerCondition1.add((byte) (playerIndex+1));

            }

        winnerCondition1.retainAll(winnerCondition2);
        //System.out.println("Remaining cards: "+cardsValues);
        if (winnerCondition1.size()==1) return winnerCondition1.get(0);

            return 0;

        }

}
