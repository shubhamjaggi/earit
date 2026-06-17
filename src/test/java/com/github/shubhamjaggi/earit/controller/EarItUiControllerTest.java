package com.github.shubhamjaggi.earit.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EarItUiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void index_returns200AndRendersTemplate() throws Exception {
        mockMvc.perform(get("/earit/ui/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(content().contentTypeCompatibleWith("text/html"));
    }

    @Test
    void index_renderedHtmlContainsAppTitle() throws Exception {
        mockMvc.perform(get("/earit/ui/index"))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Ear It!")));
    }

    @Test
    void index_renderedHtmlContainsNavButtons() throws Exception {
        mockMvc.perform(get("/earit/ui/index"))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Notes")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Chords")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Scales")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Intervals")));
    }
}
