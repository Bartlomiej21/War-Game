package com.epam.prejap.oop.war;

public class TextScreen {

    static String screen = """
            WAR, 2 players, ? deck, 2 cards
            Player1 played: %d
            Player2 played: %d
            %s
                        
            """;

    public static void main(String[] args) {
        String separator = "########";
        System.out.format(screen,2,3,separator);
    }
}
