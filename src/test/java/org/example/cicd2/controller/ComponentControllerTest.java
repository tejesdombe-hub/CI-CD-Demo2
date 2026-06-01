package org.example.cicd2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.cicd2.dto.ComponentResponse;
import org.example.cicd2.dto.CreateComponentRequest;
import org.example.cicd2.dto.UpdateComponentRequest;
import org.example.cicd2.exception.ComponentNotFoundException;
import org.example.cicd2.service.ComponentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ComponentController.class)
class ComponentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ComponentService service;

    @Test
    void createComponent_success() throws Exception {
        CreateComponentRequest req = CreateComponentRequest.builder()
                .componentName("Resistor1")
                .componentType("Resistor")
                .manufacturer("Acme")
                .price(0.1)
                .quantity(10)
                .build();

        ComponentResponse resp = ComponentResponse.builder().id(1L).componentName("Resistor1").componentType("Resistor").manufacturer("Acme").price(0.1).quantity(10).build();
        when(service.createComponent(any(CreateComponentRequest.class))).thenReturn(resp);

        mockMvc.perform(post("/api/v1/components")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void getComponent_success() throws Exception {
        ComponentResponse resp = ComponentResponse.builder().id(2L).componentName("X").componentType("Capacitor").manufacturer("M").price(1.0).quantity(2).build();
        when(service.getComponentById(2L)).thenReturn(resp);

        mockMvc.perform(get("/api/v1/components/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2));
    }

    @Test
    void updateComponent_success() throws Exception {
        UpdateComponentRequest req = UpdateComponentRequest.builder().componentName("U").componentType("Resistor").manufacturer("M").price(2.0).quantity(3).build();
        ComponentResponse resp = ComponentResponse.builder().id(3L).componentName("U").componentType("Resistor").manufacturer("M").price(2.0).quantity(3).build();
        when(service.updateComponent(3L, req)).thenReturn(resp);

        mockMvc.perform(put("/api/v1/components/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3));
    }

    @Test
    void deleteComponent_success() throws Exception {
        doNothing().when(service).deleteComponent(4L);

        mockMvc.perform(delete("/api/v1/components/4"))
                .andExpect(status().isNoContent());
    }

    @Test
    void validationFailure_onCreate() throws Exception {
        // missing componentName
        CreateComponentRequest req = CreateComponentRequest.builder()
                .componentName("")
                .componentType("Resistor")
                .manufacturer("M")
                .price(1.0)
                .quantity(1)
                .build();

        mockMvc.perform(post("/api/v1/components")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").isArray());
    }

    @Test
    void getNotFound_returns404() throws Exception {
        when(service.getComponentById(99L)).thenThrow(new ComponentNotFoundException("not found"));

        mockMvc.perform(get("/api/v1/components/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("not found"));
    }
}

