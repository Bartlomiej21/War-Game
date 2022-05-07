package com.epam.prejap.oop.war;

/**
 * @author Bartlomiej Slominski
 *
 * This is a main class running a game.
 * Use new JSONParser(false).prepareGame("src/main/resources/warTest.json");
 * to start a game with example scenario, or you can substitute it for any valid JSON file.
 * Switch boolean to true to shuffle cards.
 */

public class Main {
    public static void main(String[] args) {
        new ScenarioManager().prepareScenariosForTheGame();
    }
}
