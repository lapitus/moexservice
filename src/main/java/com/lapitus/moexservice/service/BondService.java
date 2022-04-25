package com.lapitus.moexservice.service;

import com.lapitus.moexservice.dto.StockDto;
import com.lapitus.moexservice.dto.TickersDto;
import com.lapitus.moexservice.moexClient.CorporateBondClient;
import com.lapitus.moexservice.moexClient.GovermentBondsClient;
import com.lapitus.moexservice.moexClient.GovermentsBondsClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BondService {

    private final CorporateBondClient corporateBondClient;
    private final GovermentBondsClient govermentBondsClient;
    private final Parser parser;
    public StockDto getBondsFromMoex(TickersDto tickersDto) {

    }
}
