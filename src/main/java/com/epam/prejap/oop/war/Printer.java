package com.epam.prejap.oop.war;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class Printer {


    private static Logger logger = LogManager.getFormatterLogger(Printer.class);

    Printer(String error){
        logger.error("App has to end prematurely because: "+error);

    }

    Printer(Exception e){
        logger.error("Exception occurred: "+e);
    }

    Printer (){
        logger.debug("Number of players: %d, Size of deck: %d",Players.numberOfPlayers,PlayersCards.totalNrOfCards);
    }

    Printer (byte winnerNr, short roundsCount){
        logger.debug("Player %d won the game! It took %d rounds.",winnerNr,roundsCount);
    }




}
