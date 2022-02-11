package com.epam.prejap.oop.war;

import com.epam.prejap.oop.screen.ScenarioScreen;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class loads possible scenarios from this directory: "src/main/resources/". Valid scenarios must begin with war_ prefix and have .json extension.
 * List of scenarios is sorted in alphabetical order. If there are no scenario files detected, default scenario will trigger.
 */
public class ScenarioManager {
    List<String> scenarioPaths;
    List<String> scenarioList;

    void prepareScenariosForTheGame(){
        this.scenarioPaths = createListOfScenarios();
        this.scenarioList = prepareScenarioName(scenarioPaths);
        if (scenarioPaths.size()==2 ){    //scenarioPaths.size()>0     scenarioPaths.size()==2  //for testing
            new ScenarioScreen(scenarioPaths);
            for (String path: scenarioPaths){
                int index = scenarioPaths.indexOf(path);
                System.out.println("Scenario: "+scenarioList.get(index)+"\n\n");
                new JSONParser(false).prepareGame(path);
            }
        } else {
            System.out.println("Scenario: Default Scenario\n\n");
            new JSONParser(true).prepareGame("src/main/resources/warTest.json");  //default scenario todo turn shuffle to true after tests
        }
    }

    public List createListOfScenarios() {
        Path path = Paths.get("src/main/resources/");
        DirectoryStream ds;
        ArrayList<String> list = new ArrayList<>();
        {
            try {
                ds = Files.newDirectoryStream(path);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        ds.forEach(i -> list.add(i.toString()));
        filterResourcesForValidScenarios(list);
        java.util.Collections.sort(list); //sorting list in alphabetical order.
        return list;
    }

    void filterResourcesForValidScenarios(List fileNames){
        Pattern p = Pattern.compile("src\\\\main\\\\resources\\\\war_.*\\.json");
        Iterator<String> iter = fileNames.iterator();
        while (iter.hasNext()) {
            String str = iter.next();
            Matcher m = p.matcher(str);
            if (!m.matches()){
                iter.remove();
            }
        }
        this.scenarioPaths = new ArrayList<>();
        for (Object s: fileNames){
            scenarioPaths.add(s.toString());
        }
    }

    static List<String> prepareScenarioName(List<String> list){
        List<String> result = new ArrayList<>();
        for (String s: list){
            String[] sarr = s.split("_");
            sarr[1] = sarr[1].replace(".json","");
            sarr[1] = sarr[1].substring(0,1).toUpperCase() + sarr[1].substring(1);
            result.add(sarr[1]);
        }
        return result;
    }
}
