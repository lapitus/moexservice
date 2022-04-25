package com.lapitus.moexservice.dto;

import com.lapitus.moexservice.model.Stock;
import lombok.Value;

import java.util.List;

@Value
public class StockDto {
    List<Stock> stocks;
}
