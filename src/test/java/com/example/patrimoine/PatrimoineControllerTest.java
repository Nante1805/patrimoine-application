// src/test/java/com/example/demo/PatrimoineControllerTest.java
package com.example.patrimoine;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PatrimoineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateOrUpdatePatrimoine() throws Exception {
        Patrimoine patrimoine = new Patrimoine();
        patrimoine.setPossesseur("John Doe");

        mockMvc.perform(put("/patrimoines/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patrimoine)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/patrimoines/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.possesseur").value("John Doe"));
    }

    @Test
    public void testGetNonExistentPatrimoine() throws Exception {
        mockMvc.perform(get("/patrimoines/999"))
                .andExpect(status().is4xxClientError());
    }
}