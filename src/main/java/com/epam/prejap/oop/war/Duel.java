package com.epam.prejap.oop.war;

import com.epam.prejap.oop.screen.DuelScreen;

import java.util.List;
import java.util.stream.Collectors;

public class Duel {

    byte winner;
    Cards duelCards;
    List<Player> duelPlayers;

    Duel(List<Player> activePlayers, Cards playedCards){

    }
    Duel(List<Player> activePlayers){
        this.duelCards = new Cards();
        this.duelPlayers = activePlayers.stream().collect(Collectors.toList());
    }

    void createDuelMessage(List<Player> activePlayers, Cards playedCards){
        int index = 0;
        for (Player p: activePlayers){
            String duelString = p.toString()+" played: "+ playedCards.getCards().get(index).cardValue;
            p.setDuelMessage(duelString);
            index++;
        }
    }

    public void addToDuelMessage(List<Player> activePlayers, Cards playedCards, String concat) {
        int index = 0;
        for (Player p: activePlayers){
            String duelString = p.getDuelMessage();
            duelString = duelString + concat;
            p.setDuelMessage(duelString);
            index++;
            System.out.println(p.getDuelMessage());
        }
    }

    void moveCardsFromPlayedCardsToPlayerDuelCards(List<Player> activePlayers, Cards playedCards){
        //System.out.println("Size of played Cards initially : "+playedCards.getCards().size());
        for (Player p: activePlayers){
            System.out.println("Card removed: "+ playedCards.getCards().get(0).cardValue);
            //int removedCard = playedCards.getCards().remove(c).getCardValue();
            p.getDuelCards().getCards().add( playedCards.getCards().remove(0));  //since removing, always first card
            //System.out.println(p.getDuelCards().getCards().size());

        }
        //playedCards should be empty after above
        System.out.println("This should be always 0: "+playedCards.getCards().size());
    }



    byte resolveDuel(Players allPlayers, List<Player> activePlayers, Cards cardsForWinner){

        //activePlayers.removeIf(i -> cards.get(i.getNumber()-1).getCards().size()<1);  // HERE
        activePlayers.removeIf(i ->  i.getPlayersCards().getCards().size()<1);  // HERE

        switch (activePlayers.size()) {
            case 1:
                winner = activePlayers.get(0).getNumber();
                break;
            case 0:   // in case it ends in a draw and there are no players with any cards left
                System.out.println("The game has ended in a draw!");
                //new DuelScreen(activePlayers);
                //System.exit(0);  //todo this is a situation without end
                break;
            default:
                winner = new Clash(allPlayers,(short)18).resolveClash(allPlayers,activePlayers,cardsForWinner);  //todo poprawić
        }
        return winner;
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

   /*
                System.out.println("Playerd cards "+playedCards.getCards().size());
                //message
                for (int i = 0; i < playedCards.size(); i++) {
                    String duelMessage = activePlayers.get(i).toString() + " played " + playedCards.getCards().get(i).cardValue;
                    System.out.println(duelMessage);
                }

                int index = 0;
                for (Player p: activePlayers){
                    if (p.getPlayersCards().getCards().size()<1){
                        System.out.println("----------> Eoc");
                        activePlayers.remove(p);
                    }
                }

                 */
