package com.epam.prejap.oop.war;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class deals with a problem of an infinite game.
 * If the number of rounds reaches [10 * number of cards], it's time to conclude game.
 * Winning is given to a player who has most cards and has the highest card.
 * If these conditions are not enough to select winner, it's a draw.
 */
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
            int playerIndex = p.getNumber();
            for (int i = 0; i < p.getPlayersCards().getCards().size(); i++) {
                childList.add(p.getPlayersCards().getCards().get(i).getCardValue());
            }
            cardsValues.add(childList);
            Integer maxTemp = Collections.max(childList);
               if (maxTemp > max) {
                   winnerCondition2.clear();
                   winnerCondition2.add((byte) (playerIndex));
                   max = maxTemp;
               } else if (maxTemp.equals(max)) winnerCondition2.add((byte) (playerIndex));
               short sizeTemp = (short) p.getPlayersCards().getCards().size();  // ch
                if (sizeTemp>size){
                    winnerCondition1.clear();
                    winnerCondition1.add((byte) (playerIndex));
                    size=sizeTemp;
                } else if (sizeTemp == size) winnerCondition1.add((byte) (playerIndex));
            }
        winnerCondition1.retainAll(winnerCondition2);
        if (winnerCondition1.size()==1) return winnerCondition1.get(0);
            return 0;
        }
}
