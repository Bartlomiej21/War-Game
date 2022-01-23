package com.epam.prejap.oop.war;

import java.io.FileFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


public class FilePath {



    public  void searchForScenarios() {
        Path path = Paths.get("/src/main/resources/");
        DirectoryStream ds = null;

    {
        try {
            ds = Files.newDirectoryStream(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    ArrayList<String> list = new ArrayList<>();
    ds.forEach(i -> list.add(i.toString()));
        System.out.println(list);

    }
}

/*
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

            // new solution below (second problem)
            //String[] result = fileNames.get(0).split("_");
            //System.out.println(result[1]);

            //a = a.replace("{bbbbbb}", "");

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

 */