package com.epam.prejap.oop.war;

import org.json.JSONArray;
import org.json.JSONException;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static org.testng.Assert.*;

public class JSONParserTest {

String json;
String jsonCorrect = "[[9,9,10,14,11,10],[9,9,11,11,13,14],[14,12,12,13,13,13]]";

    @Test
    public void testIfJSONStringParsedFromFileIsCorrect() throws FileNotFoundException {
        json  = "";
        ArrayList<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/war.json"))) {
            while (br.ready()) {
                result.add(br.readLine());
                json = br.lines().collect(Collectors.joining());
                json = "[" + json;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(json, jsonCorrect);



    }

    @Test
    public void IsJSONArrayAbleToCreateObjectFromGivenString() {
        new JSONArray(jsonCorrect);
        System.out.println("Possible");
    }

}