package com.epam.prejap.oop.war;

import java.util.Random;

public class RandomNumber {

    Random rand = new Random();

    RandomNumber(byte nrOfPlayersInADuel){

        int n = rand.nextInt(nrOfPlayersInADuel);
    }

}
