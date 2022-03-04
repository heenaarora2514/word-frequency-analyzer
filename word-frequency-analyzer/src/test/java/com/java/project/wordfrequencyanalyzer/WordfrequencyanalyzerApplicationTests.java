package com.java.project.wordfrequencyanalyzer;

import com.java.project.wordfrequencyanalyzer.controller.WordFrequencyAnalyzerController;
import com.java.project.wordfrequencyanalyzer.service.WordFrequencyAnalyzer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WordfrequencyanalyzerApplicationTests {

    @Autowired
    private WordFrequencyAnalyzerController wordFrequencyAnalyzerController;

    @Autowired
    private WordFrequencyAnalyzer wordFrequencyAnalyzer;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(wordFrequencyAnalyzerController);
        Assertions.assertNotNull(wordFrequencyAnalyzer);
    }


}
