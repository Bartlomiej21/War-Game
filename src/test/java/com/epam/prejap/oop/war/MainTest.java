package com.epam.prejap.oop.war;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MainTest {

    @Test
    public void testMain() {
        new JSONParser(false).prepareGame("src/main/resources/warTest.json");
    }
}
