package com.epam.prejap.oop.war;


public class Main {

    public static void main(String[] args) {
        String json = """
                [
                [3],
                [2],
                ]""";

        JSONParser jparse = new JSONParser(json);
        new GameScreen(jparse.nrOfPlayers, jparse.cardNumber,jparse.cards);


        //System.out.println("Deck in a form of Linked List:  "+jparse.cards); //Deck in a form of Linked List:  [[3], [2]]
        //System.out.println(jparse.nrOfPlayers);  // 2
        //System.out.println("p1: "+jparse.cards.get(0)+"\np2: "+jparse.cards.get(1));


        //p1: 3
        //p2: 2
    }

}
