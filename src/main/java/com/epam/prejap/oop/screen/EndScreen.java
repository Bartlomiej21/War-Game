package com.epam.prejap.oop.screen;

import com.epam.prejap.oop.war.Players;

public class EndScreen implements Screen {

    String message;

    public EndScreen(byte winner, short size, short totalNrOfCards){
        //message = separator+ String.format("WAR, %d players, ? deck, player %d WON with %d/%d cards",Players.numberOfPlayers,winner,size,totalNrOfCards);
        message = separator+ String.format("WAR, %d players, ? deck, player %d WON with %d/%d cards",3,winner,size,totalNrOfCards);
        showMessage();
    }

    public EndScreen(short rounds, byte winner){

        message = separator+chooseMessage(winner,rounds);
        showMessage();
        //System.exit(0);
    }

    public void showMessage(){
        System.out.println(message+separator);
    }

    String chooseMessage(byte winner,short rounds){
        switch (winner) {
            case 0:
                message = String.format("Stopping the game due to %d battles without resolution.\n" +
                        "Unfortunately, winner could not be established this time, because there was no player " +
                        "with the highest card and the greatest amount of cards.",rounds);
                        break;

            default:
                message = String.format("Stopping the game due to %d battles without resolution.\n" +
                        "Winner is the owner of the highest card AND the owner of the greatest amount of " +
                        "cards: player%d.",rounds,winner);


        }
        return message;
    }


}


/*
SEPARATOR
WAR, 2 players, ? deck, player1 WON with 2/2 cards
SEPARATOR


 */
