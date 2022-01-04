package com.epam.prejap.oop.war;


public class Main {

    public static void main(String[] args) {
        /*
        String json = """
                [
                [3],
                [2],
                ]""";


         */

        String json = """
                [
                [11,2,6,7],
                [5,6,13]
                ]""";

        /*
        String json = """
                [
                [11,2,6,7],
                [2,14,15,8],
                [5,6,13]
                ]""";


         */
        new JSONParser(json);


        //JSONParser jparse = new JSONParser(json);
        //new GameScreen(jparse.NR_OF_PLAYERS, JSONParser.cardNumber,jparse.cards);

        //p1: 3
        //p2: 2
    }

}
