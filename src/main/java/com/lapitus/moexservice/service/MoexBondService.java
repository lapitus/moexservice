package com.lapitus.moexservice.service;

import com.lapitus.moexservice.dto.BondsDto;
import com.lapitus.moexservice.dto.StockDto;
import com.lapitus.moexservice.dto.TickersDto;
import com.lapitus.moexservice.model.Currency;
import com.lapitus.moexservice.model.Stock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MoexBondService {
    private final BondRepository bondRepository;
    private final CacheManager cacheManager;
    public StockDto getBondsFromMoex(TickersDto tickersDto) {

        List<BondsDto> resultSet = new ArrayList<>();
        resultSet.addAll(bondRepository.getCorporateBonds());
        resultSet.addAll(bondRepository.getGovermentBonds());
        List<BondsDto> stocksDto = resultSet.stream()
                .filter(bond -> tickersDto.getTickers().contains(bond.getTicker()))
                .collect(Collectors.toList());

        List<Stock> stocks = stocksDto.stream()
                .map(bond -> {
                    return Stock.builder()
                            .name(bond.getName())
                            .ticker(bond.getTicker())
                            .figi(bond.getTicker())
                            .type("Bond")
                            .currency(Currency.RUB)
                            .source("MOEX")
                            .build();
                })
                .collect(Collectors.toList());
        return new StockDto(stocks);

    }
}
