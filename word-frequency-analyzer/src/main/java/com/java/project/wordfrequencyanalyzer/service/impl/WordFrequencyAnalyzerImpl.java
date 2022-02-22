package com.java.project.wordfrequencyanalyzer.service.impl;

import com.java.project.wordfrequencyanalyzer.exception.DataNotFoundException;
import com.java.project.wordfrequencyanalyzer.service.WordFrequency;
import com.java.project.wordfrequencyanalyzer.service.WordFrequencyAnalyzer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * WordFrequencyAnalyzerImpl
 * <br>
 * <code>WordFrequencyAnalyzerImpl</code>
 * <br>
 *
 * @author heena.arora
 */

@Service
@Slf4j
public class WordFrequencyAnalyzerImpl implements WordFrequencyAnalyzer {

    private static final String STARTING_METHOD_REFERENCE = "Starting method {}";
    private static final String ENDING_METHOD_REFERENCE = "Ending method {}";

    /**
     * Method to retrieve the frequency of the most occurring word in a given sentence
     *
     * @param text required text in which we need to find the frequency of the most occurring word
     * @return the integer value of the most occurring word in the given sentence
     */
    @Override
    public int calculateHighestFrequency(String text) {
        var methodName = "calculateHighestFrequency";
        log.info(STARTING_METHOD_REFERENCE, methodName);

        var listOfWords = getListOfWords(text);

        if (listOfWords.isEmpty()) {
            throw new DataNotFoundException("There are no alphabetical words present in the given text");
        }

        var result = listOfWords.stream().collect(
                Collectors.groupingBy(String::toLowerCase,
                        Collectors.counting()));

        var max = result.entrySet().stream().max(Map.Entry.comparingByValue()).orElseThrow();

        log.info(ENDING_METHOD_REFERENCE, methodName);
        return max.getValue().intValue();
    }

    /**
     * Method to retrieve the frequency of the given word in a given sentence
     *
     * @param text required text in which we need to find the frequency of the given word
     * @param word required word for which we need to find the frequency in the given text
     * @return the integer value of the frequency of the given word in the given sentence
     */
    @Override
    public int calculateFrequencyForWord(String text, String word) {
        var methodName = "calculateFrequencyForWord";
        log.info(STARTING_METHOD_REFERENCE, methodName);

        var listOfWords = getListOfWords(text);

        if (listOfWords.isEmpty()) {
            throw new DataNotFoundException("There are no alphabetical words present in the given text");
        }

        var frequency = listOfWords.stream()
                .filter(input -> input.equalsIgnoreCase(word)).count();
        if (frequency == 0) {
            throw new DataNotFoundException("There is no given word present in the given text");
        }
        log.info(ENDING_METHOD_REFERENCE, methodName);
        return (int) frequency;
    }

    /**
     * Method to retrieve the most frequent N words in a given sentence
     *
     * @param text required text in which we need to find the frequency of the given word
     * @param n    required number of words for which the frequent words should be found in the text
     * @return @return {@link List<WordFrequency>} the list of the most frequent N words
     */
    @Override
    public List<WordFrequency> calculateMostFrequentNWords(String text, int n) {
        var methodName = "calculateMostFrequentNWords";
        log.info(STARTING_METHOD_REFERENCE, methodName);

        if (n == 0) {
            throw new DataNotFoundException("Most frequent words should be found greater than 0");
        }

        var listOfWords = getListOfWords(text);

        if (listOfWords.isEmpty()) {
            throw new DataNotFoundException("There are no alphabetical words present in the given sentence");
        }

        var wordFrequencyList = new ArrayList<WordFrequencyImpl>();

        var result = listOfWords.stream().collect(
                Collectors.groupingBy(Function.identity(),
                        Collectors.counting()));

        result.forEach((key, value) -> wordFrequencyList.add(new WordFrequencyImpl(key, value.intValue())));

        log.info(ENDING_METHOD_REFERENCE, methodName);

        return wordFrequencyList.stream().sorted(Comparator.comparing(WordFrequencyImpl::getFrequency).reversed()
                .thenComparing(WordFrequencyImpl::getWord)).limit(n).collect(Collectors.toList());
    }


    private List<String> getListOfWords(String text) {
        var methodName = "getListOfWords";
        log.info(STARTING_METHOD_REFERENCE, methodName);

        var list = new ArrayList<String>();
        var regexPattern = Pattern.compile("[a-zA-Z]+");
        var regexMatch = regexPattern.matcher(text);
        while (regexMatch.find()) {
            list.add(regexMatch.group(0).toLowerCase());
        }

        log.info(ENDING_METHOD_REFERENCE, methodName);
        return list;
    }
}
