package com.epam.prejap.oop.screen;

import com.epam.prejap.oop.war.GameInfo;

public class EndScreen implements Screen {

    String message;
    String deck = GameInfo.INSTANCE.getDeck();

    public EndScreen(byte nrOfPlayers, byte winner, short size, short totalNrOfCards){
        message = separator+ String.format("WAR, %d players, %s deck, player%d WON with %d/%d cards",nrOfPlayers, deck,winner,size,totalNrOfCards);
        showMessage();
    }

    public EndScreen(short rounds, byte winner){
        message = separator+chooseMessage(winner,rounds);
        showMessage();
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
