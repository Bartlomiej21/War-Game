package com.epam.prejap.oop.war;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.prejap.oop.screen.DuelScreen;

public class Main {

    public static void main(String[] args) {
        new JSONParser().prepareGame();
        //new FilePath();
    }
}


/*

todo 1. wyplenic static
2. w Duel zrobić 2 tury?
3. zaimplementować te elementy

End of round 179

Cards remaining in player1 hands:
14 9 13 11
Cards remaining in player2 hands:

Cards remaining in player3 hands:
11 14 11 12 10 13 10 13 9 13 9 12 9 14

WAR, 3 players, ? deck, 18 cards
Player1 played: 14
Player3 played: 11
=========


End of round 180


=========

Stopping the game due to 180 battles without resolution.
Winner is the owner of the highest card AND the owner of the greatest amount of cards: player3.




 */