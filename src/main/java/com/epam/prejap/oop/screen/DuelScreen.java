package com.epam.prejap.oop.screen;


public class DuelScreen implements Screen {

    String message;

    public DuelScreen(){


    }

    public void showMessage(){
        message = String.format("""
        Duel between players will be decided by a coin toss.
        Tossing... player%d has won!
    """,coinToss());
        message = message + separator;
    }

    boolean coinToss(){
        //int myInt = myBoolean ? 1 : 0;
        return true;
    }

}


/*
     Duel between players will be decided by a coin toss.
        Tossing... player1 has won!
        SEPARATOR
*/