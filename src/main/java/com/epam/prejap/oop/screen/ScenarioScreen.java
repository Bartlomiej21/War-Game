package com.epam.prejap.oop.screen;

import java.util.List;

/**
 * This class shows possible scenarios.
 * Keep in mind that presented json files do not need to be valid, just need a valid name. JSONParser deals with checking if JSON is proper.
 */
public class ScenarioScreen implements Screen {
    String message;

    public ScenarioScreen(List<String> fileNames){
        getMessage(fileNames);
        showMessage();
    }

    public void getMessage(List<String> fileNames){
        message = "Found "+fileNames.size()+" scenario files:";
        for (String s: fileNames){
            message = message+"\n"+s.substring(s.lastIndexOf("\\")+1);
        }
        message = message+"\n\nExecuting in given order."+separator;
    }

    public void showMessage() {
        System.out.println(message);
    }
}
