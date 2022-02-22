package com.java.project.wordfrequencyanalyzer.service;

import java.util.List;

public interface WordFrequencyAnalyzer {

    /**
     * Method to retrieve the frequency of the most occurring word in a given sentence
     *
     * @param text required text in which we need to find the frequency of the most occurring word
     * @return the integer value of the most occurring word in the given sentence
     */
    int calculateHighestFrequency(String text);

    /**
     * Method to retrieve the frequency of the given word in a given sentence
     *
     * @param text required text in which we need to find the frequency of the given word
     * @param word required word for which we need to find the frequency in the given text
     * @return the integer value of the frequency of the given word in the given sentence
     */
    int calculateFrequencyForWord(String text, String word);

    /**
     * Method to retrieve the most frequent N words in a given sentence
     *
     * @param text required text in which we need to find the frequency of the given word
     * @param n    required number of words for which the frequent words should be found in the text
     * @return @return {@link List<WordFrequency>} the list of the most frequent N words
     */
    List<WordFrequency> calculateMostFrequentNWords(String text, int n);
}
