package com.lapitus.moexservice.dto;

import lombok.Value;

@Value
public class BondsDto {
    String ticker;
    String name;
    Double price;
}
