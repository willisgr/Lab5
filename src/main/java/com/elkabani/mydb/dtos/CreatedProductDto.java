package com.elkabani.mydb.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class CreatedProductDto {
    private Long generatedId;
    private String name;
    private String description;
    private BigDecimal price;
    private Long categoryId;
}
