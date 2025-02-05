package com.meli.socialmeli.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;

    @NotNull(message = "El  id no puede estar vacío")
    @Positive(message = "El id debe ser mayor a cero")
    @JsonProperty("user_id")
    private Integer userId;

    @NotNull(message = "La fecha no puede estar vacía")
    private LocalDate date;

    @NotNull
    @Valid
    private ProductDto product;

    private Integer category;

    @Max(value = 10000000, message = "El precio máximo por producto es de 10.000.000")
    @NotNull(message = "El campo no puede estar vacío.")
    @Positive
    private Double price;

    @JsonProperty("has_promo")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean hasPromo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double discount;

}
