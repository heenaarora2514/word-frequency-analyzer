package com.java.project.wordfrequencyanalyzer.service.impl;

import com.java.project.wordfrequencyanalyzer.service.WordFrequency;
import com.java.project.wordfrequencyanalyzer.testutils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class WordFrequencyAnalyzerImplTest {

    @InjectMocks
    private WordFrequencyAnalyzerImpl wordFrequencyAnalyzerImpl;

    @Test
    void calling_calculateHighestFrequency_expect_response() {

        final int response = wordFrequencyAnalyzerImpl.calculateHighestFrequency("As an admin user, List of 100 books available in store, on click of list of 100 books it should show details screen in 100 ways");

        assertThat(response).isEqualTo(3);
    }


    @Test
    void calling_calculateFrequencyForWord_expect_response() {

        final int response = wordFrequencyAnalyzerImpl.calculateFrequencyForWord("As an admin user, List of 100 books available in store, on click of list of 100 books it should show details screen in 100 ways", "available");

        assertThat(response).isEqualTo(1);
    }

    @Test
    void calling_calculateMostFrequentNWords_expect_response() {

        final List<WordFrequency> response = wordFrequencyAnalyzerImpl.calculateMostFrequentNWords("As an admin user, List of 100 books available in store, on click of list of 100 books it should show details screen in 100 ways", 3);

        assertThat(response).isNotNull();
        assertThat(response.size()).isEqualTo(3);
        assertThat(response).isEqualTo(TestUtils.getMostFrequentNWordsList());
    }
}
