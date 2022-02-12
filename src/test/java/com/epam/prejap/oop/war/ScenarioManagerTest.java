package com.epam.prejap.oop.war;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.testng.Assert.*;

public class ScenarioManagerTest {

    List<String> fileNames;

    @DataProvider(name="ScenarioInfo")
    Object[][] provideScenarios(){
        return new Object[][]{
                {"src\\main\\resources\\war_deadSimpleGame.json","DeadSimpleGame"},
                {"src\\main\\resources\\war_illegalSyntax.json","IllegalSyntax"},
                {"src\\main\\resources\\war_infiniteGame.json","InfiniteGame"}
        };
    }

    @BeforeMethod
    public void setUp() {
        fileNames = new ArrayList<>(List.of("src\\main\\resources\\any",
                "src\\main\\resources\\validAndLegalJSON.json",
                "src\\main\\resources\\war.json","src\\main\\resources\\war_deadSimpleGame.json",
                "src\\main\\resources\\war_illegalSyntax.json","src\\main\\resources\\war_infiniteGame.json"));
    }

    @Test
    public void testCreateListOfScenarios() {
        SoftAssert softAssertion = new SoftAssert();
        Path path = Paths.get("src/main/resources/");
        DirectoryStream ds = null;
        ArrayList<String> list = new ArrayList<>();
        {
            try {
                ds = Files.newDirectoryStream(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ds.forEach(i -> list.add(i.toString()));
        //There are currently 10 files in src/main/resources/
        softAssertion.assertEquals(list.size(),10);
        java.util.Collections.sort(list);
        softAssertion.assertEquals(list.get(0),"src\\main\\resources\\.gitkeep");
        softAssertion.assertEquals(list.get(list.size()-1),"src\\main\\resources\\war_infiniteGame.json");
        softAssertion.assertAll();
    }

    @Test
    public void testFilterResourcesForValidScenarios() {
        Pattern p = Pattern.compile("src\\\\main\\\\resources\\\\war_.*\\.json");
        Iterator<String> iter = fileNames.iterator();
        while (iter.hasNext()) {
            String str = iter.next();
            Matcher m = p.matcher(str);
            if (!m.matches()){
                iter.remove();
            }
        }
        assertEquals(fileNames.size(),3);
    }

    @Test
    public void testPrepareScenariosForTheGame() {
        List<String> scenarioPaths = List.of("src\\main\\resources\\war_deadSimpleGame.json",
                "src\\\\main\\\\resources\\\\war_illegalSyntax.json\\",
                "src\\main\\resources\\war_infiniteGame.json");
        if (scenarioPaths.size()>0){
            System.out.println("There are valid scenarios.");
        }
    }

    @Test (dataProvider="ScenarioInfo")
    public void testPrepareScenarioName(String filePath, String result) {
        String[] sarr = filePath.split("_");
        sarr[1] = sarr[1].replace(".json","");
        sarr[1] = sarr[1].substring(0,1).toUpperCase() + sarr[1].substring(1);
        assertEquals(sarr[1],result);
    }
}
