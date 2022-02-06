package com.epam.prejap.oop.war;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.prejap.oop.screen.DuelScreen;

public class Main {

    public static void main(String[] args) {
        //new JSONParser().prepareGame();   //use this to start game with 1 one chosen scenario
        new ScenarioManager().prepareScenariosForTheGame();  //use this to start game with multiple scenarios chosen from a directory
    }
}
