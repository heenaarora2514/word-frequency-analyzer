package com.java.project.wordfrequencyanalyzer.controller;

import com.java.project.wordfrequencyanalyzer.service.WordFrequency;
import com.java.project.wordfrequencyanalyzer.service.WordFrequencyAnalyzer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * WordFrequencyAnalyzerController
 * <br>
 * <code>WordFrequencyAnalyzerController</code>
 * <br>
 *
 * @author heena.arora
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/word-frequency-analyzer")
public class WordFrequencyAnalyzerController {

    private final WordFrequencyAnalyzer wordFrequencyAnalyzer;

    /**
     * Method to retrieve the frequency of the most occurring word in a given sentence
     *
     * @param text required text in which we need to find the frequency of the most occurring word
     * @return the integer value of the most occurring word in the given sentence
     */
    @GetMapping("/words/frequency/highest/{text}")
    @ResponseBody
    @Operation(summary = "Receive the frequency of the most occurring word in a given sentence", //
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved the frequency of the most occurring word in a given sentence", //
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "500", description = "Server Error", content = @Content(mediaType = "application/json"))})
    public int getHighestWordFrequency(@Parameter(name = "text", required = true, description = "The text in which we need to find the frequency of the most occurring word") @Valid @NonNull @PathVariable final String text) {
        return wordFrequencyAnalyzer.calculateHighestFrequency(text);
    }

    /**
     * Method to retrieve the frequency of the given word in a given sentence
     *
     * @param text required text in which we need to find the frequency of the given word
     * @param word required word for which we need to find the frequency in the given text
     * @return the integer value of the frequency of the given word in the given sentence
     */
    @GetMapping("/words/count/{text}/{word}")
    @ResponseBody
    @Operation(summary = "Receive the frequency of the given word in a given sentence", //
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved the frequency of the given word in a given sentence", //
                            content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "500", description = "Server Error", content = @Content(mediaType = "application/json"))})
    public int getWordFrequency(@Parameter(name = "text", required = true, description = "The text in which we need to find the frequency of the given word") @Valid @NonNull @PathVariable final String text, @Parameter(name = "word", required = true, description = "The word for which we need to find the frequency in the given text") @Valid @NonNull @PathVariable final String word) {
        return wordFrequencyAnalyzer.calculateFrequencyForWord(text, word);
    }

    /**
     * Method to retrieve the most frequent N words in a given sentence
     *
     * @param text          required text in which we need to find the frequency of the given word
     * @param numberOfWords required number of words for which the frequent words should be found in the text
     * @return @return {@link List<WordFrequency>} the list of the most frequent N words
     */
    @GetMapping("/words/{text}/most/{numberOfWords}")
    @ResponseBody
    @Operation(summary = "Receive the frequency of the given word in a given sentence", //
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved the most frequent N words in a given sentence", //
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = WordFrequency.class))),
                    @ApiResponse(responseCode = "404", description = "Not Found", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "500", description = "Server Error", content = @Content(mediaType = "application/json"))})
    public List<WordFrequency> getMostFrequentNWords(@Parameter(name = "text", required = true, description = "The text in which we need to find the frequency of the given word") @Valid @NonNull @PathVariable final String text, @Parameter(name = "numberOfWords", required = true, description = "The number of words for which the frequent words should be found in the text") @Valid @NonNull @PathVariable final int numberOfWords) {
        return wordFrequencyAnalyzer.calculateMostFrequentNWords(text, numberOfWords);
    }
}
