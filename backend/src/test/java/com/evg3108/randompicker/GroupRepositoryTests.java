package com.evg3108.randompicker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class GroupRepositoryTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldCreateNewGroup() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/groups/create")
                        .content("{\n" +
                                "  \"id\": \"1\",\n" +
                                "  \"title\": \"title\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldEditExistingGroup() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/groups/edit")
                        .content("{\n" +
                                "  \"id\": \"1\",\n" +
                                "  \"title\": \"new title\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteGroup() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/groups/delete?id=1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldFindGroupById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/groups/1"))
                .andExpect(status().isOk());
    }

}
