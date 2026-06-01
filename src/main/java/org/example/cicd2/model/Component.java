package org.example.cicd2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Domain entity representing an electrical component.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Component {
    private Long id;
    private String componentName;
    private String componentType;
    private String manufacturer;
    private Double price;
    private Integer quantity;
}

