package com.epam.prejap.oop.war;

import java.util.*;
import java.util.regex.*;


public class ScenarioManager {

    public static void main(String args[]) {
        List<String> fileNames = new ArrayList<>();
        fileNames.add("war_deadSimpleGame.json");
        fileNames.add("any");
        fileNames.add("validAndLegalJSON.json");
        fileNames.add("war_illegalSyntax.json");
        fileNames.add("war_infiniteGame.json");
        System.out.println(fileNames);

    Pattern p = Pattern.compile("war.*\\.json");

    Iterator<String> iter = fileNames.iterator();
     while (iter.hasNext()) {
        String str = iter.next();
        Matcher m = p.matcher(str);
        if (!m.matches()){
            iter.remove();
        }
    }

            System.out.println(fileNames);

    fileNames = prepareName(fileNames);
            System.out.println(fileNames);



    // Scenario: DeadSimpleGame
    // Scenario: InfiniteGame

}


    static List<String> prepareName(List<String> list){
        List<String> result = new ArrayList<>();
        for (String s: list){
            String[] sarr = s.split("_");
            sarr[1] = sarr[1].replace(".json","");
            sarr[1] = sarr[1].substring(0,1).toUpperCase() + sarr[1].substring(1);
            result.add(sarr[1]);

            //String cap = str.substring(0, 1).toUpperCase() + str.substring(1);


        }
        return result;
    }

}
