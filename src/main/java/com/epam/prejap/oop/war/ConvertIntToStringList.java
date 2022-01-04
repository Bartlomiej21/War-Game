package com.epam.prejap.oop.war;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConvertIntToStringList {

    List stringList;

    ConvertIntToStringList(List playersInt) {
       this.stringList = convertIntListToStringList(
                playersInt,
                s -> String.valueOf(s));
        //System.out.println("PlayersList: "+playersString);


    }

    public static <T, U> List<U>
    convertIntListToStringList(List<T> listOfInteger,
                               Function<T, U> function)
    {
        return listOfInteger.stream()
                .map(function)
                .collect(Collectors.toList());

    }
}
