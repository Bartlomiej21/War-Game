package com.epam.prejap.oop.screen;

import com.epam.prejap.oop.war.Card;
import com.epam.prejap.oop.war.Player;
import com.epam.prejap.oop.war.Players;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DealingScreen implements Screen {
    String message;
    Players players;
    short totalCards;
    String luckyPlayers;
    boolean luckyPlayersPresent;

    public DealingScreen(Players players,short totalCards) {
        this.players = players;
        this.totalCards = totalCards;
        if (totalCards%players.getPlayers().size()!=0) {
            luckyPlayersPresent=true;
            luckyPlayers = "Lucky players:";
        }
        this.message = getMessage();
    }

    public String getMessage() {
        String message = String.format("WAR, %d players, ? deck, DEALING", players.getPlayers().size());
        int max = players.getPlayers().get(0).getPlayersCards().getCards().size();
        for (Player p: players.getPlayers()){
            message = message.concat("\nPlayer"+p.getNumber()+" hand: ");
            List<Integer> cards = new ArrayList<>();
            for (Card c: p.getPlayersCards().getCards()){
                cards.add(c.getCardValue());
            }
            if (luckyPlayersPresent){
                if(cards.size()==max) luckyPlayers = luckyPlayers.concat(" player"+p.getNumber()+",");
            }
            Collections.sort(cards);
            Collections.reverse(cards);
            message = message.concat(cards.toString());
        }
        if (luckyPlayersPresent) {
            message = message.concat("\n\n"+luckyPlayers);
            message = message.substring(0, message.length()-1)+".";
        }
        return message+separator;
    }

    public void showMessage(){
        System.out.println(message);
    }
}
