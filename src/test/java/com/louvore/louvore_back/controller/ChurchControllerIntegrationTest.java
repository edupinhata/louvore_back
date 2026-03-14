package com.louvore.louvore_back.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.louvore.louvore_back.dto.request.ChurchRequest;
import com.louvore.louvore_back.dto.request.LoginRequest;
import com.louvore.louvore_back.dto.response.LoginResponse;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ChurchControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static String adminToken;

    @Test
    @Order(1)
    void loginAsAdmin() throws Exception {
        LoginRequest loginRequest = new LoginRequest("admin", "admin123");
        MvcResult result = mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andReturn();
        LoginResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), LoginResponse.class);
        adminToken = response.token();
    }

    @Test
    @Order(2)
    void createChurch_asAdmin_returnsCreated() throws Exception {
        ChurchRequest request = new ChurchRequest("Test Church", "São Paulo", "SP", true);
        mockMvc.perform(post("/api/v1/churches")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + adminToken)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test Church"))
                .andExpect(jsonPath("$.city").value("São Paulo"));
    }

    @Test
    @Order(3)
    void createChurch_withoutAuth_returnsUnauthorized() throws Exception {
        ChurchRequest request = new ChurchRequest("Test Church", "São Paulo", "SP", true);
        mockMvc.perform(post("/api/v1/churches")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @Order(4)
    void createChurch_withBlankName_returnsBadRequest() throws Exception {
        ChurchRequest request = new ChurchRequest("", "São Paulo", "SP", true);
        mockMvc.perform(post("/api/v1/churches")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + adminToken)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(5)
    void getAllChurches_returnsOk() throws Exception {
        mockMvc.perform(get("/api/v1/churches")
                .header("Authorization", "Bearer " + adminToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }
}
