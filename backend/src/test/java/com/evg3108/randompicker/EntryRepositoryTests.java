package com.evg3108.randompicker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class EntryRepositoryTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldCreateNewEntry() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/entries/create")
                        .content("{\n" +
                                "  \"groupID\": \"1\",\n" +
                                "  \"title\": \"title\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldEditExistingEntry() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/entries/edit")
                        .content("{\n" +
                                "  \"id\": \"1\",\n" +
                                "  \"title\": \"new title\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldFindEntryById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/entries/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteEntry() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/entries/delete?id=1"))
                .andExpect(status().isOk());
    }

}
