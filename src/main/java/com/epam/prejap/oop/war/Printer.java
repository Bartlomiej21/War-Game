package com.epam.prejap.oop.war;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class logs error to the file. Each file is formatted such that it's name contain the moment of logging.
 */
class Printer {

    private static Logger logger = LogManager.getFormatterLogger(Printer.class);
    Exception e;
    byte numberOfPlayers;
    short totalNrOfCards;
    byte winnerNr;
    short roundsCount;

    Printer(Exception e){
        this.e = e;
        logger.error("Exception occurred: "+e);
    }

    Printer (byte nrOfPlayers, short nrOfCards, byte winnerNr, short roundsCount){
        this.numberOfPlayers = nrOfPlayers;
        this.totalNrOfCards = nrOfCards;
        this.winnerNr = winnerNr;
        this.roundsCount = roundsCount;
    }

    public Printer(byte winner, short round) {
        this.winnerNr = winner;
        this.roundsCount = round;
    }

    void printError(String error){
        logger.error("App has to end prematurely because: "+error);
    }

    void printException(Exception e){
        logger.error("Exception occurred: "+e);
    }

    void printMsgBeforeGame(){
        logger.debug("Number of players: %d, Size of deck: %d",numberOfPlayers,totalNrOfCards);
    }
    
    void printMsgAfterGame(){
        if (winnerNr==0){
            logger.debug("This time no player have achieved victory.");
        } else {
            logger.debug("Player %d won the game! It took %d rounds.",winnerNr,roundsCount);
        }
    }
}
