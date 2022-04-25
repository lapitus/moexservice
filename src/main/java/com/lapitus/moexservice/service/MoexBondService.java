package com.lapitus.moexservice.service;

import com.lapitus.moexservice.dto.StockDto;
import com.lapitus.moexservice.dto.TickersDto;
import com.lapitus.moexservice.moexClient.CorporateBondClient;
import com.lapitus.moexservice.moexClient.GovermentBondClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MoexBondService {

    private final CorporateBondClient corporateBondClient;
    private final GovermentBondClient govermentBondClient;
    private final Parser parser;
    public StockDto getBondsFromMoex(TickersDto tickersDto) {

    }
}
