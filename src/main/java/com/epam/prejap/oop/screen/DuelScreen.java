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
     Duel between players will be decided by a coin toss.
        Tossing... player1 has won!
        SEPARATOR
*/