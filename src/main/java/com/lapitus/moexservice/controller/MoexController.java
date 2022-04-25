package com.lapitus.moexservice.controller;

import com.lapitus.moexservice.dto.StockDto;
import com.lapitus.moexservice.dto.TickersDto;
import com.lapitus.moexservice.service.MoexBondService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bonds")
public class MoexController {

    private final MoexBondService moexBondService;
    @PostMapping("getBondByTickers")
    StockDto getBondsFromMoex(@RequestBody TickersDto tickersDto) {
        return moexBondService.getBondsFromMoex(tickersDto);
    }
}
