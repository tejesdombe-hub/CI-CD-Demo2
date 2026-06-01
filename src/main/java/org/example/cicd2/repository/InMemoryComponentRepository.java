package org.example.cicd2.repository;

import org.example.cicd2.model.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryComponentRepository implements ComponentRepository {
    private final ConcurrentMap<Long, Component> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    @Override
    public Component save(Component component) {
        long id = idGenerator.incrementAndGet();
        component.setId(id);
        storage.put(id, component);
        return component;
    }

    @Override
    public Optional<Component> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Component> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Component update(Component component) {
        storage.put(component.getId(), component);
        return component;
    }

    @Override
    public void deleteById(Long id) {
        storage.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return storage.containsKey(id);
    }
}

