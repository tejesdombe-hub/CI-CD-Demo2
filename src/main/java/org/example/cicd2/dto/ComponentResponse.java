package org.example.cicd2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComponentResponse {
    private Long id;
    private String componentName;
    private String componentType;
    private String manufacturer;
    private Double price;
    private Integer quantity;
}

