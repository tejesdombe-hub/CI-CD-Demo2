package org.example.cicd2.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.cicd2.dto.ComponentResponse;
import org.example.cicd2.dto.CreateComponentRequest;
import org.example.cicd2.dto.UpdateComponentRequest;
import org.example.cicd2.service.ComponentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/components")
@RequiredArgsConstructor
@Validated
public class ComponentController {
    private final ComponentService service;

    @PostMapping
    public ResponseEntity<ComponentResponse> create(@Valid @RequestBody CreateComponentRequest request) {
        ComponentResponse response = service.createComponent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComponentResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getComponentById(id));
    }

    @GetMapping
    public ResponseEntity<List<ComponentResponse>> getAll() {
        return ResponseEntity.ok(service.getAllComponents());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComponentResponse> update(@PathVariable Long id, @Valid @RequestBody UpdateComponentRequest request) {
        return ResponseEntity.ok(service.updateComponent(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteComponent(id);
        return ResponseEntity.noContent().build();
    }
}

