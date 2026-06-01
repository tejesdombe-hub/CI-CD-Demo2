package org.example.cicd2.service;

import lombok.RequiredArgsConstructor;
import org.example.cicd2.dto.ComponentResponse;
import org.example.cicd2.dto.CreateComponentRequest;
import org.example.cicd2.dto.UpdateComponentRequest;
import org.example.cicd2.exception.ComponentNotFoundException;
import org.example.cicd2.model.Component;
import org.example.cicd2.repository.ComponentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for managing components.
 */
@Service
@RequiredArgsConstructor
public class ComponentServiceImpl implements ComponentService {
    private final ComponentRepository repository;

    @Override
    public ComponentResponse createComponent(CreateComponentRequest request) {
        Component entity = Component.builder()
                .componentName(request.getComponentName())
                .componentType(request.getComponentType())
                .manufacturer(request.getManufacturer())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .build();

        Component saved = repository.save(entity);
        return toResponse(saved);
    }

    @Override
    public ComponentResponse getComponentById(Long id) {
        Component component = repository.findById(id)
                .orElseThrow(() -> new ComponentNotFoundException("Component not found with id: " + id));
        return toResponse(component);
    }

    @Override
    public List<ComponentResponse> getAllComponents() {
        return repository.findAll().stream().map(this::toResponse).collect(Collectors.toList());
    }

    @Override
    public ComponentResponse updateComponent(Long id, UpdateComponentRequest request) {
        Component existing = repository.findById(id)
                .orElseThrow(() -> new ComponentNotFoundException("Component not found with id: " + id));

        existing.setComponentName(request.getComponentName());
        existing.setComponentType(request.getComponentType());
        existing.setManufacturer(request.getManufacturer());
        existing.setPrice(request.getPrice());
        existing.setQuantity(request.getQuantity());

        Component updated = repository.update(existing);
        return toResponse(updated);
    }

    @Override
    public void deleteComponent(Long id) {
        if (!repository.existsById(id)) {
            throw new ComponentNotFoundException("Component not found with id: " + id);
        }
        repository.deleteById(id);
    }

    private ComponentResponse toResponse(Component c) {
        return ComponentResponse.builder()
                .id(c.getId())
                .componentName(c.getComponentName())
                .componentType(c.getComponentType())
                .manufacturer(c.getManufacturer())
                .price(c.getPrice())
                .quantity(c.getQuantity())
                .build();
    }
}

