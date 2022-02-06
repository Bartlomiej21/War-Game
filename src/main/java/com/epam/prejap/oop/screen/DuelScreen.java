package com.epam.prejap.oop.screen;

import com.epam.prejap.oop.war.Player;
import java.util.List;

import java.util.Random;

public class DuelScreen implements Screen {
    String message;

    public DuelScreen(List<Player> players){
        getMessage(players);
        showMessage();
    }

    public void showMessage(){
        System.out.println(message);
    }

    public void getMessage(List<Player> players){
        message = String.format("""
        Duel between players will be decided by a coin toss.
        Tossing... player%d has won!
    """,players.get(coinToss()).getNumber());
        message = message + separator;
    }

    byte coinToss(){
        Random rand = new Random();
        int randomNr = rand.nextBoolean() ? 1 : 0;
        return (byte) randomNr;
    }

}

/*
WAR, 3 players, SMALL deck, turn #122 - DRAW with 8/24 cards
================================================================
                 WE HAVE A WAR LADIES AND GENTLEMEN!
================================================================
Player1 played: 9 ? 12 ? 10 ? 13 ? EoC
Player2 played: 9 ? 12 ? 10 ? 13 ? EoC
Player3 played: 9 ? 12 ? 10 ? 13 ? -----------> WINNER FOUND!

War lasted 5 rounds.
SEPARATOR

 */
