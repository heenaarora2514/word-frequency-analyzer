package com.java.project.wordfrequencyanalyzer.testutils;

import com.java.project.wordfrequencyanalyzer.service.WordFrequency;
import com.java.project.wordfrequencyanalyzer.service.impl.WordFrequencyImpl;

import java.util.List;

public class TestUtils {
    private TestUtils() {

    }

    public static List<WordFrequency> getWordFrequencyList() {
        WordFrequencyImpl wordFrequency = WordFrequencyImpl.builder().word("the").frequency(2).build();
        return List.of(wordFrequency);
    }


    public static List<WordFrequency> getMostFrequentNWordsList() {
        WordFrequencyImpl wordFrequency1 = WordFrequencyImpl.builder().word("of").frequency(3).build();
        WordFrequencyImpl wordFrequency2 = WordFrequencyImpl.builder().word("books").frequency(2).build();
        WordFrequencyImpl wordFrequency3 = WordFrequencyImpl.builder().word("in").frequency(2).build();
        return List.of(wordFrequency1, wordFrequency2, wordFrequency3);
    }
}
