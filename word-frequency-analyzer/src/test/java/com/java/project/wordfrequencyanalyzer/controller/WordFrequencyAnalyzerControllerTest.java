package com.java.project.wordfrequencyanalyzer.controller;

import com.java.project.wordfrequencyanalyzer.service.WordFrequency;
import com.java.project.wordfrequencyanalyzer.service.WordFrequencyAnalyzer;
import com.java.project.wordfrequencyanalyzer.testutils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WordFrequencyAnalyzerControllerTest {

    @InjectMocks
    private WordFrequencyAnalyzerController wordFrequencyAnalyzerController;

    @Mock
    private WordFrequencyAnalyzer wordFrequencyAnalyzer;


    @Test
    void calling_getHighestWordFrequency_expect_response() {

        when(wordFrequencyAnalyzer.calculateHighestFrequency("The sun shines over the lake")).thenReturn(2);

        final int response = wordFrequencyAnalyzerController.getHighestWordFrequency("The sun shines over the lake");

        assertThat(response).isEqualTo(2);
        verify(wordFrequencyAnalyzer, times(1)).calculateHighestFrequency("The sun shines over the lake");
        verifyNoMoreInteractions(wordFrequencyAnalyzer);
    }

    @Test
    void calling_getWordFrequency_expect_response() {

        when(wordFrequencyAnalyzer.calculateFrequencyForWord("The sun shines over the lake", "the")).thenReturn(2);

        final int response = wordFrequencyAnalyzerController.getWordFrequency("The sun shines over the lake", "the");

        assertThat(response).isEqualTo(2);
        verify(wordFrequencyAnalyzer, times(1)).calculateFrequencyForWord("The sun shines over the lake", "the");
        verifyNoMoreInteractions(wordFrequencyAnalyzer);
    }

    @Test
    void calling_getMostFrequentNWords_expect_response() {

        when(wordFrequencyAnalyzer.calculateMostFrequentNWords("The sun shines over the lake", 1)).thenReturn(TestUtils.getWordFrequencyList());

        final List<WordFrequency> response = wordFrequencyAnalyzerController.getMostFrequentNWords("The sun shines over the lake", 1);

        assertThat(response).isNotNull();
        assertThat(response.get(0).getFrequency()).isEqualTo(2);
        verify(wordFrequencyAnalyzer, times(1)).calculateMostFrequentNWords("The sun shines over the lake", 1);
        verifyNoMoreInteractions(wordFrequencyAnalyzer);
    }
}
