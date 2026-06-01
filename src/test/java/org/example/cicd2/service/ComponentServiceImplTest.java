package org.example.cicd2.service;

import org.example.cicd2.dto.ComponentResponse;
import org.example.cicd2.dto.CreateComponentRequest;
import org.example.cicd2.dto.UpdateComponentRequest;
import org.example.cicd2.exception.ComponentNotFoundException;
import org.example.cicd2.model.Component;
import org.example.cicd2.repository.ComponentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ComponentServiceImplTest {

    @Mock
    private ComponentRepository repository;

    @InjectMocks
    private ComponentServiceImpl service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createComponent_success() {
        CreateComponentRequest req = CreateComponentRequest.builder()
                .componentName("Resistor A")
                .componentType("Resistor")
                .manufacturer("Acme")
                .price(0.5)
                .quantity(100)
                .build();

        Component saved = Component.builder()
                .id(1L)
                .componentName(req.getComponentName())
                .componentType(req.getComponentType())
                .manufacturer(req.getManufacturer())
                .price(req.getPrice())
                .quantity(req.getQuantity())
                .build();

        when(repository.save(any(Component.class))).thenReturn(saved);

        ComponentResponse resp = service.createComponent(req);

        assertNotNull(resp);
        assertEquals(1L, resp.getId());
        assertEquals("Resistor A", resp.getComponentName());
        verify(repository, times(1)).save(any(Component.class));
    }

    @Test
    void getComponentById_success() {
        Component c = Component.builder().id(2L).componentName("Cap").componentType("Capacitor").manufacturer("X").price(1.0).quantity(10).build();
        when(repository.findById(2L)).thenReturn(Optional.of(c));

        ComponentResponse resp = service.getComponentById(2L);
        assertEquals(2L, resp.getId());
        assertEquals("Cap", resp.getComponentName());
    }

    @Test
    void getComponentById_notFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ComponentNotFoundException.class, () -> service.getComponentById(99L));
    }

    @Test
    void getAllComponents_success() {
        Component a = Component.builder().id(1L).componentName("A").componentType("Resistor").manufacturer("M").price(0.1).quantity(5).build();
        Component b = Component.builder().id(2L).componentName("B").componentType("Capacitor").manufacturer("M2").price(0.2).quantity(6).build();
        when(repository.findAll()).thenReturn(Arrays.asList(a, b));

        List<ComponentResponse> all = service.getAllComponents();
        assertEquals(2, all.size());
    }

    @Test
    void updateComponent_success() {
        Component existing = Component.builder().id(10L).componentName("Old").componentType("Resistor").manufacturer("M").price(1.0).quantity(1).build();
        when(repository.findById(10L)).thenReturn(Optional.of(existing));

        UpdateComponentRequest req = UpdateComponentRequest.builder()
                .componentName("New")
                .componentType("Resistor")
                .manufacturer("M")
                .price(2.0)
                .quantity(2)
                .build();

        when(repository.update(any(Component.class))).thenAnswer(i -> i.getArgument(0));

        ComponentResponse updated = service.updateComponent(10L, req);
        assertEquals("New", updated.getComponentName());
        assertEquals(2.0, updated.getPrice());
    }

    @Test
    void updateComponent_notFound() {
        when(repository.findById(123L)).thenReturn(Optional.empty());
        UpdateComponentRequest req = UpdateComponentRequest.builder()
                .componentName("x").componentType("t").manufacturer("m").price(1.0).quantity(1).build();
        assertThrows(ComponentNotFoundException.class, () -> service.updateComponent(123L, req));
    }

    @Test
    void deleteComponent_success() {
        when(repository.existsById(5L)).thenReturn(true);
        doNothing().when(repository).deleteById(5L);
        service.deleteComponent(5L);
        verify(repository, times(1)).deleteById(5L);
    }

    @Test
    void deleteComponent_notFound() {
        when(repository.existsById(55L)).thenReturn(false);
        assertThrows(ComponentNotFoundException.class, () -> service.deleteComponent(55L));
    }
}

