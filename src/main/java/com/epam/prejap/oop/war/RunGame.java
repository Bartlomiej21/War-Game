package com.epam.prejap.oop.war;

import com.epam.prejap.oop.screen.EndScreen;

/**
 * This class runs loops of the game. It's supposed to end game either as a draw, clear winner or forced winner conclusion.
 */
public class RunGame  {
    Players playersInGame;
    short totalNrOfCards;
    byte nrOfPlayers;

    RunGame(Players playersList, short totalNrOfCards) {
        this.playersInGame = playersList;
        this.totalNrOfCards = totalNrOfCards;
        this.nrOfPlayers = (byte) playersInGame.getPlayers().size();
    }

    void playGame() {
        System.out.println("Welcome to the game of WAR!");
        new Printer(nrOfPlayers,totalNrOfCards,(byte)0,(byte)0).printMsgBeforeGame();
        Cards cardsForWinner = new Cards();
        short round = 0;
        short gameLimit = (short) (totalNrOfCards*10);
        //short gameLimit = 10;  // for short tests
        while (playersInGame.getPlayers().size()>1 && round<gameLimit){
            showCards();    //SHOWS player cards
            Clash clash = new Clash(playersInGame,totalNrOfCards,round);  //todo add stuff
            clash.playedCards= Clash.createListOfPlayedCards(clash.activePlayers);
            clash.printGameScreen(clash.activePlayers, totalNrOfCards,clash.playedCards);
            clash.resolveClash(clash.activePlayers, cardsForWinner);

            clash.addCardsToWinner(playersInGame, cardsForWinner, clash.winner);  //todo there is always a winner, even a default one
            playersInGame.getPlayers().removeIf(i -> i.getPlayersCards().getCards().size()<1);
            cardsForWinner.getCards().clear();
            round++;
            System.out.println("End of round "+round+"\n");
            }
        boolean gameFinishedEarly = (gameLimit==round);
        if (gameFinishedEarly){
            LongGameResolution lgs = new LongGameResolution(playersInGame);
            new Printer(nrOfPlayers,totalNrOfCards,lgs.winner,round);
            new EndScreen(round, lgs.winner);
        } else {
            byte winner = playersInGame.getPlayers().get(0).getNumber();
            short size = (short) playersInGame.getPlayers().get(0).getPlayersCards().getCards().size();
            //short size = 18;
            new Printer(winner, round);
            new EndScreen(winner, size, totalNrOfCards);
        }
    }

    public void showCards(){
        for (Player p: playersInGame) {
            System.out.println("Cards remaining in player"+(p.getNumber())+" hands: ");
            for (Card c: p.getPlayersCards().getCards()) {
                System.out.print(c.cardValue+" ");
            }
            System.out.println("");
        }
        System.out.println("");
    }
}


