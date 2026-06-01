package org.example.cicd2.repository;

import org.example.cicd2.model.Component;

import java.util.List;
import java.util.Optional;

/**
 * Repository abstraction for components. Implementations may use in-memory storage for this learning project.
 */
public interface ComponentRepository {
    Component save(Component component);

    Optional<Component> findById(Long id);

    List<Component> findAll();

    Component update(Component component);

    void deleteById(Long id);

    boolean existsById(Long id);
}

