package com.epam.prejap.oop.war;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class Printer {


    private static Logger logger = LogManager.getFormatterLogger(Printer.class);

    String error;
    Exception e;
    byte numberOfPlayers;
    short totalNrOfCards;
    byte winnerNr;
    short roundsCount;

    Printer(String error){
        //logger.error("App has to end prematurely because: "+error);
        this.error = error;
    }

    Printer(Exception e){
        this.e = e;
        //logger.error("Exception occurred: "+e);
    }


    //logger.debug("Number of players: %d, Size of deck: %d",Players.numberOfPlayers,PlayersCards.totalNrOfCards);
    
    Printer (byte nrOfPlayers, short nrOfCards, byte winnerNr, short roundsCount){
        //logger.debug("Player %d won the game! It took %d rounds.",winnerNr,roundsCount);
        this.numberOfPlayers = nrOfPlayers;
        this.totalNrOfCards = nrOfCards;
        this.winnerNr = winnerNr;
        this.roundsCount = roundsCount;
        
    }

    public Printer(byte winner, short round) {
        this.winnerNr = winner;
        this.roundsCount = round;
    }


//todo methods instead of constructor

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
        logger.debug("Player %d won the game! It took %d rounds.",winnerNr,roundsCount);
    }


}
