package com.epam.prejap.oop.war;

import java.util.List;
import java.util.LinkedList;

public class GameScreen {


    GameScreen (int nrPlayers, int nrOfCards,List allCards){
        StringBuilder sb = new StringBuilder();
        String line = "WAR, "+nrPlayers+" players, ? deck, "+nrOfCards+" cards\n";
        sb.append(line);
        String line2 = "Player1 played: "+String.valueOf(allCards.get(0)).replace("[","").replace("]","")+"\n";
        sb.append(line2);
        String line3 = "Player2 played: "+String.valueOf(allCards.get(1)).replace("[","").replace("]","")+"\n";
        sb.append(line3);
        String separator = "############";
        sb.append(separator);
        System.out.println(sb);

    }



    /*
    WAR, 2 players, ? deck, 2 cards
    Player1 played: 3
    Player2 played: 2
    SEPARATOR
    */

}
