package com.efimchick.ifmo.streams.countwords;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Words {

    public String countWords(List<String> lines) {

        Map<String, Long> map = lines.stream()
                .map(s -> s.replaceAll("[^\\p{IsAlphabetic}]", " "))
                .flatMap((String s) -> Arrays.asList(s.split("\\s+")).stream())

                .map(word -> word.toLowerCase())
                .filter(word -> word.length() > 3)

                .collect(Collectors.groupingBy(f -> f, Collectors.counting()));

        //System.out.println(map);

        return map.entrySet()
                .stream()

                .filter(e -> e.getValue() >= 10)
                .sorted((e1, e2) ->
                e1.getValue().equals(e2.getValue()) ? e1.getKey().compareTo(e2.getKey())
                        : -1 * e1.getValue().compareTo(e2.getValue())
                )

                .map(e -> e.getKey() + " - " + e.getValue())
                .collect(Collectors.joining("\n"));
    }
}
