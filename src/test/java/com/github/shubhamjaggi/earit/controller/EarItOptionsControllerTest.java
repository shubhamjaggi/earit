package com.github.shubhamjaggi.earit.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @SpringBootTest rather than @WebMvcTest because SpringDoc's WebMvcConfigurer
// interferes with the WebMvc slice. Player's static initializer is safe here —
// it only runs when a player endpoint handler is first invoked, never during
// Spring context startup or options endpoint calls.
@SpringBootTest
@AutoConfigureMockMvc
class EarItOptionsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllChordTypes_returns200WithAllSevenTypesInOrder() throws Exception {
        mockMvc.perform(get("/earit/options/chordTypes"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(content().json(
                        "[\"Major\",\"Minor\",\"Diminished\",\"Major 7th\",\"Dominant 7th\",\"Minor 7th\",\"Minor 7th Flat 5\"]",
                        true));
    }

    @Test
    void getAllScaleTypes_returns200WithAllSevenTypesInOrder() throws Exception {
        mockMvc.perform(get("/earit/options/scaleTypes"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(content().json(
                        "[\"Major\",\"Natural Minor\",\"Harmonic Minor\",\"Major Pentatonic\",\"Minor Pentatonic\",\"Major Blues\",\"Minor Blues\"]",
                        true));
    }

    @Test
    void getAllIntervals_returns200WithAll25IntervalsInOrder() throws Exception {
        mockMvc.perform(get("/earit/options/intervals"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(content().json(
                        "[\"Perfect 4th\",\"Perfect 5th\",\"Perfect 8th\"," +
                        "\"Major 2nd\",\"Major 3rd\",\"Major 6th\",\"Major 7th\"," +
                        "\"Minor 2nd\",\"Minor 3rd\",\"Minor 6th\",\"Minor 7th\"," +
                        "\"Augmented 1st\",\"Augmented 2nd\",\"Augmented 3rd\",\"Augmented 4th\"," +
                        "\"Augmented 5th\",\"Augmented 6th\",\"Augmented 7th\",\"Augmented 8th\"," +
                        "\"Diminished 3rd\",\"Diminished 4th\",\"Diminished 5th\"," +
                        "\"Diminished 6th\",\"Diminished 7th\",\"Diminished 8th\"]",
                        true));
    }

    @Test
    void getAllNotes_returns200AndStartsWithA0() throws Exception {
        mockMvc.perform(get("/earit/options/notes"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("application/json"))
                .andExpect(jsonPath("$[0]").value("A0"))
                .andExpect(jsonPath("$[1]").value("A#0"))
                .andExpect(jsonPath("$[2]").value("Bb0"))
                .andExpect(jsonPath("$[3]").value("B0"))
                .andExpect(jsonPath("$[4]").value("C1"));
    }

    @Test
    void getAllNotes_contains151Entries() throws Exception {
        // 4 (A0–B0) + 8 octaves × 17 entries + 11 (C9–G9) = 151
        mockMvc.perform(get("/earit/options/notes"))
                .andExpect(jsonPath("$.length()").value(151));
    }

    @Test
    void getAllNotes_containsMiddleCAndConcertA() throws Exception {
        mockMvc.perform(get("/earit/options/notes"))
                .andExpect(jsonPath("$[?(@=='C4')]").isNotEmpty())   // middle C
                .andExpect(jsonPath("$[?(@=='A4')]").isNotEmpty())   // concert A (440 Hz)
                .andExpect(jsonPath("$[?(@=='G9')]").isNotEmpty());  // last note in enum
    }
}
