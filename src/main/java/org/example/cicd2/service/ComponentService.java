package org.example.cicd2.service;

import org.example.cicd2.dto.ComponentResponse;
import org.example.cicd2.dto.CreateComponentRequest;
import org.example.cicd2.dto.UpdateComponentRequest;

import java.util.List;

public interface ComponentService {
    ComponentResponse createComponent(CreateComponentRequest request);

    ComponentResponse getComponentById(Long id);

    List<ComponentResponse> getAllComponents();

    ComponentResponse updateComponent(Long id, UpdateComponentRequest request);

    void deleteComponent(Long id);
}

