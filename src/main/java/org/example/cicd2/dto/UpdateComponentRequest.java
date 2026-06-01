package org.example.cicd2.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateComponentRequest {
    @NotBlank(message = "componentName cannot be blank")
    private String componentName;

    @NotBlank(message = "componentType cannot be blank")
    private String componentType;

    @NotBlank(message = "manufacturer cannot be blank")
    private String manufacturer;

    @NotNull(message = "price must be provided")
    @Positive(message = "price must be greater than zero")
    private Double price;

    @NotNull(message = "quantity must be provided")
    @Min(value = 0, message = "quantity cannot be negative")
    private Integer quantity;
}

