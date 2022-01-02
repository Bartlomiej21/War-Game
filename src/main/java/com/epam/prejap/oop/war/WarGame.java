package com.epam.prejap.oop.war;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.function.*;

public class WarGame{

    public static <T, U> List<U>
    convertIntListToStringList(List<T> listOfInteger,
                               Function<T, U> function)
    {
        return listOfInteger.stream()
                .map(function)
                .collect(Collectors.toList());

    }


    public static void main(String args[]) {

        List<List<Integer>> cards = new LinkedList<>();
        List<Integer> child1= new LinkedList<>();
        List<Integer> child2= new LinkedList<>();
        List<Integer> child3= new LinkedList<>();

        child1.add(3); child1.add(4); child1.add(5); child1.add(6);
        child2.add(2); child2.add(14); child2.add(15); child2.add(16);
        child3.add(3); child3.add(8); //child3.add(12); child3.add(8);
        cards.add(child1);
        cards.add(child2);
        cards.add(child3);


        List<Integer> players = IntStream.rangeClosed(1,3).boxed().collect(Collectors.toList());
        List<String> playersString = convertIntListToStringList(
                players,
                s -> String.valueOf(s));

        List<Integer> pool = new LinkedList<>();

        //IntStream.of(A).boxed().collect(Collectors.toList());


        System.out.println("Cards:  "+cards);

        //System.out.println(playersString.get(1));
        //System.out.println(cards.get(1).get(2));
        //int x = Integer.parseInt(playersString.get(0));
        // ##############################################################################
        int i =1;
        while (i!=0){

            for (String s: playersString){  //remember that all inactive players must be deleted or this will throw error if 0 cards remain
                //System.out.println(s);

                pool.add(cards.get( Integer.parseInt(s)-1).remove(0) );   //removing first card from player and givint it to a pool

                //pool.add(cards.get((Integer.parseInt(s))-1) .get(0));  // add first card from every player
                //System.out.println("Before removing");
                //cards.remove(0).remove(0);
                //System.out.println(cards);
                //System.out.println(   (cards.get(Integer.parseInt(s)-1)).remove(0)   );    //.removeFirst();


                //cards.get( Integer.parseInt(s)-1).remove(0);
                //cards.get(Integer.parseInt(s)-1)).remove(0);



                System.out.println(cards);

                System.out.println(pool);
                System.out.println(s);
            }

            List<String> duelPlayers = new LinkedList<>();
            Integer max = Collections.max(pool);
            int occurrences = Collections.frequency(pool, max);
            System.out.println("Max: "+max+"   Times: "+occurrences);

            //(occurences==1) ? System.out.println("Resolution") : System.out.println("Duel");

            //###################################   DUEL
            for (int j=0; j<pool.size();j++){
                if (pool.get(j).equals(max)){
                    duelPlayers.add(playersString.get(j));
                }
            }

            System.out.println("Duel Players: "+duelPlayers);



            //#################################  check if has at least two cards

            // tutaj powinno być while chyba
            for (String s: duelPlayers) {
                if (cards.get(Integer.parseInt(s)-1).size()>1) {
                    System.out.println("Wieksze od zeraz");
                }
                else {
                    System.out.println("Mniejsze od zera dla gracza nr "+s);
                    pool.addAll(cards.get( Integer.parseInt(s)-1 ) );
                    cards.get(Integer.parseInt(s)-1).clear();  // Usuwamy karty gracza z cards
                    duelPlayers.remove(s);
                    playersString.remove(s);
                    System.out.println("Players: "+playersString+"  Player"+s+" was removed.");
                }
            }

            System.out.println("Cards: "+cards);
            System.out.println("Pool: "+pool);



            if (duelPlayers.size()>1){
                System.out.println("tu jeszcze działa");
                for (String s: duelPlayers){
                    pool.add(cards.get( Integer.parseInt(s)-1).remove(0));
                }
            }




            // ###### wyzerować listy
            pool.clear();
            duelPlayers.clear();
            i--;
        }






    }
}
